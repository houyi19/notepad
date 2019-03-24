package com.study.notepad.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.study.notepad.R;
import com.study.notepad.bean.NoteBean;
import com.study.notepad.util.DataBaseUtil;
import com.study.notepad.util.onNoteDataChangeListener;

import java.io.File;
import java.util.ArrayList;

//汇总进行各项处理的dialog
public class BaseFileDialog {

    public static BaseFileDialog newInstance() {

        Logger.i("newInstance");
        BaseFileDialog dialog = new BaseFileDialog();
        return dialog;
    }

    public void createBaseDialog(final Context mContext, final NoteBean noteBean , final int pos) {

        ArrayList<String> entrys = new ArrayList<String>();
        entrys.add("分享文件");
        entrys.add("删除文件");
        entrys.add("重命名文件");

        final CharSequence[] items = entrys.toArray(new CharSequence[entrys.size()]);

        // File delete confirm
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.dialog_title_options));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {
                    startShareFileDialog(mContext, noteBean);
                } else if (item == 1) {
                    startDeleteFileDialog(mContext,noteBean,pos);
                } else if (item == 2) {
//                    TODO 重命名文件
                }
            }
        });
        builder.setCancelable(true);
        builder.setNegativeButton(mContext.getString(R.string.dialog_action_cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    //    具体分享内容
    public void startShareFileDialog(Context mContext, NoteBean noteBean) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        Uri fileUri = FileProvider.getUriForFile(mContext, "com.study.notepad.fileprovider", new File(noteBean.getmFilePath()));
        //  FIXME Android API 24 文件保护与之前版本不同
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        } else {
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(noteBean.getmFilePath())));
        }
        shareIntent.setType("audio/mp4");
        mContext.startActivity(Intent.createChooser(shareIntent, mContext.getText(R.string.send_to)));
    }

    public void startDeleteFileDialog (final Context mContext, final NoteBean noteBean, final int pos) {
        // File delete confirm
        AlertDialog.Builder confirmDelete = new AlertDialog.Builder(mContext);
        confirmDelete.setTitle(mContext.getString(R.string.dialog_title_delete));
        confirmDelete.setMessage(mContext.getString(R.string.dialog_text_delete));
        confirmDelete.setCancelable(true);
        confirmDelete.setPositiveButton(mContext.getString(R.string.dialog_action_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            //删掉文件从数据，本地地方，以及通过recycleView刷新机制来通知数据减少
                            remove(mContext,noteBean,pos);

                        } catch (Exception e) {
                            Logger.e("exception", e);
                        }

                        dialog.cancel();
                    }
                });
        confirmDelete.setNegativeButton(mContext.getString(R.string.dialog_action_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = confirmDelete.create();
        alert.show();
    }

    public void remove(Context mContext,NoteBean noteBean,int pos){
        //delete file from storage
        File file = new File(noteBean.getmFilePath());
        file.delete();

        Toast.makeText(
                mContext,
                String.format(
                        mContext.getString(R.string.toast_file_delete),
                        noteBean.getmName()
                ),
                Toast.LENGTH_SHORT
        ).show();

        DataBaseUtil dataBaseUtil = new DataBaseUtil(mContext);
        Logger.i(String.valueOf(noteBean.getmId()));
        dataBaseUtil.delSpeechContent(noteBean.getmId());
    }





}
