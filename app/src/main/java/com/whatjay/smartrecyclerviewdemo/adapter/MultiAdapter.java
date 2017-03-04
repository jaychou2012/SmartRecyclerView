package com.whatjay.smartrecyclerviewdemo.adapter;

import android.content.Context;

import com.whatjay.recyclerview.adapter.BaseMultiSmartAdapter;
import com.whatjay.recyclerview.viewholder.SmarViewHolder;
import com.whatjay.smartrecyclerviewdemo.R;
import com.whatjay.smartrecyclerviewdemo.entity.MultiItem;

import java.util.List;

/**
 * Created by office on 2017/3/4.
 */
public class MultiAdapter extends BaseMultiSmartAdapter<MultiItem> {

    public MultiAdapter(Context context, List<MultiItem> data) {
        super(context, data);
        addItemType(0, R.layout.item_multi1);
        addItemType(1, R.layout.item_multi2);
    }

    @Override
    protected void bindData(SmarViewHolder smarViewHolder, MultiItem item) {
        switch (item.getItemType()) {
            case 0:
                smarViewHolder.setText(R.id.tv_text1, "布局一："+item.getName());
                break;
            case 1:
                smarViewHolder.setText(R.id.tv_text2, "布局二："+item.getName());
                break;
        }
    }
}
