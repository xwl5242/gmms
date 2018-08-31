package com.zhx.gmms.modules.sys.right.bean;

import java.util.List;

/**
 * 新增权限也就是菜单时的vo类
 * @author xwl
 */
public class MenuData {

	private String id;
	private String pid;
	private String url;
	private String text;
	private String icon;
	private String type;
	private List<Auth> auth;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Auth> getAuth() {
		return auth;
	}
	public void setAuth(List<Auth> auth) {
		this.auth = auth;
	}
	@Override
	public String toString() {
		return "MenuData [id=" + id + ", pid=" + pid + ", url=" + url
				+ ", text=" + text + ", icon=" + icon + ", type=" + type
				+ ", auth=" + auth + "]";
	}
	public static class Auth{
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "Auth [id=" + id + "]";
		}
		
	}
}
