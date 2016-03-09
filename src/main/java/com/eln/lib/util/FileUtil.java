package com.eln.lib.util;

import com.eln.lib.util.log.MLog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 创建  文件File 和 目录Folder 
 */
public class FileUtil {

	private static final String TAG = "FileUtil.java";
	
	/** 创建文件 */
	public static File createFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			MLog.w(TAG, "创建单个文件" + destFileName + "失败，目标文件已存在！");
			return file;
		}
		if (destFileName.endsWith(File.separator)) {
			MLog.w(TAG, "创建单个文件" + destFileName + "失败，目标文件不能为目录！");
			return file;
		}
		//判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			//如果目标文件所在的目录不存在，则创建父目录
			MLog.w(TAG, "目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {
				MLog.w(TAG, "创建目标文件所在目录失败！");
				return file;
			}
		}
		//创建目标文件
		try {
			if (file.createNewFile()) {
				MLog.w(TAG, "创建单个文件" + destFileName + "成功！");
				return file;
			} else {
				MLog.w(TAG, "创建单个文件" + destFileName + "失败！");
				return file;
			}
		} catch (IOException e) {
			MLog.e(TAG, "创建单个文件" + destFileName + "失败！",e);
			return file;
		}
	}

	/** 创建目录 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			MLog.w(TAG, "创建目录" + destDirName + "失败，目标目录已经存在");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		//创建目录
		if (dir.mkdirs()) {
			MLog.w(TAG, "创建目录" + destDirName + "成功！");
			return true;
		} else {
			MLog.w(TAG, "创建目录" + destDirName + "失败！");
			return false;
		}
	}

	/** 创建临时文件</br>
	 *  临时文件的含义：该方法保证创建出来的文件在方法调用前不存在，而且任何两次调用都不会返回相同的路径<br>
	 * @param  prefix 文件名前缀（如：temp_）
	 * @param  suffix 文件名后缀（如：.txt）
	 * @return /temp_xxx.txt (xxx是随机数)
	 */
	public static String createTempFile(String prefix, String suffix, String dirName) {
		File tempFile = null;
		if (dirName == null) {
			try {
				//在默认文件夹下创建临时文件
				tempFile = File.createTempFile(prefix, suffix);
				//返回临时文件的路径
				return tempFile.getCanonicalPath();
			} catch (IOException e) {
				MLog.e(TAG, "创建临时文件失败！！",e);
				return null;
			}
		} else {
			File dir = new File(dirName);
			//如果临时文件所在目录不存在，首先创建
			if (!dir.exists()) {
				if (!FileUtil.createDir(dirName)) {
					MLog.w(TAG, "创建临时文件失败，不能创建临时文件所在的目录！");
					return null;
				}
			}
			try {
				//在指定目录下创建临时文件
				tempFile = File.createTempFile(prefix, suffix, dir);
				return tempFile.getCanonicalPath();
			} catch (IOException e) {
				MLog.e(TAG, "创建临时文件失败！！",e);
				return null;
			}
		}
	}

    /**
     * 根据byte数组，生成文件
     */
    public static File createFileByByte(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }
}