package com.andrjhf.okpermission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

import static com.andrjhf.okpermission.OKPermissionActivity.INTENT_KEY_DIALOG_ITEMS;
import static com.andrjhf.okpermission.OKPermissionActivity.INTENT_KEY_DIALOG_MSG;
import static com.andrjhf.okpermission.OKPermissionActivity.INTENT_KEY_DIALOG_TITLE;
import static com.andrjhf.okpermission.OKPermissionActivity.INTENT_KEY_MULTIPLE_PERMISSIONS;
import static com.andrjhf.okpermission.OKPermissionActivity.INTENT_KEY_SHOW_DIALOG;

/**
 * @author :  jiahongfei
 * @email : jiahongfeinew@163.com
 * @date : 2018/1/18
 * @desc :
 */

public class OKPermissionManager {

    public static class PermissionItem implements Serializable {
        public final String permission;
        public final int imageId;
        public final String name;

        public PermissionItem(String permission, String name, int imageId) {
            this.permission = permission;
            this.imageId = imageId;
            this.name = name;
        }
    }

    public interface OKPermissionListener {
        void onOKPermission(@NonNull String[] permissions, @NonNull int[] grantResults);
    }

    private Bundle mBundle = new Bundle();
    private OKPermissionListener mOKPermissionListener;

    private OKPermissionManager(Builder builder) {
        mBundle.putStringArray(INTENT_KEY_MULTIPLE_PERMISSIONS, builder.mPermissions);

        mBundle.putBoolean(INTENT_KEY_SHOW_DIALOG, builder.mShowDialog);

        mBundle.putString(INTENT_KEY_DIALOG_TITLE, builder.mDialogTitle);
        mBundle.putString(INTENT_KEY_DIALOG_MSG, builder.mDialogMsg);
        mBundle.putSerializable(INTENT_KEY_DIALOG_ITEMS, builder.mDialogItems);

        mOKPermissionListener = builder.mOKPermissionListener;
    }

    /**
     * 应用权限
     *
     * @param context
     */
    public void applyPermission(Context context) {
        OKPermissionActivity.setOKPermissionListener(mOKPermissionListener);
        Intent intent = new Intent(context, OKPermissionActivity.class);
        intent.putExtras(mBundle);
        context.startActivity(intent);
    }

    public static class Builder {

        private String[] mPermissions;
        private String mDialogTitle;
        private String mDialogMsg;
        private ArrayList<PermissionItem> mDialogItems;
        private boolean mShowDialog = false;
        private OKPermissionListener mOKPermissionListener;

        public Builder(PermissionItem[] permissions) {
            mPermissions = new String[permissions.length];
            mDialogItems = new ArrayList<>();
            for (int i = 0; i<permissions.length ;i++){
                mPermissions[i] = permissions[i].permission;
                mDialogItems.add(permissions[i]);
            }
        }

        public Builder setShowDialog(boolean showDialog) {
            mShowDialog = showDialog;
            return this;
        }

        public Builder setDialogTitle(String dialogTitle) {
            mDialogTitle = dialogTitle;
            return this;
        }

        public Builder setDialogMsg(String dialogMsg) {
            mDialogMsg = dialogMsg;
            return this;
        }

        public Builder setOKPermissionListener(OKPermissionListener permissionListener) {
            mOKPermissionListener = permissionListener;
            return this;
        }

        public OKPermissionManager builder() {
            return new OKPermissionManager(this);
        }
    }

}
