package com.example.a11455.apagcp_criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 *代码清单7-2修改模板代码
 * CrimeActivity的超类改为AppCompatActivity
 */

public class CrimeActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }

    /*
    代码清单 8-9 清理CrimeActivity类
     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        /*
//        代码清单8-5 为CrimeActivity更新布局文件引用
//         */
//        setContentView(R.layout.activity_fragment);
//
//
//        /**
//         * 代码清单7-15 获取FragmentManager
//         */
//        FragmentManager fm = getSupportFragmentManager();
//
//        /**
//         * 代码清单7-16 添加一个CrimeFragment
//         */
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//
//        if (fragment==null){
//            /*
//            代码清单8-6 近乎通用的CrimeActivity类
//             */
//            fragment = new CrimeFragment();
//            fm.beginTransaction()
//                    .add(R.id.fragment_container,fragment)
//                    .commit();
//        }
//    }

}
