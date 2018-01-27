package com.andrjhf.okpermission;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import static com.andrjhf.okpermission.OKPermission.REQUEST_CODE_PERMISSION;

/**
 * Created by jiahongfei on 2018/1/12.
 */

public class OKPermissionActivity extends Activity {

    private static final String TAG = "OKPermissionActivity";

    private static OKPermissionManager.OKPermissionListener sOKPermissionListener;
    private static OKPermissionManager.OKPermissionKeyBackListener sKeyBackListener;

    /**
     * 多个权限
     */
    public static final String INTENT_KEY_MULTIPLE_PERMISSIONS = "intent_key_multiple_permissions";

    /**
     * 是否弹出提示对话框
     */
    public static final String INTENT_KEY_SHOW_DIALOG = "intent_key_show_dialog";

    /**
     * 提示框标题
     */
    public static final String INTENT_KEY_DIALOG_TITLE = "intent_key_dialog_title";
    /**
     * 提示框提示语
     */
    public static final String INTENT_KEY_DIALOG_MSG = "intent_key_dialog_msg";
    /**
     * 对话框Item
     */
    public static final String INTENT_KEY_DIALOG_ITEMS = "intent_key_dialog_items";

    private Context mContext;
    private String[] mPermissions;
    private String mDialogTitle;
    private String mDialogMsg;
    private ArrayList<OKPermissionManager.PermissionItem> mDialogItems = new ArrayList<>();
    private boolean mShowDialog;
    private Dialog mDialog = null;

    public static void setOKPermissionListener(OKPermissionManager.OKPermissionListener okPermissionListener) {
        sOKPermissionListener = okPermissionListener;
    }

    public static void setKeyBackListener(OKPermissionManager.OKPermissionKeyBackListener keyBackListener) {
        sKeyBackListener = keyBackListener;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mContext = this;

        getIntentData();

        showApplyPermissionDialog();
    }

    private void getIntentData() {
        if (null == getIntent() || null == getIntent().getExtras()) {
            return;
        }
        Bundle bundle = getIntent().getExtras();
        mPermissions = bundle.getStringArray(INTENT_KEY_MULTIPLE_PERMISSIONS);
        mDialogTitle = bundle.getString(INTENT_KEY_DIALOG_TITLE, null);
        mDialogMsg = bundle.getString(INTENT_KEY_DIALOG_MSG, null);
        mDialogItems = (ArrayList<OKPermissionManager.PermissionItem>) bundle.getSerializable(INTENT_KEY_DIALOG_ITEMS);
        mShowDialog = bundle.getBoolean(INTENT_KEY_SHOW_DIALOG, false);
    }

    private void showApplyPermissionDialog() {
        final List<String> requestPermission = OKPermission.requestPermission(mContext, mPermissions);
        if (requestPermission.size() <= 0) {
            finish();
            return;
        }
        if (mShowDialog) {

            final OKPermissionDialog okPermissionDialog = new OKPermissionDialog(mContext, R.style.CustomDialog);
            okPermissionDialog.setOKPermissionTitle(mDialogTitle);
            okPermissionDialog.setOKPermissionMessage(mDialogMsg);
            okPermissionDialog.setRecyclerView(requestPermission, mDialogItems);
            okPermissionDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    okPermissionDialog.dismiss();
                    OKPermission.okPermission((Activity) mContext, requestPermission);

                }
            });
            okPermissionDialog.setDialogKeyBackListener(new OKPermissionDialog.DialogKeyBackListener() {
                @Override
                public void onKeyBackListener() {
                    if(null != sKeyBackListener){
                        sKeyBackListener.onKeyBackListener();
                    }
                    finish();
                }
            });
            mDialog = okPermissionDialog;
            okPermissionDialog.show();

        } else {
            OKPermission.okPermission((Activity) mContext, requestPermission);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION: {
                if (null != sOKPermissionListener) {
                    sOKPermissionListener.onOKPermission(permissions, grantResults);
                }
                break;
            }
            default:
                break;
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        if(null != mDialog && mDialog.isShowing()){
            mDialog.dismiss();
            mDialog = null;
        }
        super.onDestroy();
    }
}
