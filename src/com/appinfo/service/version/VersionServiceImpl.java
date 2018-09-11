package com.appinfo.service.version;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appinfo.dao.version.VersionMapper;
import com.appinfo.entity.Version;

@Service
public class VersionServiceImpl implements VersionService {
	@Autowired
	VersionMapper  versionMapper;
	
	@Override
	public List<Version> getVersionList(Integer appId) {
		return versionMapper.getVersionList(appId);
	}
	@Override
	public Version getVersionById(Integer id) {
		return versionMapper.getVersionById(id);
	}
	
	@Override
	public int addVersion(Version version) {
		return versionMapper.addVersion(version);
	}
	@Override
	public int modifyVersion(Version version) {
		return versionMapper.modifyVersion(version);
	}
	@Override
	public int updateVersionFileById(Integer id) {
		return versionMapper.updateVersionFileById(id);
	}
	@Override
	public int deleteVersionByAppId(Integer appId) {
		return versionMapper.deleteVersionByAppId(appId);
	}
	
}
	



