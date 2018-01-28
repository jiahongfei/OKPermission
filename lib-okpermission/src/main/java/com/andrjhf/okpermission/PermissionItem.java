package com.andrjhf.okpermission;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author :  jiahongfei
 * @email : jiahongfeinew@163.com
 * @date : 2018/1/28
 * @desc : 申请权限的包装类，可以配置提示对话框权限的展示名字和图片
 */

public class PermissionItem implements Serializable, Parcelable {
    public final String permission;
    public final int imageId;
    public final String name;

    public PermissionItem(String permission, String name, int imageId) {
        this.permission = permission;
        this.imageId = imageId;
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.permission);
        dest.writeInt(this.imageId);
        dest.writeString(this.name);
    }

    protected PermissionItem(Parcel in) {
        this.permission = in.readString();
        this.imageId = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<PermissionItem> CREATOR = new Parcelable.Creator<PermissionItem>() {
        @Override
        public PermissionItem createFromParcel(Parcel source) {
            return new PermissionItem(source);
        }

        @Override
        public PermissionItem[] newArray(int size) {
            return new PermissionItem[size];
        }
    };
}
