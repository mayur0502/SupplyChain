package com.jbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entity.Category;
import com.jbk.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	CategoryService service;

	@PostMapping(value = "/save-category")
	public ResponseEntity<Boolean> saveCategory(@RequestBody Category category) {
		boolean isAdded = service.saveCategory(category);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {

			return new ResponseEntity<Boolean>(isAdded, HttpStatus.ALREADY_REPORTED);
		}

	}

}