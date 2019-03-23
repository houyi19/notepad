package com.study.notepad.bean;

import java.io.Serializable;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
//储存记事内容，包含图片；
public class NoteBean implements Serializable{

    //    id号，标识唯一值
    private int id;

    public NoteBean(String content, String picUrl, String time) {
        this.content = content;
        this.picUrl = picUrl;
        this.time = time;
    }

    //    表示记事内容；
    private String content;
    //    表示图片的存储地址；
    private String picUrl;
    //    表示发布记事的时间标识；
    private String time;
    @Override
    public String toString() {
        return "NoteBean{" +
                "id=" + id +
                ", title='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", time=" + time +
                '}';
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
}
