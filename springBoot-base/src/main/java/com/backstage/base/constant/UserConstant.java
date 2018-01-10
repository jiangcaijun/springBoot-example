package com.backstage.base.constant;

public class UserConstant {
	/**
	 * 用户登录后保存在session中的KEY
	 */
	public static final String USER_INFO = "userInfo";
	public static final String USER_ID = "userID";
	/**
	 * 用户权限信息保存在session中的KEY，格式：List<Permission>
	 */
	public static final String PERMISSION_TREE = "permissionTree_";
	/**
	 * 用户权限code保存在session中的KEY，格式：Set<String>
	 */
	public static final String PERMISSION_CODE = "permissionCode_";
	/**
	 * 用户按钮权限code保存在session中的KEY，格式：Set<String>
	 */
	public static final String PERMISSION_BCODE = "permissionBCode_";
	/**
	 * 用户按钮权限code保存在session中的KEY，格式：Set<String>
	 */
	public static final String MENU_ARRY = "menuArry_";
	/**
	 * Monitor用户保存在REDIS中的超时时间（分钟）
	 */
	public static final long MORUSER_OUTIME = 30L;
	/**
	 * 用户默认密码
	 */	public static final String PASS = "11111111";

}
