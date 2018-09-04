package com.appinfo.service.user;


import org.springframework.beans.factory.annotation.Autowired;

import com.appinfo.dao.user.DevUserMapper;
import com.appinfo.entity.DevUser;

import org.springframework.stereotype.Service;

@Service
public class DevUserServiceImpl implements DevUserService {
	@Autowired
	DevUserMapper devUserMapper;
	@Override
	public Integer devUserCode(String devCode) {
		
		return devUserMapper.devUserCode(devCode);
	}

	@Override
	public DevUser devLogin(String devCode, String devPassword) {
		
		return devUserMapper.devLogin(devCode, devPassword);
	}

}
