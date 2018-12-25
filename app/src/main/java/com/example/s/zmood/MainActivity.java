package com.example.s.zmood;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.s.zmood.adapter.NoteAdapter;
import com.example.s.zmood.entity.NoteEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NoteEntity[] notes={
            new NoteEntity("品味故乡","2018-08-01","很少回乡，乡下我认识的人越来越少了，我不认识的人越来越多了，叫我爷爷的人开始有了，我叫爷爷的人都没了....",R.drawable.noteimage1)
            ,new NoteEntity("秋意","2018-01-05","朝阳润红了天边的云霞，带着清新，带着炫灿，舒适感顿时传遍全身，似漫步云端。远处的群山清晰可见，连绵起伏。草丛里蟋蟀嗤嗤鸣叫，夹杂着...",R.drawable.noteimage2)
            ,new NoteEntity("三六九等","2018-11-01","贾平凹在小说《废都》中这样说:“一等公民是公仆，祖孙三代都幸福;二等公民搞承包，吃喝嫖赌全报销...",R.drawable.noteimage3)
            ,new NoteEntity("生死轮回间","2018-01-07","始终相信，飘缈的未必是虚幻的，那是因为，人们追索过，却不曾真正触摸过。更加相信，那些飘缈的林林总总，定会有一天在众生的苦苦寻绎下...",R.drawable.noteimage4)
            ,new NoteEntity("那场秋风","2017-03-01","一轻飘柳，荡漾着秋风。丝丝凉意里几朵灰白色的絮花，上下晃动起舞....",R.drawable.noteimage5)
            ,new NoteEntity("山林情怀","2018-02-01","因为从小生长在一个依山傍水的湖滨小城，所以热爱山的巍峨叠嶂，热爱水的烟波浩渺，热爱自然的变幻旖...",R.drawable.noteimage6)
    };
    private List<NoteEntity> noteslist = new ArrayList<>();
    private NoteAdapter noteAdapter;
    @Override//显示menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Toast.makeText(this,"you click add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"you click setting",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
                default:
        }
        return  true;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WelcomeActivity.instance.finish();//关掉欢迎页
        Toolbar toolbar = findViewById(R.id.activitymaintoolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.activitymaindrawerlayout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navigationView.setCheckedItem(R.id.acticle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
        initnotes();
        RecyclerView recyclerView = findViewById(R.id.recyclr_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        noteAdapter = new NoteAdapter(noteslist);
        recyclerView.setAdapter(noteAdapter);

////        ArrayAdapter<NoteEntity> listAdapter = new ArrayAdapter<NoteEntity>(this,)
//        listView = findViewById(R.id.listview);
//
//        notes = new ArrayList<>();
//        notes.add(new NoteEntity("夏至","2018-02-01","夏天时我在郊外",R.drawable.noteheadtest));
//        notes.add(new NoteEntity("食人一族","2018-03-01","张鎏太难吃了",R.drawable.noteheadtest));
//        notes.add(new NoteEntity("娃哈哈","2018-01-05","娃哈哈集团总经理入狱",R.drawable.noteheadtest));
//        notes.add(new NoteEntity("谁说","2018-01-07","我是一个买烧饼的",R.drawable.noteheadtest));
//        notes.add(new NoteEntity("虫师","2018-08-01","巴拉拉拉黑魔法",R.drawable.noteheadtest));
//        notes.add(new NoteEntity("中国式家长","2018-11-01","我是王晨涛",R.drawable.noteheadtest));
//        myListAdapter = new MyListAdapter(notes,MainActivity.this);
//        listView.setAdapter(myListAdapter);
//
//        FloatingActionButton addnote = findViewById(R.id.floatingActionButton);
//
//        addnote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddNoteFragment addNoteFragment = new AddNoteFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.activitymain,addNoteFragment).commit();
//            }
//        });
        swipeRefreshLayout = findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshnotes();
            }
        });

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
                runOnUiThread(new Runnable() {
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
    private void initnotes(){
            noteslist.clear();
            for(int i = 0;i<30;i++)
            {
                Random random = new Random();
                int index = random.nextInt(notes.length);
                noteslist.add(notes[index]);
            }
}
}
