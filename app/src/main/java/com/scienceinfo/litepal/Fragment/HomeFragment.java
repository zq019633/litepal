package com.scienceinfo.litepal.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scienceinfo.litepal.Adapter.MainRecyclerViewAdapter;
import com.scienceinfo.litepal.Adapter.MyRecyclerViewAdapter;
import com.scienceinfo.litepal.Bean.Curriculum;
import com.scienceinfo.litepal.Bean.Manager;
import com.scienceinfo.litepal.R;

import org.litepal.crud.DataSupport;

import java.util.List;

public class HomeFragment extends Fragment {

    private static Context content1;
    private RecyclerView mRecyclerView;
    private List<Curriculum> allCour;

    public static android.app.Fragment newInstance(String param1, Context content) {
        content1 = content;
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //引用创建好的xml布局
        View view = inflater.inflate(R.layout.homefragment, container, false);
        mRecyclerView = view.findViewById(R.id.rv);

        //所有的课程
        allCour = DataSupport.findAll(Curriculum.class);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(content1, LinearLayoutManager.VERTICAL, false));
//初始化适配器
        MainRecyclerViewAdapter mAdapter = new MainRecyclerViewAdapter(allCour);
//设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//设置适配器
        mRecyclerView.setAdapter(mAdapter);
    }
}
