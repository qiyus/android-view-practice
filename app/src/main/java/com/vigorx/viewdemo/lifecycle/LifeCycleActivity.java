package com.vigorx.viewdemo.lifecycle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.vigorx.viewdemo.R;

/**
 * Created by songlei on 16/10/28.
 */

public class LifeCycleActivity extends AppCompatActivity {
    private final static String TAG = "com.vigorx.lifecycle";
    private TextView mLeftTop;
    private TextView mRightBottom;
    private ToggleButton mToggleButton;
    private Button mDialogButton;
    private Button mPopupMenuButton;
    private Button mPopupWindowButton;
    private Button mExitButton;
    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getString(R.string.log_lifecycle_create));

        setContentView(R.layout.activity_lifecycle);
        mLeftTop = (TextView) findViewById(R.id.textView);
        mRightBottom = (TextView) findViewById(R.id.textView2);
        mLayout = (LinearLayout) findViewById(R.id.linear);

        // ToggleButton点击的处理。
        mToggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rect rect = new Rect();
                if (mToggleButton.isChecked()) {
                    mLayout.getLocalVisibleRect(rect);
                } else {
                    mLayout.getGlobalVisibleRect(rect);
                }
                mLeftTop.setText(rect.left + getString(R.string.text_lifecycle_separator) + rect.top);
                mRightBottom.setText(rect.right + getString(R.string.text_lifecycle_separator) + rect.bottom);
            }
        });

        // Dialog点击的处理。
        mDialogButton = (Button) findViewById(R.id.buttonDialog);
        mDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LifeCycleActivity.this);
                builder.setTitle(R.string.dialog_lifecycle_title)
                        .setMessage(R.string.dialog_lifecycle_message)
                        .setPositiveButton(R.string.dialog_lifecycle_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Snackbar snackbar = Snackbar.make(mLayout, R.string.snack_lifecycle_ok_message, Snackbar.LENGTH_SHORT);
                                snackbar.setAction(R.string.dialog_lifecycle_ok, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Log.i(TAG, getString(R.string.log_lifecycle_snack_ok));
                                    }
                                });
                                snackbar.show();
                            }
                        })
                        .setNegativeButton(R.string.dialog_lifecycle_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Snackbar snackbar = Snackbar.make(mLayout, R.string.snack_lifecycle_cancel_message, Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }
                        })
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                Log.i(TAG, getString(R.string.log_lifecycle_dismiss_d));
                            }
                        })
                        // 模态设置
                        .setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // PopupWindow点击的处理
        mPopupWindowButton = (Button) findViewById(R.id.buttonPopupWindow);
        mPopupWindowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 设置list表示内容
                View container = getLayoutInflater().inflate(R.layout.popupwindow_lifecycle, null);
                String[] data = new String[]{"one", "two", "three", "four"};
                ListView listView = (ListView) container.findViewById(R.id.popup_window);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(LifeCycleActivity.this,
                        R.layout.support_simple_spinner_dropdown_item, data);
                listView.setAdapter(adapter);

                // 设置listview分隔线颜色。
                listView.setDivider(new ColorDrawable(Color.YELLOW));
                listView.setDividerHeight(1);

                // 创建popup window
                PopupWindow popupWindow = new PopupWindow(container,
                        500,
                        LinearLayout.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.i(TAG, getString(R.string.log_lifecycle_dismiss_w));
                    }
                });

                /*// 模态窗口
                popupWindow.setFocusable(false);
                popupWindow.setOutsideTouchable(false);*/

                // 显示popup window
                popupWindow.showAsDropDown(mPopupWindowButton);
            }
        });

        // PopupMenu点击的处理。
        mPopupMenuButton = (Button) findViewById(R.id.buttonPopupMenu);
        mPopupMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(LifeCycleActivity.this, mPopupMenuButton);
                popupMenu.getMenuInflater().inflate(R.menu.menu_lifecycle_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Snackbar snackbar = Snackbar.make(mPopupMenuButton, item.getTitle(), Snackbar.LENGTH_SHORT);
                        snackbar.show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        // exit点击的处理
        mExitButton = (Button) findViewById(R.id.buttonExit);
        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, getString(R.string.log_lifecycle_save));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, getString(R.string.log_lifecycle_restore));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, getString(R.string.log_lifecycle_configuration));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, getString(R.string.log_lifecycle_resume));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, getString(R.string.log_lifecycle_start));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, getString(R.string.log_lifecycle_pause));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, getString(R.string.log_lifecycle_restart));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, getString(R.string.log_lifecycle_stop));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, getString(R.string.log_lifecycle_destroy));
    }
}
