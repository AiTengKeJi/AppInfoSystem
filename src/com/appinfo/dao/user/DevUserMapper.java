package com.appinfo.dao.user;

import org.apache.ibatis.annotations.Param;

import com.appinfo.entity.DevUser;



public interface DevUserMapper {
	/**
	 * 检查登录名是否存在
	 * @param userCode
	 * @return
	 */
	Integer devUserCode(@Param("devCode")String devCode);
	/**
	 * 检查登陆名或密码是否存在
	 * @param devCode
	 * @param devPassword
	 * @return
	 */
	DevUser devLogin(@Param("devCode") String devCode,
			  @Param("devPassword") String devPassword);
}
