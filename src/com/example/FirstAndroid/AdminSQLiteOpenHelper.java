package com.example.FirstAndroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dzuniga on 22/02/14.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL("create table votantes(dni integer primary key, nombre text, colegio text, nomesa integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _old, int _new) {
        _db.execSQL("drop table if exists votantes");
        _db.execSQL("create table votantes(dni integer primary key, nombre text, colegio text, nomesa integer)");
    }
}
