package com.example.s.zmood;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static void sendRequestWithOkhttp(String address,String param,okhttp3.Callback callback)
    {
        RequestBody requestBody = RequestBody.create(JSON, param);
//        System.out.println(requestBody.toString());
        OkHttpClient client=new OkHttpClient();
        Request request = new Request.Builder()
            .post(requestBody)
            .url(address)
            .build();
        client.newCall(request).enqueue(callback);
    }
}
