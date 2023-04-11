package com.edenstar.model;

import javax.persistence.Embeddable;

@Embeddable
public class Product {

	private int productID;
	private int companyID;
	private String description;
	private String origin;
	private String priceRange;

	public Product() {
		super();
	}

	public Product(int productID, int companyID, String description, String origin, String priceRange) {
		super();
		this.productID = productID;
		this.companyID = companyID;
		this.description = description;
		this.origin = origin;
		this.priceRange = priceRange;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", companyID=" + companyID + ", description=" + description
				+ ", origin=" + origin + ", priceRange=" + priceRange + "]";
	}

}