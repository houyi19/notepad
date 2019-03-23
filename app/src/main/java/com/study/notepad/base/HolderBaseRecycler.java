package com.study.notepad.base;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public abstract class HolderBaseRecycler<T> extends HolderBase<T> {


    public HolderBaseRecycler(@NonNull View itemView) {
        super(itemView);
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onDestroy() {
    }
}
