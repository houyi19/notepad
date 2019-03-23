package com.study.notepad.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.study.notepad.R;
import com.study.notepad.adapter.NoteContentAdapter;
import com.study.notepad.bean.NoteBean;

import java.util.ArrayList;

/**
 * Author by bier
 * Date on 2019/3/23.
 **/
public class NoteContentFragment extends Fragment {

    private static final String TAG = NoteContentFragment.class.getSimpleName();

    private RecyclerView mRecycler;
    private NoteContentAdapter mAdapter;
//    测试数据;
    private ArrayList<Integer>mTestModel = new ArrayList<>();
//    记事本数据;
    private static ArrayList<NoteBean>mTestModel1 = new ArrayList<>();


    public static NoteContentFragment newInstance(ArrayList<NoteBean> mModel) {
        Logger.d(mModel.toString());
        NoteContentFragment fragment = new NoteContentFragment();
        mTestModel1 = mModel;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Logger.d("onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        mTestModel.add(1);
        mTestModel.add(2);
        mTestModel.add(3);
        InitView(view);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.d("onCreateView");
        View v = inflater.inflate(R.layout.frag_note_content,container,false);
        return v;
    }

    @Override
    public void onStart() {
        Logger.d("onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Logger.d("onResume");
        super.onResume();
    }

    private void InitView(View  v) {
        mRecycler = v.findViewById(R.id.frag_main_content);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(linearLayoutManager);
        mAdapter = new NoteContentAdapter();
        mAdapter.addModel(mTestModel1);
        mRecycler.setAdapter(mAdapter);
    }
}
