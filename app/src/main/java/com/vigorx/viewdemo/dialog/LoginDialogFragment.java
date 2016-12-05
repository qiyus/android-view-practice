package com.vigorx.viewdemo.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.vigorx.viewdemo.R;

/**
 * Created by songlei on 2016/12/2.
 */

public class LoginDialogFragment extends BaseDialogFragment {

    private OnLoginDialogListener mListener;
    private EditText mUserName;
    private EditText mPassword;

    public interface OnLoginDialogListener {
        void login(String userName, String password);
        void cancel();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (Build.VERSION.SDK_INT >= 23) {
            mListener = (OnLoginDialogListener) context;
        }
    }

    @Override
    /**
     * SDK API<23时，onAttach(Context)不执行，需要使用onAttach(Activity)。
     * Fragment自身的Bug，v4的没有此问题
     */
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < 23) {
            mListener = (OnLoginDialogListener) activity;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);
        mUserName = (EditText) view.findViewById(R.id.userName);
        mUserName.setText(mTitle);
        mPassword = (EditText) view.findViewById(R.id.password);
        builder.setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.login(mUserName.getText().toString(), mPassword.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.cancel();
                    }
                });
        return builder.create();
    }
}
