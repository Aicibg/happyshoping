package com.baidayi.activity;

import com.baidayi.config.ConstantS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * 应用启动 动画
 * 
 * @author: wll
 */
public class SplashActivity extends Activity {

	public static final String TAG = SplashActivity.class.getSimpleName();

	private ImageView mSplashItem = null;

	protected void findViewById() {
		mSplashItem = (ImageView) findViewById(R.id.splash_loading_item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		ConstantS.SCREEN_DENSITY = metrics.density;
		ConstantS.SCREEN_HEIGHT = metrics.heightPixels;
		ConstantS.SCREEN_WIDTH = metrics.widthPixels;

		new Handler(getMainLooper());
		findViewById();
		initView();
	}

	protected void initView() {
		// 动画效果
		Animation translate = AnimationUtils.loadAnimation(this,
				R.anim.splash_loading);
		translate.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent();
				intent.setClass(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				// enterAnim进入动画,exitAnim退出动画
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				SplashActivity.this.finish();
			}
		});
		mSplashItem.setAnimation(translate);
	}

}
