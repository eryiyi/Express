package com.eln.lib.ui.viewpager;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.eln.lib.R;


public abstract class CustomPagerAdapter extends PagerAdapter {

	int[] mTitle;
	String[] mTitlestr;

	public CustomPagerAdapter(int[] title) {
		mTitle = title;
	}
	
	public CustomPagerAdapter(String[] titlestr) {
		mTitlestr = titlestr;
	}

	@Override
	public int getCount() {
		if(mTitlestr!=null){
			return mTitlestr.length;
		}else{
			return mTitle.length;
		}
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == (object);
	}

//	@Override
//	public int getItemPosition(Object object) {
//		// for(int i=0;i<viewCache.size();i++){
//		// if(viewCache.get(i).get()==(View) object){
//		// return i;
//		// }
//		// }
//		return POSITION_NONE;
//	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
			View view = getView(position);
			if (view != null && view.getParent() == null) {
				((ViewPager) container).addView(view);
			}

		return view;
	}

	protected abstract View getView(int position);
	

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		super.finishUpdate(container);
	}


	// 适用于FixTab的Adapter
	public static ViewPager initFixedViewFlow(Activity context,
			int[] mTitleStrList, PagerAdapter mAdapter, int index) {
		ViewPager mViewFlow = (ViewPager) context
				.findViewById(R.id.viewpager);
		mViewFlow.setAdapter(mAdapter);
		mViewFlow.requestFocus();
//		mViewFlow.setCurrentItem(mTitleStrList.length * 1000 + index, false);
		mViewFlow.setCurrentItem(index, false);
		FixedTabsView tabsView = (FixedTabsView) context
				.findViewById(R.id.tabsview);

		FixedTabsAdapter tabsAdapter = null;
		tabsAdapter = new FixedTabsAdapter(context, mTitleStrList);
		tabsView.setAdapter(tabsAdapter);
		tabsView.setViewPager(mViewFlow);
		return mViewFlow;
	}
	
	public static ViewPager initFixedViewFlow(Activity context,
			String[] mTitleStrList, PagerAdapter mAdapter, int index) {
		ViewPager mViewFlow = (ViewPager) context
				.findViewById(R.id.viewpager);
		mViewFlow.setAdapter(mAdapter);
		mViewFlow.requestFocus();
//		mViewFlow.setCurrentItem(mTitleStrList.length * 1000 + index, false);
		mViewFlow.setCurrentItem(index, false);
		FixedTabsView tabsView = (FixedTabsView) context
				.findViewById(R.id.tabsview);

		FixedTabsAdapter tabsAdapter = null;
		tabsAdapter = new FixedTabsAdapter(context, mTitleStrList);
		tabsView.setAdapter(tabsAdapter);
		tabsView.setViewPager(mViewFlow);
		return mViewFlow;
	}

	   public static ViewPager initFixedViewFlow(Activity context, View view,
	            String[] mTitleStrList, PagerAdapter mAdapter, int index) {
	        ViewPager mViewFlow = (ViewPager) view
	                .findViewById(R.id.viewpager);
	        mViewFlow.setAdapter(mAdapter);
	        mViewFlow.requestFocus();
//	        mViewFlow.setCurrentItem(mTitleStrList.length * 1000 + index, false);
	        mViewFlow.setCurrentItem(index, false);
	        FixedTabsView tabsView = (FixedTabsView) context
	                .findViewById(R.id.tabsview);

	        FixedTabsAdapter tabsAdapter = null;
	        tabsAdapter = new FixedTabsAdapter(context, mTitleStrList);
	        tabsView.setAdapter(tabsAdapter);
	        tabsView.setViewPager(mViewFlow);
	        return mViewFlow;
	    }
	   
	
}