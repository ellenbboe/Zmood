package com.example.s.zmood.fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.s.zmood.MydatabaseHelper;
import com.example.s.zmood.R;
import com.example.s.zmood.adapter.NoteAdapter;
import com.example.s.zmood.entity.NoteEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.support.constraint.Constraints.TAG;

public class MainFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    MydatabaseHelper dbhelper;
//    private NoteEntity[] notes={
//            new NoteEntity("品味故乡","2018-08-01","很少回乡，乡下我认识的人越来越少了，我不认识的人越来越多了，叫我爷爷的人开始有了，我叫爷爷的人都没了....",R.drawable.noteimage1)
//            ,new NoteEntity("那场秋风","2017-03-01","一轻飘柳，荡漾着秋风。丝丝凉意里几朵灰白色的絮花，上下晃动起舞....",R.drawable.noteimage5)
//            ,new NoteEntity("山林情怀","2018-02-01","因为从小生长在一个依山傍水的湖滨小城，所以热爱山的巍峨叠嶂，热爱水的烟波浩渺，热爱自然的变幻旖...",R.drawable.noteimage6)
//    };
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
        dbhelper = new MydatabaseHelper(view.getContext());
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

    
    @Override
    public void onStart() {
        super.onStart();
        initnotes();
        noteAdapter.notifyDataSetChanged();
        Log.d(TAG, "onStart: refresh");
    }

    private void initnotes(){
        noteslist.clear();
        try{
        Cursor c = dbhelper.query();
        while(c.moveToNext())
        {
            NoteEntity one = new NoteEntity(c.getString(c.getColumnIndex("title")),
                    c.getString(c.getColumnIndex("date")),
                    c.getString(c.getColumnIndex("content")),
                    c.getInt(c.getColumnIndex("imageid"))
                    );
            noteslist.add(one);
        }
        }catch (NullPointerException e)
        {
            Log.d(TAG, "initnotes: exception");
        }

//        for(int i = 0;i<30;i++)
//        {
//            Random random = new Random();
//            int index = random.nextInt(notes.length);
//            noteslist.add(notes[index]);
//        }
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
