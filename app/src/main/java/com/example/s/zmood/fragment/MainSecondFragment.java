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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.s.zmood.HttpUtil;
import com.example.s.zmood.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public class MainSecondFragment extends Fragment {

    private Handler handler;
    private TextView answer;
    private Button sendbutton;
    private String answers;
    private EditText question;
    private String param;
    JSONObject jsonObject = new JSONObject();
    JSONObject perception = new JSONObject();
    JSONObject inputText = new JSONObject();
    JSONObject userInfo = new JSONObject();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        return view;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        answer = view.findViewById(R.id.answer);
        question = view.findViewById(R.id.Textsend);
        sendbutton = view.findViewById(R.id.send);
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    userInfo.put("apiKey","90c391a54e4741759d1abd84a0f45f7a");
                    userInfo.put("userId","317852");
                    inputText.put("text",question.getText().toString());
                    perception.put("inputText",inputText);
                    jsonObject.put("reqType",0);
                    jsonObject.put("perception",perception);
                    jsonObject.put("userInfo",userInfo);
                    param = jsonObject.toString();
                    send();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        handler = new Handler();
        super.onViewCreated(view, savedInstanceState);
    }

    private void send(){
        HttpUtil.sendRequestWithOkhttp("http://openapi.tuling123.com/openapi/api/v2",param,new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                parseJsonWithJsonObject(response);
                handler.post(runnable);
            }
        });
    }

Runnable runnable = new Runnable() {
    @Override
    public void run() {
        answer.setText(answers);
    }
};


    private void parseJsonWithJsonObject(Response response) throws IOException {
        String responseData=response.body().string();
        try {
        JSONObject jsonObject = new JSONObject(responseData);
        JSONObject results = jsonObject.getJSONArray("results").getJSONObject(0);
        JSONObject values = results.getJSONObject("values");
        answers = values.getString("text");
//        System.out.println(answers);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
