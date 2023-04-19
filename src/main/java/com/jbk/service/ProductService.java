package com.jbk.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.entity.Product;
import com.jbk.model.FinalProduct;


public interface ProductService {
	
	public boolean saveProduct(Product product);
	public Product getProductById(String productId);
	public List<Product> getAllProducts();
	public boolean deleteProductById(String productId);
	public boolean updateProduct(Product product);
	
	public List<Product> sortProductsById_ASC();

	public List<Product> sortProductsByName_DESC();

	public List<Product> getMaxPriceProducts();
	
	public double getMaxPrice();

	public double countSumOfProductPrice();

	public int getTotalCountOfProducts();

	public FinalProduct getFinalProductById(String productId);
	
	public String uploadSheet(MultipartFile myFile);
	public String exportToExcel(HttpSession session);
}
