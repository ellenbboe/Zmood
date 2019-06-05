package com.example.s.zmood.entity;

import com.example.s.zmood.R;

import java.util.Date;
import java.util.Random;

public class NoteEntity {
    private String title;
    private String date;
    private String description;
    private int imageResourceId;
    private int[] images = {R.drawable.noteimage1,R.drawable.noteimage2,R.drawable.noteimage3,
            R.drawable.noteimage4,R.drawable.noteimage5,R.drawable.noteimage6};
//    private int sort;


    public NoteEntity(String title, String date, String description, int imageResourceId) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }
    public NoteEntity(String title, String description) {
        this.title = title;
        Date date = new Date();
        this.date = date.toString();
        this.description = description;
        Random random = new Random();
        int index = random.nextInt(images.length);
        this.imageResourceId = images[index];
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }


}
