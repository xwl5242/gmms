package com.zhx.gmms.modules.sys.role.bean;

import com.zhx.gmms.frame.BaseEntity;

public class SysRole extends BaseEntity {

	private String id;
	private String roleName;
	private String roleDesc;
	private String isDel;
	private String creator;
	private String createTime;
	private String updator;
	private String updateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
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
		return "Role=[id:"+this.id+",roleName:"+this.roleName+",roleDesc:"+this.roleDesc
				+",isDel:"+this.isDel+",creator:"+this.creator+",createTime:"+this.createTime
				+",updator:"+this.updator+",updateTime:"+this.updateTime+"]";
	}
	
}
