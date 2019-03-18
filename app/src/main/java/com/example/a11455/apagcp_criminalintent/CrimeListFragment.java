package com.example.a11455.apagcp_criminalintent;

    /*
    代码清单 8-9 清理CrimeActivity类-3
     */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/*
代码清单 8-11 实现CrimeListFragment
 */
public class CrimeListFragment  extends Fragment {

    //Nothing yet

    /*
    代码清单 8-16 为CrimeListFragment配置视图
     */
    private RecyclerView mCrimeRecyclerView;

    /*
    代码清单 8-20 设置Adapter-1
     */
    private CrimeAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);

        mCrimeRecyclerView = (RecyclerView)view.
                findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*
    代码清单 8-20 设置Adapter-1
        */
        updateUI();

        return view;

    }

    private void updateUI() {
        CrimeLab crimeLab =CrimeLab.get(getActivity());
        List<Crime>crimes = crimeLab.getCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    /*
    代码清单8-17 定义ViewHolder内部类
     */
    private class CrimeHolder extends RecyclerView.ViewHolder{
        public CrimeHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_crime,parent,false));
        }
    }

    /*
    代码清单8-18 创建Adapter内部类
     */
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime>mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes =crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
