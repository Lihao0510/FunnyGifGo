package com.lihao.funnygif.views;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lihao.funnygif.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Administrator on 2016/7/27.
 */
public class GifDetailActivity extends Activity {

    private GifImageView mGifView;
    private TextView mTextView;
    private int mWidth, mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gifdetail_layout);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth();
        mHeight = mWidth * 7 / 10;
        wm = null;
        initViews();
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String name = intent.getStringExtra("name");
        int width = intent.getIntExtra("width", mWidth);
        int height = intent.getIntExtra("height", mHeight);
        Glide.with(this).load(url).asGif().override(mWidth,mWidth*height/width).diskCacheStrategy(DiskCacheStrategy.NONE).fitCenter().into(mGifView);
        mTextView.setText(name);
    }

    private void initViews() {
        mGifView = (GifImageView) findViewById(R.id.gif_detail);
        mTextView = (TextView) findViewById(R.id.text_detail);
    }

    public void returnMain(View v) {
        finish();
    }
}
