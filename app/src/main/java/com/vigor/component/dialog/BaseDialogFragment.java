package com.vigor.component.dialog;

import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Vigor on 2016/12/2.
 */

public class BaseDialogFragment extends DialogFragment {
    protected String mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            mTitle = "";
        }
        else {
            mTitle = bundle.getString("TITLE");
        }
    }
}
