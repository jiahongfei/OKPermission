package com.andrjhf.okpermission;

import android.support.annotation.NonNull;

/**
 * @author :  jiahongfei
 * @email : jiahongfeinew@163.com
 * @date : 2018/1/28
 * @desc : 权限申请结束的监听，用于判断权限是否申请成功或者失败
 */

public interface OKPermissionListener {
    void onOKPermission(@NonNull String[] permissions, @NonNull int[] grantResults);
}
