package com.appinfo.service.version;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appinfo.dao.version.VersionMapper;
import com.appinfo.entity.Version;

@Service
public class VersionServiceImpl implements VersionService {
	@Autowired
	VersionMapper  bersion;
	@Override
	public List<Version> apVersionList(Integer appId) {
		// TODO Auto-generated method stub
		return bersion.apVersionList(appId);
	}
	@Override
	public Version versionQuery(Integer id) {
		// TODO Auto-generated method stub
		return bersion.versionQuery(id);
	}
	
}
	



