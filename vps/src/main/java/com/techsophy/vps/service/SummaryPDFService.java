package com.techsophy.vps.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.techsophy.vps.utils.SummaryPDFGenerator;

@Service
public class SummaryPDFService {
	private final Logger logger = LoggerFactory.getLogger(SummaryPDFService.class);
	
	public ResponseEntity<byte[]> summaryPDF(String refId,JSONObject result) {
		logger.info("In summaryPDF()");
		SummaryPDFGenerator sPDFgen = new SummaryPDFGenerator();
		try {
			ByteArrayOutputStream out = sPDFgen.generatePDF(refId,result);
			byte[] contents = out.toByteArray();

		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.parseMediaType("application/pdf"));
		    // Here you have to set the actual filename of your pdf
		    String filename = "summary-"+refId+".pdf";
		    headers.setContentDispositionFormData(filename, filename);
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		    ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		    return response;
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}	
