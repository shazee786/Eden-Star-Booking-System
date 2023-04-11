package com.edenstar.model.booking.forms;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.edenstar.model.User;
import com.edenstar.model.booking.AddQuote;

public class QuotationForm {
	
	private String v1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"+
			""+
			"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">"+
			"<head>"+
			"<!--[if gte mso 9]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]-->"+
			"<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>"+
			"<meta content=\"width=device-width\" name=\"viewport\"/>"+
			"<!--[if !mso]><!-->"+
			"<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>"+
			"<!--<![endif]-->"+
			"<title></title>"+
			"<!--[if !mso]><!-->"+
			"<link href=\"https://fonts.googleapis.com/css?family=Ubuntu\" rel=\"stylesheet\" type=\"text/css\"/>"+
			"<!--<![endif]-->"+
			"<style type=\"text/css\">"+
			"		body {"+
			"			margin: 0;"+
			"			padding: 0;"+
			"		}"+
			""+
			"		table,"+
			"		td,"+
			"		tr {"+
			"			vertical-align: top;"+
			"			border-collapse: collapse;"+
			"		}"+
			""+
			"		* {"+
			"			line-height: inherit;"+
			"		}"+
			""+
			"		a[x-apple-data-detectors=true] {"+
			"			color: inherit !important;"+
			"			text-decoration: none !important;"+
			"		}"+
			""+
			"		.ie-browser table {"+
			"			table-layout: fixed;"+
			"		}"+
			""+
			"		[owa] .img-container div,"+
			"		[owa] .img-container button {"+
			"			display: block !important;"+
			"		}"+
			""+
			"		[owa] .fullwidth button {"+
			"			width: 100% !important;"+
			"		}"+
			""+
			"		[owa] .block-grid .col {"+
			"			display: table-cell;"+
			"			float: none !important;"+
			"			vertical-align: top;"+
			"		}"+
			""+
			"		.ie-browser .block-grid,"+
			"		.ie-browser .num12,"+
			"		[owa] .num12,"+
			"		[owa] .block-grid {"+
			"			width: 500px !important;"+
			"		}"+
			""+
			"		.ie-browser .mixed-two-up .num4,"+
			"		[owa] .mixed-two-up .num4 {"+
			"			width: 164px !important;"+
			"		}"+
			""+
			"		.ie-browser .mixed-two-up .num8,"+
			"		[owa] .mixed-two-up .num8 {"+
			"			width: 328px !important;"+
			"		}"+
			""+
			"		.ie-browser .block-grid.two-up .col,"+
			"		[owa] .block-grid.two-up .col {"+
			"			width: 246px !important;"+
			"		}"+
			""+
			"		.ie-browser .block-grid.three-up .col,"+
			"		[owa] .block-grid.three-up .col {"+
			"			width: 246px !important;"+
			"		}"+
			""+
			"		.ie-browser .block-grid.four-up .col [owa] .block-grid.four-up .col {"+
			"			width: 123px !important;"+
			"		}"+
			""+
			"		.ie-browser .block-grid.five-up .col [owa] .block-grid.five-up .col {"+
			"			width: 100px !important;"+
			"		}"+
			""+
			"		.ie-browser .block-grid.six-up .col,"+
			"		[owa] .block-grid.six-up .col {"+
			"			width: 83px !important;"+
			"		}"+
			""+
			"		.ie-browser .block-grid.seven-up .col,"+
			"		[owa] .block-grid.seven-up .col {"+
			"			width: 71px !important;"+
			"		}"+
			""+
			"		.ie-browser .block-grid.eight-up .col,"+
			"		[owa] .block-grid.eight-up .col {"+
			"			width: 62px !important;"+
			"		}"+
			""+
			"		.ie-browser .block-grid.nine-up .col,"+
			"		[owa] .block-grid.nine-up .col {"+
			"			width: 55px !important;"+
			"		}"+
			""+
			"		.ie-browser .block-grid.ten-up .col,"+
			"		[owa] .block-grid.ten-up .col {"+
			"			width: 60px !important;"+
			"		}"+
			""+
			"		.ie-browser .block-grid.eleven-up .col,"+
			"		[owa] .block-grid.eleven-up .col {"+
			"			width: 54px !important;"+
			"		}"+
			""+
			"		.ie-browser .block-grid.twelve-up .col,"+
			"		[owa] .block-grid.twelve-up .col {"+
			"			width: 50px !important;"+
			"		}"+
			"	</style>"+
			"<style id=\"media-query\" type=\"text/css\">"+
			"		@media only screen and (min-width: 520px) {"+
			"			.block-grid {"+
			"				width: 500px !important;"+
			"			}"+
			""+
			"			.block-grid .col {"+
			"				vertical-align: top;"+
			"			}"+
			""+
			"			.block-grid .col.num12 {"+
			"				width: 500px !important;"+
			"			}"+
			""+
			"			.block-grid.mixed-two-up .col.num3 {"+
			"				width: 123px !important;"+
			"			}"+
			""+
			"			.block-grid.mixed-two-up .col.num4 {"+
			"				width: 164px !important;"+
			"			}"+
			""+
			"			.block-grid.mixed-two-up .col.num8 {"+
			"				width: 328px !important;"+
			"			}"+
			""+
			"			.block-grid.mixed-two-up .col.num9 {"+
			"				width: 369px !important;"+
			"			}"+
			""+
			"			.block-grid.two-up .col {"+
			"				width: 250px !important;"+
			"			}"+
			""+
			"			.block-grid.three-up .col {"+
			"				width: 166px !important;"+
			"			}"+
			""+
			"			.block-grid.four-up .col {"+
			"				width: 125px !important;"+
			"			}"+
			""+
			"			.block-grid.five-up .col {"+
			"				width: 100px !important;"+
			"			}"+
			""+
			"			.block-grid.six-up .col {"+
			"				width: 83px !important;"+
			"			}"+
			""+
			"			.block-grid.seven-up .col {"+
			"				width: 71px !important;"+
			"			}"+
			""+
			"			.block-grid.eight-up .col {"+
			"				width: 62px !important;"+
			"			}"+
			""+
			"			.block-grid.nine-up .col {"+
			"				width: 55px !important;"+
			"			}"+
			""+
			"			.block-grid.ten-up .col {"+
			"				width: 50px !important;"+
			"			}"+
			""+
			"			.block-grid.eleven-up .col {"+
			"				width: 45px !important;"+
			"			}"+
			""+
			"			.block-grid.twelve-up .col {"+
			"				width: 41px !important;"+
			"			}"+
			"		}"+
			""+
			"		@media (max-width: 520px) {"+
			""+
			"			.block-grid,"+
			"			.col {"+
			"				min-width: 320px !important;"+
			"				max-width: 100% !important;"+
			"				display: block !important;"+
			"			}"+
			""+
			"			.block-grid {"+
			"				width: 100% !important;"+
			"			}"+
			""+
			"			.col {"+
			"				width: 100% !important;"+
			"			}"+
			""+
			"			.col>div {"+
			"				margin: 0 auto;"+
			"			}"+
			""+
			"			img.fullwidth,"+
			"			img.fullwidthOnMobile {"+
			"				max-width: 100% !important;"+
			"			}"+
			""+
			"			.no-stack .col {"+
			"				min-width: 0 !important;"+
			"				display: table-cell !important;"+
			"			}"+
			""+
			"			.no-stack.two-up .col {"+
			"				width: 50% !important;"+
			"			}"+
			""+
			"			.no-stack .col.num4 {"+
			"				width: 33% !important;"+
			"			}"+
			""+
			"			.no-stack .col.num8 {"+
			"				width: 66% !important;"+
			"			}"+
			""+
			"			.no-stack .col.num4 {"+
			"				width: 33% !important;"+
			"			}"+
			""+
			"			.no-stack .col.num3 {"+
			"				width: 25% !important;"+
			"			}"+
			""+
			"			.no-stack .col.num6 {"+
			"				width: 50% !important;"+
			"			}"+
			""+
			"			.no-stack .col.num9 {"+
			"				width: 75% !important;"+
			"			}"+
			""+
			"			.video-block {"+
			"				max-width: none !important;"+
			"			}"+
			""+
			"			.mobile_hide {"+
			"				min-height: 0px;"+
			"				max-height: 0px;"+
			"				max-width: 0px;"+
			"				display: none;"+
			"				overflow: hidden;"+
			"				font-size: 0px;"+
			"			}"+
			""+
			"			.desktop_hide {"+
			"				display: block !important;"+
			"				max-height: none !important;"+
			"			}"+
			"		}"+
			"	</style>"+
			"</head>"+
			"<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #FFFFFF;\">"+
			"<!--[if IE]><div class=\"ie-browser\"><![endif]-->"+
			"<table bgcolor=\"#FFFFFF\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; Margin: 0 auto; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #FFFFFF; width: 100%;\" valign=\"top\" width=\"100%\">"+
			"<tbody>"+
			"<tr style=\"vertical-align: top;\" valign=\"top\">"+
			"<td style=\"word-break: break-word; vertical-align: top; border-collapse: collapse;\" valign=\"top\">"+
			"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color:#FFFFFF\"><![endif]-->"+
			"<div style=\"background-color:transparent;\">"+
			"<div class=\"block-grid\" data-body-width-father=\"500px\" rel=\"col-num-container-box-father\" style=\"Margin: 0 auto; min-width: 320px; max-width: 500px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">"+
			"<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">"+
			"<!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:transparent;\"><tr><td align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:500px\"><tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->"+
			"<!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\"background-color:transparent;width:500px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\"><![endif]-->"+
			"<div class=\"col num12\" data-body-width-son=\"500\" rel=\"col-num-container-box-son\" style=\"min-width: 320px; max-width: 500px; display: table-cell; vertical-align: top;\">"+
			"<div style=\"width:100% !important;\">"+
			"<!--[if (!mso)&(!IE)]><!-->"+
			"<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">"+
			"<!--<![endif]-->"+
			"<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">"+
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px\"><td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\"><![endif]--><img align=\"center\" alt=\"Image\" border=\"0\" class=\"center fixedwidth\" src=\"cid:edenlogoimage\" style=\"outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; clear: both; border: 0; height: auto; float: none; width: 100%; max-width: 125px; display: block;\" title=\"Image\" width=\"125\"/>"+
			"<!--[if mso]></td></tr></table><![endif]-->"+
			"</div>"+
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->"+
			"<div style=\"color:#555555;font-family:'Ubuntu', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">"+
			"<div style=\"font-family: 'Ubuntu', Tahoma, Verdana, Segoe, sans-serif; font-size: 12px; line-height: 14px; color: #555555;\">"+
			"<p style=\"font-size: 14px; line-height: 45px; text-align: center; margin: 0;\"><span style=\"font-size: 38px;\"><strong><em>Eden Star</em></strong></span></p>"+
			"</div>"+
			"</div>"+
			"<!--[if mso]></td></tr></table><![endif]-->"+
			"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">"+
			"<tbody>"+
			"<tr style=\"vertical-align: top;\" valign=\"top\">"+
			"<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px; border-collapse: collapse;\" valign=\"top\">"+
			"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; border-top: 1px solid #BBBBBB;\" valign=\"top\" width=\"100%\">"+
			"<tbody>"+
			"<tr style=\"vertical-align: top;\" valign=\"top\">"+
			"<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; border-collapse: collapse;\" valign=\"top\"><span></span></td>"+
			"</tr>"+
			"</tbody>"+
			"</table>"+
			"</td>"+
			"</tr>"+
			"</tbody>"+
			"</table>"+
			"<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Tahoma, Verdana, sans-serif\"><![endif]-->"+
			"<div style=\"color:#555555;font-family:'Ubuntu', Tahoma, Verdana, Segoe, sans-serif;line-height:120%;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">"+
			"<div style=\"font-family: 'Ubuntu', Tahoma, Verdana, Segoe, sans-serif; font-size: 12px; line-height: 14px; color: #555555;\">"+
			"<p style=\"font-size: 14px; line-height: 16px; text-align: center; margin: 0;\"><strong><span style=\"font-size: 18px; line-height: 21px;\">Kiosk Quotation</span></strong></p>"+
			"<p style=\"font-size: 14px; line-height: 16px; text-align: center; margin: 0;\"> </p>"+
			"</div>"+
			"</div>"+
			"<!--[if mso]></td></tr></table><![endif]-->"+
			"<div style=\"font-size:16px;text-align:center;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif\">";
			
