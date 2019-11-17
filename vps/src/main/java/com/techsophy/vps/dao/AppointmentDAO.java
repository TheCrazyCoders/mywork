package com.techsophy.vps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.techsophy.vps.utils.DBUtils;

@Repository
public class AppointmentDAO {

	private final Logger logger = LoggerFactory.getLogger(AppointmentDAO.class);

	@Qualifier("vps")
	@Autowired
	DataSource vpsDataSource;

	@Autowired
	DBUtils dbUtils;

	public String getAllCountries() {
		Connection vpsConn = null;
		Statement countryStmt = null;
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject countryObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			countryStmt = vpsConn.createStatement();
			String conutryQuery = "select * from vps_country";
			logger.info("in getAllCountries() and fetching the result for query - " + conutryQuery);
			ResultSet rs = countryStmt.executeQuery(conutryQuery);
			while (rs.next()) {
				countryObj = new JSONObject();
				countryObj.put("id", rs.getString(1));
				countryObj.put("name", rs.getString(2));
				arr.put(countryObj);
			}
			response.put("countries", arr);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getAllCountries() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getAllCountries() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(countryStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getAllCountries() " + response.toString());
		return response.toString();
	}

	public String getVisaTypes(String countryId) {
		Connection vpsConn = null;
		Statement categoryStmt = null;
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject visaTypesObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			categoryStmt = vpsConn.createStatement();
			String categoryQuery = "select distinct(visa_type) from vps_visa_types vt "
					+ "left join vps_service_center sc " + "on  vt.sc_id = sc.sc_id "
					+ "where sc.country_id in (select cn.country_id from vps_country cn where cn.country_id='"
					+ countryId + "')";
			logger.info("in getVisaTypes() and fetching the result for query - " + categoryQuery);
			ResultSet rs = categoryStmt.executeQuery(categoryQuery);
			while (rs.next()) {
				visaTypesObj = new JSONObject();
				visaTypesObj.put("name", rs.getString(1));
				/*
				 * visaTypesObj.put("visafee", rs.getInt(3)); visaTypesObj.put("enjazitfee",
				 * rs.getInt(4)); visaTypesObj.put("servicefee", rs.getInt(5));
				 * visaTypesObj.put("insurancefee", rs.getInt(6));
				 * visaTypesObj.put("insuranceitfee", rs.getInt(7));
				 * visaTypesObj.put("medicalfee", rs.getInt(8));
				 */
				arr.put(visaTypesObj);
			}
			response.put("visatypes", arr);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getVisaTypes() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getVisaTypes() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(categoryStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getVisaTypes() " + response.toString());
		return response.toString();
	}

	public String getGender() {
		Connection vpsConn = null;
		Statement stmt = null;
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject genderObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			stmt = vpsConn.createStatement();
			String query = "select * from vps_gender";
			logger.info("in getGender() and fetching the result for query - " + query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				genderObj = new JSONObject();
				genderObj.put("name", rs.getString(1));
				arr.put(genderObj);
			}
			response.put("gender", arr);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getGender() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getGender() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(stmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getGender() " + response.toString());
		return response.toString();
	}

	public String getMaritalStatus() {
		Connection vpsConn = null;
		Statement maritalStmt = null;
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject msObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			maritalStmt = vpsConn.createStatement();
			String maritalQuery = "select * from vps_marital_status";
			logger.info("in getMaritalStatus() and fetching the result for query - " + maritalQuery);
			ResultSet rs = maritalStmt.executeQuery(maritalQuery);
			while (rs.next()) {
				msObj = new JSONObject();
				msObj.put("name", rs.getString(1));
				arr.put(msObj);
			}
			response.put("maritalstatus", arr);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getMaritalStatus() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getMaritalStatus() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(maritalStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getMaritalStatus() " + response.toString());
		return response.toString();
	}

	public String getReligion() {
		Connection vpsConn = null;
		Statement religionStmt = null;
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject religionObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			religionStmt = vpsConn.createStatement();
			String religionQuery = "select * from vps_religion";
			logger.info("in getReligion() and fetching the result for query - " + religionQuery);
			ResultSet rs = religionStmt.executeQuery(religionQuery);
			while (rs.next()) {
				religionObj = new JSONObject();
				religionObj.put("name", rs.getString(1));
				arr.put(religionObj);
			}
			response.put("religion", arr);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getReligion() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getReligion() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(religionStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getReligion() " + response.toString());
		return response.toString();
	}

	public String getModeOfEntry() {
		Connection vpsConn = null;
		Statement modeofentryStmt = null;
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject moeObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			modeofentryStmt = vpsConn.createStatement();
			String modeofentryQuery = "select * from vps_mode_of_entry";
			logger.info("in getModeOfEntry() and fetching the result for query - " + modeofentryQuery);
			ResultSet rs = modeofentryStmt.executeQuery(modeofentryQuery);
			while (rs.next()) {
				moeObj = new JSONObject();
				moeObj.put("name", rs.getString(1));
				arr.put(moeObj);
			}
			response.put("modeofentry", arr);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getModeOfEntry() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getModeOfEntry() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(modeofentryStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getModeOfEntry() " + response.toString());
		return response.toString();
	}

	public String getPortOfEntries() {
		Connection vpsConn = null;
		Statement portofentryStmt = null;
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject poeObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			portofentryStmt = vpsConn.createStatement();
			String portofentryQuery = "select * from vps_port_of_entry";
			logger.info("in getPortOfEntries() and fetching the result for query - " + portofentryQuery);
			ResultSet rs = portofentryStmt.executeQuery(portofentryQuery);
			while (rs.next()) {
				poeObj = new JSONObject();
				poeObj.put("name", rs.getString(1));
				arr.put(poeObj);
			}
			response.put("portofentries", arr);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getPortOfEntries() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getPortOfEntries() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(portofentryStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getPortOfEntries() " + response.toString());
		return response.toString();
	}

	public String getTypeOfEntries() {
		Connection vpsConn = null;
		Statement typeofentryStmt = null;
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject toeObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			typeofentryStmt = vpsConn.createStatement();
			String typeofentryQuery = "select * from vps_type_of_entries";
			logger.info("in getTypeOfEntries() and fetching the result for query - " + typeofentryQuery);
			ResultSet rs = typeofentryStmt.executeQuery(typeofentryQuery);
			while (rs.next()) {
				toeObj = new JSONObject();
				toeObj.put("name", rs.getString(1));
				arr.put(toeObj);
			}
			response.put("typeofentries", arr);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getTypeOfEntries() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getTypeOfEntries() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(typeofentryStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getTypeOfEntries() " + response.toString());
		return response.toString();
	}

	public String getPassportTypes(String countryId) {
		Connection vpsConn = null;
		Statement passportStmt = null;
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject pstObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			passportStmt = vpsConn.createStatement();
			String pptQuery = "select pst.* from vps_passport_types pst " + "left join vps_country cn "
					+ "on  pst.country_id = cn.country_id " + "where cn.country_id='" + countryId + "'";
			logger.info("in getPassportTypes() and fetching the result for query - " + pptQuery);
			ResultSet rs = passportStmt.executeQuery(pptQuery);
			while (rs.next()) {
				pstObj = new JSONObject();
				pstObj.put("name", rs.getString(1));
				arr.put(pstObj);
			}
			response.put("passporttypes", arr);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getPassportTypes() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getPassportTypes() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(passportStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getPassportTypes() " + response.toString());
		return response.toString();
	}

	public String getCities(String countryId) {
		Connection vpsConn = null;
		Statement citiStmt = null;
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject pstObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			citiStmt = vpsConn.createStatement();
			String citiQuery = "SELECT * FROM vps_service_center where parent_id is null and  country_id='" + countryId + "'";
			/*String citiQuery = "select city.* from vps_city city " + "left join vps_country cn "
					+ "on  city.country_id = cn.country_id " + "where cn.country_id='" + countryId + "'";
			logger.info("in getCities() and fetching the result for query - " + citiQuery);*/
			ResultSet rs = citiStmt.executeQuery(citiQuery);
			while (rs.next()) {
				pstObj = new JSONObject();
				pstObj.put("id", rs.getString(1));
				pstObj.put("name", rs.getString(2));
				arr.put(pstObj);
			}
			response.put("cities", arr);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getCities() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getCities() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(citiStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getCities() " + response.toString());
		return response.toString();
	}

	public String getServiceCenters(String countryId, String visaType, String missionId) {
		Connection vpsConn = null;
		Statement serviceCenterStmt = null;
		JSONObject response = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject scObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			serviceCenterStmt = vpsConn.createStatement();
			String serviceCenterQuery = "select sc.* from vps_service_center sc\n" + 
					"left join vps_visa_types vt on vt.sc_id = sc.sc_id\n" + 
					"where sc.parent_id IN (select sc_id from vps_service_center where parent_id = \n" + 
					"(select sc_id from vps_service_center where parent_id is null and country_id='" + countryId + "' and sc_id='"+ missionId +"'))\n" + 
					"and vt.visa_type='" + visaType + "' group by sc.sc_id";
			/*String serviceCenterQuery = "select sc.sc_id,sc.name,sc.address,sc.city from vps_service_center sc " + "left join vps_visa_types vt " + 
					"on sc.sc_id=vt.sc_id " + "left join vps_country cn " + "on cn.country_id = sc.country_id " + 
					"where vt.visa_type='" + visaType + "' and cn.country_id='" + countryId + "' group by sc.sc_id,sc.name,sc.address,sc.city";*/
			
			logger.info("in getServiceCenters() and fetching the result for query - " + serviceCenterQuery);
			ResultSet rs = serviceCenterStmt.executeQuery(serviceCenterQuery);
			while (rs.next()) {
				scObj = new JSONObject();
				scObj.put("sc_id", rs.getString(1));
				scObj.put("name", rs.getString(2));
				scObj.put("address", rs.getString(3));
				scObj.put("city", rs.getString(4));
				scObj.put("country_id", rs.getString(5));
				scObj.put("parent_id", rs.getString(6));
				arr.put(scObj);
				System.out.println(scObj);
			}
			response.put("servicecenters", arr);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getServiceCenters() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getServiceCenters() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(serviceCenterStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getServiceCenters() " + response.toString());
		return response.toString();
	}

	public String getAvailableTimeSlots(String serviceId, String startDay, String endDay) {
		HashMap<String, JSONObject> timeSlotMap = new HashMap<String, JSONObject>();
		HashMap<String, JSONObject> timeSlotConfigMap = new HashMap<String, JSONObject>();
		String response = "";
		Connection vpsConn = null;
		Statement timSlotStmt = null;
		JSONObject tsObj = new JSONObject();
		JSONObject tsConfigObj = new JSONObject();
		try {
			vpsConn = vpsDataSource.getConnection();
			timSlotStmt = vpsConn.createStatement();

			// TODO: add start & end dates in where clause
			String timeSlotQuery = "select day,slot_no,scheduled_normal_visits,scheduled_priority_visits from vps_time_slots where sc_id='"
					+ serviceId + "' order by day,slot_no asc";
			logger.info("in getAvailableTimeSlots() and fetching the result for query - " + timeSlotQuery);
			ResultSet rs = timSlotStmt.executeQuery(timeSlotQuery);
			String key = null;
			while (rs.next()) {
				key = rs.getString(1) + ":" + rs.getInt(2);
				tsObj = new JSONObject();
				tsObj.put("slot_no", rs.getInt(2));
				tsObj.put("day", rs.getString(1));
				tsObj.put("scheduled_normal_visits", rs.getInt(3));
				tsObj.put("scheduled_priority_visits", rs.getInt(4));
				timeSlotMap.put(key, tsObj);
			}
			String timConfigQuery = "select sc_id,slot_no,start_time,end_time,max_normal_visits,max_priority_visits from vps_time_slot_configuration where sc_id='"
					+ serviceId + "'";
			logger.info("in getAvailableTimeSlots() and fetching the result for query - " + timConfigQuery);
			rs = timSlotStmt.executeQuery(timConfigQuery);
			while (rs.next()) {
				key = rs.getString(2);
				tsConfigObj = new JSONObject();
				tsConfigObj.put("slot_no", rs.getInt(2));
				tsConfigObj.put("start_time", rs.getString(3));
				tsConfigObj.put("max_normal_visits", rs.getInt(5));
				tsConfigObj.put("max_priority_visits", rs.getInt(6));
				timeSlotConfigMap.put(key, tsConfigObj);
			}
			response = returnTimeSlotJson(timeSlotMap, timeSlotConfigMap, startDay, endDay);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getAvailableTimeSlots() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getAvailableTimeSlots() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(timSlotStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getAvailableTimeSlots() " + response.toString());
		return response;
	}
/*
 * @author sakshi
 * This method is creating new appointments and saving user related details in DB, and 
 * calling a method to generate Appointment reference No.
 * @param input contains all the fields of the form filled by the user.
 * @return String Appointment reference Id
 */
	public JSONObject createAppointment(String input) {
		Connection vpsConn = null;
		PreparedStatement createStmt = null;
		JSONObject response = new JSONObject();
		try {
			response.put("app_ref_id", "null");
			String appointmentId = dbUtils.generateId().toUpperCase();
			String visaAppointmentQuery = dbUtils.createVisaAppointmentQuery(input, appointmentId);
			String visaTimeSlotQuery = dbUtils.visaTimeSlotQuery(input);
			String visaApplicationQuery = dbUtils.createVisaApplicationQuery(input, appointmentId);
			vpsConn = vpsDataSource.getConnection();
			vpsConn.setAutoCommit(false);
			createStmt = vpsConn.prepareStatement(visaTimeSlotQuery);
			int affectedRows = createStmt.executeUpdate();

			if (affectedRows >= 0) {
				createStmt = vpsConn.prepareStatement(visaAppointmentQuery);
				affectedRows = createStmt.executeUpdate();
				if (affectedRows >= 0) {
					createStmt = vpsConn.prepareStatement(visaApplicationQuery);
					affectedRows = createStmt.executeUpdate();
					if (affectedRows == 0) {
						response.put("app_ref_id", "null");
					}
					if (affectedRows >= 0) {
						response.put("app_ref_id", appointmentId.toUpperCase());
					}
				}
			}
			vpsConn.commit();
			
		} catch (SQLException e) {
			logger.error("failed to fetch details from createAppointment() due to - " + e);
			try {
				vpsConn.rollback();
			} catch (SQLException sql) {
				logger.error("failed to fetch details from createAppointment() due to - " + e);
				sql.printStackTrace();
			}
			e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from createAppointment() due to - " + e);
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from createAppointment() " + response.toString());
		return response;
	}

	public String returnTimeSlotJson(HashMap<String, JSONObject> tsMap, HashMap<String, JSONObject> tsConfigMap,
			String startDay, String endDay) {
		Set<Entry<String, JSONObject>> config = tsConfigMap.entrySet();
		SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar cal = new GregorianCalendar();
		Date startDate = new Date();
		Date endDate = null;
		int diffInDays = 0;
		JSONObject finalTsObj = new JSONObject();
		JSONObject finalTsConfigObj = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONArray days = new JSONArray();

		try {
			if (startDay != null) {
				startDate = dateOnly.parse(startDay);
			}
			if (endDay != null) {
				endDate = dateOnly.parse(endDay);
			}
			if (endDate != null) {
				diffInDays = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
			}
			if (diffInDays == 0) {
				diffInDays = 30;
			}
			cal.setTime(startDate);

			JSONObject finalDaysObj;
			// for each day
			for (int i = 0; i < diffInDays; i++) {
				finalDaysObj = new JSONObject();
				finalDaysObj.put("day", dateOnly.format(cal.getTime()));

				// for each entry of service center
				for (Entry<String, JSONObject> configEntry : config) {
					JSONObject tsConfigValObj = configEntry.getValue();

					JSONObject tsValObj = tsMap
							.get(dateOnly.format(cal.getTime()) + ":" + tsConfigValObj.get("slot_no"));

					int scheduledNormalVisits = 0, scheduledPriorityVisits = 0;
					int maxPriorityVisits = Integer.parseInt(tsConfigValObj.get("max_priority_visits").toString());
					int maxNormalVisits = Integer.parseInt(tsConfigValObj.get("max_normal_visits").toString());

					if (tsValObj != null) {
						// had earlier scheduled appointments, so subtract them
						scheduledNormalVisits = Integer.parseInt(tsValObj.get("scheduled_normal_visits").toString());
						scheduledPriorityVisits = Integer
								.parseInt(tsValObj.get("scheduled_priority_visits").toString());

					}
					finalTsObj = new JSONObject();
					finalTsObj.put("slot_no", tsConfigValObj.get("slot_no"));
					String startTimeVal = tsConfigValObj.get("start_time").toString();
					int startTime = Integer.parseInt(startTimeVal.substring(0, 2));
					if (startTime > 11) {
						if (startTime >= 13) {
							startTime = startTime - 12;
							if (startTime > 9) {
								finalTsObj.put("start_time",
										startTime + startTimeVal.substring(2, startTimeVal.length() - 3) + " PM");
							} else {
								finalTsObj.put("start_time",
										"0" + startTime + startTimeVal.substring(2, startTimeVal.length() - 3) + " PM");
							}
						} else {
							finalTsObj.put("start_time", startTimeVal.substring(0, startTimeVal.length() - 3) + " PM");
						}
					} else {
						finalTsObj.put("start_time", startTimeVal.substring(0, startTimeVal.length() - 3) + " AM");
					}
					finalTsObj.put("max_normal_visits", maxNormalVisits - scheduledNormalVisits);
					finalTsObj.put("max_priority_visits", maxPriorityVisits - scheduledPriorityVisits);
					arr.put(finalTsObj);
				}
				finalDaysObj.put("slots", arr);
				days.put(finalDaysObj);
				cal.add(Calendar.DATE, 1);
				arr = new JSONArray();
			}
			finalTsConfigObj.put("days", days);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return finalTsConfigObj.toString();
	}

	public String getVisaFeeDetails(String visaType, String noOfVisits, String countryId) {
		Connection vpsConn = null;
		Statement visaFeeStmt = null;
		JSONObject response = new JSONObject();
		JSONObject visaFeeObj = new JSONObject();
		try {
			response.put("data", "null");
			vpsConn = vpsDataSource.getConnection();
			visaFeeStmt = vpsConn.createStatement();
			String visaFeeQuery = "select visa_fee,enjazit_fee,service_fee,insurance_fee,insuranceit_fee,medical_fee from vps_visa_fees where visa_type='"
					+ visaType + "' and country_id='" + countryId + "' and type_of_entry='" + noOfVisits + "'";
			logger.info("in getVisaFeeDetails() and fetching the result for query - " + visaFeeQuery);
			ResultSet rs = visaFeeStmt.executeQuery(visaFeeQuery);
			if (rs.next()) {
				visaFeeObj = new JSONObject();
				int visaFee = rs.getInt(1);
				int enjazItFee = rs.getInt(2);
				int serviceFee = rs.getInt(3);
				int insuranceFee = rs.getInt(4);
				int insuranceItFee = rs.getInt(5);
				int medicalFee = rs.getInt(6);
				int totalFee = visaFee + enjazItFee + serviceFee + insuranceFee + insuranceItFee + medicalFee;
				visaFeeObj.put("visa_fee", visaFee);
				visaFeeObj.put("enjazit_fee", enjazItFee);
				visaFeeObj.put("service_fee", serviceFee);
				visaFeeObj.put("insurance_fee", insuranceFee);
				visaFeeObj.put("insuranceit_fee", insuranceItFee);
				visaFeeObj.put("medical_fee", medicalFee);
				visaFeeObj.put("total_fee", totalFee);
			} else {
				visaFeeQuery = "select visa_fee,enjazit_fee,service_fee,insurance_fee,insuranceit_fee,medical_fee from vps_visa_fees where visa_type='"
						+ visaType + "' and country_id='" + countryId + "' and type_of_entry='ALL'";
				logger.info("in getVisaFeeDetails() and fetching the result for query - " + visaFeeQuery);
				rs = visaFeeStmt.executeQuery(visaFeeQuery);
				while (rs.next()) {
					int visaFee = rs.getInt(1);
					int enjazItFee = rs.getInt(2);
					int serviceFee = rs.getInt(3);
					int insuranceFee = rs.getInt(4);
					int insuranceItFee = rs.getInt(5);
					int medicalFee = rs.getInt(6);
					int totalFee = visaFee + enjazItFee + serviceFee + insuranceFee + insuranceItFee + medicalFee;
					visaFeeObj.put("visa_fee", visaFee);
					visaFeeObj.put("enjazit_fee", enjazItFee);
					visaFeeObj.put("service_fee", serviceFee);
					visaFeeObj.put("insurance_fee", insuranceFee);
					visaFeeObj.put("insuranceit_fee", insuranceItFee);
					visaFeeObj.put("medical_fee", medicalFee);
					visaFeeObj.put("total_fee", totalFee);
				}
			}
			response.put("data", visaFeeObj);
		} catch (SQLException e) {
			logger.error("failed to fetch details from getVisaFeeDetails() due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch details from getVisaFeeDetails() due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(visaFeeStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getVisaFeeDetails() " + response.toString());
		return response.toString();
	}
	public String getTrackDetailsByENum(String eNum) {
		Connection vpsConn = null;
		Statement trackDetailsStmt = null;
		String trackDetailsQuery;
		JSONObject response = new JSONObject();
		JSONObject trackObj = new JSONObject();
		JSONArray trackArr = new JSONArray();
		try {
			response.put("track_details", new JSONArray());
			vpsConn = vpsDataSource.getConnection();
			trackDetailsStmt = vpsConn.createStatement();
			trackDetailsQuery = "select vt.message, vu.user_name, vt.action_time from vsc_visa_tracker vt left join vsc_users vu on " + 
					"vu.email=vt.vsc_user_id where e_number='"+eNum+"' order by action_time asc";
			ResultSet rs = trackDetailsStmt.executeQuery(trackDetailsQuery);
			while (rs.next()) {
				trackObj = new JSONObject();
				trackObj.put("status", rs.getString(1));
				trackObj.put("uname", rs.getString(2));
				trackObj.put("time", rs.getString(3));
				trackArr.put(trackObj);
			}
			response.put("track_details", trackArr);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeStatement(trackDetailsStmt);
			dbUtils.closeConnection(vpsConn);
		}
		return response.toString();
	}

	public JSONObject getSummaryDetails(String refId, String serviceid) {
		logger.info("in getSummaryDetails() to fetch summary details for ref id - " + refId
				+ " and service id - " + serviceid);
		Connection vpsConn = null;
		Statement summaryStmt = null;
		JSONObject response = new JSONObject();
		JSONObject appointmentObj = new JSONObject();
		JSONArray appointmentArr = new JSONArray();
		JSONObject sponsorObj = new JSONObject();
		JSONArray sponsorArr = new JSONArray();
		JSONObject passportObj = new JSONObject();
		JSONArray passportArr = new JSONArray();
		JSONObject personalObj = new JSONObject();
		JSONArray personalArr = new JSONArray();
		JSONObject addressObj = new JSONObject();
		JSONArray addressArr = new JSONArray();
		try {
			vpsConn = vpsDataSource.getConnection();
			summaryStmt = vpsConn.createStatement();

			String summaryQuery = "select sc.name as serviceCenter,va.app_date as applicationDate,vtsc.start_time as applicationTime,"
					+ "concat(vva.first_name,' ',vva.last_name) as applicantName,va.app_type as applicationType,"
					+ "vva.sponsor_id as sponsorId, vva.invitation_no as invitationNo, vva.invitation_or_preapproved as invPreFlag,"
					+ "vva.passport_no as passportNo, vva.pp_type as passportType, vva.date_of_issue as ppDOI, vva.place_of_issue ppPOI,"
					+ "vva.date_of_expiry as ppDOE, vva.first_name as firstName, vva.last_name as lastName, vva.father_name as fatherName,"
					+ " vva.marital_status as maritalStatus,vva.gender, vva.religion, vva.qualification, vva.occupation, vva.email, vva.address_1 as houseNo,"
					+ " vva.address_2 as building, vva.address_3 as area, vva.address_city as city, vva.mobile_no,va.total_visa_fee "
					+ "from vps_appointment va " + "left join vps_service_center sc on sc.sc_id=va.sc_id "
					+ "left join vps_time_slot_configuration vtsc on vtsc.slot_no=va.slot_no and vtsc.sc_id=va.sc_id "
					+ "left join vps_visa_application vva on vva.app_id=va.app_id and vva.sc_id=va.sc_id "
				    + "where va.app_id='"+ refId + "' and va.sc_id='" + serviceid + "'";
					//+ "where va.app_id='3A89790E' and va.sc_id='IN001'";

			logger.info("in getSummaryDetails() and executing the query - " + summaryQuery);

			ResultSet rs = summaryStmt.executeQuery(summaryQuery);
			while (rs.next()) {
				appointmentObj.put("serviceCenter", rs.getString(1));
				appointmentObj.put("applicationDate", rs.getString(2));
				int appTime = Integer.parseInt(rs.getString(3).substring(0, 2));
				String time = rs.getString(3);
				if (appTime > 11) {
					if (appTime >= 13) {
						appTime = appTime - 12;
						if (appTime > 9) {
							time = time.substring(2, time.length() - 3) + " PM";
						} else {
							time = "0" + appTime + time.substring(2, time.length() - 3) + " PM";
						}
					} else {
						time = time.substring(0, time.length() - 3) + " PM";
					}
				} else {
					time = time.substring(0, time.length() - 3) + " AM";
				}
				appointmentObj.put("applicationTime", time);
				appointmentObj.put("applicantName", rs.getString(4));
				appointmentObj.put("applicationType", rs.getString(5));
				appointmentObj.put("total_visa_fee", rs.getString(28));
				appointmentArr.put(appointmentObj);
				
				sponsorObj.put("sponsorId", rs.getString(6));
				sponsorObj.put("invitationNo", rs.getString(7));
				sponsorObj.put("invPreFlag", rs.getString(8));
				sponsorArr.put(sponsorObj);
				
				passportObj.put("passportNo", rs.getString(9));
				passportObj.put("passportType", rs.getString(10));
				passportObj.put("ppDOI", rs.getString(11));
				passportObj.put("ppPOI", rs.getString(12));
				passportObj.put("ppDOE", rs.getString(13));
				passportArr.put(passportObj);
				
				personalObj.put("firstName", rs.getString(14));
				personalObj.put("lastName", rs.getString(15));
				personalObj.put("fatherName", rs.getString(16));
				personalObj.put("maritalStatus", rs.getString(17));
				personalObj.put("gender", rs.getString(18));
				personalObj.put("religion", rs.getString(19));
				personalObj.put("qualification", rs.getString(20));
				personalObj.put("occupation", rs.getString(21));
				personalObj.put("email", rs.getString(22));
				personalArr.put(personalObj);
				
				addressObj.put("houseNo", rs.getString(23));
				addressObj.put("building", rs.getString(24));
				addressObj.put("area", rs.getString(25));
				addressObj.put("city", rs.getString(26));
				addressObj.put("mobile_no", rs.getString(27));
				addressArr.put(addressObj);
			}
			response.put("appointmentInfo", appointmentArr);
			response.put("sponsorInfo", sponsorArr);
			response.put("passportInfo", passportArr);
			response.put("personalInfo", personalArr);
			response.put("addressInfo", addressArr);
		} catch (SQLException e) {
			logger.error("failed to fetch summary details for ref id - " + refId + " service id - " + serviceid
					+ " due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch summary details for ref id - " + refId + " service id - " + serviceid
					+ " due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(summaryStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getSummaryDetails() " + response.toString());
		return response;
	}
	
	public String updateAppointment(String appId, String payrefid ) {
		String updateAppointmentQuery = null;
		Statement updateStmt = null;
		Connection vpsConn = null;
		JSONObject response = new JSONObject();
		
		updateAppointmentQuery = "UPDATE vps_appointment SET payment_reference = " + payrefid + " WHERE app_id = '" + appId+"'";
        logger.info("in updateAppointmentQuery() to prepare query - " + updateAppointmentQuery);
		try {
			vpsConn = vpsDataSource.getConnection();
			updateStmt = vpsConn.createStatement();
			PreparedStatement preparedStmt = vpsConn.prepareStatement(updateAppointmentQuery);
			
			
		      preparedStmt.executeUpdate();
		      response.put("appId", appId);
		      vpsConn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtils.closeStatement(updateStmt);
			dbUtils.closeConnection(vpsConn);
		}
		return response.toString();
	}
	public String rescheduleAppointment(String appId,String input) {
		Statement updateStmt = null;
		Connection vpsConn = null;
		JSONObject response = new JSONObject();
		String visaAppointmentQuery;
		
		try {
			vpsConn = vpsDataSource.getConnection();
			updateStmt = vpsConn.createStatement();
			visaAppointmentQuery = dbUtils.updateVisaApplicationQuery(input, appId);
			//PreparedStatement preparedStmt = vpsConn.prepareStatement(rescheduleAppointmentQuery);
			PreparedStatement preparedStmt1 = vpsConn.prepareStatement(visaAppointmentQuery);
			
			
		      //preparedStmt.executeUpdate();
		      preparedStmt1.executeUpdate();
		      response.put("appId", appId);
		      vpsConn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			dbUtils.closeStatement(updateStmt);
			dbUtils.closeConnection(vpsConn);
		}
		return response.toString();
	}

	public String modifyAppointment(String appId, String input) throws ParseException {
		
		Statement updateStmt = null;
		Connection vpsConn = null;
		JSONObject response = new JSONObject();
		//String visaAppointmentQuery = dbUtils.updateVisaAppointmentQuery(appId, input);
		String visaApplicationQuery = dbUtils.updateVisaApplicationQuery(appId, input);
		
		try {
			vpsConn = vpsDataSource.getConnection();
			updateStmt = vpsConn.createStatement();
			
			//PreparedStatement preparedStmt1 = vpsConn.prepareStatement(visaAppointmentQuery);
			PreparedStatement preparedStmt2 = vpsConn.prepareStatement(visaApplicationQuery);
		    //preparedStmt1.executeUpdate();
		      preparedStmt2.executeUpdate();
		      response.put("appId", appId);
		      vpsConn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtils.closeStatement(updateStmt);
			dbUtils.closeConnection(vpsConn);
		}
		return response.toString();
	}

	public String cancelAppointment(String appId, String appStatus) {
		String cancelAppointmentQuery = null;
		Statement updateStmt = null;
		Connection vpsConn = null;
		JSONObject response = new JSONObject();
		
		cancelAppointmentQuery = "UPDATE vps_appointment SET app_status =  '"+appStatus+"' WHERE app_id = '" + appId+"'";
        logger.info("in cancelAppointmentQuery() to prepare query - " + cancelAppointmentQuery);
		try {
			vpsConn = vpsDataSource.getConnection();
			updateStmt = vpsConn.createStatement();
			PreparedStatement preparedStmt = vpsConn.prepareStatement(cancelAppointmentQuery);
			
			
		      preparedStmt.executeUpdate();
		      response.put("appId", appId);
		      vpsConn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtils.closeStatement(updateStmt);
			dbUtils.closeConnection(vpsConn);
		}
		return response.toString();
     }
	public String getAppointmentDetails(String refId) {
		logger.info("in getAppointmentDetails() to fetch summary details for ref id - " + refId+ " " );
		Connection vpsConn = null;
		Statement summaryStmt = null;
		JSONObject response = new JSONObject();
		JSONObject appointmentObj = new JSONObject();
		JSONArray appointmentArr = new JSONArray();
		
		try {
			vpsConn = vpsDataSource.getConnection();
			summaryStmt = vpsConn.createStatement();

			/*String appDetailsQuery = "select sc.name as serviceCenter,va.app_date as applicationDate,vtsc.start_time as applicationTime,"
					+ "concat(vva.first_name,' ',vva.last_name) as applicantName,va.app_type as applicationType,"
					+ "vva.sponsor_id as sponsorId, vva.invitation_no as invitationNo, vva.invitation_or_preapproved as invPreFlag,"
					+ "vva.passport_no as passportNo, vva.pp_type as passportType, vva.date_of_issue as ppDOI, vva.place_of_issue ppPOI,"
					+ "vva.date_of_expiry as ppDOE, vva.first_name as firstName, vva.last_name as lastName, vva.father_name as fatherName,"
					+ " vva.marital_status as maritalStatus,vva.gender, vva.religion, vva.qualification, vva.occupation, vva.email, vva.address_1 as houseNo,"
					+ " vva.address_2 as building, vva.address_3 as area, vva.address_city as city, vva.mobile_no,vva.date_of_birth,vva.nationality,vva.sc_id,va.total_visa_fee,va.app_status,va.visa_type "
					+ "from vps_appointment va " + "left join vps_service_center sc on sc.sc_id=va.sc_id "
					+ "left join vps_time_slot_configuration vtsc on vtsc.slot_no=va.slot_no and vtsc.sc_id=va.sc_id "
					+ "left join vps_visa_application vva on vva.app_id=va.app_id and vva.sc_id=va.sc_id "
					+ "where va.app_id='"+ refId + "'";*/

//			QUERY  start
			StringBuffer appDetailsQuery1=new StringBuffer("select p.sc_id,p.name missionname,");
					appDetailsQuery1.append("c.applicationDate,c.applicationTime,c.applicantName,c.applicationType,");
					appDetailsQuery1.append("c.parentname as serviceCenter,c.sponsorId as sponsorId,c.invitationNo as invitationNo,c.invPreFlag as invPreFlag,c.passportNo as passportNo,"); 
					appDetailsQuery1.append("c.passportType as passportType,c.ppDOI as ppDOI,c.ppPOI ppPOI,c.ppDOE as ppDOE,c.firstName as firstName, "); 
					appDetailsQuery1.append("c.lastName as lastName,c.fatherName as fatherName, c.maritalStatus as maritalStatus,c.gender, c.religion, c.qualification,"); 
					appDetailsQuery1.append("c.occupation, c.email, c.houseNo as houseNo,c.building as building, c.area as area, c.city as city,");
					appDetailsQuery1.append("c.mobile_no,c.sc_id, c.nationality,c.date_of_birth,c.total_visa_fee,c.app_status,c.visa_type from  vps_service_center p join");
											
					appDetailsQuery1.append("(select a.parent_id hubparent,a.name parentname,b.parent_id missionid,");
					appDetailsQuery1.append("a.sc_id servicesc,b.sc_id hubsc,service.applicationDate,service.applicationTime,");
					appDetailsQuery1.append("service.applicantName,service.applicationType,service.sponsor_id as sponsorId, service.invitation_no as invitationNo,");
					appDetailsQuery1.append(" service.invitation_or_preapproved as invPreFlag,service.passport_no as passportNo,service.pp_type as passportType,");
					appDetailsQuery1.append(" service.date_of_issue as ppDOI,service.place_of_issue ppPOI,service.date_of_expiry as ppDOE,service.first_name as firstName,"); 
					appDetailsQuery1.append("service.last_name as lastName,service.father_name as fatherName,service.marital_status as maritalStatus,service.gender,");
					appDetailsQuery1.append("service.religion,service.qualification,service.occupation,service.email,service.address_1 as houseNo,service.address_2 as building,"); 
					appDetailsQuery1.append("service.address_3 as area,service.address_city as city,service.mobile_no,service.sc_id,service.nationality,service.date_of_birth,");
					appDetailsQuery1.append("service.total_visa_fee,service.app_status,service.visa_type from vps_service_center a join vps_service_center b on a.parent_id=b.sc_id join"); 

					appDetailsQuery1.append("(select sc.name as serviceCenter,va.app_date as applicationDate,vtsc.start_time as applicationTime,");
					appDetailsQuery1.append("concat(vva.first_name,' ',vva.last_name) as applicantName, va.app_type as applicationType,");
					appDetailsQuery1.append(" vva.sponsor_id as sponsor_id, vva.invitation_no as invitation_no,");
					appDetailsQuery1.append("vva.invitation_or_preapproved as invitation_or_preapproved,vva.passport_no as passport_no, ");
					appDetailsQuery1.append("vva.pp_type as pp_type, vva.date_of_issue as date_of_issue, vva.place_of_issue place_of_issue, vva.date_of_expiry as date_of_expiry, ");
					appDetailsQuery1.append("vva.first_name as first_name, vva.last_name as last_name,vva.father_name as father_name,  vva.marital_status as marital_status,");
					appDetailsQuery1.append("vva.gender, vva.religion, vva.qualification,vva.occupation, vva.email, vva.address_1 as address_1,");
					appDetailsQuery1.append("vva.address_2 as address_2, vva.address_3 as address_3, vva.address_city as address_city,");
					appDetailsQuery1.append("vva.mobile_no, vva.sc_id, vva.nationality,vva.date_of_birth,va.total_visa_fee,va.app_status,va.visa_type ");	      
					appDetailsQuery1.append("from vps_appointment va left join vps_service_center sc ");
					appDetailsQuery1.append("on sc.sc_id=va.sc_id left join vps_time_slot_configuration vtsc on vtsc.slot_no=va.slot_no and vtsc.sc_id=va.sc_id ");
					appDetailsQuery1.append(" left join vps_visa_application vva on vva.app_id=va.app_id and vva.sc_id=va.sc_id  where va.app_id='"+ refId + "') ");
					appDetailsQuery1.append("service on a.name=service.serviceCenter	) c on p.sc_id=c.missionid");
			
			
//			Query End
			
			
			
	
			
			
			logger.info("in getSummaryDetails() and executing the query - " + appDetailsQuery1.toString());

			ResultSet rs = summaryStmt.executeQuery(appDetailsQuery1.toString());
			while (rs.next()) {
				appointmentObj.put("missionid", rs.getString(1));
				appointmentObj.put("missionname", rs.getString(2));
				appointmentObj.put("applicationDate", rs.getString(3));
				int appTime = Integer.parseInt(rs.getString(4).substring(0, 2));
				String time = rs.getString(3);
				if (appTime > 11) {
					if (appTime >= 13) {
						appTime = appTime - 12;
						if (appTime > 9) {
							time = time.substring(2, time.length() - 3) + " PM";
						} else {
							time = "0" + appTime + time.substring(2, time.length() - 3) + " PM";
						}
					} else {
						time = time.substring(0, time.length() - 3) + " PM";
					}
				} else {
					time = time.substring(0, time.length() - 3) + " AM";
				}
				appointmentObj.put("applicationTime", time);
				appointmentObj.put("applicantName", rs.getString(5));
				appointmentObj.put("applicationType", rs.getString(6));
				appointmentObj.put("serviceCenter", rs.getString(7));			
				appointmentObj.put("sponsorId", rs.getString(8));
				appointmentObj.put("invitationNo", rs.getString(9));
				appointmentObj.put("invPreFlag", rs.getString(10));
				
				appointmentObj.put("passportNo", rs.getString(11));
				appointmentObj.put("passportType", rs.getString(12));
				appointmentObj.put("ppDOI", rs.getString(13));
				appointmentObj.put("ppPOI", rs.getString(14));
				appointmentObj.put("ppDOE", rs.getString(15));
				
				appointmentObj.put("firstName", rs.getString(16));
				appointmentObj.put("lastName", rs.getString(17));
				appointmentObj.put("fatherName", rs.getString(18));
				appointmentObj.put("religion", rs.getString(19));
				
				appointmentObj.put("gender", rs.getString(20));
				appointmentObj.put("maritalStatus", rs.getString(21));
				appointmentObj.put("qualification", rs.getString(22));
				appointmentObj.put("occupation", rs.getString(23));
				appointmentObj.put("email", rs.getString(24));
				
				appointmentObj.put("houseNo", rs.getString(25));
				appointmentObj.put("building", rs.getString(26));
				appointmentObj.put("area", rs.getString(27));
				appointmentObj.put("city", rs.getString(28));
				appointmentObj.put("mobile_no", rs.getString(29));
				appointmentObj.put("sc_id", rs.getString(30));
				appointmentObj.put("nationality", rs.getString(31));
				appointmentObj.put("date_of_birth", rs.getString(32));
				appointmentObj.put("total_visa_fee", rs.getString(33));
				appointmentObj.put("app_status", rs.getString(34));
				appointmentObj.put("visa_type", rs.getString(35));
				
				
				appointmentArr.put(appointmentObj);
			}
			response.put("appointmentInfo", appointmentArr);
			
		} catch (SQLException e) {
			logger.error("failed to fetch summary details for ref id - " + refId + " due to - " + e);
			// e.printStackTrace();
		} catch (JSONException e) {
			logger.error("failed to fetch summary details for ref id - " + refId + " due to - " + e);
			// e.printStackTrace();
		} finally {
			dbUtils.closeStatement(summaryStmt);
			dbUtils.closeConnection(vpsConn);
		}
		logger.info("response from getAppointmentDetails() " + response.toString());
		System.out.println(response.toString());
		return response.toString();
	}
}
