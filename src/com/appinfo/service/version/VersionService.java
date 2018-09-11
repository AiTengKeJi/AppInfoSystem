package com.appinfo.service.version;

import java.util.List;

import com.appinfo.entity.Version;

public interface VersionService {
	
	List<Version> getVersionList(Integer appId);

	Version getVersionById(Integer id);
	
	int addVersion(Version version);
	   
	int modifyVersion(Version version);
	
	int updateVersionFileById(Integer id);
	
	int deleteVersionByAppId(Integer appId);
}
