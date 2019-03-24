package com.study.notepad.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.study.notepad.bean.NoteBean;

import java.util.ArrayList;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
//进行数据库操作的工具类；
public class DataBaseUtil {

    private Context mContext;
    private DatabaseHelper dbHelper;

    public DataBaseUtil(Context mContext) {
        this.mContext = mContext;
    }

    //    查询数据
    public ArrayList<NoteBean> queryContent() {
        final ArrayList<NoteBean> listItem = new ArrayList<NoteBean>();
        //第二个参数是数据库名
        dbHelper = DatabaseHelper.gwtInstance(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from note", null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String content = cursor.getString(1);
                String picUrl = cursor.getString(2);
                String time = cursor.getString(3);
                NoteBean bean = new NoteBean(content, picUrl, time,0);
                listItem.add(bean);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return listItem;
    }

    //    添加数据
    public void addContent(NoteBean noteBean) {
        dbHelper = DatabaseHelper.gwtInstance(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", noteBean.getContent());
        values.put("picUrl", noteBean.getPicUrl());
        values.put("time", noteBean.getTime());
        //insert（）方法中第一个参数是表名，第二个参数是表示给表中未指定数据的自动赋值为NULL。第三个参数是一个ContentValues对象
        db.insert("note", null, values);
        db.close();
    }

    //    TODO 删除数据
    public void delContent() {

    }

    // TODO 更新数据
    public void updateContent() {

    }

    //   添加语音数据
    public void addSpeechContent(NoteBean noteBean) {
        dbHelper = DatabaseHelper.gwtInstance(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("recording_name", noteBean.getmName());
        values.put("file_path", noteBean.getmFilePath());
        values.put("length", noteBean.getmLength());
        values.put("time_added",noteBean.getmTime());
        //insert（）方法中第一个参数是表名，第二个参数是表示给表中未指定数据的自动赋值为NULL。第三个参数是一个ContentValues对象
        db.insert("saved_recordings", null, values);
        db.close();
    }
    //   查询语音数据

    public ArrayList<NoteBean> querySpeechContent() {
        final ArrayList<NoteBean> listItem = new ArrayList<NoteBean>();
        //第二个参数是数据库名
        dbHelper = DatabaseHelper.gwtInstance(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from saved_recordings", null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String content = cursor.getString(1);
                String file_path = cursor.getString(2);
                long length = cursor.getLong(3);
                String time = cursor.getString(4);
                NoteBean bean = new NoteBean(content, file_path,length, time,1);
                listItem.add(bean);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return listItem;
    }

    //    TODO 删除语音数据
    public void delSpeechContent() {

    }

    // TODO 更新语音数据
    public void updateSpeechContent() {

    }

    //   读取已经插入的语音数据
    public int getCount() {
        dbHelper = DatabaseHelper.gwtInstance(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = { DatabaseHelper.DBHelperItem._ID };
        Cursor c = db.query("saved_recordings", projection, null, null, null, null, null);
        int count = c.getCount();
        c.close();
        return count;
    }
}
