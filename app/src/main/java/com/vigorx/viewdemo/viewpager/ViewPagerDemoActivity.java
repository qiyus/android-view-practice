package com.vigorx.viewdemo.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vigorx.viewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ViewPager pager = (ViewPager) findViewById(R.id.pager_top);
        pager.setAdapter(new MoraFragmentAdapter(getSupportFragmentManager()));

        ViewPager pagerBottom = (ViewPager) findViewById(R.id.pager_bottom);
        pagerBottom.setAdapter(new MoraPageAdapter());
    }

    private class MoraFragmentAdapter extends FragmentPagerAdapter {
        private MoraFragmentAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FragmentViewPager.newInstance(getString(R.string.title_section1), R.drawable.rock);
                case 1:
                    return FragmentViewPager.newInstance(getString(R.string.title_section2), R.drawable.paper);
                case 2:
                    return FragmentViewPager.newInstance(getString(R.string.title_section3), R.drawable.scissors);
                default:
                    return FragmentViewPager.newInstance(getString(R.string.title_section1), R.drawable.rock);
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    private class MoraPageAdapter extends PagerAdapter {

        private List<View> mViewList = new ArrayList<>();

        private MoraPageAdapter() {
            super();
            mViewList.add(getMoraView(R.string.title_section1, R.drawable.rock));
            mViewList.add(getMoraView(R.string.title_section2, R.drawable.paper));
            mViewList.add(getMoraView(R.string.title_section3, R.drawable.scissors));
        }

        private View getMoraView(int titleResourceId, int imageResourceId) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.fragment_viewpager, null);
            TextView textView = (TextView) view.findViewById(R.id.title);
            textView.setText(titleResourceId);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            imageView.setBackgroundResource(imageResourceId);
            return view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViewList.get(position);
            container.addView(view);
            System.out.println("instantiateItem:" + position);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
            System.out.println("destroyItem:" + position);
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
