package com.smartdev.sqlwithroom.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.smartdev.sqlwithroom.R;
import com.smartdev.sqlwithroom.model.BuyItem;
import com.smartdev.sqlwithroom.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.BuyListViewHolder> {
    private Context mContext;
    private List<BuyItem> mList =new ArrayList<>();
    private BuyItem currentBuyItem;

    public BuyListAdapter(Context context) {
        mContext = context;
    }
    public class BuyListViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView countText;
        View mView;

        public BuyListViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);
            mView = itemView;
        }
    }
    @Override
    public BuyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.buy_item, parent, false);
        return new BuyListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(BuyListViewHolder holder, int position) {
        currentBuyItem = mList.get(position);
        holder.nameText.setText(currentBuyItem.getName());
        holder.countText.setText(String.valueOf(currentBuyItem.getAmount()));
        holder.itemView.setTag(currentBuyItem.getId());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TODO: napraviti edit stranu */
                Toast.makeText(mContext, "Kliknut je " + currentBuyItem.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /* Update list in adapter*/
    public void updateAdapter(List<BuyItem> newList) {
        this.mList = newList;
        notifyDataSetChanged();
    }
    public BuyItem getCurrentBuyItem (int position){
        return mList.get(position);
    }
}
