package com.example.myadapter;

import android.graphics.drawable.Drawable;

public class ListInfo {
	private Drawable iconName;
	private String AppName;
	private String Permission;
	public Drawable getIconName() {
		return iconName;
	}
	public void setIconName(Drawable iconName) {
		this.iconName = iconName;
	}
	public String getAppName() {
		return AppName;
	}
	public void setAppName(String appName) {
		AppName = appName;
	}
	public String getPermission() {
		return Permission;
	}
	public void setPermission(String permission) {
		Permission = permission;
	}	
}
