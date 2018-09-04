package com.appinfo.dao.user;

import org.apache.ibatis.annotations.Param;

import com.appinfo.entity.BackendUser;

public interface BackendUserMapper {
	
	
	/**
	 * 检查登录名是否存在
	 * @param userCode
	 * @return
	 */
	Integer checkUserCode(@Param("userCode") String userCode);
	
	/**
	 * 检查登录名是否存在
	 * @param userCode
	 * @return
	 */
	BackendUser checkLogin(@Param("userCode") String userCode,
						  @Param("userPassword") String userPassword);
}
