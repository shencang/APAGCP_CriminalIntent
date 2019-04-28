package com.example.a11455.apagcp_criminalintent.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.a11455.apagcp_criminalintent.Crime.Crime;
import com.example.a11455.apagcp_criminalintent.Crime.CrimeLab;
import com.example.a11455.apagcp_criminalintent.Fragment.CrimeFragment;
import com.example.a11455.apagcp_criminalintent.R;

import java.util.List;

/*
  代码清单11-1 创建ViewPager
 */

public class CrimePagerActivity extends AppCompatActivity {

    /*
    代码清单11-2 设置pager adapter -1
    */
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        /*
        代码清单11-2 设置pager adapter -2
        */
        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

    }
}
