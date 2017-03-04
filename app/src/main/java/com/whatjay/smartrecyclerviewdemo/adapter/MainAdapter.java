package com.whatjay.smartrecyclerviewdemo.adapter;

import android.content.Context;

import com.whatjay.recyclerview.adapter.BaseSmartAdapter;
import com.whatjay.recyclerview.viewholder.SmarViewHolder;
import com.whatjay.smartrecyclerviewdemo.R;

import java.util.List;

/**
 * Created by office on 2017/3/4.
 */
public class MainAdapter extends BaseSmartAdapter<String> {

    public MainAdapter(Context context, List<String> lists) {
        super(context, R.layout.item_main, lists);
    }

    @Override
    public void bindData(SmarViewHolder holder, String s) {
        holder.setText(R.id.tv_text, s);
    }
}
