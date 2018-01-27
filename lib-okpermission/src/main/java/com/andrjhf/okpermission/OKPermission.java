package com.andrjhf.okpermission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiahongfei on 2018/1/12.
 */

class OKPermission {

    public static final int REQUEST_CODE_PERMISSION = 0;

    public static List<String> requestPermission(Context context, String[] permissions) {
        List<String> requestPermission = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                //没有授权
                requestPermission.add(permissions[i]);
            }
        }
        return requestPermission;
    }

    public static void okPermission(Activity activity, List<String> requestPermission) {

        if (requestPermission.size() > 0) {
            ActivityCompat.requestPermissions(activity,
                    requestPermission.toArray(new String[requestPermission.size()]),
                    REQUEST_CODE_PERMISSION);
        }
    }

}
