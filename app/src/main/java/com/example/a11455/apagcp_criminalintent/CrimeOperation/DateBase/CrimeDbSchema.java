package com.example.a11455.apagcp_criminalintent.CrimeOperation.DateBase;
/*
   代码清单14-1 定义Crime内部类(包含前部定义)
 */


public class CrimeDbSchema {

    public static final class CrimeTable {
        public static final String NAME = "crimes";

        /*
        代码清单 14-2 定义数据表字段
         */
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";

            /*
            代码清单 15-3 添加嫌疑人数据库字段
             */
            public static final String SUSPECT = "suspect";
        }
    }
}
