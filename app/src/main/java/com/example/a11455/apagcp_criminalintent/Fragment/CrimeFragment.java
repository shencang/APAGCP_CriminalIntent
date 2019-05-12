package com.example.a11455.apagcp_criminalintent.Fragment;

/**
 * 代码清单 7-9 导入支持库版Fragment
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.a11455.apagcp_criminalintent.Activity.CrimePagerActivity;
import com.example.a11455.apagcp_criminalintent.CrimeOperation.CrimeLab;
import com.example.a11455.apagcp_criminalintent.Model.Crime;
import com.example.a11455.apagcp_criminalintent.R;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static com.example.a11455.apagcp_criminalintent.CrimeOperation.CrimeLab.get;

/**
 * 代码清单 7-8 继承Fragment类
 */
public class CrimeFragment extends Fragment {

    private static final String TAG = "CrimeFragment";
    /**
     * 代码清单 7-10 覆盖Fragment.onCrate(Bundle)方法
     */
    private Crime mCrime;
    /**
     * 代码清单 7-12 生成并使用EditText组件-1
     */
    private EditText mTitleField;
    /**
     * 代码清单 7-13 设置Button文字-1
     */
    private Button mDateButton;
    /**
     * 代码清单 7-14 监听CheckBox的变化-1
     */
    private CheckBox mSolvedCheckBox;

    /*
     第九章挑战
     */
    private Date mDate;

    /*
    代码清单 10-6 编写newInstance(UUID)方法-1
     */

    private static final String ARG_CRIME_ID = "crime_id";

    /*
    代码清单 12-3 显示DialogFragment- 1
     */
    private static final String DIALOG_DATE = "DialogDate";

    /*
    代码清单 12-8 设置目标fragment -1
     */
    private static final int REQUEST_DATE = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        代码清单10-4 获得extra数据并取得Crime对象
         */
        // mCrime = new Crime();

        /*
        代码清单 10-8 从argument获取数据
         */
//        UUID crimeID = (UUID) Objects.requireNonNull(getActivity()).
//                getIntent().
//                getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = get(getActivity()).getCrime(crimeId);


        /*
    第十三章挑战 第一部分 ：删除crime记录
     */
        setHasOptionsMenu(true);
    }

    /**
     * 代码清单 7-11 覆盖onCreateView(···)方法
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.
                inflate(R.layout.fragment_crime, container, false);


        /**
         * 代码清单 7-12 生成并使用EditText组件-2
         */
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        /*
        代码清单10-5 更新视图对象-1
         */
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                //这个地方留空
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mCrime.setTitle(s.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

                //这个也是

            }
        });
        /**
         * 代码清单 7-13 设置Button文字-2
         */



             /*
            第九章挑战
             */
        //  mDate = new Date();
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        updateDate();
        /*
         代码清单 12-3 显示DialogFragment- 2
        */
        // mDateButton.setEnabled(false);

        //------------------------------------------------------
        //自行添加的

        /*
         代码清单 12-3 显示DialogFragment- 3
        */
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                /*
                代码清单 12-6 添加newInstance()方法
                 */
                // DatePickerFragment dialog = new DatePickerFragment();

                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mCrime.getDate());
                /*
               代码清单 12-8 设置目标fragment -2
                */
                dialog.setTargetFragment(CrimeFragment.this,
                        REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        /**
         * 代码清单 7-14 监听CheckBox的变化-2
         */
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        /*
        代码清单10-5 更新视图对象-2
         */
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }

    /*
   代码清单 10-6 编写newInstance(UUID)方法-2
    */

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;

    }

    /*
    代码清单 12-11 响应DatePicker对话框
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
    }

    /*
    执行12-12后续的操作，内容12-13
     */
    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
        Log.e(TAG, mCrime.getDate().toString());
    }

    /*
    第十三章挑战 第一部分 ：删除crime记录
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_crime: {
//                Crime crime = new Crime();
//                CrimeLab.get(getActivity()).addCrime(crime);
//                CrimeLab.get(getActivity()).
//                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
//                startActivity(intent);
                CrimeLab.get(getActivity()).deleteCrime(mCrime);
                Objects.requireNonNull(getActivity()).finish();
                return true;
            }

            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_delete, menu);
    }

    /*
    代码清单14-11 Crime 数据刷新
     */
    @Override
    public void onPause() {
        super.onPause();

        CrimeLab.get(getActivity()).updateCrime(mCrime);
    }
}
