package com.study.notepad.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.study.notepad.R;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/

//第一次时查询数据为空时显示此fragment
public class EmptyFragment extends Fragment {

    public static EmptyFragment newInstance() {
        EmptyFragment fragment = new EmptyFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i("onCreateView");
        View v = inflater.inflate(R.layout.frag_database_empty,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Logger.i("onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }
}
