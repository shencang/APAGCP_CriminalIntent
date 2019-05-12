package com.example.a11455.apagcp_criminalintent.CrimeOperation.DateBase;

/*
 代码清单 14-13 创建CrimeCursorWrapper
 */
import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.a11455.apagcp_criminalintent.Model.Crime;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    /*
    代码清单 14-14 新增getCrime（）方法
     */
    public Crime getCrime(){
        String uuidString = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title  = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        long date     = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));
        int isSolved  = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SOLVED));
        return null;
    }
}
