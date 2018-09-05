package com.zhx.gmms.frame;


/**
 * base实体类，封装page信息和其他基础信息
 * @author xwl
 */
public class BaseEntity {

	private String isDel;//是否删除
	private String creator;//创建者
	private String createTime;//创建时间
	private String updator;//更新着
	private String updateTime;//更新时间
	
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "BaseEntity [isDel=" + isDel + ", creator=" + creator + ", createTime="
				+ createTime + ", updator=" + updator + ", updateTime="
				+ updateTime + "]";
	}
	
}
