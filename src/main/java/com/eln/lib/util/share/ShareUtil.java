package com.eln.lib.util.share;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ShareUtil {
	public static void shareIntent(Context context, String titleString,
			String content) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, titleString);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra(Intent.EXTRA_TEXT, content);
		try {
			context.startActivity(Intent.createChooser(intent, "更多分享"));
		} catch (Exception e) {
			Toast.makeText(context, "没有找到分享软件", Toast.LENGTH_SHORT).show();
		}

	}
}
