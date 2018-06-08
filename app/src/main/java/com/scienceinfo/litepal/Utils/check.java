package com.scienceinfo.litepal.Utils;

import android.Manifest;
import android.content.Context;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpOptions;

public class check {
    public static void Permission(Context context) {
        Acp.getInstance(context).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE
                                , Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        //以下为自定义提示语、按钮文字
                        .setDeniedMessage("您拒绝存储权限申请，部分功能需要您授权，您可以去设置页面重新授权")
                        .setDeniedCloseBtn("关闭")
                        .setDeniedSettingBtn("设置权限")
                        .setRationalMessage("部分功能需要您授权，否则将不能正常使用")
                        .setRationalBtn("我知道了")
                        .build(),
                null);
    }
}