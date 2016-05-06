package com.baidayi.fragment;

import com.baidayi.activity.FragmentAddressActivity;
import com.baidayi.activity.FragmentCollectActivity;
import com.baidayi.activity.FragmentHelpActivity;
import com.baidayi.activity.R;
import com.baidayi.config.ConstantS;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 个人中心 内容显示
 * 
 * @author: wll
 */
public class UserFragment extends Fragment implements OnClickListener {

	private View mParent;
	private Intent mIntent = null;
	private FragmentActivity mActivity;
	private ImageView userIcon = null;
	private TextView userName = null;
	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;
	private SharedPreferences settings = null;
	private LinearLayout fragment_user_collect_linear,
			fragment_user_address_linear, fragment_userhelp_linear;

	public static HomeFragment newInstance(int index) {
		HomeFragment f = new HomeFragment();
		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);

		return f;
	}

	public int getShownIndex() {
		return getArguments().getInt("index", 0);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		mActivity = getActivity();
		mParent = getView();
		// 我的收藏
		fragment_user_collect_linear = (LinearLayout) mParent
				.findViewById(R.id.fragment_user_collect_linear);
		fragment_user_collect_linear.setOnClickListener(this);
		fragment_user_address_linear = (LinearLayout) mParent
				.findViewById(R.id.fragment_user_address_linear);
		fragment_user_address_linear.setOnClickListener(this);
		fragment_userhelp_linear = (LinearLayout) mParent
				.findViewById(R.id.fragment_userhelp_linear);
		fragment_userhelp_linear.setOnClickListener(this);

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user, container, false);
		return view;
	}

	private void initview() {
		userIcon = (ImageView) mParent.findViewById(R.id.fragment_dsx_pic);
		userName = (TextView) mParent
				.findViewById(R.id.fragment_dsx_username_text);

		settings = mActivity.getSharedPreferences(ConstantS.PREFS_NAME, 0);
		String UserName = settings.getString("UserName", null);
		String ImageUrl = settings.getString("ImageUrl", null);
		String gender = settings.getString("gender", null);
		userName.setText(UserName);
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(mActivity));
		options = new DisplayImageOptions.Builder()
				.displayer(new RoundedBitmapDisplayer(0xff000000, 0))
				.cacheInMemory().cacheOnDisc().build();
		imageLoader.displayImage(ImageUrl, userIcon, options);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		initview();
		super.onHiddenChanged(hidden);
	}

	@Override
	public void onResume() {
		initview();
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_user_collect_linear:
			mIntent = new Intent();
			mIntent.setClass(mActivity, FragmentCollectActivity.class);
			startActivity(mIntent);
			break;
		case R.id.fragment_userhelp_linear:
			mIntent = new Intent();
			mIntent.setClass(mActivity, FragmentHelpActivity.class);
			startActivity(mIntent);
			break;
		case R.id.fragment_user_address_linear:
			mIntent = new Intent();
			mIntent.setClass(mActivity, FragmentAddressActivity.class);
			startActivity(mIntent);
			break;
		}
	}

}
