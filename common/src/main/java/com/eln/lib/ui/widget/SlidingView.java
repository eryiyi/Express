package com.eln.lib.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class SlidingView extends ViewGroup {

	private FrameLayout mContainer;
	private Scroller mScroller;
	private VelocityTracker mVelocityTracker;
	private int mTouchSlop;
	private float mLastMotionX;
	private float mLastMotionY;
	private static final int SNAP_VELOCITY = 1000;
	private View mMenuView;
	private View mDetailView;

	public SlidingView(Context context) {
		super(context);
		init();
	}

	public SlidingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SlidingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mContainer.measure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final int width = r - l;
		final int height = b - t;
		mContainer.layout(0, 0, width, height);
	}

	private void init() {
		mContainer = new FrameLayout(getContext());
		mContainer.setBackgroundColor(0xff000000);
		mScroller = new Scroller(getContext());
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
		super.addView(mContainer);
	}

	public void setView(View v) {
		if (mContainer.getChildCount() > 0) {
			mContainer.removeAllViews();
		}
		mContainer.addView(v);
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
		postInvalidate();
	}

	@Override
	public void computeScroll() {
		if (!mScroller.isFinished()) {
			if (mScroller.computeScrollOffset()) {
				int oldX = getScrollX();
				int oldY = getScrollY();
				int x = mScroller.getCurrX();
				int y = mScroller.getCurrY();
				if (oldX != x || oldY != y) {
					scrollTo(x, y);
				}
				// Keep on drawing until the animation has finished.
				invalidate();
			} else {
				clearChildrenCache();
			}
		} else {
			if (!this.mScroller.computeScrollOffset()){
				if(getScrollX() == 0 && mDetailView.getVisibility() == View.VISIBLE){
					mDetailView.setVisibility(View.INVISIBLE);					
				}
			}
			clearChildrenCache();
		}
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		final int action = ev.getAction();
		final float x = ev.getX();
		final float y = ev.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			float oldScrollX = getScrollX();
			Log.e("ad", "oldScroll == " + oldScrollX);
			Log.e("ad", "mLastMotionX == " + mLastMotionX);
			if (oldScrollX < 0 && mLastMotionX > mMenuView.getWidth()) {//right
				Log.e("ad", "return ==left  isMenuOpen  true");
				showRightView();
				return true;
			} else if (oldScrollX > 0 && mLastMotionX <mDetailView.getWidth()) { // left
				Log.e("ad", "return ==right  isMenuOpen  true");
				if(x < getWidth() - oldScrollX ){
					showRightView();					
				}
				return true;
			}
			break;

		case MotionEvent.ACTION_MOVE:
			break;

		case MotionEvent.ACTION_UP:
		

			break;
		}
		return false;
	}


	private int getMenuViewWidth() {
		if (mMenuView == null) {
			return 0;
		}
		return mMenuView.getWidth();
	}

	private int getDetailViewWidth() {
		if (mDetailView == null) {
			return 0;
		}
		return mDetailView.getWidth();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	public View getDetailView() {
		return mDetailView;
	}

	public void setDetailView(View mDetailView) {
		this.mDetailView = mDetailView;
	}

	public View getMenuView() {
		return mMenuView;
	}

	public void setMenuView(View mMenuView) {
		this.mMenuView = mMenuView;
	}

	void toggle() {
		int menuWidth = mMenuView.getWidth();
		int oldScrollX = getScrollX();
		if (oldScrollX == 0) {
			smoothScrollTo(-menuWidth);
		} else if (oldScrollX == -menuWidth) {
			smoothScrollTo(menuWidth);
		}
	}

	public void showLeftView() {
		int menuWidth = mMenuView.getWidth();
		int oldScrollX = getScrollX();
		if (oldScrollX == 0) {
			smoothScrollTo(-menuWidth);
		} else if (oldScrollX == -menuWidth) {
			smoothScrollTo(menuWidth);
		}
	}

	public void showRightView() {
		int menuWidth = mDetailView.getWidth();
		int oldScrollX = getScrollX();
		if (oldScrollX == 0) {
			mDetailView.setVisibility(View.VISIBLE);
			smoothScrollTo(menuWidth);
		} else if (oldScrollX == menuWidth) {
			smoothScrollTo(-menuWidth);
		}
	}

	void smoothScrollTo(int dx) {
		int duration = 500;
		int oldScrollX = getScrollX();
		mScroller.startScroll(oldScrollX, getScrollY(), dx, getScrollY(),
				duration);
		invalidate();
	}

	void enableChildrenCache() {
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View layout = (View) getChildAt(i);
			layout.setDrawingCacheEnabled(true);
		}
	}

	void clearChildrenCache() {
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View layout = (View) getChildAt(i);
			layout.setDrawingCacheEnabled(false);
		}
	}

}
