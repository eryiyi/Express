package com.seeker.common.entity;

import java.io.Serializable;

import android.content.Context;
import android.text.TextUtils;

import com.eln.lib.util.SharedPreferencesUtil;
import com.google.gson.Gson;

/**
 * 用户基础信息，使用SharedPreferencesUtil.WriteSettings存储
 * @author ZhengXiaoBin 
 *  CreateTime: 2014年8月14日
 */
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	public String user_id;//用户唯一标识
	public String staff_id;
	public String account_code=""; //登录账号
	public String person_name="";  //员工姓名 
	public String nick_name="";//昵称
	public int sex;//性别
	public String birthday="";
	public String email="";//邮箱
	public String area="";//地区
	public String phone="";//手机
	public float credit;//学习积分
	public long gold;//金币
	public int age;//年龄（系统年份-出生年份+1）若没有出生年份，则为0
	public String post_id;//岗位ID（一个岗位可以有多个职位）
    public String post_name;//岗位名称
	
	public String position_id;//职位ID
	public String position_name="";//工作职位
	
	public String dept_id="";//所属部门的ID 
	public String dept_name="";//所属部门的名称
	public int level ;//等级
	public String level_name="";//"一年级"
    public String header_url="";//头像URL
    public String exp="";//经验
    public long max_exp;// 最大经验
	public boolean mobile_rpt_usable;//报表权限
	
	private static UserBean mInstance;
	public static final String Tag = "UserBean";
	

	@Override
	public String toString() {
		return   new Gson().toJson(this);
	}
	
	public static UserBean getInstance(Context context) {
		if (mInstance == null) {
			//TODO 使用SharedPreferencesUtil.ReadString 会发现数据 读不完整情况
//			String temp = SharedPreferencesUtil.ReadString(context,
//					UserBean.Tag);
//			String temp=SharedPreferencesUtil.getInstance(context).getString(Tag);
//			if(!TextUtils.isEmpty(temp)){
//				mInstance = GsonUtil.fromJson(temp, UserBean.class);	
//			}
		}
		return mInstance;
	}
	
	public static void updateUserBean(Context mContext,UserBean userbean){
		SharedPreferencesUtil.getInstance(mContext).putString(Tag, userbean.toString());
		mInstance=null;
	}

	public boolean getMobileRptUsable() {
		return mobile_rpt_usable;
	}

	public void setMobileRptUsable(boolean mobileRptUsable) {
		this.mobile_rpt_usable = mobileRptUsable;
	}

	
	public String getPostId() {
		return post_id;
	}

	public void setPostId(String postId) {
		post_id = postId;
	}

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

	public String getPositionId() {
		return position_id;
	}

	public void setPositionId(String positionId) {
		position_id = positionId;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int Level) {
		this.level = Level;
	}
	
	public String getLevelName()
	{
		if (level_name == null) {
			level_name = "";
		}
		return level_name;
	}
	
	public void setLevelName(String levelName)
	{
		this.level_name = levelName;
	}

//	public String getUserAccount() {
//		return userAccount;
//	}
//
//	public void setUserAccount(String userAccount) {
//		this.userAccount = userAccount;
//	}
//
//	public int getIsOnline() {
//		return isOnline;
//	}
//
//	public void setIsOnline(int isOnline) {
//		this.isOnline = isOnline;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public UserBean(){}


	@Override
	public boolean equals(Object o) {
		return hashCode() == o.hashCode();
	}

	@Override
	public int hashCode() {
		return user_id.hashCode();
	}

	/** 联系人选择器使用 */
	public int getContactID(){
		return  user_id.hashCode();
	}

	public String getPersonId() {
		return user_id;
	}

	public void setPersonId(String PersonId) {
		this.user_id = PersonId;
	}

	public String getPersonName() {
		return person_name;
	}

	public void setPersonName(String PersonName) {
		if(!TextUtils.isEmpty(PersonName)){
			this.person_name = PersonName;			
		}
	}

	public String getNickName() {
		return nick_name;
	}

	public void setNickName(String NickName) {
		if(!TextUtils.isEmpty(NickName)){
			this.nick_name = NickName;
		}
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int Sex) {
		if(Sex > 0){
			this.sex = Sex;			
		}
	}
	/**
	 * 获取区域信息
	 * @return
	 */
	public String getArea() {
		return phone;
	}

	public  void setArea(String Area){
		this.phone = Area;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(!TextUtils.isEmpty(email)){
			email = email;
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String Phone) {
		if(!TextUtils.isEmpty(Phone)){
			this.phone = Phone;
		}
	}

	public String getPositionName() {
		return position_name;
	}

	public void setPositionName(String PositionName) {
		if(!TextUtils.isEmpty(PositionName)){
			this.position_name = PositionName;
		}
	}

	public float getStudyIntegral() {
		return credit;
	}

	public void setStudyIntegral(float Credit) {
		if(Credit > 0){
			this.credit = Credit;
		}
	}

	public long getGoldCoin() {
		return gold;
	}

	public void setGoldCoin(long _Gold) {
		if(_Gold > 0){
			this.gold = _Gold;
		}
	}

	public int getAge() {
		return age;
	}

	public void setAge(int Age) {
		if(Age > 0){
			this.age = Age;
		}
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String Birthday) {
		this.birthday = Birthday;
	}

	public String getDeptID() {
		return dept_id;
	}

	public void setDeptID(String DeptID) {
		this.dept_id = DeptID;
	}

	public String getDeptName() {
		return dept_name;
	}

	public void setDeptName(String DeptName) {
		this.dept_name = DeptName;
	}

    public String getHeaderUrl() {
        return header_url;
    }

    public void setHeaderUrl(String headerUrl) {
        header_url = headerUrl;
    }
    
    public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public long getMax_exp() {
		return max_exp;
	}

	public void setMax_exp(long max_exp) {
		this.max_exp = max_exp;
	}
	
	
}
