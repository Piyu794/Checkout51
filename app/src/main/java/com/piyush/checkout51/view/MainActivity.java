package com.piyush.checkout51.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.piyush.checkout51.R;
import com.piyush.checkout51.viewmodel.ItemViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvNoItem)
    public TextView tvNoItem;
    @BindView(R.id.rvItems)
    public RecyclerView rvItems;

    private Adapter adapter;
    private ItemViewModel viewModel;
    private AlertDialog progressDialog;

    private int selectedValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //Pre Initialization of All Required Classes
        init();

        //To Access ViewModel
        viewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        //Observing The List For Any Changes
        viewModel.getItemsList().observe(this, items -> {
            //If List is Empty then show Proper Message Otherwise Show List Of Items
            if (items.isEmpty()) {
                tvNoItem.setVisibility(View.VISIBLE);
                rvItems.setVisibility(View.GONE);
                progressDialog.dismiss();
            } else {
                adapter = new Adapter(MainActivity.this, items);
                rvItems.setAdapter(adapter);
                tvNoItem.setVisibility(View.GONE);
                rvItems.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
            }
        });
    }

    private void init() {
        rvItems.setLayoutManager(new GridLayoutManager(this, 2));
        //Making Custom Progress Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.custom_progress_dialog);
        progressDialog = builder.create();
        progressDialog.show();
    }

    private void showSortDialog() {
        //Inflate Layout & Initialize Views
        final View view = LayoutInflater.from(this).inflate(R.layout.custom_radio_group, null);
        RadioGroup rgDialog = view.findViewById(R.id.rgDialogSort);
        RadioButton rbSortAsc = view.findViewById(R.id.rbSortNameAsc);
        RadioButton rbSortDesc = view.findViewById(R.id.rbSortNameDesc);
        RadioButton rbSortCb = view.findViewById(R.id.rbSortCb);

        //Creating Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle(getString(R.string.sort_title));
        final AlertDialog dialog = builder.create();

        //Hold The Value of Selected Radio Button
        switch (selectedValue) {
            case 1:
                rbSortAsc.setChecked(true);
                break;
            case 2:
                rbSortDesc.setChecked(true);
                break;
            case 3:
                rbSortCb.setChecked(true);
                break;
        }
        rgDialog.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.rbSortNameAsc:
                    //Store Value To Know Which Option Was Clicked
                    selectedValue = 1;
                    //Observe Sorted List By Name Ascending
                    viewModel.sortByNameAsc().observe(MainActivity.this, items -> {
                        dialog.dismiss();
                        //Notify Adapter as soon as Data Set Changes
                        adapter.notifyDataSetChanged();
                    });
                    break;
                case R.id.rbSortNameDesc:
                    //Store Value To Know Which Option Was Clicked
                    selectedValue = 2;
                    //Observe Sorted List By Name Descending
                    viewModel.sortByNameDesc().observe(MainActivity.this, items -> {
                        dialog.dismiss();
                        //Notify Adapter as soon as Data Set Changes
                        adapter.notifyDataSetChanged();
                    });
                    break;
                case R.id.rbSortCb:
                    //Store Value To Know Which Option Was Clicked
                    selectedValue = 3;
                    //Observe Sorted List By CashBack Value
                    viewModel.sortByCashBack().observe(MainActivity.this, items -> {
                        dialog.dismiss();
                        //Notify Adapter as soon as Data Set Changes
                        adapter.notifyDataSetChanged();
                    });
                    break;
            }
        });

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuSort)
            showSortDialog();   //Show Sorting Option Dialog on Menu Item Click
        return true;
    }
}