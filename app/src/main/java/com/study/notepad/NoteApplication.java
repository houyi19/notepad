package com.study.notepad;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public class NoteApplication extends Application {

    private boolean isDebug = BuildConfig.DEBUG;

    @Override
    public void onCreate() {
        super.onCreate();

//      debug模式开启log模式，初始化第三方log库文件；
        if (isDebug) {
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
    }
}
