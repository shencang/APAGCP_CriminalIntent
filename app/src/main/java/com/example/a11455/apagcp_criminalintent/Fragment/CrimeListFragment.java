package com.example.a11455.apagcp_criminalintent.Fragment;

    /*
    代码清单 8-9 清理CrimeActivity类-3
     */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a11455.apagcp_criminalintent.Activity.CrimePagerActivity;
import com.example.a11455.apagcp_criminalintent.CrimeOperation.CrimeLab;
import com.example.a11455.apagcp_criminalintent.Model.Crime;
import com.example.a11455.apagcp_criminalintent.R;

import java.util.List;
import java.util.Locale;

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

    /*
    代码清单 13-15 记录子标题状态
     */
    private boolean mSubtitleVisible;

    /*
    代码清单 13-19 保存子标题状态栏 -1
     */

    /*
      第十三章挑战 第三部分 ：用于RecyclerView的空视图
     */
    private TextView mEmptyTextView;
    private Button mAddBtn;
    private static final String SAVED_SUBTITLE = "subtitle";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.
                findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*
      第十三章挑战 第三部分 ：用于RecyclerView的空视图
        */
        mEmptyTextView = (TextView) view.findViewById(R.id.no_crime_in_view_tv);
        mAddBtn = (Button) view.findViewById(R.id.add_crime_btn);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
                startActivity(intent);
                updateUI();
            }
        });
        /*
        代码清单 13-19 保存子标题状态栏 -2
         */
        if (savedInstanceState != null) {

            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE);

        }



        /*
    代码清单 8-20 设置Adapter-1
        */
        updateUI();

        return view;

    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
         /*
         代码清单 10-9 在onResume()方法中刷新列表项-2
         */
          /*
      第十三章挑战 第三部分 ：用于RecyclerView的空视图
     */
        if (crimes.size() > 0) {
            mEmptyTextView.setVisibility(View.INVISIBLE);
            mAddBtn.setVisibility(View.INVISIBLE);
            mCrimeRecyclerView.setVisibility(View.VISIBLE);
            if (mAdapter == null) {
                mAdapter = new CrimeAdapter(crimes);
                mCrimeRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.setCrimes(crimes);
                mAdapter.notifyDataSetChanged();
                // mAdapter.notifyItemChanged();
            }
        } else {
            mCrimeRecyclerView.setVisibility(View.INVISIBLE);
            mEmptyTextView.setVisibility(View.VISIBLE);
            mAddBtn.setVisibility(View.VISIBLE);
        }


        /*
        代码清单13-18 显示最新状态
        */
        updateSubtitle();

    }
    /*
    代码清单 13-6 实例化选项菜单
     */

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);

        /*
        代码清单 13-16 更新菜单项-1
         */
        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    /*
    代码清单 13-7 调用setHasOptionMenu
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /*
    代码清单 13-10 响应菜单项选择事件
     */

    /*
    代码清单 13-14 响应SHOW SUBTITLE菜单项点击事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.new_crime: {
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
                startActivity(intent);
                return true;
            }
            case R.id.show_subtitle: {
                /*
               代码清单 13-16 更新菜单项-1
                */
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    /*
     代码清单13-13 设置工具栏子标题
     */
    private void updateSubtitle() {
         /*
         第十三章挑战 第二部分 ：复数字符串数组
        */
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        String subtitle;
        Locale locale = getActivity().getResources().getConfiguration().locale;
        String language = locale.getLanguage();

        if (language.endsWith("zh")) {
            subtitle = getString(R.string.subtitle_format, crimeCount);
        } else {
            subtitle = getResources()
                    .getQuantityString(R.plurals.subtitle_plural, crimeCount, crimeCount);
        }


        /*
        代码清单 13-17 实现菜单项标题与子标题的联动
         */
        if (mSubtitleVisible) {
            subtitle = null;
        }


        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    /*
   代码清单 10-9 在onResume()方法中刷新列表项-1
    */
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
     /*
        代码清单 13-19 保存子标题状态栏 -3
         */

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE, mSubtitleVisible);

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

        /*
        代码清单 14-20 添加setCrimes（List<Crime>)方法
         */
        public void setCrimes(List<Crime> crimes) {
            mCrimes = crimes;
        }
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
        public void onClick(View view) {

                    /*
                    代码清单10-1 启动CrimeActivity
                     */
            //Toast.makeText(getActivity(),mCrime.getTitle()+"clicked!",Toast.LENGTH_SHORT).show();

                   /*
                   代码清单 10-3 传递Crime实例
                    */
//                    Intent intent = new Intent(getActivity(),CrimeActivity.class);
//                    startActivity(intent);

            /*
            代码清单 11-4 配置启动CrimePagerActivity
             */
//            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);

        }


    }


}
