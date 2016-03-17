package com.eln.lib.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * LinearLayout的基类
 * @author geolo
 *
 */
public abstract class BaseLinearLayout extends LinearLayout {

	@Override
	public boolean equals(Object o) {
		return hashCode() == o.hashCode();
	}
	
	@Override
	public int hashCode() {
		return this.getClass().getSimpleName().hashCode();
	}
	
	public BaseLinearLayout(Context context) {
		super(context);
	}
	
	public BaseLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@TargetApi(android.os.Build.VERSION_CODES.HONEYCOMB)
	public BaseLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public abstract void onResume();
	public abstract void initData();
}
