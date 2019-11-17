package com.techsophy.vps.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import com.techsophy.vps.service.JavaEmailService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techsophy.vps.dao.AppointmentDAO;

@Service
public class AppointmentService {
	
	@Autowired
	AppointmentDAO apDao;
	@Autowired
	JavaEmailService mailService;

	public String getAllCountries() {
		return apDao.getAllCountries();
	}

	public String getVisaTypes(String countryId) {
		return apDao.getVisaTypes(countryId);
	}

	public String getGender() {
		return apDao.getGender();
	}

	public String getReligion() {
		return apDao.getReligion();
	}

	public String getMaritalStatus() {
		return apDao.getMaritalStatus();
	}

	public String getModeOfEntry() {
		return apDao.getModeOfEntry();
	}

	public String getTypeOfEntries() {
		return apDao.getTypeOfEntries();
	}

	public String getPortOfEntries() {
		return apDao.getPortOfEntries();
	}

	public String getPassportTypes(String countryId) {
		return apDao.getPassportTypes(countryId);
	}

	public String getCities(String countryId) {
		return apDao.getCities(countryId);
	}

	public String getServiceCenters(String countryId, String visaType, String missionId) {
		return apDao.getServiceCenters(countryId, visaType, missionId);
	}

	public String getAvailableTimeSlots(String serviceId, String startDay, String endDay) {
		return apDao.getAvailableTimeSlots(serviceId, startDay, endDay);
	}
	/*
	 * @author sakshi
     * @return calls createAppointment from AppointmentDAO
     * 
	 */

	public String createAppointment(String input) {
//		return apDao.createAppointment(input);

		JSONObject respo = apDao.createAppointment(input);
		 if(respo.length()!=0) {
			 try {
				JSONObject createObj = new JSONObject(input);
				String toEmail = createObj.opt("email") != null ? "" + createObj.getString("email") + "" : null;
				mailService.createEmailMessage(toEmail, "Appointment Created", "Your appointment created successfully.");
			} catch (JSONException e) {
//				logger.err
			}
		 }
		return respo.toString();
	}

	public byte[] createBarCode(String appRefId) {
		byte[] barCode = null;
		try {
			Code128Bean bean = new Code128Bean();
			final int dpi = 160;
			bean.setModuleWidth(UnitConv.in2mm(2.8f / dpi));
			// bean.doQuietZone(false);
			File outputFile = new File(appRefId + ".JPG");
			FileOutputStream out = new FileOutputStream(outputFile);
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);
			bean.generateBarcode(canvas, appRefId);
			canvas.finish();
			barCode = Files.readAllBytes(outputFile.toPath());
			outputFile.delete();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return barCode;
	}
	
	public String getVisaFeeDetails(String visaType, String noOfVisits, String countryId) {
		return apDao.getVisaFeeDetails(visaType, noOfVisits, countryId);
	}
	public String getTrackDetailsByENum(String eNum) {
		return apDao.getTrackDetailsByENum(eNum);
	}

	public  String updateAppointment(String appId, String payrefid) {
		return apDao.updateAppointment(appId, payrefid);
	}
	public String rescheduleAppointment(String appId, String input) {
		return apDao.rescheduleAppointment(appId, input);
	}

	public String modifyAppointment(String appId,String input) throws ParseException {
		return apDao.modifyAppointment(appId, input);
	}

	public String cancelAppointment(String appId,String appStatus) {
		return apDao.cancelAppointment(appId, appStatus);
	}

	public String getAppointmentDetails(String appId) {
		return apDao.getAppointmentDetails(appId);
	}


}
