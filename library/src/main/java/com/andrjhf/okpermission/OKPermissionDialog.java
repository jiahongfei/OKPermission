package com.andrjhf.okpermission;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * @author :  jiahongfei
 * @email : jiahongfeinew@163.com
 * @date : 2018/1/18
 * @desc :
 */

class OKPermissionDialog extends Dialog {

    private Context mContext;
    private View.OnClickListener mOnClickListener;

    public OKPermissionDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public OKPermissionDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected OKPermissionDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        setContentView(R.layout.dialog_okpermission);
        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mOnClickListener){
                    mOnClickListener.onClick(v);
                }
            }
        });
    }

    public void setOKPermissionTitle(String title){
        TextView textView = findViewById(R.id.titleTextView);
        textView.setText(title);
    }

    public void setOKPermissionMessage(String message){
        TextView textView = findViewById(R.id.msgTextView);
        textView.setText(message);
    }

    public void setRecyclerView(List<String> requestPermission, List<OKPermissionManager.PermissionItem> dialogItems){
        int spanCount = 3;
        if(requestPermission.size() <= 3){
            spanCount = requestPermission.size();
        }
        OKPermissionAdapter okPermissionAdapter = new OKPermissionAdapter(requestPermission, dialogItems);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,spanCount));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(okPermissionAdapter);
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }

}
