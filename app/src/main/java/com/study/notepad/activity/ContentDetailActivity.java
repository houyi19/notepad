package com.study.notepad.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sendtion.xrichtext.RichTextView;
import com.study.notepad.R;
import com.study.notepad.bean.NoteBean;
import com.study.notepad.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//内容显示主体页
public class ContentDetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private RichTextView mContent;//笔记内容
    private TextView mTime;//笔记创建时间

    private String mFinalContent;
    private ProgressDialog loadingDialog;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);
        Logger.i("onCreate");
        InitView();
    }

    private void InitView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_note);
        toolbar.setTitle("笔记详情");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //toolbar.setNavigationIcon(R.drawable.ic_dialog_info);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        NoteBean res = (NoteBean) getIntent().getSerializableExtra("noteBean");
        mTime = findViewById(R.id.tv_note_title);
        mTime.setText(res.getTime());
        mTitle = findViewById(R.id.tv_note_title);
        mTitle.setText(res.getTitle());


        mContent = findViewById(R.id.tv_note_content);
        mFinalContent = res.getContent();
        mContent.post(new Runnable() {
            @Override
            public void run() {
                dealWithContent();
            }
        });
    }

    private void dealWithContent() {
        mContent.clearAllLayout();
        showDataSync(mFinalContent);
    }

    /**
     * 异步方式显示数据
     *
     * @param html
     */
    private void showDataSync(final String html) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                showEditData(emitter, html);
            }
        })
                //.onBackpressureBuffer()
                .subscribeOn(Schedulers.io())//生产事件在io
                .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onComplete() {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                        }
                        Logger.e("onError: " + e.getMessage());
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(String text) {
                        if (mContent != null) {
                            if (text.contains("<img") && text.contains("src=")) {
                                //imagePath可能是本地路径，也可能是网络地址
                                String imagePath = StringUtil.getImgSrc(text);
                                mContent.addImageViewAtIndex(mContent.getLastIndex(), imagePath);
                            } else {
                                mContent.addTextViewAtIndex(mContent.getLastIndex(), text);
                            }
                        }
                    }
                });

    }


    /**
     * 显示数据
     *
     * @param html
     */
    private void showEditData(ObservableEmitter<String> emitter, String html) {
        try {
            List<String> textList = StringUtil.cutStringByImgTag(html);
            for (int i = 0; i < textList.size(); i++) {
                String text = textList.get(i);
                emitter.onNext(text);
            }
            emitter.onComplete();
        } catch (Exception e) {
            e.printStackTrace();
            emitter.onError(e);
        }
    }

}
