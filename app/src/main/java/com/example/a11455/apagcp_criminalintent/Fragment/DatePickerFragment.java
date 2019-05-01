package com.example.a11455.apagcp_criminalintent.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.example.a11455.apagcp_criminalintent.R;

 /*
   代码清单 12-2 创建DialogFragment
 */

public class DatePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        /*
        代码清单 12-4 给AlterDialog添加DatePicker-1
         */
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        //return super.onCreateDialog(savedInstanceState);

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
