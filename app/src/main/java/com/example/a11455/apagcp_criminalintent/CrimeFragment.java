package com.example.a11455.apagcp_criminalintent;

/**
 * 代码清单 7-9 导入支持库版Fragment
 */
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    /**
     * 代码清单 7-11 覆盖onCreateView(···)方法
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_crime,container,false);


        /**
         * 代码清单 7-12 生成并使用EditText组件-2
         */
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //这个地方留空
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                mCrime.setTitle(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

                //这个也是

            }
        });


        return v;
    }



}
