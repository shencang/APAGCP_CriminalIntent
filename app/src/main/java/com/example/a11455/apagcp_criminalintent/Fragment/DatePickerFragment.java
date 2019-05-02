package com.example.a11455.apagcp_criminalintent.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.example.a11455.apagcp_criminalintent.R;

import java.util.Calendar;
import java.util.Date;

 /*
   代码清单 12-2 创建DialogFragment
 */

public class DatePickerFragment extends DialogFragment {

    /*
    代码清单 12-5 添加newInstance(Date)方法-1
     */
    private static final String AGE_DATE = "date";

    private DatePicker mDatePicker;

    /*
    代码清单 12-5 添加newInstance(Date)方法-2
     */
    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(AGE_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        /*
        代码清单 12-7 获取Date对象并初始化DatePicker-1
         */
        Date date = (Date) getArguments().getSerializable(AGE_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);



        /*
        代码清单 12-4 给AlterDialog添加DatePicker-1
         */
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        //return super.onCreateDialog(savedInstanceState);


        /*
        代码清单 12-7 获取Date对象并初始化DatePicker-2
         */
        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);
        /*
        代码清单 12-4 给AlterDialog添加DatePicker-2
         */
        return new AlertDialog
                .Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();

    }
}
