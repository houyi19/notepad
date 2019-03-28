package com.study.notepad.adapter.holders;

import com.study.notepad.R;
import com.study.notepad.activity.ContentDetailActivity;
import com.study.notepad.base.HolderBase;
import com.study.notepad.bean.NoteBean;
import com.study.notepad.dialog.BaseFileDialog;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public class HolderCharPicNote extends HolderBase<NoteBean> implements View.OnClickListener {

    private TextView mContent, mTime;
    private CardView mContentContainer;
    private Context mContext;

    public HolderCharPicNote(@NonNull View itemView) {
        super(itemView);
        mContent = itemView.findViewById(R.id.holder_char_list_title);
        mContentContainer = itemView.findViewById(R.id.holder_char_card_view_note);
        mTime = itemView.findViewById(R.id.holder_char_list_time);
        mContext = itemView.getContext();
    }


    public void bindHolder(final NoteBean noteBean,final int pos) {
        super.bindHolder(noteBean);
        mContent.setText(noteBean.getContent());
        mTime.setText(noteBean.getTime());

        mContentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ContentDetailActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("noteBean",noteBean);
                mContext.startActivity(i);

            }
        });
        mContentContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                BaseFileDialog.newInstance().createBaseDialog(mContext,noteBean,pos);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
