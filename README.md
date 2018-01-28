# OKPermission
Android 6.0 申请权限，支持权限说明对话框，单个申请、多个申请权限

简书地址：[点击这里](https://www.jianshu.com/p/7ae6f234f392)

> 如下介绍我写的Android6.0申请权限的开源库，github地址[点击这里](https://github.com/jiahongfei/OKPermission)

### OKPermission
这是一个申请权限的开源库。

开源库支持：弹出权限说明对话框，单个、多个权限申请。
#### OKPermission对外暴露的类
```
//OKPermission的管理类，用于申请权限、配置各种参数
class OKPermissionManager

//申请权限的包装类，可以配置提示对话框权限的展示名字和图片
class PermissionItem

//权限申请结束的监听，用于判断权限是否申请成功或者失败
interface OKPermissionListener

//权限提示对话框，点击返回键的监听
interface OKPermissionKeyBackListener
```
#### 弹出权限申请提示对话框，并且申请多个权限
```
//申请的权限在这里配置，一个危险权限组只需要申请一个权限即可。
//例如：[LOCATION]权限组，只需要申请[ACCESS_FINE_LOCATION]或者[ACCESS_COARSE_LOCATION]即可，如下配置。
 PermissionItem[] permissionItems = new PermissionItem[]{
                new PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机状态", R.mipmap.ic_launcher),
                new PermissionItem(Manifest.permission.CAMERA, "照相机", R.mipmap.ic_launcher),
                new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "位置信息2", R.mipmap.ic_launcher)};
        OKPermissionManager okPermissionManager = new OKPermissionManager
                .Builder(permissionItems)
               //权限申请的回调方法
                .setOKPermissionListener(new OKPermissionListener() {
                    @Override
                    public void onOKPermission(@NonNull String[] permissions, @NonNull int[] grantResults) {
                        Log.e(TAG, Arrays.toString(permissions));
                    }
                })
                //权限申请提示对话框，点击返回键回调的监听
                .setKeyBackListener(new OKPermissionKeyBackListener() {
                    @Override
                    public void onKeyBackListener() {
                        Log.e(TAG,"OKPermissionKeyBackListener");
                    }
                })
               //开启权限申请提示对话框
                .setShowDialog(true)
                //配置标题
                .setDialogTitle("开启App")
                //配置提示信息
                .setDialogMsg("为了能够正常使用，需要开启以下权限")
                .builder();
        okPermissionManager.applyPermission(mContext);
```
上面那段代码对应如下图片

![申请权限.gif](https://github.com/jiahongfei/OKPermission/tree/master/Screenshot/申请权限.gif)
#### 快速申请权限
```
//这种方式可申请一个权限也可以申请多个权限
 OKPermissionManager.applyPermissionNoDialog(mContext, new String[]{Manifest.permission.READ_PHONE_STATE}, new OKPermissionListener() {
            @Override
            public void onOKPermission(@NonNull String[] permissions, @NonNull int[] grantResults) {

            }
        });
```
如下图片：

![快速申请权限.gif](https://github.com/jiahongfei/OKPermission/tree/master/Screenshot/快速申请权限.gif)
