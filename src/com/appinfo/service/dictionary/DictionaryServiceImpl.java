package com.appinfo.service.dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appinfo.dao.dictionary.DictionaryMapper;
import com.appinfo.entity.Dictionary;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	@Autowired
	DictionaryMapper dm;
	
	/**
	 * 根据typeCode查找下拉框信息
	 * @param typeCode
	 * @return
	 */
	public List<Dictionary> queryByTypeCode(String typeCode){
		return dm.queryByTypeCode(typeCode);
	}
}
