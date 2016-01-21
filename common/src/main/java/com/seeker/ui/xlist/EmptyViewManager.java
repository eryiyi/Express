package com.seeker.ui.xlist;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eln.lib.R;


public class EmptyViewManager {

	View mAttachView;
	TextView mEmptyTextView;
	LinearLayout mRetryLayout;
	Button mRetryButton;
	ProgressBar mProgressBar;
	TextView mNoDataTextView;
	

	public EmptyViewManager(View activity, View view) {
		mAttachView = view;
		mRetryLayout = (LinearLayout) activity
				.findViewById(R.id.empty_not_web_rl);
		mRetryButton = (Button) activity.findViewById(R.id.empty_retry_btn);
		mProgressBar = (ProgressBar) activity.findViewById(R.id.empty_loading_pb);
		mNoDataTextView = (TextView) activity.findViewById(R.id.empty_no_data);
		mRetryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mEmptyInterface != null) {
					mEmptyInterface.doRetry();
				}
			}
		});

	}

	public EmptyViewManager(Activity activity, View view) {
		mAttachView = view;
		mRetryLayout = (LinearLayout) activity
				.findViewById(R.id.empty_not_web_rl);
		mRetryButton = (Button) activity.findViewById(R.id.empty_retry_btn);
		mProgressBar = (ProgressBar) activity.findViewById(R.id.empty_loading_pb);
		mNoDataTextView = (TextView) activity.findViewById(R.id.empty_no_data);
		mRetryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mEmptyInterface != null) {
					mEmptyInterface.doRetry();
				}
			}
		});
	}
	
	public void setType(EmptyStyle type) {
		if (mAttachView != null) {
			mAttachView.setVisibility(View.GONE);
		}

		mRetryLayout.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.GONE);
		mNoDataTextView.setVisibility(View.GONE);
		switch (type) {
		case EmptyStyle_LOADING:
			mProgressBar.setVisibility(View.VISIBLE);
			break;
		case EmptyStyle_RETRY:
			mRetryLayout.setVisibility(View.VISIBLE);
			break;
		case EmptyStyle_NODATA:
			mNoDataTextView.setVisibility(View.VISIBLE);
			break;
		case EmptyStyle_NORMAL:
			if (mAttachView != null) {
				mAttachView.setVisibility(View.VISIBLE);
			}
			break;

		default:
			break;
		}
	}

	public void setNoDataDefault(String string) {
		mNoDataTextView.setText(string);
	}

	EmptyInterface mEmptyInterface;

	public void setEmptyInterface(EmptyInterface emptyInterface) {
		mEmptyInterface = emptyInterface;
	}

	public interface EmptyInterface {
		public void doRetry();
	}

	public enum EmptyStyle {
		EmptyStyle_LOADING, EmptyStyle_RETRY, EmptyStyle_NODATA, EmptyStyle_NORMAL;
	}
}
