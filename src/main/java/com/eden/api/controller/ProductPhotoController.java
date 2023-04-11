package com.eden.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eden.api.service.CustomerService;
import com.eden.api.service.UserService;
import com.eden.api.service.validation.ProductPhotoValidation;
import com.eden.api.util.Constants;
import com.eden.api.util.Response;
import com.eden.api.util.ResponseEnum;
import com.edenstar.model.Product;
import com.edenstar.model.ProductPhoto;
import com.edenstar.model.User;
import com.edenstar.model.dash.CreateProductPhoto;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class ProductPhotoController {

	Response response = new Response();

	ProductPhotoValidation prodPhotoValid = new ProductPhotoValidation();

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;

	public CreateProductPhoto checkChanges(CreateProductPhoto p) {

		// gets the product photo record from database and compares
		// to see if the new data is null, and if so copy the database value over

		// get the record
		ProductPhoto r = new ProductPhoto();

		try {
			r = customerService.getProductPhotobyID(p.getProductPhotoId());

			// the product_ID and product_photo_ID remain the same.
			p.setProductID(r.getProductID());

			if (p.getDescription() == null)
				p.setDescription(r.getDescription().toString());

			if (p.getProductPhoto() == null)
				p.setProductPhoto(r.getProductPhoto());

		} catch (Exception e) {
			e.printStackTrace();
		} // try-catch

		return p;

	} // checkChanges

	// check admin or manager level
	public boolean checkClearance(String staff_email) {

		User r = new User();

		try {

			r = userService.getUserDetails(staff_email);

			if (r.getUserLevel().contentEquals("manager_admin")) {

				System.out.println("manager clearance accepted");
				return true;

			} else if (r.getUserLevel().equals("admin")) {

				System.out.println("administrator level clearance verified");
				return true;

			} else
				return false;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;

	} // checkClearance

	public boolean userExists(String staff_email) {

		User r = new User();
		boolean u_exists = true;

		try {
			System.out.println("staff_email = " + staff_email);

			r = userService.getUserDetails(staff_email);
			if (r.getEmailID() == null)
				return false;

			System.out.println("result r = " + r.getEmailID());

			// first check to see if the staff email exists on the database
			if (!r.getEmailID().contentEquals(staff_email)) {
				// does not match
				System.out.println("user " + staff_email + " does not exist");
				u_exists = false;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return u_exists;

	} // userExists

	public boolean productExists(int productID) {

		Product r = new Product();
		boolean c_exists = true;

		try {

			r = customerService.getProduct(productID);
			System.out.println("get product = " + r.getDescription());

			// if the record does not exist we should return false
			if (r.getDescription() == null)
				return false;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return c_exists;

	} // productExists

	private boolean productPhotoExists(int productPhotoID) {

		ProductPhoto r = new ProductPhoto();
		boolean c_exists = true;

		try {

			r = customerService.getProductPhotobyID(productPhotoID);
			// System.out.println("get product photo = " + r.get(0).getDescription());

			// if the record does not exist we should return false
			if (r.getDescription() == null)
				return false;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return c_exists;
	} // productPhotoExists

	// **************************************************************************************
	// add a product photo for a existing product
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_ADD_PROD_PHOTO, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addProductPhoto(@RequestBody CreateProductPhoto p) {

		try {

			// make sure none of the mandatory fields are null
			if (prodPhotoValid.isNull(p) == true) {
				response = Response.build("Error",
						"mandatory parameters : [staff_email / description / productID / productPhoto] cannot be null",
						false);
				return response;
			} // isNull check

			// check to see if any of the mandatory fields are empty
			if (prodPhotoValid.fieldsAreEmpty(p) == true) {
				response = Response.build("Error",
						"no data entered for mandatory parameters, either [staff_email / description / productID / productPhoto ]",
						false);
				return response;
			} // isEmpty check

			// check to see if the staff user exists on the database
			if (!userExists(p.getStaff_email())) {
				response = Response.build("Error", "staff email id " + p.getStaff_email() + " does not exist", false);
				return response;
			} // userExists

			// we need to check if the product exists before adding the product photo

			if (productExists(p.getProductID())) {

				System.out.println("adding product photo to database ...");

				// if the product exists we create a new product under the product id number

				System.out.println("product_ID = " + p.getProductID());

				// we now create a product photo under the product id
				int product_photo_id = customerService.addProductPhoto(p);

				if (product_photo_id > 0) {
					response = Response.build("Success", "Product photo successfully added to the database", true);
					p.setProductPhotoId(product_photo_id);
					response.setData(p);
					// return response;
				} else {
					response = Response.build("Failure", "product photo could not be added to the database", false);
					return response;
				} // nested if

			} else {
				response = Response.build("Failure", "product photo could not be added, product does not exist", false);
				return response;
			} // outer if

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}
		return response;

	} // add a product photo

	// **************************************************************************************
	// DELETE a product photo associated with a product
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_DELETE_PROD_PHOTO, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response deleteProductPhoto(@RequestBody CreateProductPhoto p) {

		try {
			// make sure that the staff_email and product_photo_ID are not null
			if (p.getStaff_email() == null || (p.getProductPhotoId() < 1)) {
				response = Response.build("Error",
						"Mandatory fields, staff_email and/or productPhotoId, cannot be null", false);
				return response;
			} // null fields

			// check to see if the fields are empty
			if (p.getStaff_email().contentEquals("") || p.getProductPhotoId() < 1) {
				response = Response.build("Error", "Mandatory fields : [productPhotoId / staff_email] cannot be empty",
						false);
				return response;
			} // empty fields

			// check to see if the staff user exists on the database
			if (!userExists(p.getStaff_email())) {
				response = Response.build("Error", "staff email id " + p.getStaff_email() + " does not exist", false);
				return response;
			} // staff exists

			// check if the staff user has clearance to perform this action
			if (checkClearance(p.getStaff_email().toString()) == false) {

				// insufficient priviledges
				response = Response.build("Access Denied",
						"insufficient privileges - you must be either be admin or manager_admin to perform this action",
						false);
				return response;

			} // clearance check

			// we now have to check if the product photo exists on the database
			if (productPhotoExists(p.getProductPhotoId())) {

				// delete procedures
				int status = customerService.deleteProductPhoto(p.getProductPhotoId());

				if (status == 1) {
					response = Response.build("Success",
							"product photo has been successfully deleted from the database", true);
					return response;

				} else {
					response = Response.build("Failure",
							"Product Photo of id " + p.getProductPhotoId() + " could not be deleted from the database",
							false);
					return response;

				} // nested if

			} else {
				// Product does not exist
				response = Response.build("Error",
						"product_photo_id " + p.getProductPhoto() + "does not exist on the database", false);
				return response;

			} // if

		} catch (

		Exception e) {

			e.printStackTrace();
		} // try-catch
		return response;

	} // deleteProductPhoto

	// **************************************************************************************
	// update a product photo information to the database
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_UPDATE_PROD_PHOTO, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateProductPhoto(@RequestBody CreateProductPhoto p) {

		try {

			// check to see if the staff user exists on the database
			if (!userExists(p.getStaff_email())) {
				response = Response.build("Error", "staff email id " + p.getStaff_email() + " does not exist", false);
				return response;
			}

			// make sure that the product photo exists on database ...
			if (productPhotoExists(p.getProductPhotoId())) {

				// if the product photo exists, we just update it with the amended details
				// first we must make sure that any null fields are replaced with database
				// values
				System.out.println(" product photo " + p.getProductPhotoId() + " exists !");

				// check to see if there are any changes against the database value and copy
				// them over
				p = checkChanges(p);

				int status = customerService.updateProductPhoto(p);

				System.out.println("status = " + status);

				if (status == 1) {
					response = Response.build("Success", "product photo details successfully updated to the database",
							true);
					response.setData(p);
				} else {
					response = Response.build("Error", "product photo details could not be updated to the database",
							false);
					return response;
				} // nested if

			} else {
				// if the product photo does not exist, throw an error
				response = Response.build("Error", "product photo does not exist on the database", false);
				return response;

			} // if

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}

		return response;

	} // updateProductPhoto

	// **************************************************************************************
	// get all product photos from the database relating to a product id
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_GET_PROD_PHOTOS, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getProductPhotos(@RequestBody CreateProductPhoto p) {

		List<ProductPhoto> productPhotoList = new ArrayList<ProductPhoto>();

		try {

			// make sure that the staff_email and emailID are not null
			if (p.getStaff_email() == null || p.getProductID() == 0) {
				response = Response.build("Error", "Mandatory field staff_email / productID cannot be null", false);
				return response;
			} // null fields

			// check to see if the fields are empty
			if (p.getStaff_email().contentEquals("") || p.getProductID() == 0) {
				response = Response.build("Error", "Mandatory field : staff_email / productID cannot be empty", false);
				return response;
			} // empty fields

			// check to see if the staff user exists on the database
			if (!userExists(p.getStaff_email())) {
				response = Response.build("Error",
						"Staff email " + p.getStaff_email() + " does not exist on the database", false);
				return response;
			}

			// check to see if the product ID is valid
			if (!productExists(p.getProductID())) {
				response = Response.build("Error",
						"Product ID " + p.getProductID() + " does not exist on the database", false);
				return response;
			} // productExists
			
			// get all the product photos related to product ID ...

			productPhotoList = customerService.getProductPhoto(p.getProductID());
			
			// if there are no product photos stored return null
			if (productPhotoList.isEmpty()) {
				response = Response.build("Error",
						"There are no photos stored for this product", false);
				return response;
			}

			response = Response.build(ResponseEnum.OK.getStatus(), Constants.SUCCESS, ResponseEnum.OK.getMessage(),
					true);
			response.setData(productPhotoList);

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}

		return response;

	} // getProductsPhotos

} // ProductPhotoController
