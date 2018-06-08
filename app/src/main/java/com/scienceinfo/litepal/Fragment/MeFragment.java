package com.scienceinfo.litepal.Fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.scienceinfo.litepal.Adapter.MyRecyclerViewAdapter;
import com.scienceinfo.litepal.Bean.Curriculum;
import com.scienceinfo.litepal.CourseControlActivity;
import com.scienceinfo.litepal.DeleteActivity;
import com.scienceinfo.litepal.MainActivity;
import com.scienceinfo.litepal.R;
import com.scienceinfo.litepal.UpdateActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

public class MeFragment extends Fragment {

    private static String pass;
    private static String user;
    private static MainActivity content;
    private TextView tvname;
    private RecyclerView mRecyclerView;


    public static android.app.Fragment newInstance(String param1, String username, String password, MainActivity mainActivity) {
        user=username;
        pass=password;
        content=mainActivity;
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        //引用创建好的xml布局
        View view = inflater.inflate(R.layout.mefragment,container,false);
        tvname = view.findViewById(R.id.user_name);
        mRecyclerView = view.findViewById(R.id.rv);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvname.setText(user);
        ArrayList<String> list=new ArrayList();
        list.add("添加课程");
        list.add("更新课程");
        list.add("删除课程");
        list.add("清空课程");

        ArrayList<Integer> img=new ArrayList<>();
        img.add(R.mipmap.add);
        img.add(R.mipmap.update);
        img.add(R.mipmap.delete);
        img.add(R.mipmap.all);




        mRecyclerView.setLayoutManager(new LinearLayoutManager(content, LinearLayoutManager.VERTICAL, false));
//初始化适配器
        MyRecyclerViewAdapter mAdapter = new MyRecyclerViewAdapter(list,img);
//设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//设置适配器
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Log.e("我被点击","="+position);

                switch (position){
                    case 0:
                        Intent intent = new Intent(content, CourseControlActivity.class);
                        content.startActivity(intent);
                        break;
                    case 1:
                        Intent update = new Intent(content, UpdateActivity.class);
                        content.startActivity(update);
                        break;
                    case 2:
                        Intent delete = new Intent(content, DeleteActivity.class);
                        content.startActivity(delete);
                        break;
                    case 3:
                        int info = DataSupport.deleteAll(Curriculum.class);
                        if(info==1){
                            Toast.makeText(content,"清空所有课程成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(content,"当前课程为空",Toast.LENGTH_SHORT).show();
                        }
                        break;


                }





            }

        });



    }
}
