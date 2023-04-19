package com.jbk.model;

import com.jbk.entity.Category;
import com.jbk.entity.Supplier;

public class FinalProduct {

	private String ProductId;
	private String ProductName;
	private Supplier supplier;
	private Category category;
	private int productQty;
	private double productPrice;
	private Charges charges;
	private double DiscountAmount;
	private double FinalProductPrice;

	public FinalProduct() {
	
	}

	public FinalProduct(String productId, String productName, Supplier supplier, Category category, int productQty,
			double productPrice, Charges charges, double discountAmount, double finalProductPrice) {
		super();
		ProductId = productId;
		ProductName = productName;
		this.supplier = supplier;
		this.category = category;
		this.productQty = productQty;
		this.productPrice = productPrice;
		this.charges = charges;
		DiscountAmount = discountAmount;
		FinalProductPrice = finalProductPrice;
	}
	
	public String getProductId() {
		return ProductId;
	}
	public void setProductId(String productId) {
		ProductId = productId;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
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
	public Charges getCharges() {
		return charges;
	}
	public void setCharges(Charges charges) {
		this.charges = charges;
	}
	public double getDiscountAmount() {
		return DiscountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		DiscountAmount = discountAmount;
	}
	public double getFinalProductPrice() {
		return FinalProductPrice;
	}
	public void setFinalProductPrice(double finalProductPrice) {
		FinalProductPrice = finalProductPrice;
	}

	@Override
	public String toString() {
		return "FinalProduct [ProductId=" + ProductId + ", ProductName=" + ProductName + ", supplier=" + supplier
				+ ", category=" + category + ", productQty=" + productQty + ", productPrice=" + productPrice
				+ ", charges=" + charges + ", DiscountAmount=" + DiscountAmount + ", FinalProductPrice="
				+ FinalProductPrice + "]";
	}

	
}
