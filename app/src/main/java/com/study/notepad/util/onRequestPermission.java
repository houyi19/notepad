package com.study.notepad.util;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public interface onRequestPermission {

//    回调授权权限的接口
    void grantPermissionResult(Activity mAct, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

}
