package com.lihao.funnygif.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.lihao.funnygif.R;

public class SplashActivity extends Activity {

	private ImageView splashImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash_layout);
		initViews();
		initImage();
	}

	private void initImage() {
		// TODO Auto-generated method stub
		ScaleAnimation splashScale = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		splashScale.setFillAfter(true);
		splashScale.setDuration(3333);
		splashScale.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				Log.d("Lihao", "�任��ʼ��");
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Log.d("Lihao", "�任������");
				startActivity();
			}

		});
		splashImageView.startAnimation(splashScale);
	}

	private void startActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		splashImageView = (ImageView) findViewById(R.id.splash_imageView);
	}

}
