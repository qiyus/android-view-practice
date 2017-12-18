package com.vigor.component.lifecycle;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.vigor.component.R;

/**
 * Created by Vigor on 16/10/28.
 */

public class LifeCycleDemoActivity extends AppCompatActivity {
    private final static String TAG = "com.vigorx.lifecycle";
    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private ImageView mStar;
    private RatingBar mRatingBar;
    private Button mLocationButton;
    private ToggleButton mToggleButton;
    private Button mDialogButton;
    private Button mPopupMenuButton;
    private Button mPopupWindowButton;
    private Button mExitButton;
    private LinearLayout mLayout;
    private PopupWindow mPopupWindow;
    private PopupMenu mPopupMenu;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getString(R.string.log_lifecycle_create));

        setContentView(R.layout.activity_popupwindow);
        mTextView1 = (TextView) findViewById(R.id.textView1);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mTextView3 = (TextView) findViewById(R.id.textView3);
        mTextView4 = (TextView) findViewById(R.id.textView4);
        mLayout = (LinearLayout) findViewById(R.id.linear);
        mLocationButton = (Button) findViewById(R.id.locationButton);
        mStar = (ImageView) findViewById(R.id.star);
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);

        // 显示layout坐标
        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rect rect = new Rect();
                mLocationButton.getLocalVisibleRect(rect);
                mTextView1.setText(getString(R.string.lifecycle_getlocal) + rect.toString());
                mLocationButton.getGlobalVisibleRect(rect);
                mTextView2.setText(getString(R.string.lifecycle_getglobal) + rect.toString());

                int[] location = new int[2];
                mLocationButton.getLocationOnScreen(location);
                mTextView3.setText(getString(R.string.lifecycle_getonscreen) + location[0] + getString(R.string.lifecycle_y) + location[1]);
                mLocationButton.getLocationInWindow(location);
                mTextView4.setText(getString(R.string.lifecycle_getinwindow) + location[0] + getString(R.string.lifecycle_y) + location[1]);
            }
        });

        // ToggleButton点击的处理。

        mToggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mToggleButton.isChecked()) {
                    mStar.setImageResource(android.R.drawable.star_big_off);
                } else {
                    mStar.setImageResource(android.R.drawable.star_big_on);
                }
            }
        });

        // Dialog点击的处理。
        mDialogButton = (Button) findViewById(R.id.buttonDialog);
        mDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LifeCycleDemoActivity.this);
                builder.setTitle(R.string.dialog_lifecycle_title)
                        .setMessage(R.string.dialog_lifecycle_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Snackbar snackbar = Snackbar.make(mLayout,
                                        R.string.snack_lifecycle_ok_message, Snackbar.LENGTH_SHORT);
                                snackbar.setAction(R.string.ok, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Log.i(TAG, getString(R.string.log_lifecycle_snack_ok));
                                    }
                                });
                                snackbar.show();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Snackbar snackbar = Snackbar.make(mLayout,
                                        R.string.snack_lifecycle_cancel_message, Snackbar.LENGTH_SHORT);
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
                final View container = getLayoutInflater().inflate(R.layout.popupwindow_lifecycle, null);
                String[] data = new String[]{"one", "two", "three", "four", "five"};
                ListView listView = (ListView) container.findViewById(R.id.popup_window);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(LifeCycleDemoActivity.this,
                        R.layout.support_simple_spinner_dropdown_item, data);
                listView.setAdapter(adapter);

                // 设置listview分隔线颜色。
                listView.setDivider(new ColorDrawable(Color.YELLOW));
                listView.setDividerHeight(1);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mRatingBar.setRating(position + 1);
                    }
                });

                // 创建popup window
                mPopupWindow = new PopupWindow(container, 400,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        true);
                mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.i(TAG, getString(R.string.log_lifecycle_dismiss_w));
                    }
                });
                mPopupWindow.setAnimationStyle(R.style.anim_popup_window);

                // 显示popup window
                int[] location = new int[2];
                mLocationButton.getLocationInWindow(location);
                mPopupWindow.showAsDropDown(mLocationButton, mLocationButton.getWidth(), -mLocationButton.getHeight());
            }
        });

        // PopupMenu点击的处理。
        mPopupMenuButton = (Button) findViewById(R.id.buttonPopupMenu);
        mPopupMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupMenu = new PopupMenu(LifeCycleDemoActivity.this, mPopupMenuButton);
                mPopupMenu.getMenuInflater().inflate(R.menu.menu_lifecycle_popup, mPopupMenu.getMenu());
                mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        mRatingBar.setRating(item.getOrder());
                        return true;
                    }
                });
                mPopupMenu.show();
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

        // context menu
        mStar.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("star setting");
                menu.add(0, Menu.FIRST, 0, "on");
                menu.add(0, Menu.FIRST + 1, 1, "off");
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case Menu.FIRST:
                mStar.setImageResource(android.R.drawable.star_big_on);
                break;
            case Menu.FIRST + 1:
                mStar.setImageResource(android.R.drawable.star_big_off);
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
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
