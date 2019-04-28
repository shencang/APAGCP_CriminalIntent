package com.example.a11455.apagcp_criminalintent.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.a11455.apagcp_criminalintent.Fragment.CrimeFragment;

import java.util.UUID;


/**
 *代码清单7-2修改模板代码
 * CrimeActivity的超类改为AppCompatActivity
 */

public class CrimeActivity extends SingleFragmentActivity {
    /*
    代码清单 10-2 创建newIntent方法-1
     */
    /*
    代码清单 10-7 使用newInstance(UUID)方法-1
     */
    private static final String EXTRA_CRIME_ID = "com.example.a11455.apagcp.criminalintent.crime_id";

    /*
    代码清单 8-9 清理CrimeActivity类-1
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

    /*
    代码清单 8-9 清理CrimeActivity类-2
     */
    /*
    代码清单 10-7 使用newInstance(UUID)方法-2
     */
    @Override
    protected Fragment createFragment(){
//        return new CrimeFragment();
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);

    }


    /*
    代码清单 10-2 创建newIntent方法-2
     */
    public static Intent newIntent(Context packageContext, UUID crimeId) {

        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

}
