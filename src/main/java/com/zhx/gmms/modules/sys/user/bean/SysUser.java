package com.zhx.gmms.modules.sys.user.bean;

import com.zhx.gmms.frame.BaseEntity;

/**
 * 用户信息
 * @author xwl
 */
public class SysUser extends BaseEntity {

	private String id;//用户id
	private String userCode;//登录名称
	private String userName;//用户昵称
	private String password;//登录密码
	private String nickName;
	private String type;
	private String phone;//用户手机
	private String mail;//用户邮箱
	private String sex;//用户性别
	private String age;//用户年龄
	private String useStatus;//用户状态
	private String lastLoginTime;//上次登录时间
	private String loginTotal;//登录次数
	private String themeId;//系统主题id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLoginTotal() {
		return loginTotal;
	}
	public void setLoginTotal(String loginTotal) {
		this.loginTotal = loginTotal;
	}
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", userCode=" + userCode + ", userName="
				+ userName + ", password=" + password + ", nickName="
				+ nickName + ", type=" + type + ", phone=" + phone + ", mail="
				+ mail + ", sex=" + sex + ", age=" + age + ", useStatus="
				+ useStatus + ", lastLoginTime=" + lastLoginTime
				+ ", loginTotal=" + loginTotal + ", themeId=" + themeId + "]";
	}
	
}
