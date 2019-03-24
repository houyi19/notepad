package com.study.notepad.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public class PermissionGuideUtil implements onRequestPermission {

    private static PermissionGuideUtil permissionGuideUtil;

    private static final int RESULT_CODE = 1;


    public static PermissionGuideUtil newInstance() {

        if (permissionGuideUtil == null)
            permissionGuideUtil = new PermissionGuideUtil();
        return permissionGuideUtil;
    }

    private ArrayList<String> mPermissionList = new ArrayList<>();

    private String[] mPermissions = new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO};

    public void GrantPermission(@NonNull Context mContext, @NonNull Activity mAct) {

        mPermissionList.clear();
        for (int i = 0; i < mPermissions.length; i++) {
            if (ContextCompat.checkSelfPermission(mContext, mPermissions[i]) != PackageManager.PERMISSION_GRANTED)
                //denied
                mPermissionList.add(mPermissions[i]);

        }

        if (!mPermissionList.isEmpty()) {
            String[] denied = mPermissionList.toArray(new String[mPermissionList.size()]);
            ActivityCompat.requestPermissions(mAct, denied, RESULT_CODE);
        } else {

        }
    }

    @Override
    public void grantPermissionResult(Activity mAct, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mAct.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RESULT_CODE:
                break;
            default:
                break;
        }
    }
}
