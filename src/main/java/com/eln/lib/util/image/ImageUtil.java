package com.eln.lib.util.image;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.eln.lib.util.device.ScreenUtil;
import com.eln.lib.util.log.MLog;
import com.eln.lib.util.sdCard.FileSuffix;
import com.eln.lib.util.sdCard.StorageUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;

/**
 * 图像处理工具类</br>
 * @author geolo
 * @see 1.{@link #getDrawableById(Context, int)}   </br> &nbsp&nbsp  由资源id获取图片
 * @see 2.{@link #getBitmapById(Context, int)}    </br>  &nbsp&nbsp  由资源id获取位图
 * @see 3.{@link #bitmapTobyte(Bitmap)}   </br>  &nbsp&nbsp  将Bitmap转化为字节数组
 * @see 4.{@link #byteTobitmap(byte[])}   </br>  &nbsp&nbsp   将byte数组转化为bitmap
 * @see 5.{@link #drawableTobitmap(Drawable)}   </br>  &nbsp&nbsp   将Drawable转化为Bitmap
 * @see 6.{@link #bitmapToDrawable(Bitmap)}   </br>  &nbsp&nbsp 将bitmap转化为drawable
 * @see 7.{@link #getBitmap(Context,File)}   </br>  &nbsp&nbsp 从Path路径获取Bitmap对象
 * @see 8.{@link #saveBitmap(Bitmap,String)}   </br>  &nbsp&nbsp 将bitmap位图保存到path路径下，图片格式为Bitmap.CompressFormat.PNG，质量为100
 * 
 */
public class ImageUtil { 

	public static final long MAX_SIZE_IMAGE = 200 * 1024;// 图片允许的大小200k
	public static final String O_TEMP_PIC_NAME = "O_temp.jpg";// 发出去临时图片名
	public static final String I_TEMP_PIC_NAME = "I_temp.jpg";// 传进来临时图片名

	/**
	 * 由资源id获取图片(Drawable)
	 *
	 * @param context 上下文
	 * @param resId 资源的id
	 * @return Drawable
	 */
	public static Drawable getDrawableById(Context context, int resId) {
		if (context == null) {
			return null;
		}
		return context.getResources().getDrawable(resId);
	}

	/**
	 * 由资源id获取位图(Bitmap)
	 *
	 * @param context
	 * @param resId
	 * @return Bitmap
	 */

	public static Bitmap getBitmapById(Context context, int resId) {
		if (context == null) {
			return null;
		}
		return BitmapFactory.decodeResource(context.getResources(), resId);
	}

	/**
	 * 将Bitmap转化为字节数组
	 * @param bitmap
	 * @return byte[]
	 */
	public static byte[] bitmapTobyte(Bitmap bitmap)  throws Exception{
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] array = baos.toByteArray();
			baos.flush();
			baos.close();
			return array;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将byte数组转化为bitmap
	 *
	 * @param data
	 * @return
	 */
	public static Bitmap byteTobitmap(byte[] data) {
		if (null == data) {
			return null;
		}
		return BitmapFactory.decodeByteArray(data, 0, data.length);
	}

	/**
	 * 将Drawable转化为Bitmap
	 *
	 * @param drawable
	 * @return
	 */

