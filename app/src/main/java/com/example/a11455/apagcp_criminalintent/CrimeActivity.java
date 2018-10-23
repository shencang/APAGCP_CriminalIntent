package com.example.a11455.apagcp_criminalintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 *代码清单7-2修改模板代码
 * CrimeActivity的超类改为AppCompatActivity
 */

public class CrimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
    }
}
