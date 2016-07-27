package com.lihao.funnygif.views;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.ViewGroup;
import android.view.Window;

import com.lihao.funnygif.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Administrator on 2016/7/27.
 */
public class GifDetailActivity extends Activity{

    private GifImageView mGifView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mGifView = new GifImageView(this);
        mGifView.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        mGifView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Intent intent = getIntent();

        setContentView(mGifView);
    }
}
