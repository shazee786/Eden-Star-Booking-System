package com.edenstar.model.dash;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;

import com.edenstar.model.Company;
import com.edenstar.model.Customer;
import com.edenstar.model.Product;
import com.edenstar.model.ProductPhoto;

public class GetCustomer extends Customer {

	private String staff_email;
	private int staff_employee_id;

//	@Embedded
//	List<ArrayList<Product>> listOfProductLists = new ArrayList<ArrayList<Product>>();
	@Embedded
	private List<ArrayList<Product>> productList = new ArrayList<ArrayList<Product>>();

	@Embedded
	private List<Company> companyList = new ArrayList<Company>();

	@Embedded
	List<ArrayList<ProductPhoto>> ProductPhotoLists = new ArrayList<ArrayList<ProductPhoto>>();
//	@Embedded
//	private List<ProductPhoto> productPhotoList = new ArrayList<ProductPhoto>();



	public GetCustomer(String staff_email, int staff_employee_id, List<ArrayList<Product>> productList,
			List<Company> companyList, List<ArrayList<ProductPhoto>> productPhotoLists) {
		super();
		this.staff_email = staff_email;
		this.staff_employee_id = staff_employee_id;
		this.productList = productList;
		this.companyList = companyList;
		ProductPhotoLists = productPhotoLists;
	}

	public GetCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetCustomer(int customerID, String emailIDCus, String firstName, String lastName, String address,
			String emirate, String poBox, String mobileNumber, String officeNumber, int tradeLicence,
			String tradeName) {
		super(customerID, emailIDCus, firstName, lastName, address, emirate, poBox, mobileNumber, officeNumber, tradeLicence,
				tradeName);
		// TODO Auto-generated constructor stub
	}

	public String getStaff_email() {
		return staff_email;
	}

	public void setStaff_email(String staff_email) {
		this.staff_email = staff_email;
	}

	public int getStaff_employee_id() {
		return staff_employee_id;
	}

	public void setStaff_employee_id(int staff_employee_id) {
		this.staff_employee_id = staff_employee_id;
	}

	public List<ArrayList<Product>> getProductList() {
		return productList;
	}

	public void setProductList(List<ArrayList<Product>> productList) {
		this.productList = productList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public List<ArrayList<ProductPhoto>> getProductPhotoLists() {
		return ProductPhotoLists;
	}

	public void setProductPhotoLists(List<ArrayList<ProductPhoto>> productPhotoLists) {
		ProductPhotoLists = productPhotoLists;
	}

} // GetCustomer
