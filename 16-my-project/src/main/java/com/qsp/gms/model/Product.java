package com.qsp.gms.model;

public class Product {
	private int p_id;
	private String p_name;
	private int p_price;
	private int p_quantity;
	private boolean p_availabilityl;
	
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_quantity() {
		return p_quantity;
	}
	public void setP_quantity(int p_quantity) {
		this.p_quantity = p_quantity;
	}
	public boolean isP_availabilityl() {
		return p_availabilityl;
	}
	public void setP_availabilityl(boolean p_availabilityl) {
		this.p_availabilityl = p_availabilityl;
	}
	
}
