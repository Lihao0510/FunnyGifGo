package com.lihao.funnygif.views;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lihao.funnygif.R;


public class MainFragment extends Fragment {
    private Activity mActivity;
    private RecyclerView mRecyclerView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recyclerview);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        initData();
    }

    private void initData() {
        mRecyclerView = new RecyclerView(mActivity);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;
    }
}
