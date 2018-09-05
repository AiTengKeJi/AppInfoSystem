package com.appinfo.service.category;

import java.util.List;

import com.appinfo.entity.Category;

public interface CategoryService {
	/**
	 * 根据父类分类id查找分类集合
	 * @param parentId
	 * @return
	 */
	List<Category> getCategoryByParentId(Integer parentId );
}
