package com.appinfo.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appinfo.dao.category.CategoryMapper;
import com.appinfo.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryMapper cm;
	/**
	 * 根据父类分类id查找分类集合
	 * @param parentId
	 * @return
	 */
	public List<Category> getCategoryByParentId(Integer parentId ){
		return cm.getCategoryByParentId(parentId);
	}
}
