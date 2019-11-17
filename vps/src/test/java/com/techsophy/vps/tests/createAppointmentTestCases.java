/*package com.techsophy.vps.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ResourceBundle;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.techsophy.vps.tests.RandomUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class createAppointmentTestCases {
	ResourceBundle rb = null;
	String dummyAppRefID = null;
	RandomUtil randomUtil = null;
	String dummySCID = null;
	String scUserName = null;
	String password = null;
	String scID = null;


	@Before
	public void setUp() throws JSONException {
		randomUtil = new RandomUtil();
		rb = ResourceBundle.getBundle("application");
		RestAssured.baseURI = rb.getString("restassured.baseURI");
		dummyAppRefID = rb.getString("test.dummy.apprefid");
		dummySCID = rb.getString("test.dummy.scid");
		scUserName = rb.getString("test.scusername");
		password = rb.getString("test.password");
		scID = rb.getString("test.scid");

	}

	 test case for createAppointment 
	@Test
	public void createAppointmentTests() throws JSONException {
		RequestSpecification httpRequest = RestAssured.given().body(
				"{\"visaType\":\"Business Visa\",\"totalVisaFee\":\"56\",\"scId\":\""+scID+"\",\"appDate\":\"2018-08-04\",\"slotNo\":1,\"address1\":\"3-142\",\"address2\":\"kalajyothi\", \"address3\":\"gachibowli\",\"addressCity\":\"hyderabad\", \"addressCountry\":\"india\",\"appType\":\"Lounge\",\"applEmail\":\""+scUserName+"\",\"birthPlace\":\"hyderabad\", \"dateExpiry\":\"2021-09-22\", \"dateIssued\":\"2018-08-30\", \"dob\":\"1991-12-02\",\"email\":\"dummy@gmail.com\",\"firstName\":\"Elina\",\"gender\":\"Female\",\"invitationNo\":\"12345\",\"inviteOrPreapproved\":\"I\",\"lastName\":\"\", \"maritalSatus\":\"Married\",\"mobile\":\"6789067890\", \"modeOfEntry\":\"By Air\",\"nationality\":\"IN\", \"occupation\":\"software developer\", \"passportNo\":\"A12 34567\",\"passportType\":\"Regular\",\"paymentType\":\"cash\",\"placeOfIssue\":\"hyderabad\", \"portOfEntry\":\"Dammam\", \"qualification\":\"B.tech\", \"religion\":\"Christianity\",\"sponsorId\":\"6789\",\"typeOfEntry\":\"Single Entry\",\"fatherName\":\"John\"}")
				.auth().basic(scUserName,password);
		Response response = httpRequest.put("/create");
		System.out.println(response.asString());
		JSONObject appRefJson = new JSONObject(response.asString());
		int statusCode = response.getStatusCode();
		assertThat(200, is(equalTo(statusCode)));
		System.out.println(appRefJson.getString("app_ref_id"));
		if (appRefJson.getString("app_ref_id").contains("null")) {
			assertEquals(appRefJson.getString("app_ref_id").contains("null"), true);
		} else {
			assertEquals(appRefJson.getString("app_ref_id").contains("{}"), true);
		}
	}

}
*/