	public static Bitmap drawableTobitmap(Drawable drawable) {
		if (null == drawable) {
			return null;
		}
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
				.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
						: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);// 重点
		return bitmap;
	}



	/**
	 * 将bitmap转化为drawable
	 *
	 * @param bitmap
	 * @return
	 */

	public static Drawable bitmapToDrawable(Bitmap bitmap) {
		if (bitmap == null) {
			return null;
		}
		return new BitmapDrawable(bitmap);
	}

	/** 从Path路径获取Bitmap对象 */
	public static Bitmap getBitmap(Context context , File file) throws FileNotFoundException {
		java.io.InputStream is = new FileInputStream(file);
		BitmapFactory.Options opts = getOptionsWithInSampleSize(file.getPath(), ScreenUtil.getMinScreenWH(context));
		return BitmapFactory.decodeStream(is, null, opts);
	}

	/**
	 * 将bitmap位图保存到path路径下，图片格式为Bitmap.CompressFormat.PNG，质量为100
	 * @since 已经对bitmap做垃圾回收处理
	 * @param bitmap
	 * @param path
	 */
	public static boolean saveBitmap(Bitmap bitmap, String path) throws IOException{
		CompressFormat format = CompressFormat.JPEG;
		if (path.endsWith(FileSuffix.PNG)|| path.endsWith(FileSuffix.JPG) ){
			format = CompressFormat.JPEG;
		}else if(path.endsWith(FileSuffix.PNG)){
			format = CompressFormat.PNG;
		}
		return saveBitmap(bitmap , path ,format, 100);
	}

	/**
	 * 将bitmap位图保存到path路径下
	 * @since 已经对bitmap做垃圾回收处理
	 * @param bitmap
	 * @param path  保存路径
	 * @param format  格式  -Bitmap.CompressFormat.PNG或Bitmap.CompressFormat.JPEG.PNG
	 * @param quality
	 *            Hint to the compressor, 0-100. 0 meaning compress for small
	 *            size, 100 meaning compress for max quality. Some formats, like
	 *            PNG which is lossless, will ignore the quality setting
	 * @return
	 */
	public static boolean saveBitmap(Bitmap bitmap, String path, CompressFormat format, int quality) throws IOException {
		boolean isOK = false;
		FileOutputStream fos = null;
		try {
			//File file = FileUtil.createFile(path);
			File file = new File (path);
			if(file != null){
				if (file.exists()){
					file.delete();
				}
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				isOK = bitmap.compress(format, quality, bos);
				destroyBitmap(bitmap);
			}
		} catch (FileNotFoundException e) {
			MLog.e("ImageUtil.java", ">>>>>>>>>>>> saveBitmap() <<<<<<<<<<<", e);
		}finally{
			if(fos != null){
				fos.flush();
				fos.close();				
			}
		}
		return isOK;
	}

	/**
	 * 是否需要缩放图片
	 * 
	 * 1.图片尺寸大于200k
	 * 2.图片大小大于屏幕最宽
	 */
	public static Boolean isNeedScaleImage(String filename){
		// 判断文件大小
		File imageFile = new File(filename);
		if(!imageFile.exists()){
			return false;
		}
		if(imageFile.length() > MAX_SIZE_IMAGE){
			return true;
		}

		// 判断文件尺寸
		Options bitmapOptions = new Options();
		bitmapOptions.inJustDecodeBounds = true;// 只取得outHeight(图片原始高度)和
		bitmapOptions.inSampleSize = 1;
		// outWidth(图片的原始宽度)而不加载图片
		Bitmap bitmap = BitmapFactory.decodeFile(filename, bitmapOptions);
		if(bitmap == null){
			return false;	
		}
		if(bitmap.getWidth() > ScreenUtil.screenMin || bitmap.getHeight() > ScreenUtil.screenMin){
			return true;
		}
		return false;
	}

	/**
	 * 自动压缩图片：如果图片超过200K，则压缩图片 
	 * 自动选择图片：如果图片方向和屏幕方向不一致，自动旋转到拍照后正确的方向
	 * @param context
	 * @param imageFile
	 * @throws IOException 
	 */
	public static File scaleAndRotateImage(Context context, File imageFile) throws IOException {
		boolean isScale = false;
		if (isNeedScaleImage(imageFile.getAbsolutePath())) {// 如果图片超过200K，则进行压缩
			isScale = true;
		}
		String tempPath = StorageUtil.getWritePathIgnoreError(context , O_TEMP_PIC_NAME);
		File fileTemp = new File(tempPath);
		if (fileTemp.exists()) {
			fileTemp.delete();
		}
		Bitmap bitmap =  scaleImageWithFilter(context , imageFile , ScreenUtil.getMinScreenWH(context) , true , true , isScale , true);
		saveBitmap(bitmap ,fileTemp.getAbsolutePath() );
		// 删除原来图片，把临时文件改为目标文件
		imageFile.delete();
		fileTemp.renameTo(imageFile);
		return imageFile;
	}

	/**
	 *  自动旋转图片，某些手机拍照后，图片的方向不会跟随屏幕的方向，需要自动旋转到正确的
	 */
	public static Bitmap rotateAutoImage(Context context , File file){
		return  scaleImageWithFilter(context ,  file, 0 , true , true , false , true );
	}

	/**
	 * 等比例缩放图片（带滤波器）
	 * 
	 * @param srcFile
	 *            来源文件
	 * @param dstFile
	 *            目标文件
	 * @param dstMaxWH
	 *            目标文件宽高最大值
	 * @param bContrast
	 *            提高对比度滤波器，可使图片变亮丽
	 * @param bSharp
	 *            锐化图片，可使图片清晰（暂时无效果）
	 * @param isScale
	 *            是否缩放
	 * @param bRotate
	 * 			是否旋转
	 */
	public static Boolean scaleImageWithFilter(Context context , File srcFile, File dstFile,  int dstMaxWH, boolean isContrast, boolean isSharp, boolean isScale ,boolean isRotate) {
		Boolean bRet = false;

		// 路径文件不存在
		if (!srcFile.exists()) {
			return bRet;
		}

		try {
			Bitmap dstBitmap = scaleImageWithFilter(context, srcFile, dstMaxWH, isContrast, isSharp, isScale, isRotate);
			bRet= saveBitmap(dstBitmap, dstFile.getPath());
			destroyBitmap(dstBitmap);
		} catch (Exception e) {
			MLog.e("ImageUtil.java", ">>>>>>>>>>>> scaleImageWithFilter() <<<<<<<<<<<", e);
			bRet = false;
			return bRet;
		}
		return bRet;
	}

	/**
	 * 等比例缩放图片（带滤波器）
	 * 
	 * @param srcFile            来源文件
	 * @param dstMaxWH   目标文件宽/高最大值
	 * @param isContrast    是否需要提高对比度滤波器，可使图片变亮丽
	 * @param isSharp         是否需要锐化图片，可使图片清晰（暂时无效果）
	 * @param isScale           是否缩放
	 * @param isRotate        是否将图片自动旋转
	 *  @return Bitmap 需要将bitmap做回收处理,请调用方法 {@link ImageUtil#destroyBitmap(Bitmap)}
	 */
	public static Bitmap scaleImageWithFilter(Context context , File srcFile,int dstMaxWH, boolean isContrast, boolean isSharp, boolean isScale ,boolean isRotate) {
		Bitmap srcBitmap = null;
		Bitmap dstBitmap = null;
		float rotate = 90.0F;//旋转的默认值
		// 路径文件不存在
		if (!srcFile.exists()) {
			return dstBitmap;
		}

		try {
			if(isRotate){
				ExifInterface localExifInterface = new ExifInterface(srcFile.getAbsolutePath());
				int rotateInt = localExifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 	ExifInterface.ORIENTATION_NORMAL);
				rotate = getImageRotate(rotateInt);
			}
		} catch (Exception e) {
			MLog.e("ImageUtil.java", ">>>>>>>>>>>> scaleImageWithFilter(1) -- 01 <<<<<<<<<<<", e);
		}

		try {
			srcBitmap =getBitmap(context , srcFile);
			dstBitmap = scaleImageWithFilter(srcBitmap ,  dstMaxWH , isContrast , isSharp , isScale , rotate );
		} catch (Exception e) {
			MLog.e("ImageUtil.java", ">>>>>>>>>>>> scaleImageWithFilter(1) -- 02 <<<<<<<<<<<", e);
		}
		return dstBitmap;
	}

	/**
	 * 等比例缩放图片（带滤波器）
	 * 
	 * @param srcBitmap      来源文件
	 * @param dstFile            目标文件
	 * @param dstMaxWH   目标文件宽/高最大值
	 * @param isContrast    是否需要提高对比度滤波器，可使图片变亮丽
	 * @param isSharp         是否需要锐化图片，可使图片清晰（暂时无效果）
	 * @param isScale          是否缩放
	 * @param rotate           设置旋转的角度值，默认是90.0
	 * @return Bitmap 需要将bitmap做回收处理,请调用方法 {@link ImageUtil#destroyBitmap(Bitmap)}
	 */
	public static Bitmap scaleImageWithFilter(Bitmap srcBitmap , int dstMaxWH, boolean isContrast, boolean isSharp, boolean isScale ,float rotate){
		Matrix matrix = new Matrix();
		Bitmap dstBitmap = null;
		try {
			int width = srcBitmap.getWidth();	// 原图片宽高
			int height = srcBitmap.getHeight();	// 原图片宽高
			float scale = 1.f;	// 获得缩放因子
			if (width > dstMaxWH || height > dstMaxWH) {
				float scaleTemp = (float) dstMaxWH / (float) width;
				float scaleTemp2 = (float) dstMaxWH / (float) height;
				if (scaleTemp > scaleTemp2)
					scale = scaleTemp2;
				else
					scale = scaleTemp;
			}
			matrix.postScale(scale, scale);										 
			matrix.postRotate(rotate);	
			dstBitmap =   Bitmap.createBitmap(srcBitmap, 0, 0, width, height,matrix, true);					 

			// 提高对比度
			if (isContrast) {
				Bitmap tempBitmap = Bitmap.createBitmap(dstBitmap.getWidth(),	dstBitmap.getHeight(), Bitmap.Config.ARGB_8888);
				Canvas canvas = new Canvas(tempBitmap);
				ColorMatrix cm = new ColorMatrix();
				float contrast = 30.f / 180.f; // 提高30对比度
				setContrast(cm, contrast);
				Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				canvas.drawBitmap(dstBitmap, 0, 0, paint);
			}

			// 提高锐化
			if (isSharp) {
				//dstBitmap = sharpenImageAmeliorate(dstBitmap);
			}

			if(srcBitmap != dstBitmap ){//rotate等于0的时候，dstBitmap和srcBitmap是用同一个内存引用地址
				destroyBitmap(srcBitmap);
			}
		} catch (Exception e) {
			MLog.e("ImageUtil.java", ">>>>>>>>>>>> scaleImageWithFilter(2) <<<<<<<<<<<", e);
		}
		return dstBitmap;
	}

	/**
	 * 获得圆角/圆形图片
	 *
	 * @param bitmap
	 * @param isCircle 是否设置为正圆形
	 * @param radius 圆角半径
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, boolean isCircle , float radius) {
		if (bitmap == null || radius == 0) {
			return bitmap;
		}
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int maxSize = 480;//设置最大尺寸480，避免内存溢出
		float left,top,right,bottom,dst_left,dst_top,dst_right,dst_bottom = 0;

		if(width > maxSize || height > maxSize ){//设置最大尺寸480，避免内存溢出
			if( width  >= height){
				height = height - (width - maxSize);
				width = maxSize ;
			}else{
				width = width - (width - maxSize);
				height = maxSize ;
			}
		}

		if(isCircle){
			if (width <= height) {
				radius = width / 2;
				top = 0;
				bottom = width;
				left = 0;
				right = width;
				height = width;
				dst_left = 0;
				dst_top = 0;
				dst_right = width;
				dst_bottom = width;
			} else {
				radius = height / 2;
				float clip = (width - height) / 2;
				left = clip;
				right = width - clip;
				top = 0;
				bottom = height;
				width = height;
				dst_left = 0;
				dst_top = 0;
				dst_right = height;
				dst_bottom = height;
			}
		}else{
			left = dst_left = 0;
			top = dst_top = 0;
			right = dst_right = width;
			bottom = dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width,height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect(( int)left, ( int )top, ( int)right, (int )bottom);
		final Rect dst = new Rect(( int)dst_left, ( int )dst_top, (int )dst_right, ( int)dst_bottom);
		final RectF rectF = new RectF(dst);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, radius, radius, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}

	/**
	 * 获得带倒影的图片
	 */
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		if (bitmap == null) {
			return null;
		}
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);
		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,width, height / 2, matrix, false);
		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,  (height + height / 2), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);
		destroyBitmap(bitmap);
		return bitmapWithReflection;
	}

	public static Bitmap GetBitmapFromUri(Context context ,Uri uri,int requiredSize) {
		try {
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, o);
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize || requiredSize == -1){
					break;
				}
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, o2);
		} catch (Exception e) {}
		return null;
	}


	/** 图片质量控制,让图片不超过200k */
	public static byte[] qualityCompless(Bitmap srcBitmap){
		return qualityCompless(srcBitmap , MAX_SIZE_IMAGE);
	}

	/** 图片质量控制 
	 * @param qualitySize 单位k*/
	public static byte[] qualityCompless(Bitmap srcBitmap , long qualitySize){
		int options = 100; 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		srcBitmap.compress(Bitmap.CompressFormat.PNG, 100,baos);
		while (baos.toByteArray().length > qualitySize * 1024) {  //循环判断如果压缩后图片是否大于200kb,大于继续压缩        
			baos.reset();//重置baos即清空baos 
			if(options < 1){
				options = 1;
			}
			srcBitmap.compress(Bitmap.CompressFormat.PNG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中 
			if(options == 1){
				break;
			}else if(options < 10){
				options -= 1;
			}else{
				options -= baos.toByteArray().length / qualitySize * 1024;//每次都减少10 				
			}
		} 
		MLog.v("ImageUtil.java", "最后压缩的容量为：" + baos.toByteArray().length + "b" +" 压缩的质量因子为：" + options +"~" + (options+10));
		return baos.toByteArray();
	}

	/** 销毁Bitmap对象 */
	public static void destroyBitmap(Bitmap bitmap){
		if (bitmap != null && !bitmap.isRecycled()){
			bitmap.recycle();
		}
		bitmap = null;
	}

	/**
	 * 图片锐化（拉普拉斯变换）
	 * @param bmp
	 * @return
	 */
	public static  Bitmap sharpenImageAmeliorate(Bitmap bmp){
		long start = System.currentTimeMillis();
		// 拉普拉斯矩阵
		int[] laplacian = new int[] { -1, -1, -1, -1, 9, -1, -1, -1, -1 };
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		int pixR = 0;
		int pixG = 0;
		int pixB = 0;
		int pixColor = 0;
		int newR = 0;
		int newG = 0;
		int newB = 0;
		int idx = 0;
		float alpha = 0.3F;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 1, length = height - 1; i < length; i++){
			for (int k = 1, len = width - 1; k < len; k++){
				idx = 0;
				for (int m = -1; m <= 1; m++){
					for (int n = -1; n <= 1; n++){
						pixColor = pixels[(i + n) * width + k + m];
						pixR = Color.red(pixColor);
						pixG = Color.green(pixColor);
						pixB = Color.blue(pixColor);
						
						newR = newR + (int) (pixR * laplacian[idx] * alpha);
						newG = newG + (int) (pixG * laplacian[idx] * alpha);
						newB = newB + (int) (pixB * laplacian[idx] * alpha);
						idx++;
					}
				}
				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));
				pixels[i * width + k] = Color.argb(255, newR, newG, newB);
				newR = 0;
				newG = 0;
				newB = 0;
			}
		}
		
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		long end = System.currentTimeMillis();
		MLog.d("ImageUtil.java", "used time="+(end - start));
		return bitmap;
	}
	
	/**
	 * 设置对比度矩阵
	 */
	private static void setContrast(ColorMatrix cm, float contrast) {
		float scale = contrast + 1.f;
		float translate = (-.5f * scale + .5f) * 255.f;
		cm.set(new float[] { scale, 0, 0, 0, translate, 0, scale, 0, 0,
				translate, 0, 0, scale, 0, translate, 0, 0, 0, 1, 0 });
	}

	/**
	 * 获得旋转角度
	 * @param rotate
	 * @return
	 */
	private static float getImageRotate(int rotate){
		float f;
		if (rotate == 6){
			f = 90.0F;
		} else if (rotate == 3){
			f = 180.0F;
		} else if (rotate == 8){
			f = 270.0F;
		} else {
			f = 0.0F;
		}
		return f;
	}

	/**
	 * 获取长宽都不超过160dip的图片，基本思想是设置Options.inSampleSize按比例取得缩略图
	 */
	private static Options getOptionsWithInSampleSize(String filePath,  int maxWidth) {
		Options bitmapOptions = new Options();
		bitmapOptions.inJustDecodeBounds = true;// 只取得outHeight(图片原始高度)和 outWidth(图片的原始宽度)而不加载图片
		BitmapFactory.decodeFile(filePath, bitmapOptions);
		bitmapOptions.inJustDecodeBounds = false;
		int inSampleSize = bitmapOptions.outWidth / (maxWidth / 10);// 应该直接除160的，但这里出16是为了增加一位数的精度
		if (inSampleSize % 10 != 0) {
			inSampleSize += 10;// 尽量取大点图片，否则会模糊
		}
		inSampleSize = inSampleSize / 10;
		if (inSampleSize <= 0) {// 判断200是否超过原始图片高度
			inSampleSize = 1;// 如果超过，则不进行缩放
		}
		bitmapOptions.inSampleSize = inSampleSize;
		return bitmapOptions;
	}

}
