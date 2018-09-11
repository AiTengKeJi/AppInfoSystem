package com.appinfo.utils;

public class CommonString {
	public static final String APP_STATUS = "APP_STATUS";
	public static final String USER_TYPE = "USER_TYPE";
	public static final String APP_FLATFORM = "APP_FLATFORM";
	public static final String PUBLISH_STATUS = "PUBLISH_STATUS";
	public static final String USER_SESSION = "userSession";
	public static final String DEV_USER_SESSION = "devUserSession";
	public static final String [] LOGO_SUFFIX_NAMES= {"jpg","png","jpeg","pneg"};
	public static final String [] APK_SUFFIX_NAMES= {"apk"};
	
	/**
	 * 检查logo图片文件后缀名是否合法
	 * @param suffixName
	 * @return
	 */
	public static boolean checkLogoSuffixName(String suffixName,String [] rules) {
		for(String str : rules) {
			if(str.equalsIgnoreCase(suffixName))
				return true;
		}
		return false;
	}
}
