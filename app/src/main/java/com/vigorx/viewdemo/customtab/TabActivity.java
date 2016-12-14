package com.vigorx.viewdemo.customtab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vigorx.viewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private final int[] TITLES = new int[]{R.string.tab_event,
            R.string.tab_diary, R.string.tab_trace, R.string.tab_milepost};
    private int mSelectedTabIndex = 1;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mPagerAdapter;
    private List<Fragment> mTabFragments = new ArrayList<>();
    private List<TabLabel> mTabLabels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab);

        initData();
        initView();
    }

    private void initData() {
        for (int titleResId : TITLES) {
            mTabFragments.add(TabFragment.newInstance(getString(titleResId)));
        }

        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return mTabFragments.get(position);
            }

            @Override
            public int getCount() {
                return mTabFragments.size();
            }
        };
    }

    private void initView() {
        TabLabel one = (TabLabel) findViewById(R.id.id_button_event);
        one.setOnClickListener(this);
        mTabLabels.add(one);

        TabLabel two = (TabLabel) findViewById(R.id.id_button_diary);
        two.setOnClickListener(this);
        mTabLabels.add(two);

        TabLabel three = (TabLabel) findViewById(R.id.id_button_trace);
        three.setOnClickListener(this);
        mTabLabels.add(three);

        TabLabel four = (TabLabel) findViewById(R.id.id_button_milepost);
        four.setOnClickListener(this);
        mTabLabels.add(four);

        mViewPager = (ViewPager) this.findViewById(R.id.id_viewpager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        selectTab(mSelectedTabIndex);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_button_event:
                selectTab(0);
                break;
            case R.id.id_button_diary:
                selectTab(1);
                break;
            case R.id.id_button_trace:
                selectTab(2);
                break;
            case R.id.id_button_milepost:
                selectTab(3);
                break;
            default:
        }

    }

    private void selectTab(int tabIndex) {
        mTabLabels.get(mSelectedTabIndex).setIconAlpha(0f);
        mTabLabels.get(tabIndex).setIconAlpha(1.0f);
        mViewPager.setCurrentItem(tabIndex, false);
        mSelectedTabIndex = tabIndex;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        if (positionOffset > 0) {
            TabLabel left = mTabLabels.get(position);
            TabLabel right = mTabLabels.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {
        selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
