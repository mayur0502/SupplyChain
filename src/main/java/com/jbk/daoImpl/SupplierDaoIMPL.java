package com.jbk.daoImpl;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.Supplier;

@Repository
public class SupplierDaoIMPL implements SupplierDao {

	@Autowired
    private	SessionFactory sf;
	
	@Override
	public boolean saveSupplier(Supplier supplier) {
		Session session= null;
		boolean isAdded=false;
		try {
			session= sf.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(supplier);
			transaction.commit();
			isAdded=true;
		}catch (PersistenceException e) {
			e.printStackTrace();
			isAdded=false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
		return isAdded;
	}
	
	

}
