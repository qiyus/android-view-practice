package com.vigorx.viewdemo.fragment;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vigorx.viewdemo.R;

public class FragmentDemoActivity extends AppCompatActivity
        implements SongTitlesFragment.OnSongTitlesListener, SongWordsFragment.OnSongWordsListner{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);
    }

    @Override
    public void onClickSongTitles(DummyContent.DummyItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SongWordsFragment wordsFragment = SongWordsFragment.newInstance(item.details);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.song_words_fragment, wordsFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onClikSongWords(String words) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SongWordsFragment wordsFragment = SongWordsFragment.newInstance(words);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.song_words_fragment, wordsFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
