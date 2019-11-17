package com.techsophy.vps.utils;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
public class TableBuilder {
	public static final URL IMG1 = TableBuilder.class.getResource("/logo.png");
	
	public static PdfPTable createHeaderTable(String refId, PdfWriter writer) throws DocumentException {
		 
        // create 6 column table
        PdfPTable table = new PdfPTable(3);
 
        // set the width of the table to 100% of page
        table.setWidthPercentage(100);
 
        // set relative columns width
        table.setWidths(new float[]{4f,4f,4f});
 
        try {
        	PdfPCell imgCell = createImageCell(IMG1);
        	imgCell.setBorder(Rectangle.NO_BORDER);
        	//imgCell.setPadding(0.5f);
        	imgCell.setPadding(10);
			table.addCell(imgCell);
			
			PdfPCell headerCell = createLabelCell("Reference ID : "+refId.toUpperCase());
			headerCell.setBorder(Rectangle.NO_BORDER);
			headerCell.setPadding(10);
			headerCell.setBackgroundColor(BaseColor.WHITE);
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(headerCell);
			
			PdfContentByte cb = writer.getDirectContent();
			Barcode128 code128 = new Barcode128();
	        //code128.setCode("E737C515");
			code128.setCode(refId.toUpperCase());
	        code128.setCodeType(Barcode128.CODE128);
	        Image code128Image = code128.createImageWithBarcode(cb, null, null);
	        PdfPCell cell = new PdfPCell(code128Image, true);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setPadding(10);
	       // cell.setPadding(0.5f);
	        table.addCell(cell);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        return table;
    }
	
	// create table
    public static PdfPTable createAppointmentInfoTable(JSONArray appointmentInfo) throws DocumentException {
 
        // create 6 column table
        PdfPTable table = new PdfPTable(6);
 
        // set the width of the table to 100% of page
        table.setWidthPercentage(100);
 
        // set relative columns width
        table.setWidths(new float[]{2f, 1.4f, 2f,1.4f,2f,1.4f});
 
        // ----------------Table Header "Title"----------------
        // font
        Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        // create header cell
        PdfPCell cell = new PdfPCell(new Phrase("Appointment Information",font));
        // set Column span "1 cell = 6 cells width"
        cell.setColspan(6);
        // set style
        Style.headerCellStyle(cell);
        // add to table
        table.addCell(cell);
        
        JSONObject appointmentInfoObj = null ;
        try {
			appointmentInfoObj = (JSONObject) appointmentInfo.get(0);
			
			//-----------------Table Cells Label/Value------------------
			// 1st Row
			table.addCell(createLabelCell("Saudi Visa Center"));
			table.addCell(createValueCell((String) appointmentInfoObj.get("serviceCenter")));
			table.addCell(createLabelCell("Appointment Date"));
	        table.addCell(createValueCell((String) appointmentInfoObj.get("applicationDate")));
	        table.addCell(createLabelCell("Appointment Time"));
	        table.addCell(createValueCell((String) appointmentInfoObj.get("applicationTime")));
	 
	        // 2nd Row
	        table.addCell(createLabelCell("Primary Applicant Name"));
	        table.addCell(createValueCell((String) appointmentInfoObj.get("applicantName")));
	        table.addCell(createLabelCell("Appointment Category"));
	        table.addCell(createValueCell((String) appointmentInfoObj.get("applicationType")));
	        table.addCell(createLabelCell("Total Visa Fees"));
	        table.addCell(createValueCell((String) appointmentInfoObj.get("total_visa_fee")));
	        /*table.addCell(createLabelCell(""));
	        table.addCell(createValueCell(""));*/
	        
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return table;
    }
    
    
    public static PdfPTable createSponsorDetailsTable(JSONArray sponsorInfo) throws DocumentException {
    	 
        // create 6 column table
        PdfPTable table = new PdfPTable(4);
 
        // set the width of the table to 100% of page
        table.setWidthPercentage(100);
 
        // set relative columns width
        table.setWidths(new float[]{2f, 1.4f, 2f,0.8f});
 
        // ----------------Table Header "Title"----------------
        // font
        Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        // create header cell
        PdfPCell cell = new PdfPCell(new Phrase("Sponsor Details",font));
        // set Column span "1 cell = 6 cells width"
        cell.setColspan(4);
        // set style
        Style.headerCellStyle(cell);
        // add to table
        table.addCell(cell);
        
        
        JSONObject sponsorInfoObj = null ;
        try {
        	sponsorInfoObj = (JSONObject) sponsorInfo.get(0);
        	
        	//-----------------Table Cells Label/Value------------------
            // 1st Row
            table.addCell(createLabelCell("Sponsor ID"));
            table.addCell(createValueCell((String) sponsorInfoObj.get("sponsorId")));
			if(((String) sponsorInfoObj.get("invPreFlag")).equalsIgnoreCase("I")) {
	        	table.addCell(createLabelCell("Invitation Number"));
	        }
	        else if(((String) sponsorInfoObj.get("invPreFlag")).equalsIgnoreCase("P")) {
	        	table.addCell(createLabelCell("Pre-Approval Number"));
	        }        
	        table.addCell(createValueCell((String) sponsorInfoObj.get("invitationNo")));
	        
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        return table;
    }
    
    public static PdfPTable createPassportInfoTable(JSONArray passportInfo) throws DocumentException {
    	 
        // create 6 column table
        PdfPTable table = new PdfPTable(6);
 
        // set the width of the table to 100% of page
        table.setWidthPercentage(100);
 
        // set relative columns width
        table.setWidths(new float[]{2f, 1.4f, 2f,1.4f,2f,1.4f});
 
        // ----------------Table Header "Title"----------------
        // font
        Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        // create header cell
        PdfPCell cell = new PdfPCell(new Phrase("Passport Information",font));
        // set Column span "1 cell = 6 cells width"
        cell.setColspan(6);
        // set style
        Style.headerCellStyle(cell);
        // add to table
        table.addCell(cell);
        
        JSONObject passportInfoObj = null ;
        try {
        	passportInfoObj = (JSONObject) passportInfo.get(0);
        	
        	//-----------------Table Cells Label/Value------------------
        	 
            // 1st Row
            table.addCell(createLabelCell("Passport Number"));
            table.addCell(createValueCell((String) passportInfoObj.get("passportNo")));
            table.addCell(createLabelCell("Passport Type"));
            table.addCell(createValueCell((String) passportInfoObj.get("passportType")));
            table.addCell(createLabelCell("Date Of Issue"));
            table.addCell(createValueCell((String) passportInfoObj.get("ppDOI")));
     
            // 2nd Row
            table.addCell(createLabelCell("Place Of Issue"));
            table.addCell(createValueCell((String) passportInfoObj.get("ppPOI")));
            table.addCell(createLabelCell("Date of Expiry"));
            table.addCell(createValueCell((String) passportInfoObj.get("ppDOE")));
            table.addCell(createLabelCell(""));
            table.addCell(createValueCell(""));
        	
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        return table;
    }
    
    
    public static PdfPTable createPersonalInfoTable(JSONArray personalInfo) throws DocumentException {
   	 
        // create 6 column table
        PdfPTable table = new PdfPTable(6);
 
        // set the width of the table to 100% of page
        table.setWidthPercentage(100);
 
        // set relative columns width
        table.setWidths(new float[]{2f, 1.4f, 2f,1.4f,2f,1.4f});
 
        // ----------------Table Header "Title"----------------
        // font
        Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        // create header cell
        PdfPCell cell = new PdfPCell(new Phrase("Personal Information",font));
        // set Column span "1 cell = 6 cells width"
        cell.setColspan(6);
        // set style
        Style.headerCellStyle(cell);
        // add to table
        table.addCell(cell);
        
        JSONObject personalInfoObj = null ;
        try {
        	personalInfoObj = (JSONObject) personalInfo.get(0);
        	
        	//-----------------Table Cells Label/Value------------------
        	 
            // 1st Row
            table.addCell(createLabelCell("First Name"));
            table.addCell(createValueCell((String) personalInfoObj.get("firstName")));
            table.addCell(createLabelCell("Last Name"));
            table.addCell(createValueCell((String) personalInfoObj.get("lastName")));
            table.addCell(createLabelCell("Father's Name"));
            table.addCell(createValueCell((String) personalInfoObj.get("fatherName")));
     
            // 2nd Row
            table.addCell(createLabelCell("Gender"));
            table.addCell(createValueCell((String) personalInfoObj.get("gender")));
            table.addCell(createLabelCell("Marital Status"));
            table.addCell(createValueCell((String) personalInfoObj.get("maritalStatus")));
            table.addCell(createLabelCell("Religion"));
            table.addCell(createValueCell((String) personalInfoObj.get("religion")));
            
            // 3rd Row
            table.addCell(createLabelCell("Qualification"));
            table.addCell(createValueCell((String) personalInfoObj.get("qualification")));
            table.addCell(createLabelCell("Occupation"));
            table.addCell(createValueCell((String) personalInfoObj.get("occupation")));
            table.addCell(createLabelCell("Email"));
            table.addCell(createValueCell((String) personalInfoObj.get("email")));
            
        } catch(Exception e) {
        	e.printStackTrace();
        }
 
        
 
        return table;
    }
    
    public static PdfPTable createAddressInfoTable(JSONArray addressInfo) throws DocumentException {
   	 
        // create 6 column table
        PdfPTable table = new PdfPTable(4);
 
        // set the width of the table to 100% of page
        table.setWidthPercentage(100);
 
        // set relative columns width
        table.setWidths(new float[]{2f, 1.4f, 2f,0.8f});
 
        // ----------------Table Header "Title"----------------
        // font
        Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        // create header cell
        PdfPCell cell = new PdfPCell(new Phrase("Address Information",font));
        // set Column span "1 cell = 6 cells width"
        cell.setColspan(4);
        // set style
        Style.headerCellStyle(cell);
        // add to table
        table.addCell(cell);
        
        JSONObject addressInfoObj = null ;
        try {
        	addressInfoObj = (JSONObject) addressInfo.get(0);
        	
        	//-----------------Table Cells Label/Value------------------
        	 
            // 1st Row
            table.addCell(createLabelCell("House No"));
            table.addCell(createValueCell((String) addressInfoObj.get("houseNo")));
            table.addCell(createLabelCell("Building"));
            table.addCell(createValueCell((String) addressInfoObj.get("building")));
            table.addCell(createLabelCell("Area"));
            table.addCell(createValueCell((String) addressInfoObj.get("area")));
     
            // 2nd Row
            table.addCell(createLabelCell("City"));
            table.addCell(createValueCell((String) addressInfoObj.get("city")));
            table.addCell(createLabelCell("Mobile"));
            table.addCell(createValueCell((String) addressInfoObj.get("mobile_no")));
            table.addCell(createLabelCell(""));
            table.addCell(createValueCell(""));
            
        } catch(Exception e) {
        	e.printStackTrace();
        }
 
        return table;
    }
    
    // create cells
    private static PdfPCell createLabelCell(String text){
        // font
        Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY);
 
        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text,font));
 
        // set style
        Style.labelCellStyle(cell);
        return cell;
    }
 
    // create cells
    private static PdfPCell createValueCell(String text){
        // font
        Font font = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
 
        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text,font));
 
        // set style
        Style.valueCellStyle(cell);
        return cell;
    }
    
    public static PdfPCell createImageCell(URL imgUrl) throws DocumentException, IOException {
        //Image img = Image.getInstance(path);
    	Image img = Image.getInstance(imgUrl);
        //img.setIndentationLeft(20);
        //img.setIndentationRight(20);
        PdfPCell cell = new PdfPCell(img, true);
        return cell;
    }
}