	private String v11 = 		"<p style=\"text-align: left;\"><em>Dear ";
	
	private String v11a_firstName_;
	private String v11b_lastname;

    private String v_11c = ",&nbsp;";

	private String v12 = ""
			+ "</em><em>thank you for your enquiry with Eden Star. Please find your quotation for the lease period you requested below.</em></p> "
			+ "<p style=\"text-align: left;\">&nbsp;" + "" + "</p> " + "<p style=\"text-align: left;\">&nbsp;" + "" + "</p> " + "<table style=\"height: 379px; width: 644px;\"> " + "<tbody> "
			+ "<tr style=\"height: 18px;\"> "
			+ "<td style=\"width: 161px; height: 18px; text-align: left;\"><strong>Quotation Date</strong></td> "
			+ "<td style=\"width: 471px; height: 18px; text-align: left;\">";
	
	private String v12a_quotationDate;
	private String v12b =  "</td> " + "</tr> "
			+ "<tr style=\"height: 18px;\"> "
			+ "<td style=\"width: 161px; height: 18px; text-align: left;\"><strong>Quotation Reference</strong></td> "
			+ "<td style=\"width: 471px; height: 18px; text-align: left;\">";
	
    private String v12c_quotation_id;

    private String v12d = "</td> " + "</tr> "
			+ "<tr style=\"height: 21px;\"> " + "<td style=\"width: 161px; height: 21px; text-align: left;\">&nbsp;";

