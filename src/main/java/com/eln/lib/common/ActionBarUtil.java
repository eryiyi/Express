package com.eln.lib.common;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.eln.lib.R;


/**
 * 带“返回”按钮的顶部标题的统一初始化，很多页面包含这个界面元素
 * 使用ActionBar替换
 */
 @Deprecated
public class ActionBarUtil {

	public interface OnBackListener {
		void onBackFinish();
	}

	public static void initTitle(Activity activity, String title) {
		TextView mTopBarTitleName = (TextView) activity
				.findViewById(R.id.title_name);
		mTopBarTitleName.setText(title);
	}
	
	public static void initTitle(View mView, String title) {
		TextView mTopBarTitleName = (TextView) mView
				.findViewById(R.id.title_name);
		mTopBarTitleName.setText(title);
	}

//	public static void setTitleCompoundDrawables(Activity activity, int left,
//			int top, int right, int bottom) {
//		TextView mTopBarTitleName = (TextView) activity
//				.findViewById(R.id.title_name);
//		mTopBarTitleName.setCompoundDrawablesWithIntrinsicBounds(left, top,
//				right, bottom);
//	}

	/**
	 * 初始化右边按钮
	 * 
	 * @param activity
	 * @param drawableID
	 *            按钮图片
	 * @param isHide
	 *            是否隐藏整个右边
	 * @param listener
	 *            点击监听
	 */
	public static void initRight(final Activity activity, int drawableID,
			boolean isHide, final OnBackListener listener) {
		ImageView mTopRightBtn = (ImageView) activity
				.findViewById(R.id.title_right);
		mTopRightBtn.setVisibility(View.VISIBLE);
		if (drawableID > 0) {
			mTopRightBtn.setImageResource(drawableID);
		}
		if (isHide) {
			mTopRightBtn.setVisibility(View.INVISIBLE);
		}
		if (listener != null) {
			mTopRightBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					closeInputMethod(activity);
					if (listener != null)
						listener.onBackFinish();
				}
			});
		}
	}

	/**
	 * 初始化右边按钮
	 * 
	 * @param activity
	 * @param text
	 *            按钮显示文字
	 * @param isHide
	 *            是否隐藏整个右边
	 * @param listener
	 *            点击监听
	 */
	public static void initRight(final Activity activity,final OnBackListener listener) {
		ImageView mTopRightBtn = (ImageView) activity
				.findViewById(R.id.title_right);
		mTopRightBtn.setVisibility(View.GONE);
		if (listener != null) {
			mTopRightBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					closeInputMethod(activity);
					if (listener != null)
						listener.onBackFinish();
				}
			});
		}
	}

	public static void initLeft(final Activity activity,
			final OnBackListener listener) {
		View mLeftRL = activity.findViewById(R.id.title_back);
		mLeftRL.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				closeInputMethod(activity);
				if (listener != null) {
					listener.onBackFinish();
				}
				activity.finish();
			}
		});
	}

	public static void initLeft(final View view, final OnBackListener listener) {
		View mLeftRL = view.findViewById(R.id.title_back);
		mLeftRL.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onBackFinish();
				}
			}
		});
	}
	
	public static void hideLeft(Activity act){
		View mLeftView =act.findViewById(R.id.title_back);
		mLeftView.setVisibility(View.GONE);		
	}
	public static void hideLeft(View mView){
		View titleView =mView.findViewById(R.id.title_back);
		titleView.setVisibility(View.GONE);
	}
	
	public static void hideTitle(View mView){
		View titleView =mView.findViewById(R.id.rl_title);
		titleView.setVisibility(View.GONE);
	}

	/**
	 * 关闭输入法弹出
	 * 
	 * @param activity
	 */
	public static void closeInputMethod(final Activity activity) {
		if (activity != null && activity.getCurrentFocus() != null) {
			InputMethodManager imm = (InputMethodManager) activity
					.getSystemService(Activity.INPUT_METHOD_SERVICE);
			boolean isOpen = imm.isActive();
			if (isOpen) {
				imm.hideSoftInputFromWindow(activity.getCurrentFocus()
						.getWindowToken(), 0);
			}
		}
	}
}
