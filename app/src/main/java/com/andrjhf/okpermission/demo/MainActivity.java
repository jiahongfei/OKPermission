package com.andrjhf.okpermission.demo;

import android.Manifest;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.andrjhf.okpermission.OKPermissionManager;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply();
            }
        });

        findViewById(R.id.textView1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply1();
            }
        });

        findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply2();

            }
        });
    }

    private void apply() {
        OKPermissionManager.PermissionItem[] permissionItems = new OKPermissionManager.PermissionItem[]{
                new OKPermissionManager.PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机状态", R.mipmap.ic_launcher),
                new OKPermissionManager.PermissionItem(Manifest.permission.CAMERA, "照相机", R.mipmap.ic_launcher),
                new OKPermissionManager.PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "位置信息2", R.mipmap.ic_launcher)};

        OKPermissionManager okPermissionManager = new OKPermissionManager
                .Builder(permissionItems)
                .setOKPermissionListener(new OKPermissionManager.OKPermissionListener() {
                    @Override
                    public void onOKPermission(@NonNull String[] permissions, @NonNull int[] grantResults) {
                        Log.e(TAG, Arrays.toString(permissions));
                    }
                })
                .setKeyBackListener(new OKPermissionManager.OKPermissionKeyBackListener() {
                    @Override
                    public void onKeyBackListener() {
                        Log.e(TAG,"OKPermissionKeyBackListener");

                    }
                })
                .setShowDialog(true)
                .setDialogTitle("开启App")
                .setDialogMsg("为了能够正常使用，需要开启以下权限")
                .builder();
        okPermissionManager.applyPermission(mContext);
    }

    private void apply1() {
        OKPermissionManager.applyPermissionNoDialog(mContext, new String[]{Manifest.permission.READ_PHONE_STATE}, new OKPermissionManager.OKPermissionListener() {
            @Override
            public void onOKPermission(@NonNull String[] permissions, @NonNull int[] grantResults) {

            }
        });
    }

    private void apply2() {
        OKPermissionManager.applyPermissionNoDialog(mContext, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA}, new OKPermissionManager.OKPermissionListener() {
            @Override
            public void onOKPermission(@NonNull String[] permissions, @NonNull int[] grantResults) {

            }
        });
    }

}
