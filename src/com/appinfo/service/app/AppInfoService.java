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
}
