package com.appinfo.service.version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.appinfo.entity.Version;

public interface VersionService {
	List <Version> apVersionList(@Param("appId") Integer appId);
	 Version versionQuery(@Param("id") Integer id);
}
