package com.piyush.checkout51.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.piyush.checkout51.R;
import com.piyush.checkout51.model.responses.Item;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    //To Access Resource Files
    private Context context;
    //To Showing Items List
    private List<Item> itemList;
    //To Inflate View
    private LayoutInflater inflater;

    Adapter(Context context, List<Item> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        Item item = itemList.get(position);

        holder.tvItemName.setText(item.getOfferName());
        String cashBackTxt = context.getString(R.string.cb_txt) + item.getCashBack();
        holder.tvCashBack.setText(cashBackTxt);

        //To Load Image
        Picasso.get().load(item.getOfferImage()).into(holder.ivItemImage, new Callback() {
            @Override
            public void onSuccess() {
                //Disappear Loader & Display Image
                holder.pbLoaderItem.setVisibility(View.GONE);
                holder.ivItemImage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Exception e) {
                //Disappear Loader
                holder.pbLoaderItem.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivItemImage)
        public ImageView ivItemImage;
        @BindView(R.id.pbLoaderItem)
        public ProgressBar pbLoaderItem;
        @BindView(R.id.tvItemName)
        public TextView tvItemName;
        @BindView(R.id.tvItemCb)
        public TextView tvCashBack;

        Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
