package com.zhx.gmms.modules.sys.theme.bean;

public class SysTheme {

	private String id;
	private String sidebar;
	private String navbar;
	private String navbarInverse;
	private String themeColor;
	private String menuDisplay;
	private String menuTxtIcon;
	private String tabFlag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSidebar() {
		return sidebar;
	}
	public void setSidebar(String sidebar) {
		this.sidebar = sidebar;
	}
	public String getNavbar() {
		return navbar;
	}
	public void setNavbar(String navbar) {
		this.navbar = navbar;
	}
	public String getNavbarInverse() {
		return navbarInverse;
	}
	public void setNavbarInverse(String navbarInverse) {
		this.navbarInverse = navbarInverse;
	}
	public String getThemeColor() {
		return themeColor;
	}
	public void setThemeColor(String themeColor) {
		this.themeColor = themeColor;
	}
	public String getMenuDisplay() {
		return menuDisplay;
	}
	public void setMenuDisplay(String menuDisplay) {
		this.menuDisplay = menuDisplay;
	}
	public String getMenuTxtIcon() {
		return menuTxtIcon;
	}
	public void setMenuTxtIcon(String menuTxtIcon) {
		this.menuTxtIcon = menuTxtIcon;
	}
	public String getTabFlag() {
		return tabFlag;
	}
	public void setTabFlag(String tabFlag) {
		this.tabFlag = tabFlag;
	}
	@Override
	public String toString() {
		return "SysTheme [id=" + id + ", sidebar=" + sidebar + ", navbar="
				+ navbar + ", navbarInverse=" + navbarInverse + ", themeColor="
				+ themeColor + ", menuDisplay=" + menuDisplay
				+ ", menuTxtIcon=" + menuTxtIcon + ", tabFlag=" + tabFlag + "]";
	}
	
}
