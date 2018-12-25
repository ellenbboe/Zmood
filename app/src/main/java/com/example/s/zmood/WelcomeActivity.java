package com.example.s.zmood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

//欢迎页
public class WelcomeActivity extends AppCompatActivity {
    public static WelcomeActivity instance = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final Intent intent = new Intent(this,MainActivity.class);
        instance=this;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent); //执行
            }
        };
        timer.schedule(task, 3000);
    }
}
