package com.example.a11455.apagcp_criminalintent.Model;

import java.util.Date;
import java.util.UUID;

/*
 * 代码清单7-3
 * Crime类的新增代码
 */
public class Crime {
    private UUID    mId;
    private String  mTitle;
    private Date    mDate;
    private boolean mSolved;

    /*
    代码清单 15-2 添加nSuspect成员变量 - 1
     */
    private String mSuspect;

    public Crime(){

        /*
        代码清单 14-15 新增Crime构造方法
         */
//        mId   = UUID.randomUUID();
//        mDate = new Date();
        this(UUID.randomUUID());
    }

    public Crime(UUID id){
        mId =id;
        mDate =new Date();
    }


    /*
     * 代码清单7-4
     * 已生成的getter方法与setter方法
     */
    public UUID getId(){
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }


    /*
    代码清单 15-2 添加nSuspect成员变量 - 2
     */

    public String getmSuspect() {
        return mSuspect;
    }

    public void setmSuspect(String mSuspect) {
        this.mSuspect = mSuspect;
    }
}
