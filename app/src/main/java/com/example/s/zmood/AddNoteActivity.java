package com.example.s.zmood;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.s.zmood.entity.NoteEntity;

/*
需要输入的:
         标题
         正文
         配图
需要选择的:
        所属分类
 */
public class AddNoteActivity extends AppCompatActivity {
    MydatabaseHelper dbhelp = null;
    EditText title;
    EditText content;
    Toolbar toolbar;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addnotetoolbar, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        dbhelp = new MydatabaseHelper(this);
        toolbar = findViewById(R.id.addnotetoolbar);
        title = findViewById(R.id.addcontenttitle);
        content = findViewById(R.id.addnotecontext);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save:
                NoteEntity one =new NoteEntity(title.getText().toString(),content.getText().toString());
                dbhelp.insert(one);
                Toast.makeText(this,"save the item",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case android.R.id.home:
                finish();
                break;
//            case R.id.changimage:
//                Toast.makeText(this,"改变图片",Toast.LENGTH_SHORT).show();
//                break;
            default:
        }
        return true;
    }
}
