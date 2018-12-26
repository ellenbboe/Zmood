package com.example.s.zmood.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.s.zmood.R;
import com.example.s.zmood.adapter.NoteAdapter;
import com.example.s.zmood.entity.NoteEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainSecondFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private NoteEntity[] notes={
            new NoteEntity("秋意","2018-01-05","朝阳润红了天边的云霞，带着清新，带着炫灿，舒适感顿时传遍全身，似漫步云端。远处的群山清晰可见，连绵起伏。草丛里蟋蟀嗤嗤鸣叫，夹杂着...",R.drawable.noteimage2)
            ,new NoteEntity("三六九等","2018-11-01","贾平凹在小说《废都》中这样说:“一等公民是公仆，祖孙三代都幸福;二等公民搞承包，吃喝嫖赌全报销...",R.drawable.noteimage3)
            ,new NoteEntity("生死轮回间","2018-01-07","始终相信，飘缈的未必是虚幻的，那是因为，人们追索过，却不曾真正触摸过。更加相信，那些飘缈的林林总总，定会有一天在众生的苦苦寻绎下...",R.drawable.noteimage4)
    };
    private List<NoteEntity> noteslist = new ArrayList<>();
    private NoteAdapter noteAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclr_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        initnotes();
        noteAdapter = new NoteAdapter(noteslist);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(noteAdapter);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshnotes();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void initnotes(){
        noteslist.clear();
        for(int i = 0;i<30;i++)
        {
            Random random = new Random();
            int index = random.nextInt(notes.length);
            noteslist.add(notes[index]);
        }
    }


    private void refreshnotes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initnotes();
                        noteAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
