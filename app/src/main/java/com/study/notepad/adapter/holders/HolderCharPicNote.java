package com.study.notepad.adapter.holders;

import com.study.notepad.R;
import com.study.notepad.base.HolderBase;
import com.study.notepad.bean.NoteBean;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public class HolderCharPicNote extends HolderBase<NoteBean> implements View.OnClickListener {

    private TextView mContent;
    public HolderCharPicNote(@NonNull View itemView) {
        super(itemView);
        mContent = itemView.findViewById(R.id.holder_note_title);
    }

    @Override
    public void bindHolder(NoteBean noteBean) {
        super.bindHolder(noteBean);
        mContent.setText(noteBean.getContent());
    }

    @Override
    public void onClick(View view) {

    }
}
