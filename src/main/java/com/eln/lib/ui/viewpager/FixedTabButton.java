
package com.eln.lib.ui.viewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;

import com.eln.lib.R;


public class FixedTabButton extends Button {
	
	//未选中文字颜色
	private int mTextColorNormal =0xFF1a1a1a; 
//			0xFF808080;
	//选中文字颜色
	private int mTextColorCenter = 0xfff51905;
	
	private int mLineColor = 0x00000000;
	private int mLineColorSelected =0xfff51905; 
//			0xFF808080;
	
	private int mLineHeight = 0;
	private int mLineHeightSelected = 3;
	
//	private int mCenterPercent = 0;
	private int mIndex =0;
	
	public FixedTabButton(Context context) {
		this(context, null);
	}
	
	public FixedTabButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public FixedTabButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		mLineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mLineHeight, context.getResources().getDisplayMetrics());
		mLineHeightSelected = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mLineHeightSelected, context.getResources().getDisplayMetrics());
//		
//		final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerExtensions, defStyle, 0);
//		
//		mLineColor = a.getColor(R.styleable.ViewPagerExtensions_lineColor, mLineColor);
//		mLineColorSelected = a.getColor(R.styleable.ViewPagerExtensions_lineColorSelected, mLineColorSelected);
//		
//		mLineHeight = a.getDimensionPixelSize(R.styleable.ViewPagerExtensions_lineHeight, mLineHeight);
//		mLineHeightSelected = a.getDimensionPixelSize(R.styleable.ViewPagerExtensions_lineHeightSelected,
//		    mLineHeightSelected);
//		
//		a.recycle();
		mTextColorNormal=context.getResources().getColor(R.color.gray);
		mTextColorCenter=context.getResources().getColor(R.color.black);
		mLineColor=context.getResources().getColor(R.color.transparent);
		mLineColorSelected=context.getResources().getColor(R.color.blue_custom);
	}
	
	
	private Paint mLinePaint = new Paint();
	
	protected synchronized void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		final int textColors[] = new int[] {
				mTextColorNormal, mTextColorCenter
			};

			setTextColor(isSelected()?textColors[1]:textColors[0]);
			setTextSize(16);
//			setSingleLine(true);
			
		final Paint linePaint = mLinePaint;
		
		linePaint.setColor(isSelected() ? mLineColorSelected : mLineColor);
		
		final int height = isSelected() ? mLineHeightSelected : mLineHeight;
		
		// draw the line
		canvas.drawRect(0, getMeasuredHeight() - height, getMeasuredWidth(), getMeasuredHeight(), linePaint);
		
	}
	
	public void setTextColorNormal(int mTextColorNormal) {
		this.mTextColorNormal = mTextColorNormal;
	}
	
	public void setTextColorCenter(int mTextColorCenter) {
		this.mTextColorCenter = mTextColorCenter;
	}
	
	public void setLineColorSelected(int color) {
		this.mLineColorSelected = color;
		invalidate();
	}
	
	public int getLineColorSelected() {
		return this.mLineColorSelected;
	}
	
	public void setLineColor(int color) {
		this.mLineColor = color;
		invalidate();
	}
	
	public int getLineColor() {
		return this.mLineColor;
	}
	
	public void setLineHeight(int height) {
		this.mLineHeight = height;
		invalidate();
	}
	
	public int getLineHeight() {
		return this.mLineHeight;
	}
	
	public void setLineHeightSelected(int height) {
		this.mLineHeightSelected = height;
		invalidate();
	}
	
	public int getLineHeightSelected() {
		return this.mLineHeightSelected;
	}
	
	public void setIndex(int index){
		mIndex = index;
	}
	
	public int getIndex(){
		return this.mIndex;
	}
	
}
