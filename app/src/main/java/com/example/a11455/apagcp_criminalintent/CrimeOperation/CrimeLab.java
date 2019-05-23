package com.example.a11455.apagcp_criminalintent.CrimeOperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a11455.apagcp_criminalintent.CrimeOperation.DateBase.CrimeBaseHelper;
import com.example.a11455.apagcp_criminalintent.CrimeOperation.DateBase.CrimeCursorWrapper;
import com.example.a11455.apagcp_criminalintent.Model.Crime;
import com.example.a11455.apagcp_criminalintent.CrimeOperation.DateBase.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * 代码清单8-1 创建单例
 */



public class CrimeLab {

    private  static CrimeLab sCrimeLab;

    /*
     * 代码清单8-2 创建可容纳Crime对象的List -1
     */

    /*
    代码清单 14-7 删除mCrimes相关代码 -1
     */
//    private List<Crime> mCrimes;

    /*
    代码清单 14-4 打开SQLiteDateBase -1
     */
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public  static CrimeLab get(Context context){
       if (sCrimeLab==null){
           sCrimeLab = new CrimeLab(context);

       }
       return sCrimeLab;
    }

    private CrimeLab(Context context){

         /*
        代码清单 14-4 打开SQLiteDateBase -2
        */
        mContext =context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

        /*
         * 代码清单8-2 创建可容纳Crime对象的List -2
         */
        /*
    代码清单 14-7 删除mCrimes相关代码 -2
     */
//        mCrimes = new ArrayList<>();

        /*
         * 代码清单8-3 生成100个crime
         */
        /*
        代码清单 13-9 再见，随机crime记录！
         */
//        for (int i=0;i<100;i++){
//            Crime crime = new Crime();
//            crime.setTitle("Crime #"+i);
//            crime.setSolved(i%2==0);//Every other one
//            mCrimes.add(crime);
        //}

    }


    /*
     * 代码清单8-2 创建可容纳Crime对象的List -3
     */
    public List<Crime> getCrimes() {
        /*
    代码清单 14-7 删除mCrimes相关代码 -3
     */
//        return mCrimes;
//        return new ArrayList<>();
    /*
    代码清单 14-18 返回crime列表
     */
    List<Crime>crimes =new ArrayList<>();
    CrimeCursorWrapper cursor = queryCrimes(null,null);

    try {
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            crimes.add(cursor.getCrime());
            cursor.moveToNext();
        }
    }finally {
        cursor.close();
    }
    return crimes;
    }

    public Crime getCrime(UUID id){
        /*
    代码清单 14-7 删除mCrimes相关代码 -4
     */
//        for (Crime crime:mCrimes){
//            if (crime.getId().equals(id)){
//                return  crime;
//            }
//        }
//        return  null;
    /*
    代码清单14-19 重写getCrime方法
     */
    CrimeCursorWrapper cursor = queryCrimes(
            CrimeTable.Cols.UUID+" = ? ",
            new String[]{id.toString()}
    );
    try {
        if (cursor.getCount()==0){
            return null;
        }
            cursor.moveToFirst();
            return cursor.getCrime();


    }finally {
        cursor.close();
    }

    }

    /*
    代码清单 13-8 添加新的crime
    */
    public void addCrime(Crime crime) {

        /*
    代码清单 14-7 删除mCrimes相关代码 -5
     */
//        mCrimes.add(crime);

        /*
        代码清单 14-9 插入记录
         */
        ContentValues values = getContentValues(crime);
        mDatabase.insert(CrimeTable.NAME,null,values);
    }

    /*
     第十三章挑战 第一部分 ：删除crime记录
     */
    public void  deleteCrime(Crime crime){

        /*
    代码清单 14-7 删除mCrimes相关代码 -1
     */
//        mCrimes.remove(crime);
        /*
        代码清单 14-9 插入记录(附加修改删除的代码)
         */
        ContentValues values = getContentValues(crime);
        mDatabase.delete(
                CrimeTable.NAME,
                CrimeTable.Cols.UUID+"= ? ",
                new String[]{crime.getId().toString()});
    }

    /*
    代码清单 14-8 创建ContentValues
     */
    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID,crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE,crime.getTitle());
        values.put(CrimeTable.Cols.DATE,crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED,crime.isSolved()?1:0);
        /*
        代码清单 15-5 写入嫌疑人信息
         */
        values.put(CrimeTable.Cols.SUSPECT,crime.getmSuspect());
        return values;
    }

    /*
    代码清单 14-10  更新记录
     */
    public void updateCrime(Crime crime){
        String uuidString =crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(
                CrimeTable.NAME,
                values,
                CrimeTable.Cols.UUID+" = ? ",
                new String[]{uuidString});
    }


    /*
    代码清单 14-12 查询crime记录
     */
    /*
    代码清单 14-17 使用Cursor 封装方法
     */
//    private Cursor queryCrimes(String whereClause ,String[] whereArgs){
    private CrimeCursorWrapper queryCrimes(String whereClause,String []whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null,//Columns - null selects all columns
                whereClause,
                whereArgs,
                null,//groupBy
                null,//having
                null//orderBy
        );
//        return cursor;
        return new CrimeCursorWrapper(cursor);
    }

}
