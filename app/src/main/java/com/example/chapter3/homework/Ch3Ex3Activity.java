package com.example.chapter3.homework;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 * 使用 ViewPager 和 Fragment 做一个简单版的好友列表界面
 * 1. 使用 ViewPager 和 Fragment 做个可滑动界面
 * 2. 使用 TabLayout 添加 Tab 支持
 * 3. 对于好友列表 Fragment，使用 Lottie 实现 Loading 效果，在 5s 后展示实际的列表，要求这里的动效是淡入淡出
 */
public class Ch3Ex3Activity extends AppCompatActivity {
    private final String title0 = "好友列表";
    private final String title1 = "聊天";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3ex3);



        // TODO: ex3-1. 添加 ViewPager 和 Fragment 做可滑动界面
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return PlaceholderFragment.newInstance();
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if(position==0){
                        return title0;
                }else{
                    return title1;
                }
            }
        });
        // TODO: ex3-2, 添加 TabLayout 支持 Tab
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
