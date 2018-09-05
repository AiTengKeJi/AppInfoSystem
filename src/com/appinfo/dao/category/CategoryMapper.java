package com.appinfo.dao.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.appinfo.entity.Category;

public interface CategoryMapper {
	
	/**
	 * 根据父类分类id查找分类集合
	 * @param parentId
	 * @return
	 */
	List<Category> getCategoryByParentId(@Param("parentId")Integer parentId );
}
