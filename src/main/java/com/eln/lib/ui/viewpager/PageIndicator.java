package com.eln.lib.ui.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.widget.TextView;

public class PageIndicator extends TextView implements IPageIndicator {
	private int totalCount = 0;
	private int currentPage = 1;
	public ViewPager.OnPageChangeListener onPageChangeListener;
	private ViewPager mViewPager;
	private int mCurrentPage;

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
	public void setViewPager(ViewPager viewPager, int initialPosition) {
		if (viewPager == null || viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
		mViewPager = viewPager;
		viewPager.setOnPageChangeListener(this);
        this.totalCount = viewPager.getAdapter().getCount();
        this.currentPage = initialPosition + 1;
        this.setText(currentPage + "/" + totalCount);
        invalidate();
	}

	@Override
	public void setOnPageChangeListener(OnPageChangeListener listener) {
		onPageChangeListener = listener;
	}

	@Override
	public void setCurrentItem(int item) {
		if (mViewPager == null) {
			throw new IllegalStateException("ViewPager has not been bound.");
		}
		mViewPager.setCurrentItem(item);
		mCurrentPage = item;
		invalidate();
	}

	@Override
	public void notifyDataSetChanged() {
		
	}
}
