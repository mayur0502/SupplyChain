package com.jbk.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.Supplier;
import com.jbk.service.SupplierService;

@Service
public class SupplierServiceIMPL implements SupplierService {
    
	@Autowired
	private SupplierDao dao;
	
	@Override
	public boolean saveSupplier(Supplier supplier) {
		boolean added = dao.saveSupplier(supplier);
		return added;
	}

}
