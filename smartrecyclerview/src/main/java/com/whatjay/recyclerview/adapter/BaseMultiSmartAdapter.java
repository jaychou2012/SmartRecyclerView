package com.whatjay.recyclerview.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.whatjay.recyclerview.entity.SmartMultiEntity;
import com.whatjay.recyclerview.viewholder.SmarViewHolder;

import java.util.List;

/**
 * Created by office on 2017/3/2.
 */
public abstract class BaseMultiSmartAdapter<T extends SmartMultiEntity> extends BaseSmartAdapter {
    private SparseArray<Integer> layouts;
    private Context context;
    private OnRecyclerViewItemClickListener onItemClickListener;
    private OnRecyclerViewItemLongClickListener onItemLongClickListener;

    public BaseMultiSmartAdapter(Context context, List<T> data) {
        super(context, null, data);
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return ((SmartMultiEntity) getLists().get(position)).getItemType();
    }

    private int getLayoutId(int viewType) {
        return layouts.get(viewType);
    }

    protected void addItemType(int type, int layoutResId) {
        if (layouts == null) {
            layouts = new SparseArray<Integer>();
        }
        layouts.put(type, layoutResId);
    }

    @Override
    public SmarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SmarViewHolder smarViewHolder = SmarViewHolder.getViewHolder(context, parent, getLayoutId(viewType));
        initClick(smarViewHolder);
        return smarViewHolder;
    }

    @Override
    public void bindData(SmarViewHolder holder, Object o) {
        bindData(holder, (T) o);
    }

    protected abstract void bindData(SmarViewHolder smarViewHolder, T item);

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
