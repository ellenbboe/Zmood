package com.example.s.zmood.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.s.zmood.HttpUtil;
import com.example.s.zmood.R;
import com.example.s.zmood.adapter.NoteAdapter;
import com.example.s.zmood.entity.NoteEntity;
import com.example.s.zmood.entity.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainSecondFragment extends Fragment {

//    final OkHttpClient client = new OkHttpClient();
    private weather weather;
    private Handler handler;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView weatherText;
//    private NoteEntity[] notes={
//            new NoteEntity("秋意","2018-01-05","朝阳润红了天边的云霞，带着清新，带着炫灿，舒适感顿时传遍全身，似漫步云端。远处的群山清晰可见，连绵起伏。草丛里蟋蟀嗤嗤鸣叫，夹杂着...",R.drawable.noteimage2)
//            ,new NoteEntity("三六九等","2018-11-01","贾平凹在小说《废都》中这样说:“一等公民是公仆，祖孙三代都幸福;二等公民搞承包，吃喝嫖赌全报销...",R.drawable.noteimage3)
//            ,new NoteEntity("生死轮回间","2018-01-07","始终相信，飘缈的未必是虚幻的，那是因为，人们追索过，却不曾真正触摸过。更加相信，那些飘缈的林林总总，定会有一天在众生的苦苦寻绎下...",R.drawable.noteimage4)
//    };
//    private List<NoteEntity> noteslist = new ArrayList<>();
//    private NoteAdapter noteAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        return view;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        RecyclerView recyclerView = view.findViewById(R.id.recyclr_view);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        weatherText = view.findViewById(R.id.weather);
        handler = new Handler();
        init();
//        noteAdapter = new NoteAdapter(noteslist);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setAdapter(noteAdapter);
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
        init();
    }

    private void init(){
//        noteslist.clear();
//        for(int i = 0;i<30;i++)
//        {
//            Random random = new Random();
//            int index = random.nextInt(notes.length);
//            noteslist.add(notes[index]);
//        }

        HttpUtil.sendRequestWithOkhttp("https://free-api.heweather.net/s6/weather/now?location=hangzhou&key=823b446b4bba46299db468b9ca0f2e6a",new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                parseJsonWithJsonObject(response);
                handler.post(runnable);
            }
        });


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Request request = new Request.Builder().get().tag(this).url("https://free-api.heweather.net/s6/weather/now?location=hangzhou&key=823b446b4bba46299db468b9ca0f2e6a").build();
//                try {
//                    Response response = client.newCall(request).execute();
//                    if(response.isSuccessful())
//                    {
//                        System.out.println("body: "+response.body().string());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();



    }

Runnable runnable = new Runnable() {
    @Override
    public void run() {
        weatherText.setText(weather.toString());
    }
};
    private void refreshnotes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        init();
//                        noteAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void parseJsonWithJsonObject(Response response) throws IOException {
        String responseData=response.body().string();
        try {
        JSONObject jsonObject = new JSONObject(responseData);
        JSONObject HeWeather6 = jsonObject.getJSONArray("HeWeather6").getJSONObject(0);
        JSONObject basic = HeWeather6.getJSONObject("basic");
        String location = basic.getString("location");
        String nowcondtxt = HeWeather6.getJSONObject("now").getString("cond_txt");//tianqi
        String nowtmp = HeWeather6.getJSONObject("now").getString("tmp");//wendu
        String nowfl = HeWeather6.getJSONObject("now").getString("fl");//tiganwendu
            weather = new weather(location,nowtmp,nowfl,nowcondtxt);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
