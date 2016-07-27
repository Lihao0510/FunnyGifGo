package com.lihao.funnygif.views;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lihao.funnygif.R;
import com.lihao.funnygif.adapters.MainAdapter;
import com.lihao.funnygif.modle.GifBean;
import com.lihao.funnygif.threads.GifTask;
import com.lihao.funnygif.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;
    private List<GifBean> mList ;
    public static int page = 1;
    private String category = Constants.BAOXIAO;

    public MainFragment(String category){
        this.category = category;
    }

    public MainFragment(){

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recyclerview);
        //initTestData(20);
        initDatas();

        return view;
    }

    private void initDatas() {
        String url = category + Constants.SEPARATOR + page;
        GifTask task = new GifTask(mRecyclerView,mActivity);
        task.execute(url);
        page++;
    }

    private void initTestData(int num) {
        mList = new ArrayList<>();
        for (int i = 0; i < num; i++) {

        }
        mAdapter = new MainAdapter(mActivity,mList);
        StaggeredGridLayoutManager mManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
