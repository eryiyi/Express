package com.seeker.ui.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.widget.TextView;

public class PageIndicator extends TextView implements IPageIndicator {
	private int totalCount = 0;
	private int currentPage = 1;
	public ViewPager.OnPageChangeListener onPageChangeListener;
	
	public PageIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		if(onPageChangeListener != null) {
			onPageChangeListener.onPageScrollStateChanged(arg0);
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		if(onPageChangeListener != null) {
			onPageChangeListener.onPageScrolled(arg0, arg1, arg2);
		}
	}

	@Override
	public void onPageSelected(int currentItem) {
		if(totalCount == 0 || currentItem >= totalCount) {
			return;
		}
		this.currentPage = currentItem + 1;
		this.setText(currentPage + "/" + totalCount);
		invalidate();
		if(onPageChangeListener != null) {
			onPageChangeListener.onPageSelected(currentItem);
		}
	}

	@Override
	public void setViewPager(ViewPager view) {
		setViewPager(view, 0);
	}

	@Override
	public void setViewPager(ViewPager view, int initialPosition) {
		if (view == null || view.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        view.setOnPageChangeListener(this);
        this.totalCount = view.getAdapter().getCount();
        this.currentPage = initialPosition + 1;
        this.setText(currentPage + "/" + totalCount);
        invalidate();
	}

	@Override
	public void setOnPageChangeListener(OnPageChangeListener listener) {
		
	}

	@Override
	public void setCurrentItem(int item) {
		
	}

	@Override
	public void notifyDataSetChanged() {
		
	}
}
