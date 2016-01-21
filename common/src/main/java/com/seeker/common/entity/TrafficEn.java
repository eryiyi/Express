package com.seeker.common.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * 流量统计
 * @author ZhengXiaoBin 
 *  CreateTime: 2014年9月3日
 */
@DatabaseTable(tableName = "traffic")
public class TrafficEn  extends BaseEn {
	
	 @DatabaseField(id=true)
	 public String date ;
	 @DatabaseField
	 public long wifi;
	 @DatabaseField
	 public long gprs;
}
