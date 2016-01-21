package com.eln.lib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class NdFragment extends Fragment {

	protected  Context mContext;
	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mContext=getActivity();
	}
	
	@Override
    public void onResume() {
		super.onResume();
	}
	
	@Override
    public void onPause() {
		super.onPause();
	}
	
	@Override
    public void onStop() {
		super.onStop();
	}
	
//	public void finish() {
//		getActivity().getSupportFragmentManager().beginTransaction().detach(this);
//	}
	
}
