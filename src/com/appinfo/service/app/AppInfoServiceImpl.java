package com.appinfo.service.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appinfo.dao.app.AppInfoMapper;
import com.appinfo.entity.AppInfo;

@Service
public class AppInfoServiceImpl implements AppInfoService {
	
	@Autowired
	AppInfoMapper aim;
	
	@Override
	public Integer getAppInfoCount(String softName, Integer status, Integer flatformId, Integer cid1, Integer cid2,
			Integer cid3) {
		return aim.getAppInfoCount(softName, status, flatformId, cid1, cid2, cid3);
	}

	@Override
	public List<AppInfo> getAppinfoList(String softName, Integer status, Integer flatformId, Integer cid1, Integer cid2,
			Integer cid3, Integer rows, Integer offset,Integer devId) {
		return aim.getAppinfoList(softName, status, flatformId, cid1, cid2, cid3, rows, offset,devId);
	}
	
	/**
	 * 检查APKName是否存在
	 * @param offset
	 * @return
	 */
	@Override
	public Integer checkAPKName(String APKName) {
		return aim.checkAPKName(APKName);
	}

	@Override
	public int saveAppInfo(AppInfo appInfo) {
		return aim.saveAppInfo(appInfo);
	}

	@Override
	public int modify(AppInfo appInfo) {
		return aim.modify(appInfo);
	}

	@Override
	public int deleteAppInfoById(Integer id) {
		return aim.deleteAppInfoById(id);
	}

	@Override
	public int deleteAppLogo(Integer id) {
		return aim.deleteAppLogo(id);
	}

	@Override
	public int updateSatus(Integer id) {
		return aim.updateSatus(id);
	}
	
	@Override
	public AppInfo getAppInfoById(Integer id) {
		return aim.getAppInfoById(id);
	}
	
	
}
