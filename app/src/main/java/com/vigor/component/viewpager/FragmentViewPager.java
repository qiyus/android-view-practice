package com.vigor.component.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vigor.component.R;


/**
 * Created by Vigor on 2016/6/16.
 */
public class FragmentViewPager extends Fragment {
    private static String TEXT = "text";
    private static String IMAGE = "image";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(getArguments().getString(TEXT));

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setBackgroundResource(getArguments().getInt(IMAGE));
        return view;
    }

    public static FragmentViewPager newInstance(String text, int image) {
        FragmentViewPager f = new FragmentViewPager();
        Bundle b = new Bundle();
        b.putString(TEXT, text);
        b.putInt(IMAGE, image);
        f.setArguments(b);
        return f;
    }
}
