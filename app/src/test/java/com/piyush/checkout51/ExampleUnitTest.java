package com.piyush.checkout51;

import com.piyush.checkout51.viewmodel.ItemViewModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    ItemViewModel viewModel;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getItemsList() {
        viewModel.getItemsList();
    }
}