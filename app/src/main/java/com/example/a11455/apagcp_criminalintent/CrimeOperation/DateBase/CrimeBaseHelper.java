package com.example.a11455.apagcp_criminalintent.CrimeOperation.DateBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a11455.apagcp_criminalintent.CrimeOperation.DateBase.CrimeDbSchema.CrimeTable;
import com.example.a11455.apagcp_criminalintent.Model.Crime;

/*
 代码清单14-3 创建CrimeBaseHelper类
 */
public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION =1 ;
    private static final String DATABASE_NAME = "crimeBase.db";

    public  CrimeBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
        代码清单 14-5 编写SQL创建初始代码
         */
         /*
        代码清单 14-6 创建crime数据表
         */
       // sqLiteDatabase.execSQL("create table "+ CrimeTable.NAME);
        sqLiteDatabase.execSQL(
                "create table "
                +CrimeTable.NAME
                +"("
                +"_id integer primary key autoincrement,"
                +CrimeTable.Cols.UUID
                +","
                +CrimeTable.Cols.TITLE
                +","
                + CrimeTable.Cols.DATE
                +","
                +CrimeTable.Cols.SOLVED
                /*
                代码清单 15-4 添加嫌疑人数据库字段
                 */
                +","
                + CrimeTable.Cols.SUSPECT
                +")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
