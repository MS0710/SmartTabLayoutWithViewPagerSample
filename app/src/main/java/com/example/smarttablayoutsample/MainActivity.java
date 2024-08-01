package com.example.smarttablayoutsample;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private SmartTabLayout smartTabLayout ;
    private ViewPager viewpager;
    private MainPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smartTabLayout = (SmartTabLayout)findViewById(R.id.viewpagertab);
        viewpager = (ViewPager)findViewById(R.id.viewpager);

        //Use for Activity
        adapter = new MainPagerAdapter(getSupportFragmentManager());
        //Use for Fragment
        //adapter = new MainPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new MainFragment(),"Main");
        adapter.addFragment(new MyFavouriteFragment(),"MyFavour");
        adapter.addFragment(new RecordFragment(),"Record");
        viewpager.setOffscreenPageLimit(3);

        final int[] tabIcons = {R.drawable.tab_ic_home, R.drawable.tab_ic_me, R.drawable.tab_ic_orders};
        final int[] tabTitles = {R.string.tab_home, R.string.tab_orders, R.string.tab_me};

        smartTabLayout.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            @SuppressLint({"MissingInflatedId", "LocalSuppress"})
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                //View view = inflater.inflate(R.layout.layout_navigation_bottom_item, container, false);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_navigation_bottom_item, container, false);
                ImageView iconView = (ImageView) view.findViewById(R.id.img_icon);
                Log.d(TAG, "createTabView: position = "+position);
                Log.d(TAG, "createTabView: tabIcons.length = "+tabIcons.length);
                Log.d(TAG, "createTabView: position % tabIcons.length = "+(position % tabIcons.length));
                iconView.setBackgroundResource(tabIcons[position % tabIcons.length]);
                TextView titleView = (TextView) view.findViewById(R.id.txt_title);
                titleView.setText(tabTitles[position % tabTitles.length]);
                return view;
            }
        });

        viewpager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewpager);


    }
}