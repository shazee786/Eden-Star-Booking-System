package com.edenstar.model.dash;

import com.edenstar.model.ProductPhoto;

public class CreateProductPhoto extends ProductPhoto {
	
	private String staff_email;

	public CreateProductPhoto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateProductPhoto(int productPhotoId, int productID, String description, byte[] productPhoto) {
		super(productPhotoId, productID, description, productPhoto);
		// TODO Auto-generated constructor stub
	}

	public CreateProductPhoto(String staff_email) {
		super();
		this.staff_email = staff_email;
	}

	public String getStaff_email() {
		return staff_email;
	}

	public void setStaff_email(String staff_email) {
		this.staff_email = staff_email;
	}
	
	

} // CreateProductPhoto
