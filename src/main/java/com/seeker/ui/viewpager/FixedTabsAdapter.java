package com.seeker.ui.viewpager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.eln.lib.R;


public class FixedTabsAdapter implements TabsAdapter {
	
	private Activity mContext;
	
	private String[] mTitles = {
	    "POSTS", "ABOUT", "PHOTOS"
	};
	
	private int[][] nTitleCols;
	
	public FixedTabsAdapter(Activity ctx) {
		this.mContext = ctx;
	}
	
	public FixedTabsAdapter(Activity ctx,int[] titles) {
		this.mContext = ctx;
		mTitles=new String[titles.length];
		for (int i = 0; i < titles.length; i++) {
			mTitles[i]=ctx.getString(titles[i]);
		}
	}
	
	public FixedTabsAdapter(Activity ctx,String[] titles) {
		this.mContext = ctx;
		mTitles=titles;
	}
	
	@Override
	public View getView(int position) {
		FixedTabButton tab;
		
		LayoutInflater inflater = mContext.getLayoutInflater();
		tab = (FixedTabButton) inflater.inflate(R.layout.tab_fixed, null);
		if(nTitleCols!= null){
			int[] nTitleCol = nTitleCols[position];
			tab.setTextColorNormal(nTitleCol[0]);
			tab.setTextColorCenter(nTitleCol[1]);
			tab.setLineColor(nTitleCol[2]);
			tab.setLineColorSelected(nTitleCol[3]);
		}
		
		if (position < mTitles.length)tab.setText(mTitles[position]);

		return tab;
	}	

	@Override
	public int getCount() {
		return mTitles.length;
	}
	
	public void setnTitleCols(int[][] nTitleCols) {
		this.nTitleCols = nTitleCols;
	}
	
}
