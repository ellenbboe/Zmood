package com.example.s.zmood;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class myProvider extends ContentProvider {
    private Context mcontext;
    MydatabaseHelper mdbHelper;
    SQLiteDatabase db=null;

    public static final String Autohority = "cn.cc.provider";

    @Override
    public boolean onCreate() {
        mcontext = getContext();
        mdbHelper = new MydatabaseHelper(getContext());
        db = mdbHelper.getWritableDatabase();
//        db.execSQL("delete from content");
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }
    @Override
    public String getType( Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri,  ContentValues values) {
        String table = MydatabaseHelper.TABLE_NAME;
        db.insert(table, null, values);
        mcontext.getContentResolver().notifyChange(uri, null);
        return uri;
    }


    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {
        return 0;
    }


    @Override
    public int update(Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {
        return 0;
    }
}
