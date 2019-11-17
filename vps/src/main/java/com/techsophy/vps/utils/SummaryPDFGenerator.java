package com.techsophy.vps.utils;
 
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
/**
 * This class is used to generate a summary pdf for Visa Applications submitted using Reference ID
 * @author Ankith
 *
 */
public class SummaryPDFGenerator 
{
	private final Logger logger = LoggerFactory.getLogger(SummaryPDFGenerator.class);
    public ByteArrayOutputStream generatePDF(String refId, JSONObject result)  throws FileNotFoundException, DocumentException
    {
    	logger.info("in generatePDF()");
    	// step 1
        Document document = new Document();
        document.setPageSize(PageSize.A4);
 
        // step 2
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //FileOutputStream outStream =new FileOutputStream("D:/Downloads/pdf.pdf");
        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
 
        // step 3
        document.open();
 
        // step 4 create PDF contents
        PdfPTable headerTable = TableBuilder.createHeaderTable(refId,writer);
        PdfPTable table1 = null;
        PdfPTable table2 = null;
        PdfPTable table3 = null;
        PdfPTable table4 = null;
        PdfPTable table5 = null;
        try {
        table1 = TableBuilder.createAppointmentInfoTable((JSONArray) result.get("appointmentInfo"));
        table2 = TableBuilder.createSponsorDetailsTable((JSONArray) result.get("sponsorInfo"));
        table3 = TableBuilder.createPassportInfoTable((JSONArray) result.get("passportInfo"));
        table4 = TableBuilder.createPersonalInfoTable((JSONArray) result.get("personalInfo"));
        table5 = TableBuilder.createAddressInfoTable((JSONArray) result.get("addressInfo"));
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
        table1.setSpacingBefore(10f);
        table1.setSpacingAfter(12.5f);
        table2.setSpacingBefore(10f);
        table2.setSpacingAfter(12.5f);
        table3.setSpacingBefore(10f);
        table3.setSpacingAfter(12.5f);
        table4.setSpacingBefore(10f);
        table4.setSpacingAfter(12.5f);
        table5.setSpacingBefore(10f);
        table5.setSpacingAfter(12.5f);
        
        document.add(headerTable);
        document.add(table1);
        document.add(table2);
        document.add(table3);
        document.add(table4);
        document.add(table5);
 
        //step 5
        document.close();
        logger.info("PDF Created!");
        return byteArrayOutputStream;
        
    }  
}