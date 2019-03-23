package com.study.notepad.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public class HolderBase<T> extends RecyclerView.ViewHolder {
    //    最基本的holder；

    public HolderBase(@NonNull View itemView) {
        super(itemView);
    }

    public void bindHolder(T t) {

    }
    public void bindHolder(T t, Object object) {
    }

    public void bindHolder(T t, Object obj0, Object obj1) {
    }

    public void bindHolder(T t, Object obj0, Object obj1, Object obj2) {
    }

    public void unbindHolder() {
    }

}