	private String v15 = "" + "</td> " + "<td style=\"width: 471px; height: 21px; text-align: left;\">&nbsp;"+
							"" + "</td> " + "</tr> " + "<tr style=\"height: 18px;\"> "
			+ "<td style=\"width: 161px; height: 18px; text-align: left;\"><strong>Location</strong></td> "
			+ "<td style=\"width: 471px; height: 18px; text-align: left;\">";
	
	private String v15a_location_name;
	
	private String v15b	= "</td> " + "</tr> "
			+ "<tr style=\"height: 18px;\"> "
			+ "<td style=\"width: 161px; height: 18px; text-align: left;\"><strong>Kiosk Number</strong></td> "
			+ "<td style=\"width: 471px; height: 18px; text-align: left;\">";
	
	private String v15g_kiosk_number;
	
    private String v15d = "</td> " + "</tr> "
			+ "<tr style=\"height: 18px;\"> "
			+ "<td style=\"width: 161px; height: 18px; text-align: left;\"><strong>Customer Name</strong></td> "
			+ "<td style=\"width: 471px; height: 18px; text-align: left;\">";
    
    private String v15c_customer_name;
    
	private String v15f = "</td> " + "</tr> "
			+ "<tr style=\"height: 18px;\"> "
			+ "<td style=\"width: 161px; height: 18px; text-align: left;\"><strong>Company Name</strong></td> "
			+ "<td style=\"width: 471px; height: 18px; text-align: left;\">";
	
