package com.study.notepad.util;

//监听数据刷新的变化,比如删除;
public interface onNoteDataChangeListener {

    void onNewDatabaseEntryDelete(int pos);
    void onDatabaseEntryRenamed(int pos);
}
