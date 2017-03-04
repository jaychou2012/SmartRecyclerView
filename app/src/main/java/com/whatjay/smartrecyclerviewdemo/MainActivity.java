package com.whatjay.smartrecyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.whatjay.recyclerview.adapter.BaseSmartAdapter;
import com.whatjay.recyclerview.view.SmartRecyclerview;
import com.whatjay.smartrecyclerviewdemo.adapter.MainAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseSmartAdapter.OnRecyclerViewItemClickListener, BaseSmartAdapter.OnRecyclerViewItemLongClickListener {
    private SmartRecyclerview recyclerview;
    private MainAdapter mainAdapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerview = (SmartRecyclerview) this.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setPullRefreshEnabled(false);
        recyclerview.setLoadingMoreEnabled(false);
        list = new ArrayList<String>();
        list.add("RefreshAndLoadMore(刷新和加载更多)");
        list.add("MultiItemList(多种布局)");
        list.add("GridItemList(Grid布局)");
        list.add("MultiGrid(多种Grid布局)");
        list.add("GridMultiList(Grid不规则布局)");
        list.add("EmptyList(空数据)");
        list.add("HeaderList(有头布局的)");
        list.add("更多功能敬请发现...谭东：whatjay.com");
        mainAdapter = new MainAdapter(this, list);
        recyclerview.setAdapter(mainAdapter);
        mainAdapter.setOnItemClickListener(this);
        mainAdapter.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent;
        switch (position) {
            case 1:
                intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, MultiListActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, GridActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, MultiGridActivity.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, GridMultiListActivity.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(this, EmptyActivity.class);
                startActivity(intent);
                break;
            case 7:
                intent = new Intent(this, HeaderListActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(this, "长按" + position, Toast.LENGTH_SHORT).show();
    }
}
