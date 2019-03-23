package com.study.notepad;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.orhanobut.logger.Logger;
import com.study.notepad.activity.PublishActivity;
import com.study.notepad.activity.PublishSpeechActivity;
import com.study.notepad.bean.NoteBean;
import com.study.notepad.fragment.EmptyFragment;
import com.study.notepad.fragment.NoteContentFragment;
import com.study.notepad.util.DataBaseUtil;
import com.study.notepad.util.FragmentManagerUtil;
import com.study.notepad.util.PermissionGuideUtil;

import java.util.ArrayList;

public class NoteMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mTitleBar;
    //    发布文字，图片记事的按钮
    private FloatingActionButton mAddCharContent;
    //    发布语音记事的按钮
    private FloatingActionButton mAddSpeechContent;
//    获取数据库的内容
    private ArrayList<NoteBean>mModels =  new ArrayList<>();

    private DataBaseUtil dataBaseUtil = new DataBaseUtil(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_note_main);
        Logger.d("onCreate");
        InitView();
        InitSetting();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_char_content:
                Intent i = new Intent(this, PublishActivity.class);
                startActivity(i);
                break;
            case R.id.add_speech_content:
                Intent speech = new Intent(this, PublishSpeechActivity.class);
                startActivity(speech);
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        mModels = dataBaseUtil.queryContent();
        if (!mModels.isEmpty()) {
            FragmentManagerUtil.replaceFragment(getSupportFragmentManager(),R.id.main_content, NoteContentFragment.newInstance(mModels));
        } else {
            FragmentManagerUtil.replaceFragment(getSupportFragmentManager(),R.id.main_content, EmptyFragment.newInstance());
        }
        super.onResume();
    }

    //    初始化view;
    private void InitView() {
        mAddCharContent = findViewById(R.id.add_char_content);
        mAddSpeechContent = findViewById(R.id.add_speech_content);
        mTitleBar = findViewById(R.id.title_bar);
        mAddCharContent.setOnClickListener(this);
        mAddSpeechContent.setOnClickListener(this);
        mModels = dataBaseUtil.queryContent();
        if (!mModels.isEmpty()) {
            FragmentManagerUtil.replaceFragment(getSupportFragmentManager(),R.id.main_content, NoteContentFragment.newInstance(mModels));
        } else {
            FragmentManagerUtil.replaceFragment(getSupportFragmentManager(),R.id.main_content, EmptyFragment.newInstance());
        }
    }

    //    初始化相关配置(例如权限的配置）
    private void InitSetting() {
        PermissionGuideUtil.newInstance().GrantPermission(getApplicationContext(), this);
    }
}
