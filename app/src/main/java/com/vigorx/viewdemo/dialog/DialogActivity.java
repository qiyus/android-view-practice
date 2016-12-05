package com.vigorx.viewdemo.dialog;

import android.Manifest;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.vigorx.viewdemo.R;

public class DialogActivity extends ListActivity
        implements LoaderManager.LoaderCallbacks<Cursor>,
        LoginDialogFragment.OnLoginDialogListener {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final String[] MENUS =
            {"AlertDialog", "SingleChoiceDialog", "MultiChoiceDialog", "LoginDialog", "CustomDialog"};

    // 检索结果
    private static final String[] PROJECTION = new String[]{ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

    // 检索条件
    private static final String SELECTION = "((" +
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " NOTNULL) AND (" +
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " != '' ))";

    private String mSelectedItem;
    private SimpleCursorAdapter mAdapter;
    private boolean mIsLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 动态获取访问外部存储权限。
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(DialogActivity.this,
                    Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(DialogActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                initContacts();
            }
        } else {
            initContacts();
        }

        mIsLarge = getResources().getBoolean(R.bool.large_layout);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int columnIndex = mAdapter.getCursor().getColumnIndex(
                        ContactsContract.CommonDataKinds.Contactables.DISPLAY_NAME);
                Cursor cursor = (Cursor) mAdapter.getItem(position);
                mSelectedItem = cursor.getString(columnIndex);

                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                builder.setItems(MENUS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            // 普通对话框
                            case 0:
                                showDialog(new AlertDialogFragment(), mSelectedItem, "alert");
                                break;
                            // 单选对话框
                            case 1:
                                showDialog(new SingleChoiceDialogFragment(), mSelectedItem, "single");
                                break;
                            // 多选对话框
                            case 2:
                                showDialog(new MultiChoiceDialogFragment(), mSelectedItem, "multi");
                                break;
                            // Login 对话框
                            case 3:
                                showDialog(new LoginDialogFragment(), mSelectedItem, "login");
                                break;
                            // 自定义视图对话框
                            case 4:
                                CustomDialogFragment customDialogFragment = new CustomDialogFragment();
                                android.app.FragmentManager fragmentManager = getFragmentManager();
                                if (mIsLarge) {
                                    customDialogFragment.show(fragmentManager, "custom");
                                }
                                else {
                                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                    transaction.add(android.R.id.content, customDialogFragment)
                                            .addToBackStack(null)
                                            .commit();
                                }
                                break;
                            default:
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                return true;
            }
        });
    }

    private void showDialog(DialogFragment dialogFragment, String title, String tag) {
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getFragmentManager(), tag);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            initContacts();
        }
    }

    private void initContacts() {
        // Create a progress bar to display while the list loads
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);

        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
        int[] toViews = {android.R.id.text1}; // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data.
        // We pass null for the cursor, then update it in onLoadFinished()
        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, null,
                fromColumns, toViews, 0);
        setListAdapter(mAdapter);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
    }

    // Called when a new Loader needs to be created
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new CursorLoader(this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PROJECTION, SELECTION, null, null);
    }

    // Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
    }

    @Override
    public void login(String username, String password) {
        String message = username + "|" + password;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancel() {
        Toast.makeText(this, "回调OK", Toast.LENGTH_SHORT).show();
    }
}
