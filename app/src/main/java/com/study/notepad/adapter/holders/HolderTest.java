package com.study.notepad.adapter.holders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.study.notepad.R;
import com.study.notepad.base.HolderBase;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/

//测试holder;
public class HolderTest extends HolderBase<Integer> {

    private TextView mTitle;
    public HolderTest(@NonNull View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.holder_note_title);
    }

    @Override
    public void bindHolder(Integer integer) {
        super.bindHolder(integer);
        mTitle.setText(String.valueOf(integer));
    }


}
