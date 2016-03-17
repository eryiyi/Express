package com.eln.lib.common.db;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.eln.lib.common.entity.TrafficEn;
/**
 * 流量统计统计类
 * @author ZhengXiaoBin 
 *  CreateTime: 2014年8月27日
 */
public class TrafficDao {
	Dao<TrafficEn, Integer> dao;

	public TrafficDao(Context context) {
		try {
			dao= BaseDataHelper.getInstance(context).getDao(TrafficEn.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createOrUpdate(TrafficEn en) {
			try {
				dao.createOrUpdate(en);
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
	
	public List<TrafficEn> queryAllTraffic(){
		try {
			return dao.queryForAll();
		} catch (SQLException e) {
			return null;
		}
	}
	
	public void clear(){
        try {
        	dao.executeRawNoArgs("delete from traffic");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
