package com.study.notepad.adapter;

import android.support.annotation.IntDef;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public class NoteType {
    //    分别对应着文字图片记事，语音记事
    @IntDef({
            CHAR_PICTURE_NOTE,
            SPEECH_NOTE
    })

    public @interface Near {};

    public static final int CHAR_PICTURE_NOTE = 1;
    public static final int SPEECH_NOTE = 2;
}
