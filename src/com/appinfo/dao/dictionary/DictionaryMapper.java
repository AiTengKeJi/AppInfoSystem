package com.appinfo.dao.dictionary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.appinfo.entity.Dictionary;

public interface DictionaryMapper {
	
	/**
	 * 根据typeCode查找下拉框信息
	 * @param typeCode
	 * @return
	 */
	List<Dictionary> queryByTypeCode(@Param("typeCode")String typeCode);
}
