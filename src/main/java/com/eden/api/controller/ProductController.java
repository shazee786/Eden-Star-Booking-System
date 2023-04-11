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
import com.eden.api.service.validation.ProductValidation;
import com.eden.api.util.Constants;
import com.eden.api.util.Response;
import com.eden.api.util.ResponseEnum;
import com.edenstar.model.Company;
import com.edenstar.model.Product;
import com.edenstar.model.User;
import com.edenstar.model.dash.CreateProduct;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class ProductController extends BaseController {

	Response response = new Response();

	ProductValidation prodValid = new ProductValidation();

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;

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


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u_exists;

	} // userExists

	public boolean companyExists(int companyID) {

		Company r = new Company();
		boolean c_exists = true;

		try {

			r = customerService.getCompany(companyID);
			System.out.println("get company = " + r.getCompanyName());

			// if the record does not exist we should return false
			if (r.getCompanyName() == null)
				return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c_exists;

	} // companyExists

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c_exists;

	} // productExists

	// **************************************************************************************
	// add a product for a existing company
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_ADD_PROD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addProduct(@RequestBody CreateProduct p) {

		try {

			// make sure none of the mandatory fields are null
			if (prodValid.isNull(p) == true) {
				response = Response.build("Error",
						"mandatory parameters : [staff_email / description / origin / priceRange / companyID ] cannot be null",
						false);
				return response;
			} // isNull check

			// check to see if any of the mandatory fields are empty
			if (prodValid.fieldsAreEmpty(p) == true) {
				response = Response.build("Error",
						"no data entered for mandatory parameters, either [staff_email / description / orgin / priceRange / companyID ]",
						false);
				return response;
			} // isEmpty check

			// check to see if the staff user exists on the database
			if (!userExists(p.getStaff_email())) {
				response = Response.build("Error", "staff email id " + p.getStaff_email() + " does not exist", false);
				return response;
			} // userExists

			// we need to check if the company exists

			if (companyExists(p.getCompanyID())) {

				System.out.println("adding product to database ...");

				// if the company exists we create a new product under the company id number

				System.out.println("company_ID = " + p.getCompanyID());

				// we now create a product under the company id
				int product_id = customerService.addProduct(p);

				if (product_id > 0) {
					response = Response.build("Success", "Product successfully added to the database", true);
					p.setProductID(product_id);
					response.setData(p);
					// return response;
				} else {
					response = Response.build("Failure", "product could not be added to the database", false);
					return response;
				} // nested if

			} else {
				response = Response.build("Failure", "product could not be added, customer does not exist", false);
				return response;
			} // outer if

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}
		return response;

	} // add a product

	// **************************************************************************************
	// DELETE a product and all associated photos from the database
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_DELETE_PROD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response deleteProduct(@RequestBody CreateProduct p) {

		try {
			// make sure that the staff_email and product_ID are not null
			if (p.getStaff_email() == null || (p.getProductID() < 1)) {
				response = Response.build("Error", "Mandatory fields, staff_email and/or productID, cannot be null",
						false);
				return response;
			} // null fields

			// check to see if the fields are empty
			if (p.getStaff_email().contentEquals("") || p.getProductID() < 1) {
				response = Response.build("Error", "Mandatory fields : [productID / staff_email] cannot be empty",
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

			System.out.println("you are here ---> productID = " + p.getProductID());

			// we now have to check if the product exists on the database
			if (productExists(p.getProductID())) {
				// delete procedures
				int status = customerService.deleteProduct(p.getProductID());

				if (status == 1) {
					response = Response.build("Success", "product and all associated records (product photos)"
							+ "  have successfully been deleted from the database", true);
					return response;

				} else {
					response = Response.build("Failure", "product_id could not be deleted from the database", false);
					return response;

				} // nested if

			} else {
				// Product does not exist
				response = Response.build("Error", "product_id does not exist on the database", false);
				return response;

			} // if

		} catch (

		Exception e) {

			e.printStackTrace();
		} // try-catch
		return response;

	} // deleteProduct

	public CreateProduct checkChanges(CreateProduct p) {

		// gets the product record from database and compares
		// to see if the new data is null, and if so copy the database value over

		// get the record
		Product r = new Product();

		try {
			r = customerService.getProduct(p.getProductID());
			
			// the company ID and product ID remain the same.
				p.setCompanyID(r.getCompanyID());
			
			if (p.getDescription() == null)
				p.setDescription(r.getDescription().toString());

			if (p.getOrigin() == null)
				p.setOrigin(r.getOrigin().toString());

			if (p.getPriceRange() == null)
				p.setPriceRange(r.getPriceRange().toString());

		} catch (Exception e) {
			e.printStackTrace();
		} // try-catch

		return p;

	} // checkChanges

	// **************************************************************************************
	// get all products from the database relating to a company id
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_GETALL_PROD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getProducts(@RequestBody CreateProduct p) {

		List<Product> productList = new ArrayList<Product>();

		try {

			// make sure that the staff_email and emailID are not null
			if (p.getStaff_email() == null || p.getCompanyID() == 0) {
				response = Response.build("Error", "Mandatory field staff_email / companyID cannot be null", false);
				return response;
			} // null fields

			// check to see if the fields are empty
			if (p.getStaff_email().contentEquals("") || p.getCompanyID() == 0) {
				response = Response.build("Error", "Mandatory field : staff_email / companyID cannot be empty", false);
				return response;
			} // empty fields

			// check to see if the staff user exists on the database
			if (!userExists(p.getStaff_email())) {
				response = Response.build("Error",
						"Staff email " + p.getStaff_email() + " does not exist on the database", false);
				return response;
			}

			// get all the products related to company ID ...

			productList = customerService.getProducts(p.getCompanyID());
			
			// check to see if there any products stored under the company
			if (productList.isEmpty()) {
				response = Response.build("Error",
						"There are no products stored under this company", false);
				return response;
			}

			response = Response.build(ResponseEnum.OK.getStatus(), Constants.SUCCESS, ResponseEnum.OK.getMessage(),
					true);
			response.setData(productList);

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}

		return response;

	} // getProducts

	// **************************************************************************************
	// update a product information to the database
	// **************************************************************************************
	@RequestMapping(value = Constants.BASE_PATH + Constants.PATH_DASH
			+ Constants.PATH_UPDATE_PROD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateProduct(@RequestBody CreateProduct p) {

		try {

			// check to see if the staff user exists on the database
			if (!userExists(p.getStaff_email())) {
				response = Response.build("Error", "staff email id " + p.getStaff_email() + " does not exist", false);
				return response;
			}

			// make sure that the customer exists on database ...
			if (productExists(p.getProductID())) {

				// if the product exists, we just update it with the amended details
				// first we must make sure that any null fields are replaced with database
				// values
				System.out.println(" product " + p.getProductID() + " exists !");

				
				// check to see if there are any changes against the database value and copy them over
				p = checkChanges(p);

				System.out.println("updating product information for product_id = " + p.getProductID() );
				int status = customerService.updateProduct(p);

				System.out.println("status = " + status);

				if (status == 1) {
					response = Response.build("Success", "product details successfully updated to the database", true);
					response.setData(p);
				} else {
					response = Response.build("Error", "product details could not be updated to the database",
							false);
					return response;
				} // nested if

			} else {
				// if the customer does not exist, throw an error
				response = Response.build("Error", "product does not exist on the database", false);
				return response;

			} // if

		} catch (

		Exception e) {
			response = Response.build(ResponseEnum.INTERNAL_SERVER_ERROR.getStatus(), Constants.INTERNAL_SYSTEM_ERROR,
					ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), false);
			e.printStackTrace();
		}

		return response;

	} // updateProduct

} // ProductController
