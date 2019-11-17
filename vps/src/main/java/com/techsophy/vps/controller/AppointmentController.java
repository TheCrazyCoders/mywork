package com.techsophy.vps.controller;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techsophy.vps.service.AppointmentService;

@RestController
@RequestMapping(value = "/")
public class AppointmentController {

	private final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	@Autowired
	AppointmentService aps;

	@RequestMapping(value = "/countries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllCountries() {
		logger.info("In getAllCountries()");
		return aps.getAllCountries();
	}

	@RequestMapping(value = "/visatypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getVisaTypes(@RequestParam("countryId") String countryId) {
		logger.info("In getVisaTypes()");
		return aps.getVisaTypes(countryId);
	}

	@RequestMapping(value = "/gender", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getGender() {
		logger.info("In getGender()");
		return aps.getGender();
	}

	@RequestMapping(value = "/maritalstatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getMaritalStatus() {
		logger.info("In getMaritalStatus()");
		return aps.getMaritalStatus();
	}

	@RequestMapping(value = "/religion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getReligion() {
		logger.info("In getAllCountries()");
		return aps.getReligion();
	}

	@RequestMapping(value = "/modeofentry", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getModeOfEntry() {
		logger.info("In getModeOfEntry()");
		return aps.getModeOfEntry();
	}

	@RequestMapping(value = "/portofentry", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPortOfEntries() {
		logger.info("In getAllCountries()");
		return aps.getPortOfEntries();
	}

	@RequestMapping(value = "/typeofentry", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getTypeOfEntries() {
		logger.info("In getTypeOfEntries()");
		return aps.getTypeOfEntries();
	}

	@RequestMapping(value = "/passporttypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPassportTypes(@RequestParam("countryId") String countryId) {
		logger.info("In getPassportTypes() for country - " + countryId);
		return aps.getPassportTypes(countryId);
	}

	@RequestMapping(value = "/cities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCities(@RequestParam("countryId") String countryId) {
		logger.info("In getCities() for country - " + countryId);
		return aps.getCities(countryId);
	}

	@RequestMapping(value = "/origins", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCountryOrigins() {
		logger.info("In getCountryOrigins()");
		return aps.getAllCountries();
	}
/*
 * @author sakshi
 * This method is used to create Appointment.
 * @param input contains all the fields filled by the user.
 * @return calls createAppointment from AppointmentService
 */
	@RequestMapping(value = "/create", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String createAppointment(@RequestBody String input) {
		logger.info("In createAppointment()");
		return aps.createAppointment(input);
	}

	@RequestMapping(value = "/servicecenters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getServiceCenters(@RequestParam("countryId") String countryId,
			@RequestParam("visaType") String visaType, @RequestParam("missionId") String missionId) {
		logger.info("In getServiceCenters() for country - " + countryId + ", missionId - "+missionId+" and visa type - " + visaType);
		return aps.getServiceCenters(countryId, visaType, missionId);
	}

	@RequestMapping(value = "/timeslots", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAvailableTimeSlots(@RequestParam("serviceId") String serviceId,
			@RequestParam(value = "startDay", required = false) String startDay,
			@RequestParam(value = "endDay", required = false) String endDay) {
		logger.info("In getAvailableTimeSlots() for service center - " + serviceId);
		return aps.getAvailableTimeSlots(serviceId, startDay, endDay);
	}

	@RequestMapping(value = "/barcode", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody byte[] getBarcode(@RequestParam("appRefId") String appRefId) {
		logger.info("In getBarcode() for reference id - " + appRefId);
		return aps.createBarCode(appRefId);

	}

	@RequestMapping(value = "/visafee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getVisaFeeDetails(@RequestParam("visaType") String visaType,
			@RequestParam("countryId") String countryId,
			@RequestParam(value = "noOfVisits", required = false) String noOfVisits) {
		logger.info("In getVisaFeeDetails() for Visa Type - " + visaType + " and for no of visits - " + noOfVisits);
		return aps.getVisaFeeDetails(visaType, noOfVisits, countryId);
	}
	
	@RequestMapping(value = "/track/byenum", method = RequestMethod.GET)
	public String getTrackDetailsByENum(@RequestParam("eNum") String eNum) {
		logger.info("in getBatchIds() to get batch id's for service id - "+eNum);
		return aps.getTrackDetailsByENum(eNum);
	}
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateAppointment(@RequestParam("appId") String appId, @RequestParam("payrefid") String payrefid) {
		logger.info("In updateAppointment() to update payrefid");
		return aps.updateAppointment(appId, payrefid);
	}
	@RequestMapping(value = "/reschedule", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String rescheduleAppointment(@RequestParam("appId") String appId,@RequestBody String input ) {
		logger.info("In rescheduleAppointment() to reschedule an appointment");
		return aps.rescheduleAppointment(appId,input);
	}
	@RequestMapping(value = "/modify", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String modifyAppointment(@RequestParam("appId") String appId ,@RequestBody String input) throws ParseException {
		logger.info("In modifyAppointment() to modify an appointment");
		return aps.modifyAppointment(appId, input);
	}
	@RequestMapping(value = "/cancel", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public String cancelAppointment(@RequestParam("appId") String appId,@RequestParam("appStatus") String appStatus) {
		logger.info("In cancelAppointment() to cancel an appointment");
		return aps.cancelAppointment(appId, appStatus);
	}
	@RequestMapping(value = "/getAppDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAppointmentDetails(@RequestParam("appId") String appId) {
		return aps.getAppointmentDetails(appId);
	}
}
