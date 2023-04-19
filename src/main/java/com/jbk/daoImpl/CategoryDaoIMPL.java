package com.jbk.daoImpl;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.CategoryDao;
import com.jbk.entity.Category;

@Repository
public class CategoryDaoIMPL implements CategoryDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public boolean saveCategory(Category category) {
		boolean isAdded = false;
		Session session = null;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(category);
			transaction.commit();
			isAdded = true;
		} catch (PersistenceException e) {
			e.printStackTrace();
			isAdded = false;
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isAdded;
	}

}
