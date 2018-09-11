package com.appinfo.service.app;

import java.util.List;

import com.appinfo.entity.AppInfo;

public interface AppInfoService {
	/**
	 * 查询满足条件的APPinfo总记录数
	 * @return
	 */
	Integer getAppInfoCount(String softName,Integer status,Integer flatformId,
			 				Integer cid1,Integer cid2,Integer cid3);
	
	/**
	 * 查询满足条件的appinfo列表
	 */
	List<AppInfo> getAppinfoList(String softName,Integer status,Integer flatformId,Integer cid1,
								Integer cid2,Integer cid3,Integer rows,
								Integer offset,Integer devId);
	
	/**
	 * 检查APKName是否存在
	 * @param offset
	 * @return
	 */
	Integer checkAPKName(String APKName);
	
	/**
	 * 保存app信息
	 * @param appinfo
	 * @return
	 */
	int saveAppInfo(AppInfo appInfo);
	
	/**
	 * 修改app信息
	 * @param appInfo
	 * @return
	 */
	int modify(AppInfo appInfo);
	/**
	 * 根据id删除app信息
	 * @param id
	 * @return
	 */
	int deleteAppInfoById(Integer id);
	/**
	 * 删除applogo
	 * @param id
	 * @return
	 */
	int deleteAppLogo(Integer id);
	
	/**
	 * 更新app状态信息
	 * @param id
	 * @return
	 */
	int updateSatus(Integer id);
	
	/**
	 * 根据id或者APP信息
	 * @param id
	 * @return
	 */
	AppInfo getAppInfoById(Integer id);
	
	boolean upperAndLower(Integer id,Integer modifyId,Integer versionId);
}
