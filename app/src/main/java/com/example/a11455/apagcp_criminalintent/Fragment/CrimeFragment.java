package com.example.a11455.apagcp_criminalintent.Fragment;

/**
 * 代码清单 7-9 导入支持库版Fragment
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.a11455.apagcp_criminalintent.Crime.Crime;
import com.example.a11455.apagcp_criminalintent.R;

import java.util.Date;
import java.util.UUID;

import static com.example.a11455.apagcp_criminalintent.Crime.CrimeLab.get;

/**
 * 代码清单 7-8 继承Fragment类
 */
public class CrimeFragment extends Fragment {

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
        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = get(getActivity()).getCrime(crimeID);
    }

    /**
     * 代码清单 7-11 覆盖onCreateView(···)方法
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);


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
        mDate = new Date();
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        CharSequence mDateCharSequence = android.text.format.DateFormat.format("yyyy年,MMMM,dd日,kk:mm:ss,EEEE", mDate);
        mDateButton.setText(mDateCharSequence);
        /*
         代码清单 12-3 显示DialogFragment- 2
        */
        // mDateButton.setEnabled(false);

        //------------------------------------------------------
        //自行添加的
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
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


}
