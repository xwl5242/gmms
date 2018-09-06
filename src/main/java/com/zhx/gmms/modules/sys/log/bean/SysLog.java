package com.zhx.gmms.modules.sys.log.bean;

import com.zhx.gmms.modules.sys.user.UserUtils;
import com.zhx.gmms.utils.DateUtils;

public class SysLog {

	private String id;
	private String className;
	private String methodName;
	private String remoteIp;
	private String reqUri;
	private String creator;
	private String createTime;
	
	public SysLog(){
		
	}
	
	public SysLog(String id,String className,String methodName,String remoteIp,String reqUri){
		this.id = id;
		this.className = className;
		this.methodName = methodName;
		this.remoteIp = remoteIp;
		this.reqUri = reqUri;
		this.creator = UserUtils.getCurUserId();
		this.createTime = DateUtils.now2yyyyMMddHHmmssStr();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public String getReqUri() {
		return reqUri;
	}
	public void setReqUri(String reqUri) {
		this.reqUri = reqUri;
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
	@Override
	public String toString() {
		return "SysLog [id=" + id + ", className=" + className
				+ ", methodName=" + methodName + ", remoteIp=" + remoteIp
				+ ", reqUri=" + reqUri + ", creator=" + creator
				+ ", createTime=" + createTime + "]";
	}
	
}
