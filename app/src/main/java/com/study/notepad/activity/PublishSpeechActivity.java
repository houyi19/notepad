package com.study.notepad.activity;

import android.content.Intent;
import android.os.Environment;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.study.notepad.R;
import com.study.notepad.service.RecordingService;

import java.io.File;

//发布语音的记事本
public class PublishSpeechActivity extends AppCompatActivity implements View.OnClickListener {

    //Recording controls
    private FloatingActionButton mRecordButton = null;
    private Button mPauseButton = null;

    private TextView mRecordingPrompt;
    private int mRecordPromptCount = 0;

    private boolean mStartRecording = true;
    private boolean mPauseRecording = true;

    private Chronometer mChronometer = null;
    long timeWhenPaused = 0; //stores time when user clicks pause button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_publish_speech);
        Logger.d("onCreate");
        InitView();
    }


    //    初始化界面
    private void InitView() {
        mRecordButton = findViewById(R.id.act_btn_Record);
        mPauseButton = findViewById(R.id.act_btn_Pause);
        mRecordingPrompt = findViewById(R.id.recording_status_text);

        Toolbar toolbar = findViewById(R.id.title_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mChronometer = findViewById(R.id.chronometer);
        mPauseButton.setVisibility(View.GONE);
        mPauseButton.setOnClickListener(this);
        mRecordButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_btn_Pause:
                onPause(mPauseRecording);
                mPauseRecording = !mPauseRecording;
                break;
            case R.id.act_btn_Record:
                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
                break;
        }
    }


    //    开始录音;
    private void onRecord(boolean start) {
        Logger.d("onRecord");
        Intent intent = new Intent(PublishSpeechActivity.this, RecordingService.class);
        if (start) {
            // start recording
            mRecordButton.setImageResource(R.drawable.ic_media_stop);
            //mPauseButton.setVisibility(View.VISIBLE);
            File folder = new File(Environment.getExternalStorageDirectory() + "/notePadRecorder");
            if (!folder.exists()) {
                //folder /SoundRecorder doesn't exist, create the folder
                folder.mkdir();
            }

            //start Chronometer
            mChronometer.setBase(SystemClock.elapsedRealtime());
            mChronometer.start();
            mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    if (mRecordPromptCount == 0) {
                        mRecordingPrompt.setText(getString(R.string.record_in_progress) + ".");
                    } else if (mRecordPromptCount == 1) {
                        mRecordingPrompt.setText(getString(R.string.record_in_progress) + "..");
                    } else if (mRecordPromptCount == 2) {
                        mRecordingPrompt.setText(getString(R.string.record_in_progress) + "...");
                        mRecordPromptCount = -1;
                    }

                    mRecordPromptCount++;
                }
            });

            //start RecordingService
            startService(intent);
            //keep screen on while recording
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            mRecordingPrompt.setText(getString(R.string.record_in_progress) + ".");
            mRecordPromptCount++;

        } else {
            //stop recording
            mRecordButton.setImageResource(R.drawable.ic_mic_white_36dp);
            //mPauseButton.setVisibility(View.GONE);
            mChronometer.stop();
            mChronometer.setBase(SystemClock.elapsedRealtime());
            timeWhenPaused = 0;
            mRecordingPrompt.setText(getString(R.string.record_prompt));

            stopService(intent);
            //allow the screen to turn off again once recording is finished
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            //录完音结束后进行跳转回原来的主页
            Toast.makeText(this, "录音完成", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    //TODO: implement pause recording
    private void onPause(boolean pause) {

        if (pause) {
            mPauseButton.setCompoundDrawablesWithIntrinsicBounds
                    (R.drawable.ic_media_play, 0, 0, 0);
            mRecordingPrompt.setText((String) getString(R.string.resume_recording_button).toUpperCase());
            timeWhenPaused = mChronometer.getBase() - SystemClock.elapsedRealtime();
            mChronometer.stop();
        } else {
            //resume recording
            mPauseButton.setCompoundDrawablesWithIntrinsicBounds
                    (R.drawable.ic_media_pause, 0, 0, 0);
            mRecordingPrompt.setText((String) getString(R.string.pause_recording_button).toUpperCase());
            mChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenPaused);
            mChronometer.start();
        }
    }
}
