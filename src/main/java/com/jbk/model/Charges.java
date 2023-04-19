package com.jbk.model;

public class Charges {

	private int gst;
	private float deliveryCharge;

	
	
	public Charges() {
		super();
	}
	public Charges(int gst, float deliveryCharge) {
		super();
		this.gst = gst;
		this.deliveryCharge = deliveryCharge;
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
		return "Charges [gst=" + gst + ", deliveryCharge=" + deliveryCharge + "]";
	}	
	
}
