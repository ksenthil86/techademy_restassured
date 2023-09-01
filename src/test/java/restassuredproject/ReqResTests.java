package restassuredproject;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqResTests {
	
	@Test
	public void post_test() {
		
		//Input Request JSON body
		String jsonBody = "{\r\n"
				+ "    \"name\": \"API Demo\",\r\n"
				+ "    \"job\": \"API Tester\"\r\n"
				+ "}";
		//RestAssured POST Request
		Response response = RestAssured.given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.and()
				.body(jsonBody)
				.post("https://reqres.in/api/users")
				.andReturn();
		//Validate HTTP Status Code
		Assert.assertEquals(response.statusCode(),201,"Passed - Status Code is as expected");
		//Validate Response fields
		Assert.assertEquals(response.jsonPath().get("name").toString(),"API Demo","Passed - Name value is as expected");
		Assert.assertEquals(response.jsonPath().get("job").toString(),"API Tester","Passed - Job value is as expected");
				
	}
	

}
																																								