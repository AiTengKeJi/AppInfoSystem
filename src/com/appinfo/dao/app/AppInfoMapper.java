package com.appinfo.dao.app;

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
	List<AppInfo> getAppinfoList(@Param("softName")String softName,@Param("status")Integer status,
			@Param("flatformId")Integer flatformId,@Param("cid1")Integer cid1,
			@Param("cid2")Integer cid2,@Param("cid3")Integer cid3,@Param("rows")Integer rows,
			@Param("offset")Integer offset,@Param("devId") Integer devId);
	
	/**
	 * 检查APKName是否存在
	 * @param offset
	 * @return
	 */
	Integer checkAPKName(@Param("APKName")String APKName);
	
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
	int deleteAppInfoById(@Param("id")Integer id);
	/**
	 * 删除applogo
	 * @param id
	 * @return
	 */
	int deleteAppLogo(@Param("id")Integer id);
	
	/**
	 * 更新app状态信息
	 * @param id
	 * @return
	 */
	int updateSatus(@Param("id")Integer id);
	
	/**
	 * 根据id或者APP信息
	 * @param id
	 * @return
	 */
	AppInfo getAppInfoById(@Param("id")Integer id);
	
	/**
	 * 更新app状态信息
	 * @param id
	 * @return
	 */
	int updateSatus(@Param("id")Integer id,@Param("status")Integer status);
}
