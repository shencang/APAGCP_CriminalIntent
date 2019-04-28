package com.example.a11455.apagcp_criminalintent.Activity;

import android.support.v4.app.Fragment;

import com.example.a11455.apagcp_criminalintent.Fragment.CrimeListFragment;

/*
代码清单 8-9 清理CrimeActivity类-3
 */

/*
代码清单 8-10 实现CrimeListActivity
 */
public class CrimeListActivity  extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
