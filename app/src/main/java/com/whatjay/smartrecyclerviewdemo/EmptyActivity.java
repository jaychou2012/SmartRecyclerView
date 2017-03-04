package com.whatjay.smartrecyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.whatjay.recyclerview.adapter.BaseSmartAdapter;
import com.whatjay.recyclerview.view.ProgressStyle;
import com.whatjay.recyclerview.view.SmartRecyclerview;
import com.whatjay.smartrecyclerviewdemo.adapter.MainAdapter;

import java.util.ArrayList;

public class EmptyActivity extends AppCompatActivity implements SmartRecyclerview.LoadingListener, BaseSmartAdapter.OnRecyclerViewItemClickListener, BaseSmartAdapter.OnRecyclerViewItemLongClickListener {
    private SmartRecyclerview recyclerview;
    private MainAdapter mainAdapter;
    private ArrayList<String> list;
    private View emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        initView();
    }

    private void initView() {
        recyclerview = (SmartRecyclerview) this.findViewById(R.id.recyclerView);
        emptyView =this.findViewById(R.id.tv_empty);
        recyclerview.setRefreshProgressStyle(ProgressStyle.BallBeat);
        recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        recyclerview.setArrowImageView(R.mipmap.ic_pulltorefresh_arrow);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setEmptyView(emptyView);
        recyclerview.setLoadingListener(this);
        list = new ArrayList<String>();
        mainAdapter = new MainAdapter(this, list);
        recyclerview.setAdapter(mainAdapter);
        mainAdapter.setOnItemClickListener(this);
        mainAdapter.setOnItemLongClickListener(this);
        recyclerview.refresh();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                list.clear();
                recyclerview.refreshComplete();
            }

        }, 1000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
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
