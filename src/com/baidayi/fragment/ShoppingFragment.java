package com.baidayi.fragment;

import com.alipay.sdk.pay.demo.PayDemoActivity;
import com.baidayi.activity.R;
import com.baidayi.adpter.ShoppingAdapter;
import com.baidayi.db.DataBaseHelper;
import com.baidayi.db.ShoppingManage;
import com.baidayi.domain.Product;
import com.baidayi.utils.ListUtil;
import com.baidayi.widget.FilpperListvew;
import com.baidayi.widget.FilpperListvew.FilpperDeleteListener;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;

/**
 * 购物车内容显示
 * 
 * @author: wll
 */
public class ShoppingFragment extends Fragment {
	private View mParent;
	private FragmentActivity mActivity;
	private RelativeLayout relativeLayout;
	private FilpperListvew shopping_listview;
	private ShoppingAdapter adapter;
	private int width;
	private Product product;
	private String productname;
	private String productdesrcption;
	private String productprice;

	/**
	 * Create a new instance of DetailsFragment, initialized to show the text at
	 * 'index'.
	 */
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
		relativeLayout = (RelativeLayout) mParent
				.findViewById(R.id.fragment_shopping_relative);
		shopping_listview = (FilpperListvew) mParent
				.findViewById(R.id.shopping_listview);
		if (new ShoppingManage().getProducts(mActivity, null).size() > 0) {
			adapter = new ShoppingAdapter(mActivity,
					new ShoppingManage().getProducts(mActivity, null));
			shopping_listview.setAdapter(adapter);
			relativeLayout.setVisibility(View.GONE);
		}
		DisplayMetrics dm = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;
		shopping_listview.setFilpperDeleteListener(new FilpperDeleteListener() {

			@Override
			public void filpperDelete(float xPosition, float yPosition) {
				// listview中要有item，否则返回
				if (shopping_listview.getChildCount() == 0)
					return;
				// 根据坐标获得滑动删除的item的index
				final int index = shopping_listview.pointToPosition(
						(int) xPosition, (int) yPosition);
				// 以下两步是获得该条目在屏幕显示中的相对位置，直接根据index删除会空指針异常。因为listview中的child只有当前在屏幕中显示的才不会为空
				//getFirstVisiblePosition()得到第一个可见的item
				int firstVisiblePostion = shopping_listview
						.getFirstVisiblePosition();
				//getChildAt()时要用index减去第一个可见的item的firstVisiblePostion，才能得到当前操作的item
				View view = shopping_listview.getChildAt(index
						- firstVisiblePostion);

				TranslateAnimation tranAnimation = new TranslateAnimation(0,
						width, 0, 0);
				tranAnimation.setDuration(500);
				tranAnimation.setFillAfter(true);
				view.startAnimation(tranAnimation);
				// 当动画播放完毕后，删除。否则不会出现动画效果（自己试验的）。
				tranAnimation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {

					}

					@Override
					public void onAnimationRepeat(Animation animation) {

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						// 删除一个item

						String itemName = new ShoppingManage()
								.getProducts(mActivity, null).get(index)
								.getProductName();
						SQLiteDatabase sqLiteDatabase = DataBaseHelper
								.getInstance(mActivity).getWritableDatabase();
						sqLiteDatabase.execSQL("DELETE FROM " + "ShoppingList"
								+ " WHERE productName = '" + itemName + "'");

						sqLiteDatabase.close();
						DataBaseHelper.closeDB();

						adapter.notifyDataSetChanged();

					}
				});

			}
		});
		shopping_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				 product=new ShoppingManage().getProducts(mActivity, null).get(position);
				 productdesrcption=product.getProductDescribe();
				 productname=product.getProductName();
				 productprice=product.getProductPrice();
				 Intent intent=new Intent(mActivity,PayDemoActivity.class);
				 intent.putExtra("productdesrcption", productdesrcption);
				 intent.putExtra("productname", productname);
				 intent.putExtra("productprice", productprice);
				 startActivity(intent);
			}
		});
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shopping, container,
				false);

		return view;
	}
  
	//在fragment被hide()或者show()的时候调用
	@Override
	public void onHiddenChanged(boolean hidden) {
		if (new ShoppingManage().getProducts(mActivity, null).size() > 0) {
			adapter = new ShoppingAdapter(mActivity,
					new ShoppingManage().getProducts(mActivity, null));
			shopping_listview.setAdapter(adapter);
			relativeLayout.setVisibility(View.GONE);
		}
		super.onHiddenChanged(hidden);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

}
