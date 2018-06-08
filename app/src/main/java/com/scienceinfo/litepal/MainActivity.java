package com.scienceinfo.litepal;

import android.app.Fragment;
import android.app.FragmentManager;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.scienceinfo.litepal.Fragment.HomeFragment;
import com.scienceinfo.litepal.Fragment.MeFragment;
import com.scienceinfo.litepal.Utils.check;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private ArrayList<Fragment> fragments;
    private Fragment homeFragment;
    private Fragment meFragment;
    private String username;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //检查权限
        check.Permission(this);

        Intent intent = this.getIntent();
         username = intent.getStringExtra("username");
         pass = intent.getStringExtra("pass");
//        Log.e("传递的用户名",'='+username);
        initView();
    }

    private void initView() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ke, "课程表").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.us, "个人中心").setActiveColorResource(R.color.colorAccent))

                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }
    private void setDefaultFragment() {

        FragmentManager fm = this.getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, HomeFragment.newInstance("首页",this));
        transaction.commit();
    }
    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment() );
        fragments.add(new MeFragment());
        return fragments;
    }


    @Override
    public void onTabSelected(int position) {
//        Toast.makeText(this,"postion"+position,Toast.LENGTH_SHORT).show();
       FragmentManager fm = this.getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance("首页",this);
                }
                transaction.replace(R.id.layFrame, homeFragment);

                break;
            case 1:

                if (meFragment == null) {
                    meFragment = MeFragment.newInstance("me",username,pass,this);
                }
                transaction.replace(R.id.layFrame, meFragment);


                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
