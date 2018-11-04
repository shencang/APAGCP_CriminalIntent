package com.example.a11455.apagcp_criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 代码清单8-1 创建单例
 */



public class CrimeLab {

    private  static CrimeLab sCrimeLab;

    /**
     * 代码清单8-2 创建可容纳Crime对象的List -1
     */

    private List<Crime> mCrimes;

    public  static CrimeLab get(Context context){
       if (sCrimeLab==null){
           sCrimeLab = new CrimeLab(context);

       }
       return sCrimeLab;
    }

    private CrimeLab(Context context){


        /**
         * 代码清单8-2 创建可容纳Crime对象的List -2
         */
        mCrimes = new ArrayList<>();

    }


    /**
     * 代码清单8-2 创建可容纳Crime对象的List -3
     */
    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime crime:mCrimes){
            if (crime.getId().equals(id)){
                return  crime;
            }
        }
        return  null;
    }
}
