package com.baidayi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * �ܹ�����ViewPager��ScrollView
 * 
 * @Description: �����ViewPager��ScrollView�еĻ�����������
 */
public class ScrollViewExtend extends ScrollView {
	// �������뼰����
	private float xDistance, yDistance, xLast, yLast;
    
	
	public ScrollViewExtend(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	public ScrollViewExtend(Context context) {
		super(context);
	}
     /**
      * onInterceptTouchEvent()�����ڴ����¼�
      * ���ص�onInterceptTouchEvent����¼��ǴӸ��ؼ���ʼ���ӿؼ����ģ�ֱ�������ػ��ߵ�û������¼���view��
      * Ȼ������ش��ӵ����ؼ��������onTouch�ģ���������Ԥ������ȻҲ���Բ��������ı��¼��Ĵ��ݷ���
      * Ҳ���Ǿ����Ƿ�����Touch�¼��������£��ӿؼ������ݣ�һ������True�������¼��ڵ�ǰ��viewGroup�лᱻ������
      * �����´���֮·���ضϣ������ӿؼ���û�л������Touch�¼�����
      * ͬʱ���¼����ݸ���ǰ�Ŀؼ���onTouchEvent()����
      * ����false������¼������ӿؼ���onInterceptTouchEvent()
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