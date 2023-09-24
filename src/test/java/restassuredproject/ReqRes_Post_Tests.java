package restassuredproject;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqRes_Post_Tests {
	
	@Test
	/*
	 * Test Case - Login Test. Validate Response Code 200 and Token Generated
	 * Type - Positive 
	 * 
	 */
	public void TC01_positive_test() {
		
		//Input Request JSON body
		String jsonBody = "{\r\n"
				+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
				+ "    \"password\": \"cityslicka\"\r\n"
				+ "}";
		//RestAssured POST Request
		Response response = RestAssured.given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.and()
				.body(jsonBody)
				.post("https://reqres.in/api/login")
				.andReturn();
		//Validate HTTP Status Code
		Assert.assertEquals(response.statusCode(),200,"Passed - Status Code is as expected");
		//Validate Response fields
		System.out.println("Token Generated - "+response.jsonPath().get("token").toString());				
	}
	
	@Test
	/*
	 * Test Case - Login Test with invalid email id. Validate Response Code 400 and Error value generated
	 * Type - Negative 
	 * 
	 */
	public void TC02_negative_invalidemail_test() {
		
		//Input Request JSON body
		String jsonBody = "{\r\n"
				+ "    \"email\": \"eve.holt@reqreswrong.in\",\r\n"
				+ "    \"password\": \"cityslicka\"\r\n"
				+ "}";
		//RestAssured POST Request
		Response response = RestAssured.given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.and()
				.body(jsonBody)
				.post("https://reqres.in/api/login")
				.andReturn();
		//Validate HTTP Status Code
		Assert.assertEquals(response.statusCode(),400,"Passed - Status Code is as expected");
		//Validate Response fields
		System.out.println("Error Generated - "+response.jsonPath().get("error").toString());
		Assert.assertEquals(response.jsonPath().get("error").toString(),"user not found","Passed - Error value is as expected");
		
	}
	
	@Test
	/*
	 * Test Case - Login Test with no password. Validate Response Code 400 and Error value generated
	 * Type - Negative 
	 * 
	 */
	public void TC03_negative_nopassword_test() {
		
		//Input Request JSON body
		String jsonBody = "{\r\n"
				+ "    \"email\": \"peter@klaven\"\r\n"
				+ "}";
		//RestAssured POST Request
		Response response = RestAssured.given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.and()
				.body(jsonBody)
				.post("https://reqres.in/api/login")
				.andReturn();
		//Validate HTTP Status Code
		Assert.assertEquals(response.statusCode(),400,"Passed - Status Code is as expected");
		//Validate Response fields
		System.out.println("Error Generated - "+response.jsonPath().get("error").toString());
		Assert.assertEquals(response.jsonPath().get("error").toString(),"Missing password","Passed - Error value is as expected");				
	}
	

}
																																								