package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.search;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class MyManager {
    private MySql mySql;
    private SQLiteDatabase mDB;
    private Context context;
    private final String DB_NAME = "lwx.db";
    private final int DB_VERSION = 1;

    public MyManager(Context context) {
        this.context = context;
        mySql = new MySql(context,DB_NAME,DB_VERSION);
        mDB = mySql.getWritableDatabase();
    }
    public Boolean insert(String name){
        Boolean boo ;
        ContentValues con = new ContentValues();
        con.put("name",name);
        long insert = mDB.insert("student",null,con);
        if(insert>0){
            boo = true;
        }else{
            boo = false;
        }
       return boo;
    }
    public ArrayList<String> getDBAll(){
        ArrayList<String> list = new ArrayList<String>();
        //String sql = select * from lwx;
        Cursor cursor = mDB.query("student", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));

            list.add(name);
        }
        return list;
    }
    public Boolean deleteRecord() {
        Boolean boo;
        int count = mDB.delete("student", null,null);
        if (count > 0) {
            boo = true;
        } else {
            boo = false;
        }
        return boo;
    }
}
