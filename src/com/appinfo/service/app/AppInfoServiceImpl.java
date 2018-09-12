package com.appinfo.service.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appinfo.dao.app.AppInfoMapper;
import com.appinfo.dao.version.VersionMapper;
import com.appinfo.entity.AppInfo;
import com.appinfo.entity.Version;

@Service
public class AppInfoServiceImpl implements AppInfoService {
	
	@Autowired
	AppInfoMapper aim;
	@Autowired
	VersionMapper verMap;
	
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

	@Override
	public boolean upperAndLower(Integer appId,Integer modifyId,Integer versionId) {
		AppInfo appInfo = aim.getAppInfoById(appId);
		switch(appInfo.getStatus()) {
			case 2:
			case 5:
				this.updateAppStatus(appId,modifyId,4);
				this.updateVersionStatus(versionId, modifyId,2);
				break;
			case 4:
				this.updateAppStatus(appId,modifyId,5);
				break;
			default:
				return false;
		}
		return true;
	}
	
	
	//修改appinfo的状态
	public void updateAppStatus(Integer appId,Integer modifyId,Integer status) {
		AppInfo appInfo = new AppInfo();
		appInfo.setId(appId);
		appInfo.setModifyBy(modifyId);
		appInfo.setStatus(status);
		this.aim.modify(appInfo);
	}
	//修改app版本信息
	public void updateVersionStatus(Integer versionId,Integer modifyId,Integer publishStatus) {
		Version version = new Version();
		version.setId(versionId);
		version.setPublishStatus(publishStatus);
		version.setModifyBy(modifyId);
		verMap.modifyVersion(version);
	}
	@Override
	public int updateSatus(Integer id,Integer status) {
		// TODO Auto-generated method stub
		return aim.updateSatus(id, status);
	}
}
