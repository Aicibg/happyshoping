package com.baidayi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 能够兼容ViewPager的ScrollView
 * 
 * @Description: 解决了ViewPager在ScrollView中的滑动反弹问题
 */
public class ScrollViewExtend extends ScrollView {
	// 滑动距离及坐标
	private float xDistance, yDistance, xLast, yLast;
    
	
	public ScrollViewExtend(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	public ScrollViewExtend(Context context) {
		super(context);
	}
     /**
      * onInterceptTouchEvent()是用于处理事件
      * （重点onInterceptTouchEvent这个事件是从父控件开始往子控件传的，直到有拦截或者到没有这个事件的view，
      * 然后就往回从子到父控件，这次是onTouch的）（类似于预处理，当然也可以不处理）并改变事件的传递方向，
      * 也就是决定是否允许Touch事件继续向下（子控件）传递，一但返回True（代表事件在当前的viewGroup中会被处理），
      * 则向下传递之路被截断（所有子控件将没有机会参与Touch事件），
      * 同时把事件传递给当前的控件的onTouchEvent()处理；
      * 返回false，则把事件交给子控件的onInterceptTouchEvent()
      */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDistance = yDistance = 0f;
			xLast = ev.getX();
			yLast = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float curX = ev.getX();
			final float curY = ev.getY();
			
//			int off= view.getMeasuredHeight()-extend.getHeight();
//			Log.i("OFF", off+"");

			xDistance += Math.abs(curX - xLast);
			yDistance += Math.abs(curY - yLast);
			xLast = curX;
			yLast = curY;
			

				if (xDistance > yDistance) {
                 
					return false;
				}
		}

		return super.onInterceptTouchEvent(ev);
	}
}