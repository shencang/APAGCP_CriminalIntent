package com.example.a11455.apagcp_criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * 代码清单7-3
 * Crime类的新增代码
 */
public class Crime {
    private UUID    mId;
    private String  mTitle;
    private Date    mDate;
    private boolean mSolved;

    public Crime(){
        mId   = UUID.randomUUID();
        mDate = new Date();
    }


    /**
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
}
