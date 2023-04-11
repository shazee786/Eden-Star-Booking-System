package com.eden.api.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.edenstar.model.Calendar;
import com.edenstar.model.booking.AddQuote;

@Component
public class BookingDAOImpl implements BookingDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BookingDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Calendar> getBookingForKiosk(int kioskID) throws Exception {

		String query = "SELECT * FROM calendar where kiosk_id ='" + kioskID + "'";
		List<Calendar> calendarList = new ArrayList<Calendar>();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

		try {

			rows = jdbcTemplate.queryForList(query);

			for (Map<String, Object> row : rows) {

				Calendar c = new Calendar();

				c.setCalendarID((Integer) (row.get("calendar_id")));
				c.setKioskID((Integer) (row.get("kiosk_id")));
				c.setLeaseStartDate((Date) (row.get("lease_start_date")));
				c.setLeaseEndDate((Date) (row.get("lease_end_date")));
				c.setLeaseDurationDays((Integer) (row.get("lease_duration_days")));

				calendarList.add(c);
			}

		} catch (Exception e) {

		} // try

		return calendarList;

	} // getBookingForKiosk

    private java.sql.Date formatDateForDB(String dateStr) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dateForDB = null;
        DateTime date_SQL_format = null;

        try {

            dateForDB = format.parse(dateStr);

            date_SQL_format = new DateTime(dateForDB);

        } catch (Exception e) {
            e.printStackTrace();
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        String dateInString = date_SQL_format.toString(fmt);

        java.sql.Date newDate = java.sql.Date.valueOf(dateInString);

        return newDate;

    } // formatDateForDB

	@Override
	public int addQuote(AddQuote q) throws Exception {

		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		int status = 0;
		
		// format the dates
		java.sql.Date lease_start_date = formatDateForDB(q.getStartDate());
		java.sql.Date lease_end_date = formatDateForDB(q.getEndDate());
        long millis=System.currentTimeMillis();
        java.sql.Date date_of_quote = new java.sql.Date(millis);
        int isExpired = 0;

		
		final String INSERT_QUERY = "insert into quote (quote_ref, customer_id, kiosk_id, employee_id, date_of_quote,"
				+ "lease_start_date, lease_end_date, lease_duration_days, lease_total, rate, expiry_duration_days, is_expired, quotation_pdf) values"
				+ " (:quote_ref, :customer_id, :kiosk_id, :employee_id, :date_of_quote,"
				+ ":lease_start_date, :lease_end_date, :lease_duration_days, :lease_total, :rate, :expiry_duration_days, :is_expired, :quotation_pdf)";

		try {
			
			System.out.println("SQL start date = " + lease_start_date);
			System.out.println("SQL end_date" + lease_end_date);
			System.out.println("SQL Date of quote" + date_of_quote);
			

			// Creating map with all required params
			MapSqlParameterSource paramMap = new MapSqlParameterSource();

			paramMap.addValue("quote_ref", q.getQuoteRef());
			paramMap.addValue("customer_id", q.getCustomerID());
			paramMap.addValue("kiosk_id", q.getKiosk_id());
			paramMap.addValue("employee_id", q.getEmployee_id());
			paramMap.addValue("date_of_quote", date_of_quote);
			paramMap.addValue("lease_start_date", lease_start_date );
			paramMap.addValue("lease_end_date", lease_end_date);
			paramMap.addValue("lease_duration_days", q.getQ_kiosk().getLease_duration());
			paramMap.addValue("lease_total", q.getQ_kiosk().getLease_total());
			paramMap.addValue("rate", q.getQ_kiosk().getDaily_rate());
			paramMap.addValue("expiry_duration_days", q.getExpiry_duration_days());
			paramMap.addValue("is_expired", isExpired);
			paramMap.addValue("quotation_pdf", q.getQuotationPDF());

			status = namedJdbcTemplate.update(INSERT_QUERY, paramMap, keyHolder, new String[] { "quote_id" });

			// get the auto generated primary key from the new customer insertion
			Number generatedComID = keyHolder.getKey();
			status = generatedComID.intValue();

			System.out.println("new quote ID = " + status);

		} catch (Exception e) {

		} // try

		return status;
	} // addQuote


} // BookingDAOImpl
