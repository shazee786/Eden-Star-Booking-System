package com.eden.api.service.validation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import com.eden.api.controller.engine.KeyGenerator;
import com.eden.api.util.Constants;
import com.edenstar.model.dash.CreateLocation;

public class LocationValidation {

	public boolean fieldsAreEmpty(CreateLocation l) {

		// check to make sure that the mandatory parameters are not empty
		if (l.getStaff_email_id().contentEquals("") || l.getNum_max_rows() < 1 || l.getNum_max_columns() < 1
				|| l.getRateList().isEmpty() || l.getLocationName().contentEquals("")
				|| l.getLocationArea().contentEquals("")) {

			return true;

		} // if

		return false;

	} // fieldsAreEmpty

	public boolean isNull(CreateLocation l) {
		// make sure that none of the mandatory fields are null

		if (l.getNum_max_columns() == 0)
			return true;
		if (l.getNum_max_rows() == 0)
			return true;
		if (l.getStaff_email_id() == null)
			return true;
		if (l.getRateList().isEmpty())
			return true;
		if (l.getLocationName() == null)
			return true;
		if (l.getLocationArea() == null)
			return true;

		return false;

	} // isNull

	public boolean validateRates(CreateLocation l) {
		// makes sure that the rates passed for the zones are within normal values
		int i = 0;
		boolean isValid = true;
		double rateCoeff = 0.00d;

		for (i = 0; i < l.getRateList().size(); i++) {

			if (l.getRateList().get(i).getRateMax() < 0.0
					|| l.getRateList().get(i).getRateMin() > l.getRateList().get(i).getRateMax()
					|| l.getRateList().get(i).getDiscountDurationDays() < 1)
				isValid = false;

		} // for loop

		// if all the values in the array are normal (i.e. isValid = true)
		// we should calculate the coefficient here for all the rates

		if (isValid) {

			for (i = 0; i < l.getRateList().size(); i++) {

				double coeff = (l.getRateList().get(i).getRateMin() - l.getRateList().get(i).getRateMax())
						/ l.getRateList().get(i).getDiscountDurationDays();

				// round off to 2 decimal places
				// rateCoeff = round(coeff, 3);
				rateCoeff = coeff;

				// set the coefficient
				l.getRateList().get(i).setRateCoeff(rateCoeff);

			} // for loop

		} // set the rate coefficient

		return isValid;

	} // validateRates

	public String writeImageStreamToFile(byte[] imgInputStream, String locationName) throws Exception {

		String absolutePath = "";
		// replace all spaces in the location name ...
		locationName = locationName.replace(" ", "_");

		try {

			System.out.println("processing image ...");

			// first we have to generate a unique name
			KeyGenerator kGen = new KeyGenerator();
			String imageFileName = "MAP_" + kGen.generateKey(locationName) + ".jpg";

			new File("images/" + locationName.toLowerCase()).mkdir();
			File targetFile = new File("images/" + locationName.toLowerCase() + "/" + imageFileName.toLowerCase());
			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(imgInputStream);

			System.out.println("targetFile path = " + targetFile.getPath());
			System.out.println("targetFile absolute path = " + targetFile.getAbsolutePath());
			absolutePath = targetFile.getAbsolutePath();

			IOUtils.closeQuietly(outStream);

		} catch (IOException io) {
			throw new RuntimeException(io);
		}

		absolutePath = absolutePath.replace(Constants.jouplefilepath, Constants.jouplenet);
		return absolutePath;

	} // writeImageStreamToFile

	public void deleteDirectory(String locationName) throws IOException {

		String myPath = System.getProperty("user.dir"); 
		locationName = locationName.replace(" ", "_");
		
		System.out.println("my path is " + myPath);
		myPath = myPath + "/images/" + locationName.toLowerCase();
		System.out.println("my path is " + myPath);
		try {
			FileUtils.deleteDirectory(new File(myPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // deleteDirectory

} // LocationValidation
