package com.jbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entity.Supplier;
import com.jbk.service.SupplierService;



@RestController
@RequestMapping(value="/supplier")
public class SupplierController {
	
	@Autowired
	SupplierService service;
	
	@PostMapping(value="/save-supplier")
	public ResponseEntity<Boolean> saveSupplier(@RequestBody Supplier supplier){
		boolean isAdded = service.saveSupplier(supplier);
		if(isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Boolean>(isAdded,HttpStatus.ALREADY_REPORTED);
		}
		
		
	}

}
