package com.example.s.zmood.entity;

public class NoteEntity {
    private String title;
    private String date;
    private String description;
    private int imageResourceId;


    public NoteEntity(String title, String date, String description, int imageResourceId) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.imageResourceId = imageResourceId;
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
