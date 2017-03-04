package com.whatjay.smartrecyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.whatjay.recyclerview.adapter.BaseSmartAdapter;
import com.whatjay.recyclerview.viewholder.SmarViewHolder;
import com.whatjay.smartrecyclerviewdemo.R;
import com.whatjay.smartrecyclerviewdemo.entity.MultiItem;

import java.util.List;

/**
 * Created by office on 2017/3/4.
 */
public class GridMultiAdapter extends BaseSmartAdapter<MultiItem> {

    public GridMultiAdapter(Context context, List<MultiItem> lists) {
        super(context, R.layout.item_main, lists);
    }

    @Override
    public void bindData(SmarViewHolder holder, MultiItem multiItem) {
        holder.setText(R.id.tv_text, multiItem.getName());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int position2 = position;
                    if (position2 >= getLists().size()) {
                        position2 = getLists().size() - 1;
                    }
                    if (getLists().size() > 0) {
                        int type = getLists().get(position2).getItemType();
                        return (type == 0) ?
                                gridManager.getSpanCount() : 1;
                    }
                    return gridManager.getSpanCount();
                }
            });
        }
    }
}
