package com.study.notepad.adapter;


import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.study.notepad.R;
import com.study.notepad.adapter.holders.HolderCharPicNote;
import com.study.notepad.adapter.holders.HolderSpeechNote;
import com.study.notepad.base.HolderBase;
import com.study.notepad.bean.NoteBean;
import com.study.notepad.util.DataBaseUtil;
import com.study.notepad.util.onNoteDataChangeListener;

import java.util.ArrayList;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public class NoteContentAdapter extends RecyclerViewAdapter implements onNoteDataChangeListener {

    private ArrayList<NoteBean> mAllmodels;
    private FragmentActivity mActivty;

    public NoteContentAdapter(FragmentActivity mActivty) {
        mAllmodels = new ArrayList<>();
        this.mActivty = mActivty;
        DataBaseUtil.setmOnDatabaseChangedListener(this);
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
            v = inflater.inflate(R.layout.holder_speech_note, viewGroup, false);
            holderBase = new HolderSpeechNote(v);
        }
        return holderBase;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBase holderBase, int i) {
        if (holderBase instanceof HolderCharPicNote) {
            ((HolderCharPicNote) holderBase).bindHolder(mAllmodels.get(i));
        } else if (holderBase instanceof HolderSpeechNote) {
            ((HolderSpeechNote) holderBase).bindHolder(mAllmodels.get(i), mActivty, i);
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
        if (!mAllmodels.isEmpty()) {
            if (mAllmodels.get(position).getType() + 1 == NoteType.CHAR_PICTURE_NOTE) {
                return NoteType.CHAR_PICTURE_NOTE;
            } else {
                return NoteType.SPEECH_NOTE;
            }
        }
        return -1;
    }


    @Override
    public void onNewDatabaseEntryDelete(int pos) {
        Logger.i("entry delete pos:" + String.valueOf(pos));
        notifyItemChanged(pos);
    }

    @Override
    public void onDatabaseEntryRenamed(int pos) {
        Logger.i("entry renamed pos:" + String.valueOf(pos));
        notifyItemChanged(pos);
    }
}
