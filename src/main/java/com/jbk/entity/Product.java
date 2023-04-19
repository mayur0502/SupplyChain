package com.jbk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class Product {

	@Id
	@Column(unique = true, nullable = false)
	private String productId;

	@NotEmpty(message = "Product Name Is Required")
	@Column(unique = true, nullable = false)
	private String productName;

	@OneToOne
	@Min(1)
	private Supplier supplier;

	@OneToOne
	@Min(1)
	private Category category;

	@Column(nullable = false)
	@Min(1)
	private int productQty;
	
	@Column(nullable = false)
	@Min(1)
	private double productPrice;

	public Product() {
		super();
	}

	public Product(String productId, @NotEmpty(message = "Product Name Is Required") String productName,
			@Min(1) Supplier supplier, @Min(1) Category category, @Min(1) int productQty, @Min(1) double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.supplier = supplier;
		this.category = category;
		this.productQty = productQty;
		this.productPrice = productPrice;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", supplier=" + supplier
				+ ", category=" + category + ", productQty=" + productQty + ", productPrice=" + productPrice + "]";
	}


}
