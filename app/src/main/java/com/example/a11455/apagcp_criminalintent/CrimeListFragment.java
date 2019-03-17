package com.example.a11455.apagcp_criminalintent;

    /*
    代码清单 8-9 清理CrimeActivity类-3
     */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
代码清单 8-11 实现CrimeListFragment
 */
public class CrimeListFragment  extends Fragment {

    //Nothing yet

    /*
    代码清单 8-16 为CrimeListFragment配置视图
     */
    private RecyclerView mCrimeRecyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);

        mCrimeRecyclerView = (RecyclerView)view.
                findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;

    }
}
