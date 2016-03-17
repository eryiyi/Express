package com.eln.lib.common.web;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eln.lib.R;
import com.eln.lib.util.ToastUtil;
import com.eln.lib.common.ActionBarUtil;

/** 
 * 继承WebChromeClient类 
 * 对js弹出框进行处理  
 * @author geolo
 */  
public class MyWebChromeClient extends WebChromeClient {

	private static final String TAG = "MyWebChromeClient.java";
	private ProgressBar mProgressBar;
	private Activity mContext;
	
	public MyWebChromeClient(Activity context,ProgressBar progressBar) {
		mContext=context;
		mProgressBar=progressBar;
	}
	
	@Override
	public void onReceivedTitle(WebView view, String title) {
		super.onReceivedTitle(view, title);
		if(mContext!=null){
			ActionBarUtil.initTitle(mContext, title);
		}
	}
	

	@Override/** 处理alert弹出框 */  
	public boolean onJsAlert(WebView view, String url, String message,
			JsResult result) {
		if (!TextUtils.isEmpty(message)) {
			ToastUtil.showLongToast(view.getContext(), message);
		}
		result.confirm();  
		return true;  
	}

	@Override  /** 处理confirm弹出框 */  
	public boolean onJsConfirm(WebView view, String url, String message,  
			final JsResult result) {  
//		MLog.d(TAG, "onJsConfirm() -- message:" + message);  
		new AlertDialog.Builder(view.getContext())
		.setMessage(message)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				result.confirm();
			}
		})
		.setNegativeButton(android.R.string.cancel,
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				result.cancel();
			}
		}).create().show();
		result.confirm();  
		return super.onJsConfirm(view, url, message, result);  
	}  

	/** 
	 * 处理prompt弹出框 
	 */  
	@Override  
	public boolean onJsPrompt(WebView view, String url, String message,  
			String defaultValue, final JsPromptResult result) {  
//		MLog.d(TAG,"onJsPrompt() -- message:" + message);  
		final LayoutInflater factory = LayoutInflater.from(view.getContext());
		final View v = factory.inflate(R.layout.javascript_propt_dialog, null);

		((TextView)v.findViewById(R.id.prompt_message_text)).setText(message);
		((EditText)v.findViewById(R.id.prompt_input_field)).setText(defaultValue);

		new AlertDialog.Builder(view.getContext())
		// .setTitle(R.string.title_dialog_prompt)
		.setView(v)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = ((EditText)v.findViewById(R.id.prompt_input_field))
						.getText().toString();
				result.confirm(value);
			}
		})
		.setNegativeButton(android.R.string.cancel,
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				result.cancel();
			}
		}).setOnCancelListener(new DialogInterface.OnCancelListener() {
			public void onCancel(DialogInterface dialog) {
				result.cancel();
			}
		}).show();

		return true;
	}  

	@Override/** 扩充缓存的容量  */
	public void onReachedMaxAppCacheSize(long requiredStorage, long quota, QuotaUpdater quotaUpdater) {
		quotaUpdater.updateQuota(requiredStorage*2);
	}

	@Override
	public void onCloseWindow(WebView window) {//网页写 window.close()
//		MLog.v(TAG, ">>>>>> onCloseWindow() <<<<<<");
		if (window.getContext() instanceof Activity) {
			((Activity)window.getContext()).finish();
		}
		super.onCloseWindow(window);
	}
	
    public void onProgressChanged(WebView view, int progress) {
    	Context mContext =view.getContext();
        if (mContext== null) {
            return;
        }
        if (!mProgressBar.isShown()) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
        mProgressBar.setProgress(progress);
        if (progress == 100) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
    
}
