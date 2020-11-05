package com.smartdev.sqlwithroom.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartdev.sqlwithroom.R;
import com.smartdev.sqlwithroom.model.BuyItem;
import com.smartdev.sqlwithroom.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextName;
    private TextView mTextViewAmount;
    private int mAmount = 0;
    private MainActivityViewModel mMainViewModel;
    private BuyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        /* Observe changes in list emitted from ViewModel*/
        mMainViewModel.getAllItemsFromViewModel().observe(this, new Observer <List<BuyItem>>() {
            @Override
            public void onChanged(List<BuyItem> buyItemList) {
                /*React to changes in list*/
                adapter.updateAdapter(buyItemList);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BuyListAdapter(this);
        recyclerView.setAdapter(adapter);
        mEditTextName = findViewById(R.id.edittext_name);
        mTextViewAmount = findViewById(R.id.textview_amount);
        Button buttonIncrease = findViewById(R.id.button_increase);
        Button buttonDecrease = findViewById(R.id.button_decrease);
        Button buttonAdd = findViewById(R.id.button_add);

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
            }
        });
        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mMainViewModel.removeItemFromDB(adapter.getCurrentBuyItem(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void increase() {
        mAmount++;
        mTextViewAmount.setText(String.valueOf(mAmount));
    }
    private void decrease() {
        if (mAmount > 0) {
            mAmount--;
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
    }

    /*Do all what need to add item*/
    private void addItem() {
        if (mEditTextName.getText().toString().trim().length() != 0 || mAmount != 0) {
            /* Get data from user input*/
            String name = mEditTextName.getText().toString();

            /*Insert  new  BuyItem to DB*/
            BuyItem newBuyItem = new BuyItem(name, String.valueOf(mAmount), String.valueOf(System.currentTimeMillis()/1000) );

            mMainViewModel.insertItemDB(newBuyItem);
            mEditTextName.getText().clear();
        }
    }
}