package com.vigor.component.customtab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vigor.component.R;

public class TabFragment extends Fragment {
    public static final String TITLE = "title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String title = getArguments().getString(TITLE);
        View view = inflater.inflate(R.layout.fragment_customtab, null);
        TextView textView = (TextView) view.findViewById(R.id.customtab_title);
        textView.setText(title);
        return view;
    }

    public static TabFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(TITLE, title);

        TabFragment fragment = new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
