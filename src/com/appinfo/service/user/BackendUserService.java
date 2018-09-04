package com.appinfo.service.user;

import com.appinfo.entity.BackendUser;

public interface BackendUserService {
	/**
	 * 检查登录名是否存在
	 * @param userCode
	 * @return
	 */
	Integer checkUserCode( String userCode);
	
	/**
	 * 检查登录名是否存在
	 * @param userCode
	 * @return
	 */
	BackendUser checkLogin(String userCode,String userPassword);
}
