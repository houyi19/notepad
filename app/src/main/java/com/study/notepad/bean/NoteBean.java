package com.study.notepad.bean;

import java.io.Serializable;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
//储存记事内容，包含图片；
public class NoteBean implements Serializable {

    //    id号，标识唯一值
    private int id;

    //    表示记事内容；
    private String content;
    //    表示图片的存储地址；
    private String picUrl;
    //    表示发布记事的时间标识；
    private String time;

    //  表示是什么类型的记时原则;   0 代表着文字和图文记事,1代表着语音记时;
    private int type;

    private String mName; // file name
    private String mFilePath; //file path
    private int mId; //id in database
    private long mLength; // length of recording in seconds
    private String mTime; // date/time of the recording

    public NoteBean(String content, String picUrl, String time, int type) {
        this.content = content;
        this.picUrl = picUrl;
        this.time = time;
        this.type = type;
    }

    public NoteBean(String mName, String mFilePath, long mLength, String mTime, int type) {
        this.mName = mName;
        this.mFilePath = mFilePath;
        this.mLength = mLength;
        this.mTime = mTime;
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmFilePath() {
        return mFilePath;
    }

    public void setmFilePath(String mFilePath) {
        this.mFilePath = mFilePath;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public long getmLength() {
        return mLength;
    }

    public void setmLength(long mLength) {
        this.mLength = mLength;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NoteBean{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", time='" + time + '\'' +
                ", type=" + type +
                ", mName='" + mName + '\'' +
                ", mFilePath='" + mFilePath + '\'' +
                ", mId=" + mId +
                ", mLength=" + mLength +
                ", mTime=" + mTime +
                '}';
    }
}
