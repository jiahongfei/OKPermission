package com.andrjhf.okpermission;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author :  jiahongfei
 * @email : jiahongfeinew@163.com
 * @date : 2018/1/18
 * @desc :
 */

class OKPermissionAdapter extends RecyclerView.Adapter {

    private List<String> mPermissionNames;
    private List<PermissionItem> mDialogItems;
    private View.OnClickListener mOnClickListener;

    public OKPermissionAdapter(List<String> requestPermission, List<PermissionItem> dialogItems){
        mPermissionNames = requestPermission;
        mDialogItems = dialogItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_okpermission,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        PermissionItem dialogItem = getDialogItem(mPermissionNames.get(position));
        if(null == dialogItem){
            return ;
        }
        viewHolder.mTextView.setText(dialogItem.name);
        viewHolder.mImageView.setImageResource(dialogItem.imageId);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mOnClickListener){
                    mOnClickListener.onClick(v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPermissionNames.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageImageView);
            mTextView = itemView.findViewById(R.id.nameTextView);
        }

    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }

    private PermissionItem getDialogItem(String keyPermission){
        for (PermissionItem dialogItem : mDialogItems){
            if(dialogItem.permission.equals(keyPermission)){
                return dialogItem;
            }
        }
        return null;
    }
}
