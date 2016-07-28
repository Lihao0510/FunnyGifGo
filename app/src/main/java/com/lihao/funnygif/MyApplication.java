package com.lihao.funnygif;

import android.app.Application;
import android.os.Environment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.File;

/**
 * Created by Administrator on 2016/7/24.
 */
public class MyApplication extends Application {

    public static RequestQueue mQueue;
    public static boolean isLoadPic;
    public static File supFile;


    @Override
    public void onCreate() {
        super.onCreate();
        mQueue = Volley.newRequestQueue(getApplicationContext());
        isLoadPic = getSharedPreferences("user_setting",MODE_PRIVATE).getBoolean("showpic",true);
        supFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/gifcaches");
        if (!supFile.exists()){
            supFile.mkdir();
        }
    }

    public static RequestQueue getRequestQueue() {
        return mQueue;
    }
}