	private String v15e_company_name;
	
	private String v15h = "</td> " + "</tr> "
			+ "<tr style=\"height: 18px;\"> " + "<td style=\"width: 161px; height: 18px; text-align: left;\">&nbsp;";

	private String v17 = "" + "</td> " + "<td style=\"width: 471px; height: 18px; text-align: left;\">&nbsp;";

	private String v18 = "" + "</td> " + "</tr> " + "<tr style=\"height: 18px;\"> "
			+ "<td style=\"width: 161px; height: 18px; text-align: left;\"><strong>Lease Period</strong></td> "
			+ "<td style=\"width: 471px; height: 18px; text-align: left;\">";
	
	private String v18a_lease_start_date;
	
	private String v18b = " to ";
	
    private String v18c_lease_end_date;
    
	private String v18d =  "</td> "
			+ "</tr> " + "<tr style=\"height: 18px;\"> "
			+ "<td style=\"width: 161px; height: 18px; text-align: left;\"><strong>Lease Duration</strong></td> "
			+ "<td style=\"width: 471px; height: 18px; text-align: left;\">";
	
	private String v18e_lease_duration_days;
	
	private String v18f = "</td> " + "</tr> "
			+ "<tr style=\"height: 18px;\"> " + "<td style=\"width: 161px; height: 18px; text-align: left;\">&nbsp;";

	private String v19 = "" + "</td> " + "<td style=\"width: 471px; height: 18px; text-align: left;\">&nbsp;";

	private String v110 = "" + "</td> " + "</tr> " + "<tr style=\"height: 18px;\"> "
			+ "<td style=\"width: 161px; height: 18px; text-align: left;\"><strong>Quotation Valid For</strong></td> "
			+ "<td style=\"width: 471px; height: 18px; text-align: left;\"><strong>";
	
	private String v110a_quotation_vaid_for_days;
	
