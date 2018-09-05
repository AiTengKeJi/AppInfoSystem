package com.appinfo.dao.appinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.appinfo.entity.AppInfo;

public interface AppInfoMapper {
	
	
	/**
	 * 查询满足条件的APPinfo总记录数
	 * @return
	 */
	Integer getAppInfoCount(@Param("softName")String softName,@Param("status")Integer status,
						@Param("flatformId")Integer flatformId,@Param("cid1")Integer cid1,
						@Param("cid2")Integer cid2,@Param("cid3")Integer cid3);
	
	/**
	 * 查询满足条件的appinfo列表
	 */
	List<AppInfo> getAppinfoList();
}
