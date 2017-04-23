package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.search;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class MySql extends SQLiteOpenHelper {
    public MySql(Context context, String name,  int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "create table student(name varchar(30) primary key)";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
