package com.example.s.zmood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.s.zmood.entity.NoteEntity;

public class MydatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "content.db";
    public static final String TABLE_NAME = "content";
    public static final int DB_VERSION = 1;

    public MydatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + TABLE_NAME
                + " (_id integer primary key autoincrement, "
                + "title text not null, "
                +"date text,"
                + "content text,"
                +"imageid integer);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public long insert(NoteEntity entity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", entity.getTitle());
        values.put("content", entity.getDescription());
        values.put("date", entity.getDate());
        values.put("imageid", entity.getImageResourceId());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public Cursor query() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public Cursor query(String name) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select * from contact where _name = ?",new String[]{name});
    }

    public void deleteDate(String date)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,NoteActivity.NOTEDATE+"=?",new String[]{date});
    }
}
