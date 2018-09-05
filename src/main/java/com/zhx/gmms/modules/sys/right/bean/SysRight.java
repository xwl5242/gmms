package com.zhx.gmms.modules.sys.right.bean;

import java.util.List;

import com.zhx.gmms.frame.BaseEntity;

public class SysRight extends BaseEntity {

	private String id;
	private String pid;
	private String rightName;
	private String rightUrl;
	private String rightDesc;
	private String icon;
	private String isLeaf;
	private Integer seq;

	//查询某角色下是否有某权限时使用，用来标记是否选中
	private String state;
	
	private List<SysRight> childNode;

	public SysRight() {
	}

	public SysRight(String id, String pid, String rightName, String rightUrl,
			String rightDesc) {
		this.id = id;
		this.pid = pid;
		this.rightName = rightName;
		this.rightUrl = rightUrl;
		this.rightDesc = rightDesc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightUrl() {
		return rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}

	public String getRightDesc() {
		return rightDesc;
	}

	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}

	public List<SysRight> getChildNode() {
		return childNode;
	}

	public void setChildNode(List<SysRight> childNode) {
		this.childNode = childNode;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "SysRight [id=" + id + ", pid=" + pid + ", rightName="
				+ rightName + ", rightUrl=" + rightUrl + ", rightDesc="
				+ rightDesc + ", icon=" + icon + ", isLeaf=" + isLeaf
				+ ", seq=" + seq + ", state=" + state + ", childNode="
				+ childNode + "]";
	}
	
}
