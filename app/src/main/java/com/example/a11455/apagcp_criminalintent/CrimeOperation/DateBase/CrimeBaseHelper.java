package com.example.a11455.apagcp_criminalintent.CrimeOperation.DateBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 代码清单14-3 创建CrimeBaseHelper类
 */
public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION =1 ;
    private static final String DATEBASE_NAME = "crimeBase.db";

    public  CrimeBaseHelper(Context context){
        super(context,DATEBASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