	private String v110b = " days</strong></td> "
			+ "</tr> " + "<tr style=\"height: 61px;\"> "
			+ "<td style=\"width: 161px; height: 61px; text-align: left;\"> " + "<h2><strong>Total</strong></h2> "
			+ "</td> " + "<td style=\"width: 471px; height: 61px; text-align: left;\"> "
			+ "<h2><strong><span style=\"color: #000000;\">";
	
	private String v110c_lease_total;
	
	private String v110d =  "</span> AED</strong></h2> " + "</td> " + "</tr> "
			+ "</tbody> " + "</table> " + "<p>&nbsp;";

	private String v111 = "" + "</p> " + "<p style=\"text-align: left;\"><em>";

	private String v112 = ""
			+ "If you have any questions or wish to proceed with the booking application, please contact ";

    private String v112a_employee_first_name;
    
	private String v112b = " on ";
	
	private String v112c_employee_contact;
	
	private String v112d = ""
			+ " and quote the reference number given above.&nbsp;";

	private String v113 = "" + "</em><em>We look forward to hearing from you soon.</em></p> "
			+ "<p style=\"text-align: left;\">&nbsp;";

	private String v114 = "" + "</p> " + "<p style=\"text-align: left;\"><em>Best Wishes&nbsp;";

	private String v115 = "" + "</em></p> " + "<p style=\"text-align: left;\"><em>Eden Star Sales Team</em></p> "
			+ "<p>&nbsp;";

	private String v116 = "" + "</p> " + "<p><strong>&nbsp;";

	private String v117 = "" + "</strong></p> " + "<p>&nbsp;";

	private String v118 = "" + "</p>";

	public QuotationForm() {
		super();
	}


	public QuotationForm(String v11a_firstName_, String v11b_lastname, String v12a_quotationDate,
			String v12c_quotation_id, String v15a_location_name, String v15c_customer_name, String v15e_company_name,
			String v15g_kiosk_number, String v18a_lease_start_date, String v18c_lease_end_date,
			String v18e_lease_duration_days, String v110a_quotation_vaid_for_days, String v110c_lease_total,
			String v112a_employee_first_name, String v112c_employee_contact) {
		super();
		this.v11a_firstName_ = v11a_firstName_;
		this.v11b_lastname = v11b_lastname;
		this.v12a_quotationDate = v12a_quotationDate;
		this.v12c_quotation_id = v12c_quotation_id;
		this.v15a_location_name = v15a_location_name;
		this.v15c_customer_name = v15c_customer_name;
		this.v15e_company_name = v15e_company_name;
		this.v15g_kiosk_number = v15g_kiosk_number;
		this.v18a_lease_start_date = v18a_lease_start_date;
		this.v18c_lease_end_date = v18c_lease_end_date;
		this.v18e_lease_duration_days = v18e_lease_duration_days;
		this.v110a_quotation_vaid_for_days = v110a_quotation_vaid_for_days;
		this.v110c_lease_total = v110c_lease_total;
		this.v112a_employee_first_name = v112a_employee_first_name;
		this.v112c_employee_contact = v112c_employee_contact;
	}

	

	public String getV11a_firstName_() {
		return v11a_firstName_;
	}


	public void setV11a_firstName_(String v11a_firstName_) {
		this.v11a_firstName_ = v11a_firstName_;
	}


	public String getV11b_lastname() {
		return v11b_lastname;
	}


	public void setV11b_lastname(String v11b_lastname) {
		this.v11b_lastname = v11b_lastname;
	}


	public String getV12a_quotationDate() {
		return v12a_quotationDate;
	}


	public void setV12a_quotationDate(String v12a_quotationDate) {
		this.v12a_quotationDate = v12a_quotationDate;
	}


	public String getV12c_quotation_id() {
		return v12c_quotation_id;
	}


	public void setV12c_quotation_id(String v12c_quotation_id) {
		this.v12c_quotation_id = v12c_quotation_id;
	}


	public String getV15a_location_name() {
		return v15a_location_name;
	}


	public void setV15a_location_name(String v15a_location_name) {
		this.v15a_location_name = v15a_location_name;
	}


	public String getV15c_customer_name() {
		return v15c_customer_name;
	}


	public void setV15c_customer_name(String v15c_customer_name) {
		this.v15c_customer_name = v15c_customer_name;
	}


	public String getV15e_company_name() {
		return v15e_company_name;
	}


	public void setV15e_company_name(String v15e_company_name) {
		this.v15e_company_name = v15e_company_name;
	}


