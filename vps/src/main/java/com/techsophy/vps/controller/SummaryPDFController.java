package com.techsophy.vps.controller;

import javax.mail.MessagingException;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techsophy.vps.dao.AppointmentDAO;
import com.techsophy.vps.service.JavaEmailService;
import com.techsophy.vps.service.SummaryPDFService;

@RestController
@RequestMapping(value = "/summary")
public class SummaryPDFController {
private final Logger logger = LoggerFactory.getLogger(SummaryPDFController.class);
	
	@Autowired
	SummaryPDFService summaryPDFService;
	
	@Autowired
	AppointmentDAO apDao;
	
	@Autowired
	JavaEmailService mailService;
	
	@RequestMapping(value = "/getpdf", method = RequestMethod.GET)
	public ResponseEntity<byte[]> generateSummaryPDF(@RequestParam("refid") String refId,@RequestParam("serviceid") String serviceid) throws MessagingException {
		logger.info("in generateSummaryPDF() for reference id - " + refId+" and service id - "+serviceid);
		//JsonParser parser = new JsonParser();
		JSONObject result = apDao.getSummaryDetails(refId, serviceid);
		//getSummaryDetails
		
			ResponseEntity<byte[]> attachment = summaryPDFService.summaryPDF(refId,result);
			//send a mail
			try {
				mailService.attachInMail(attachment,result.getJSONArray("personalInfo").getJSONObject(0).get("email").toString());
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return attachment;
		// email attachment
	}
}
