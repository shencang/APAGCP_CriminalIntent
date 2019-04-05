package com.example.a11455.apagcp_criminalintent;

    /*
    代码清单 8-9 清理CrimeActivity类-3
     */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
代码清单 8-11 实现CrimeListFragment
 */
public class CrimeListFragment extends Fragment {

    //Nothing yet

    /*
    代码清单 8-16 为CrimeListFragment配置视图
     */
    private RecyclerView mCrimeRecyclerView;

    /*
    代码清单 8-20 设置Adapter-1
     */
    private CrimeAdapter mAdapter;

    /*
    代码清单 9-3 控制图片显示-1
     */
    private ImageView mSolvedImageView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.
                findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*
    代码清单 8-20 设置Adapter-1
        */
        updateUI();

        return view;

    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    /*
    代码清单8-17 定义ViewHolder内部类
     */
        /*
    代码清单8-24 检测用户点击事件-1
     */
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /*
        代码清单 8-21 在构造方法中实例化视图组件-1
         */
        private TextView mTitleTextView;
        private TextView mDateTextView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));

              /*
    代码清单8-24 检测用户点击事件-2
     */
              itemView.setOnClickListener(this);


            /*
        代码清单 8-21 在构造方法中实例化视图组件-2
         */
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);

             /*
             代码清单 9-3 控制图片显示-2
              */
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);


        }

        /*
     代码清单 8-22 实现Bind(crime)方法
      */
        private Crime mCrime;

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());

             /*
                代码清单 9-3 控制图片显示-3
             */
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);


        }
                /*
                代码清单8-24 检测用户点击事件-3
                  */
                public void onClick(View view){

                    /*
                    代码清单10-1 启动CrimeActivity
                     */
                    //Toast.makeText(getActivity(),mCrime.getTitle()+"clicked!",Toast.LENGTH_SHORT).show();

                   /*
                   代码清单 10-3 传递Crime实例
                    */
//                    Intent intent = new Intent(getActivity(),CrimeActivity.class);
//                    startActivity(intent);
                    Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
                    startActivity(intent);

                }



    }

    /*
    代码清单8-18 创建Adapter内部类
     */
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            /*
            代码清单8-23 调用bind(Crime) 方法
             */
            Crime crime = mCrimes.get(position);
            holder.bind(crime);

        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }


}
