package com.appinfo.service.dictionary;

import java.util.List;

import com.appinfo.entity.Dictionary;

public interface DictionaryService {
	/**
	 * 根据typeCode查找下拉框信息
	 * @param typeCode
	 * @return
	 */
	List<Dictionary> queryByTypeCode(String typeCode);
}
