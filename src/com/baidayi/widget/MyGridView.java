package com.baidayi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 自定义gridView
 * 
 * @author: wll
 */
public class MyGridView extends GridView {
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGridView(Context context) {
		super(context);
	}

	/**
	 * 设置不滚动
	 */
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}