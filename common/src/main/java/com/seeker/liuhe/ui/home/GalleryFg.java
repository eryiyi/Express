package com.seeker.liuhe.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eln.lib.R;
import com.seeker.base.BaseFg;

public class GalleryFg extends BaseFg {

	private Activity mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.item_list_layout, null);
		initView(view);
		initData();
		return view;
	}

	private void initView(View view) {

	}

	public static Fragment newInstance() {
		GalleryFg fragment = new GalleryFg();
		fragment.setArguments(new Bundle());
		return fragment;
	}

	public void initData() {
		// doRequest(mTable, mLastPosition,mType);
	}

}
