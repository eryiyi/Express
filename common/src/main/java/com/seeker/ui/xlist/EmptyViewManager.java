package com.seeker.ui.xlist;

import android.app.Activity;
import android.graphics.drawable.Drawable;
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
				if (mEmptyListener != null) {
					mEmptyListener.doRetry();
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
				if (mEmptyListener != null) {
					mEmptyListener.doRetry();
				}
			}
		});
	}
	
	public void setType(EmptyEnum type) {
		if (mAttachView != null) {
			mAttachView.setVisibility(View.GONE);
		}

		mRetryLayout.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.GONE);
		mNoDataTextView.setVisibility(View.GONE);
		switch (type) {
		case EMPTY_LOADING:
			mProgressBar.setVisibility(View.VISIBLE);
			break;
		case EMPTY_RETRY:
			mRetryLayout.setVisibility(View.VISIBLE);
			break;
		case EMPTY_NODATA:
			mNoDataTextView.setVisibility(View.VISIBLE);
			break;
		case EMPTY_NORMAL:
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

	public void setNoDataImage(Drawable drawable) {
		mNoDataTextView.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
	}

	EmptyListener mEmptyListener;

	public void setEmptyListener(EmptyListener emptyListener) {
		mEmptyListener = emptyListener;
	}

	public interface EmptyListener {
		public void doRetry();
	}

    public enum EmptyEnum {
		EMPTY_LOADING, EMPTY_RETRY, EMPTY_NODATA, EMPTY_NORMAL;
    }
}
