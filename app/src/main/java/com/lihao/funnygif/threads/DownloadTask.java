package com.lihao.funnygif.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.lihao.funnygif.MyApplication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Administrator on 2016/7/27.
 */
public class DownloadTask extends AsyncTask<String, Void, String> {

    private GifImageView mImageView;
    private Context mContext;
    private File mFile;

    public DownloadTask(Context context, GifImageView imageView, File file) {
        mContext = context;
        mImageView = imageView;
        mFile = file;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            //File file = new File(mContext.getFilesDir().toString() + "/clse.gif");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(5000);
            InputStream in = conn.getInputStream();
            FileOutputStream fos = new FileOutputStream(mFile);
            byte[] b = new byte[1024 * 8];
            int len;
            while ((len = in.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.flush();
            in.close();
            fos.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return mFile.getName();
    }

    @Override
    protected void onPostExecute(String s) {
        String baseUrl = MyApplication.supFile.toString();
        String fileUrl = baseUrl + "/" + s;
        File f = new File(fileUrl);
        boolean b = f.exists();
        Log.d("Lihao", fileUrl + ":::" + b);
    }
}
