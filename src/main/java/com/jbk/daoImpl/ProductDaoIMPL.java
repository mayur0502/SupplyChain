package com.jbk.daoImpl;

import java.util.Comparator;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;

@Repository
public class ProductDaoIMPL implements ProductDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public boolean saveProduct(Product product) {
		boolean isAdded = false;
		Session session = null;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(product);
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

	@Override
	public Product getProductById(String productId) {
		Product product = null;
		Session session = null;
		try {
			session = sf.openSession();

			product = session.get(Product.class, productId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return product;
	}

	@Override
	public List<Product> getAllProducts() {

		Session session = null;
		List<Product> list = null;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public boolean deleteProductById(String productId) {
		Session session = null;
		boolean isDeleted = false;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();

			Product product = session.get(Product.class, productId);
			if (product != null) {
				session.delete(product);
				transaction.commit();
				isDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product) {
		Session session = null;
		boolean isUpdated = false;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			Product dbProduct = getProductById(product.getProductId());
			if (dbProduct != null) {
				session.update(product);
				transaction.commit();
				isUpdated = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isUpdated;
	}

	@Override
	public List<Product> sortProductsById_ASC() {
		Session session = null;
		List<Product> list = null;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("productId"));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<Product> sortProductsByName_DESC() {
		Session session = null;
		List<Product> list = null;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.desc("productName"));
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<Product> getMaxPriceProducts() {
		Session session = null;
		List<Product> list = null;
		try {
			double maxPrice = getMaxPrice();
			if (maxPrice > 0) {
	    session = sf.openSession();
				Criteria criteria = session.createCriteria(Product.class);
				criteria.add(Restrictions.eq("productPrice", maxPrice));
				list = criteria.list();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;

	}

	@Override
	public double countSumOfProductPrice() {
		Session session = null;
		List<Double> list = null;
		double sumOfPrice = 0;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.sum("productPrice"));
			list = criteria.list();
			if (!list.isEmpty()) {
				sumOfPrice = list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return sumOfPrice;
	}

	@Override
	public int getTotalCountOfProducts() {
		Session session = null;
		List<Integer> list = null;
		int totalProducts = 0;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.rowCount());
			list = criteria.list();
			if (!list.isEmpty()) {
				totalProducts = list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return totalProducts;
			}	

	@Override
	public double getMaxPrice() {
		Session session = null;
		List<Double> list = null;
		double maxPrice = 0;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.max("productPrice"));
			list = criteria.list();
			if (!list.isEmpty()) {
				maxPrice = list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return maxPrice;
	}

	@Override
	public String uploadProducts(List<Product> list) {
		Session session = null;
		int addedCount = 0;
		int excludedCount =0;


			for (Product product : list) {
				try {
				session = sf.openSession();
				session.save(product);
				session.beginTransaction().commit();
				addedCount++;
			}
		catch(PersistenceException e) {
			excludedCount++;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session  !=null) {
				session.close();
			}
		}
		}
		
		return "Added = "+addedCount+"Excluded = "+excludedCount;
	}

}
