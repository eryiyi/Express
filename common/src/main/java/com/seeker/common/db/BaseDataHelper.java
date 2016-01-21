package com.seeker.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.eln.lib.util.log.MLog;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.seeker.common.entity.TrafficEn;

import java.sql.SQLException;
/**
 * 数据库-创建本地数据库
 * @author ZhengXiaoBin 
 *  CreateTime: 2014年8月27日
 */
public class BaseDataHelper extends OrmLiteSqliteOpenHelper {

	final private static String DATABASE_NAME="eln.db";
	final private static int DATABASE_VERSION=3;
	private static BaseDataHelper mInstance;
	
	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, TrafficEn.class);
		} catch (SQLException e) {
	    	MLog.e("Unable to create databases");
		} 
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int oldVersion,int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, TrafficEn.class,true);
		} catch (SQLException e) {
	    	MLog.e("Unable to onUpgrade databases");
		} 
	}
		
	public BaseDataHelper(Context context){
		 super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public static BaseDataHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance= new BaseDataHelper(context);
		}
		return mInstance;
	}
}
