package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

	@Id
	@Column(unique = true, nullable = false)
	private int categoryId;

	@Column(unique = true, nullable = false)
	private String categoryName;
	@Column(nullable = false)
	private String discription;
	@Column(nullable = false)
	private int discount;
	@Column(nullable = false)
	private int gst;
	@Column(nullable = false)
	private float deliveryCharge;

	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(int categoryId, String categoryName, String discription, int discount, int gst,
			float deliveryCharge) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.discription = discription;
		this.discount = discount;
		this.gst = gst;
		this.deliveryCharge = deliveryCharge;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getGst() {
		return gst;
	}

	public void setGst(int gst) {
		this.gst = gst;
	}

	public float getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", discription=" + discription
				+ ", discount=" + discount + ", gst=" + gst + ", deliveryCharge=" + deliveryCharge + "]";
	}

}
