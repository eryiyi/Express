package com.eln.lib.common;

import android.graphics.Bitmap;

import com.eln.lib.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;


public class ImageOption {

	static DisplayImageOptions imageOption;

	public static DisplayImageOptions getInstance() {
		if (imageOption == null) {
			imageOption = createOption(R.drawable.default_image,
					R.drawable.default_image, R.drawable.default_image);
		}
		return imageOption;
	}

	public static DisplayImageOptions createOption(int loadingImg,
			int emptyImg, int failedImg) {
		return createOption(loadingImg, emptyImg, failedImg, 0);
	}

	public static DisplayImageOptions createOption(int loadingImg,
			int emptyImg, int failedImg, int radio) {
		if (radio != 0) {
			return new DisplayImageOptions.Builder()
					.displayer(new RoundedBitmapDisplayer(radio))
					.showImageOnLoading(loadingImg)
					.showImageForEmptyUri(emptyImg).showImageOnFail(failedImg)
					.cacheInMemory(true).cacheOnDisc(true)
					.considerExifParams(true)
					.bitmapConfig(Bitmap.Config.RGB_565).build();
		} else {
			return new DisplayImageOptions.Builder()
					.showImageOnLoading(loadingImg)
					.showImageForEmptyUri(emptyImg).showImageOnFail(failedImg)
					.cacheInMemory(true).cacheOnDisc(true)
					.considerExifParams(true)
					.bitmapConfig(Bitmap.Config.RGB_565).build();
		}

	}
}
