package com.study.notepad.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public class DatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static DatabaseHelper databaseHelper = null;

    private static final String CREATE_NOTE_DB = "create table note(" +
            "id integer primary key autoincrement," +
            "content text," +
            "picUrl varchar(20)," +
            "time varchar(20))";

    //    FIXME 防止sql内存泄露，采用单例
    public synchronized static DatabaseHelper gwtInstance(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context, "noteDataBase", null, 1);
        }
        return databaseHelper;
    }

    DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_NOTE_DB);
        Toast.makeText(mContext, "创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
