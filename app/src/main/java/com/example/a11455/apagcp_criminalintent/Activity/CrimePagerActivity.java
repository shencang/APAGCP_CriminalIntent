package com.example.a11455.apagcp_criminalintent.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.a11455.apagcp_criminalintent.CrimeOperation.CrimeLab;
import com.example.a11455.apagcp_criminalintent.Fragment.CrimeFragment;
import com.example.a11455.apagcp_criminalintent.Model.Crime;
import com.example.a11455.apagcp_criminalintent.R;

import java.util.List;
import java.util.UUID;

/*
  代码清单11-1 创建ViewPager
 */

public class CrimePagerActivity extends AppCompatActivity {

    /*
    代码清单 11-3 创建newIntent方法-1
     */
    private static final String EXTRA_CRIME_ID = "com.example.a11455.apagcp.criminalintent.crime_id";

    /*
    代码清单11-2 设置pager adapter -1
    */
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    /*
    代码清单 11-3 创建newIntent方法-2
     */
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;

    }

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

        /*
    代码清单 11-3 创建newIntent方法-3
     */
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);



    /*
    代码清单 11-6 设置初始分页显示项
     */
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
