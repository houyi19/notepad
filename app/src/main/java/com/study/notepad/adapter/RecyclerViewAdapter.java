package com.study.notepad.adapter;

import android.support.v7.widget.RecyclerView;

import com.study.notepad.base.HolderBase;
import com.study.notepad.base.HolderBaseRecycler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public abstract class RecyclerViewAdapter extends RecyclerView.Adapter<HolderBase> {
    private List<WeakReference<HolderBaseRecycler>> mHolders = new ArrayList<>();

    protected void saveHolderBaseRecycler(HolderBase holder) {
        if (!(holder instanceof HolderBaseRecycler)) {
            return;
        }

        mHolders.add(new WeakReference<>((HolderBaseRecycler) holder));
    }

    public void onDestroy() {
        int size = mHolders.size();
        if (size == 0) {
            return;
        }

        for (WeakReference<HolderBaseRecycler> ref : mHolders) {
            if (ref == null) {
                continue;
            }

            HolderBaseRecycler holder = ref.get();
            if (holder != null) {
                holder.onDestroy();
            }
        }
        mHolders.clear();
    }

    public void onPause() {
        for (WeakReference<HolderBaseRecycler> ref : mHolders) {
            if (ref == null) {
                continue;
            }

            HolderBaseRecycler holder = ref.get();
            if (holder != null) {
                holder.onPause();
            }
        }
    }

    public void onResume() {
        for (WeakReference<HolderBaseRecycler> ref : mHolders) {
            if (ref == null) {
                continue;
            }

            HolderBaseRecycler holder = ref.get();
            if (holder != null) {
                holder.onResume();
            }
        }
    }
}