	public String getV15g_kiosk_number() {
		return v15g_kiosk_number;
	}


	public void setV15g_kiosk_number(String v15g_kiosk_number) {
		this.v15g_kiosk_number = v15g_kiosk_number;
	}


	public String getV18a_lease_start_date() {
		return v18a_lease_start_date;
	}


	public void setV18a_lease_start_date(String v18a_lease_start_date) {
		this.v18a_lease_start_date = v18a_lease_start_date;
	}


	public String getV18c_lease_end_date() {
		return v18c_lease_end_date;
	}


	public void setV18c_lease_end_date(String v18c_lease_end_date) {
		this.v18c_lease_end_date = v18c_lease_end_date;
	}


	public String getV18e_lease_duration_days() {
		return v18e_lease_duration_days;
	}


	public void setV18e_lease_duration_days(String v18e_lease_duration_days) {
		this.v18e_lease_duration_days = v18e_lease_duration_days;
	}


	public String getV110a_quotation_vaid_for_days() {
		return v110a_quotation_vaid_for_days;
	}


	public void setV110a_quotation_vaid_for_days(String v110a_quotation_vaid_for_days) {
		this.v110a_quotation_vaid_for_days = v110a_quotation_vaid_for_days;
	}


	public String getV110c_lease_total() {
		return v110c_lease_total;
	}


	public void setV110c_lease_total(String v110c_lease_total) {
		this.v110c_lease_total = v110c_lease_total;
	}


	public String getV112a_employee_first_name() {
		return v112a_employee_first_name;
	}


	public void setV112a_employee_first_name(String v112a_employee_first_name) {
		this.v112a_employee_first_name = v112a_employee_first_name;
	}


	public String getV112c_employee_contact() {
		return v112c_employee_contact;
	}


	public void setV112c_employee_contact(String v112c_employee_contact) {
		this.v112c_employee_contact = v112c_employee_contact;
	}


	@Override
	public String toString() {
		return  v1  + v11 + v11a_firstName_  +  v11b_lastname + v_11c +  v12 +  v12a_quotationDate
			    + v12b +  v12c_quotation_id + v12d  + v15 + v15a_location_name  + v15b 
				+ v15g_kiosk_number  + v15d  + v15c_customer_name + v15f
				+ v15e_company_name + v15h + v17 + v18
			    + v18a_lease_start_date  + v18b 
				+ v18c_lease_end_date  + v18d  + v18e_lease_duration_days
				+ v18f  + v19  + v110 + v110a_quotation_vaid_for_days
				+ v110b + v110c_lease_total  + v110d 
				+ v111  + v112  + v112a_employee_first_name 
				+ v112b  + v112c_employee_contact + v112d + v113
				+ v114  + v115  + v116  + v117  + v118;
	}
	
	private String listCompanies(AddQuote q) {
		String companiesList = "";
		
		for (int i = 0; i < q.getCompanyList().size(); i++) {
			companiesList = companiesList +  q.getCompanyList().get(i).getCompanyName();
			
			if ( i == q.getCompanyList().size() -1) {
				
			} else {
				companiesList = companiesList + " , ";
			}
		}
		
		return companiesList;
		
	}
	
	public void loadValues(AddQuote q, User u) {
		
		
		
		this.v11a_firstName_ = q.getFirstName() + " ";
		this.v11b_lastname = q.getLastName();
		this.v12a_quotationDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(q.getDate_of_quote());
		this.v12c_quotation_id = q.getQuoteRef();
		this.v15a_location_name = q.getQ_kiosk().getLocation_name();
		this.v15c_customer_name = q.getFirstName() + " " + q.getLastName();
		this.v15e_company_name = listCompanies(q);
		this.v15g_kiosk_number = String.valueOf(q.getQ_kiosk().getKioskNumber());
		this.v18a_lease_start_date = q.getStartDate();
		this.v18c_lease_end_date = q.getEndDate();
		this.v18e_lease_duration_days = String.valueOf(q.getQ_kiosk().getLease_duration()) + " days";
		this.v110a_quotation_vaid_for_days = String.valueOf(q.getExpiry_duration_days());
		this.v110c_lease_total = String.valueOf(q.getQ_kiosk().getLease_total());
		this.v112a_employee_first_name = u.getFirstName();
		this.v112c_employee_contact = u.getEmailID();
	}

} //QuotationForm
