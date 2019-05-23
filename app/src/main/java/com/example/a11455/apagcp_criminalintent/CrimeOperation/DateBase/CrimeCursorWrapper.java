package com.example.a11455.apagcp_criminalintent.CrimeOperation.DateBase;

/*
 代码清单 14-13 创建CrimeCursorWrapper
 */

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.a11455.apagcp_criminalintent.Model.Crime;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    /*
    代码清单 14-14 新增getCrime（）方法
     */
    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SOLVED));
//        return null;
        /*
        代码清单 15-6 读取嫌疑人信息-1
         */
        String suspect = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SUSPECT));

        /*
        代码清单14-16 完成getCrime()方法
         */
        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        /*
        代码清单 15-6 读取嫌疑人信息-2
         */
        crime.setmSuspect(suspect);

        return crime;
    }
}
