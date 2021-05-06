package com.example.addressbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=2;
    public DBHelper(Context context) {super(context, "addressdb", null, DATABASE_VERSION); }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String addressSQL = "create table ad_db(" +
                "_id integer primary key autoincrement," +
                "name text," +
                "content text)" ;
        db.execSQL(addressSQL);

        db.execSQL("insert into ad_db (name, content) values ('홍길동', '010-000-0000')");
        db.execSQL("insert into ad_db (name, content) values ('홍길미', '010-000-0001')");
        db.execSQL("insert into ad_db (name, content) values ('홍길남', '010-000-0002')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table ad_db");
            onCreate(db);
        }
    }
}
