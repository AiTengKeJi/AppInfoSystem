package com.appinfo.dao.version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.appinfo.entity.Version;

public interface VersionMapper {
	   List <Version> getVersionList(@Param("appId") Integer appId);
	   
	   Version getVersionById(@Param("id") Integer id);
	   
	   int addVersion(Version version);
	   
	   int modifyVersion(Version version);
	   
	   int updateVersionFileById(@Param("id") Integer id);
	   
	   int deleteVersionByAppId(@Param("appId") Integer appId);
}
