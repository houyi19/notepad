package com.study.notepad.adapter.holders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.study.notepad.R;
import com.study.notepad.base.HolderBase;
import com.study.notepad.bean.NoteBean;
import com.study.notepad.dialog.BaseFileDialog;
import com.study.notepad.fragment.PlayRecordingDialogFragment;

import java.util.concurrent.TimeUnit;

public class HolderSpeechNote extends HolderBase<NoteBean> {

    private TextView mFileName,mFileLength,mTime;
    private CardView mCardContent;
    private Context mContext;


    public HolderSpeechNote(@NonNull View itemView) {
        super(itemView);
        mCardContent = itemView.findViewById(R.id.card_view);
        mFileName = itemView.findViewById(R.id.file_name_text);
        mFileLength = itemView.findViewById(R.id.file_length_text);
        mTime = itemView.findViewById(R.id.file_date_added_text);
        mContext = itemView.getContext();


    }


    public void bindHolder(final NoteBean noteBean, final Object object, final int pos) {
        super.bindHolder(noteBean, object);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(noteBean.getmLength());
        long seconds = TimeUnit.MILLISECONDS.toSeconds(noteBean.getmLength())
                - TimeUnit.MINUTES.toSeconds(minutes);
        mFileLength.setText(String.format("%02d:%02d", minutes, seconds));
        mFileName.setText(noteBean.getmName());
        mTime.setText(noteBean.getmTime());
        mCardContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayRecordingDialogFragment.start((FragmentActivity) object,noteBean);
            }
        });

        //长按进行分享,删除,重命名功能
        mCardContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                BaseFileDialog.newInstance().createBaseDialog(mContext,noteBean,pos);
                return false;
            }
        });
    }

}
