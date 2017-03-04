package com.whatjay.smartrecyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.whatjay.recyclerview.adapter.BaseMultiSmartAdapter;
import com.whatjay.recyclerview.view.ProgressStyle;
import com.whatjay.recyclerview.view.SmartRecyclerview;
import com.whatjay.smartrecyclerviewdemo.adapter.MultiAdapter;
import com.whatjay.smartrecyclerviewdemo.entity.MultiItem;

import java.util.ArrayList;

public class MultiGridActivity extends AppCompatActivity implements SmartRecyclerview.LoadingListener, BaseMultiSmartAdapter.OnRecyclerViewItemClickListener, BaseMultiSmartAdapter.OnRecyclerViewItemLongClickListener {
    private SmartRecyclerview recyclerview;
    private MultiAdapter multiAdapter;
    private ArrayList<MultiItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_list);
        initView();
    }

    private void initView() {
        recyclerview = (SmartRecyclerview) this.findViewById(R.id.recyclerView);
        recyclerview.setRefreshProgressStyle(ProgressStyle.BallBeat);
        recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        recyclerview.setArrowImageView(R.mipmap.ic_pulltorefresh_arrow);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.setLoadingListener(this);
        list = new ArrayList<MultiItem>();
        multiAdapter = new MultiAdapter(this, list);
        recyclerview.setAdapter(multiAdapter);
        multiAdapter.setOnItemClickListener(this);
        multiAdapter.setOnItemLongClickListener(this);
        recyclerview.refresh();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                list.clear();
                for (int i = 0; i < 30; i++) {
                    MultiItem multiItem = new MultiItem();
                    multiItem.setName("文字" + i);
                    if (i % 4 == 0) {
                        multiItem.setItemType(0);
                    } else {
                        multiItem.setItemType(1);
                    }
                    list.add(multiItem);
                }
                recyclerview.refreshComplete();
            }

        }, 1000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                for (int i = 0; i < 30; i++) {
                    MultiItem multiItem = new MultiItem();
                    multiItem.setName("文字" + i);
                    if (i % 4 == 0) {
                        multiItem.setItemType(0);
                    } else {
                        multiItem.setItemType(1);
                    }
                    list.add(multiItem);
                }
                recyclerview.loadMoreComplete();
            }

        }, 1000);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "点击" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(this, "长按" + position, Toast.LENGTH_SHORT).show();
    }
}
