package com.example.s.zmood;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NoteActivity extends AppCompatActivity {

    public static final String NOTETITLE = "notetitle";
    public static final String IMAGEID = "noteimageid";
    public static final String NOTECONTEXT = "notecontext";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();
        String notetitle = intent.getStringExtra(NOTETITLE);
        int noteimageid = intent.getIntExtra(IMAGEID,0);
        String context = intent.getStringExtra(NOTECONTEXT);


        Toolbar toolbar = findViewById(R.id.activitynotetoolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.activitynoteCollapsintoolbar);
        ImageView noteimage = findViewById(R.id.activitynoteimageview);
        TextView note = findViewById(R.id.activitynotetextview);
        setSupportActionBar(toolbar);
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!= null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(notetitle);
        Glide.with(this).load(noteimageid).into(noteimage);

        note.setText(context);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
