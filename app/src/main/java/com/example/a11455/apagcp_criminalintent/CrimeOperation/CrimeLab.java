package com.example.a11455.apagcp_criminalintent.CrimeOperation;

import android.content.Context;

import com.example.a11455.apagcp_criminalintent.Model.Crime;

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

        /**
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

    /*
    代码清单 13-8 添加新的crime
    */
    public void addCrime(Crime crime) {

        mCrimes.add(crime);
    }

    /*
     第十三章挑战 第一部分 ：删除crime记录
     */
    public void  deleleCrime(Crime crime){

        mCrimes.remove(crime);
    }

}
