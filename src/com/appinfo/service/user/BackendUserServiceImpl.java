package com.appinfo.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appinfo.dao.user.BackendUserMapper;
import com.appinfo.entity.BackendUser;


@Service
public class BackendUserServiceImpl implements BackendUserService {
	
	@Autowired
	BackendUserMapper backendUserMapper;
	
	/**
	 * 检查登录名是否存在
	 * @param userCode
	 * @return
	 */
	public Integer checkUserCode( String userCode){
		return backendUserMapper.checkUserCode(userCode);
	}
	
	/**
	 * 检查登录名是否存在
	 * @param userCode
	 * @return
	 */
	public BackendUser checkLogin( String userCode,String userPassword) {
		return backendUserMapper.checkLogin(userCode, userPassword);
	}
}
