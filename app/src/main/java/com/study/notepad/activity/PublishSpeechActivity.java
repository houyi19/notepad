package com.study.notepad.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.study.notepad.R;

//发布语音的记事本
public class PublishSpeechActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_speech);
        Logger.d("onCreate");
    }
}
