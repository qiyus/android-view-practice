package com.vigor.component.fragmenttabhost;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import com.vigor.component.fragmenttabhost.dummy.DummyContent;
import com.vigor.component.R;

public class FragmentTabActivity extends FragmentActivity implements OneFragment.OnListFragmentInteractionListener,
        TwoFragment.OnFragmentInteractionListener, ThreeFragment.OnFragmentInteractionListener {
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("one")
                        .setIndicator(getLayoutInflater().inflate(R.layout.fragment_tab_one, null)),
                OneFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("two")
                        .setIndicator(getLayoutInflater().inflate(R.layout.fragment_tab_two, null)),
                TwoFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("three")
                        .setIndicator(getLayoutInflater().inflate(R.layout.fragment_tab_three, null)),
                ThreeFragment.class, null);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
