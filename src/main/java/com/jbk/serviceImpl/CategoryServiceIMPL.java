package com.jbk.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.CategoryDao;
import com.jbk.entity.Category;
import com.jbk.service.CategoryService;

@Service
public class CategoryServiceIMPL implements CategoryService {

	@Autowired
	private CategoryDao dao;

	@Override
	public boolean saveCategory(Category category) {
		boolean isAdded = dao.saveCategory(category);
		return isAdded;
	}

}
