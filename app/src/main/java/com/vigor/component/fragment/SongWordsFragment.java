package com.vigor.component.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vigor.component.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SongWordsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongWordsFragment extends Fragment {
    private static final String SONG_WORDS = "words";

    private String mWords;
    private OnSongWordsListner mListner;

    public SongWordsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param words Parameter 1.
     * @return A new instance of fragment SongWordsFragment.
     */
    public static SongWordsFragment newInstance(String words) {
        SongWordsFragment fragment = new SongWordsFragment();
        Bundle args = new Bundle();
        args.putString(SONG_WORDS, words);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWords = getArguments().getString(SONG_WORDS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song_words, container, false);
        TextView words = (TextView) view.findViewById(R.id.song_words);
        words.setText(mWords);
        words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.onClikSongWords("要查看回退效果！");
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSongWordsListner) {
            mListner = (OnSongWordsListner) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSongTitlesListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListner = null;
    }

    public interface OnSongWordsListner {
        void onClikSongWords(String words);
    }
}
