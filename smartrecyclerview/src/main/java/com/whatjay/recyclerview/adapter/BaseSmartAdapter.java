package com.whatjay.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.whatjay.recyclerview.viewholder.SmarViewHolder;

import java.util.List;

/**
 * Created by office on 2017/2/27.
 */
public abstract class BaseSmartAdapter<T> extends RecyclerView.Adapter<SmarViewHolder> {
    private Context context;
    protected int itemLayoutId = -1;
    private View itemView;
    private List<T> lists;
    private OnRecyclerViewItemClickListener onItemClickListener;
    private OnRecyclerViewItemLongClickListener onItemLongClickListener;

    public BaseSmartAdapter(Context context, int layoutId, List<T> lists) {
        itemLayoutId = layoutId;
        this.context = context;
        this.lists = lists;
    }

    public BaseSmartAdapter(Context context, View view, List<T> lists) {
        this(context, 0, lists);
        this.context = context;
        this.itemView = view;
        this.lists = lists;
    }

    @Override
    public SmarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (itemView == null) {
            SmarViewHolder smarViewHolder = SmarViewHolder.getViewHolder(context, parent, itemLayoutId);
            initClick(smarViewHolder);
            return smarViewHolder;
        } else {
            SmarViewHolder smarViewHolder = SmarViewHolder.getViewHolder(context, parent, itemView);
            initClick(smarViewHolder);
            return smarViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(SmarViewHolder holder, int position) {
        bindData(holder, lists.get(position));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    private void initClick(final SmarViewHolder smarViewHolder) {
        if (onItemClickListener != null) {
            smarViewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, smarViewHolder.getAdapterPosition());
                }
            });
        }
        if (onItemLongClickListener != null) {
            smarViewHolder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(v, smarViewHolder.getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public List<T> getLists() {
        return lists;
    }

    public abstract void bindData(SmarViewHolder holder, T t);

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public static interface OnRecyclerViewItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }
}
