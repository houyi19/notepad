package com.study.notepad.adapter;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.study.notepad.R;
import com.study.notepad.adapter.holders.HolderCharPicNote;
import com.study.notepad.adapter.holders.HolderTest;
import com.study.notepad.base.HolderBase;
import com.study.notepad.bean.NoteBean;

import java.util.ArrayList;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public class NoteContentAdapter extends RecyclerViewAdapter {

    private ArrayList<NoteBean> mAllmodels;

    public NoteContentAdapter() {
        mAllmodels = new ArrayList<>();
    }

    public void addModel(ArrayList<NoteBean> model) {
        mAllmodels.clear();
        if (!model.isEmpty()) {
            for (NoteBean i : model) {
                mAllmodels.add(i);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HolderBase onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v;
        HolderBase holderBase = null;
        if (i == NoteType.CHAR_PICTURE_NOTE) {
            v = inflater.inflate(R.layout.holder_char_note_item, viewGroup, false);
            holderBase = new HolderCharPicNote(v);
        } else if (i == NoteType.SPEECH_NOTE) {
            return null;
        }
        return holderBase;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBase holderBase, int i) {
        if (holderBase instanceof HolderCharPicNote) {
            ((HolderCharPicNote) holderBase).bindHolder(mAllmodels.get(i));
        }
    }


    @Override
    public int getItemCount() {
        if (mAllmodels.isEmpty()) {
            return 0;
        }
        Logger.i(String.valueOf(mAllmodels.size()));
        return mAllmodels.size();
    }


    @Override
    public int getItemViewType(int position) {
//        TODO:测试数据均为int型
        return NoteType.CHAR_PICTURE_NOTE;
    }
}
