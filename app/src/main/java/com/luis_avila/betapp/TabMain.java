package com.luis_avila.betapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class TabMain extends AppCompatActivity {

    private  static final  String TAG = "TabMain";
    private SectionsPageAdapter mSectionsPageAdapter;
    ViewPager mViewPager;
    static String correokey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);
        Log.d(TAG, "oncreate: Starting");
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        Intent intent = getIntent();
        correokey = intent.getStringExtra("haha");

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

        private void setupViewPager (ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Partidos(),"Partidos");
        adapter.addFragment(new Apuestas(), "Apuestas");
        viewPager.setAdapter(adapter);
    }

}

