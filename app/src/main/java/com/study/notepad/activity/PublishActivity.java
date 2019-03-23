package com.study.notepad.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.orhanobut.logger.Logger;
import com.study.notepad.R;
import com.study.notepad.bean.NoteBean;
import com.study.notepad.util.DataBaseUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/

//主要发布记事内容的页面
public class PublishActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mPublish;
    private EditText mContent;
    private String dataTime, mPicUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.act_publish_content);
        Logger.d("onCreate");
        InitView();
    }

    @Override
    protected void onDestroy() {
        Logger.d("onDestroy");
        super.onDestroy();
    }

    private void InitView() {
        mPublish = findViewById(R.id.act_publish_btn);
        mContent = findViewById(R.id.act_publish_note);
        mPublish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_publish_btn:
                long time = System.currentTimeMillis();
                dataTime = timeStamp2Date(String.valueOf(time / 1000), "yyyy-MM-dd HH:mm:ss");
                mPicUrl = null;
                NoteBean noteBean = new NoteBean(mContent.getText().toString(), mPicUrl, dataTime);
                Logger.i(noteBean.toString());
                DataBaseUtil dataBaseUtil = new DataBaseUtil(this);
                dataBaseUtil.addContent(noteBean);
                break;
        }
    }

    //    转换成日期时间yyyy-MM-dd HH:mm:ss;
    public String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }
}
