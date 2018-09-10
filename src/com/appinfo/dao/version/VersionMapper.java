package com.appinfo.dao.version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.appinfo.entity.Version;

public interface VersionMapper {
	   List <Version> apVersionList(@Param("appId") Integer appId);
	   Version versionQuery(@Param("id") Integer id);
}
