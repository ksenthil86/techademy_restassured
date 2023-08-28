package restassuredproject;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqResTests {
	
	@Test
	public void post_test() {
		
		String jsonBody = "{\r\n"
				+ "    \"name\": \"API Demo\",\r\n"
				+ "    \"job\": \"API Tester\"\r\n"
				+ "}";
		Response response = RestAssured.given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.and()
				.body(jsonBody)
				.post("https://reqres.in/api/users")
				.andReturn();
		Assert.assertEquals(response.statusCode(),201);
		Assert.assertEquals(response.jsonPath().get("name").toString(),"API Demo");
		Assert.assertEquals(response.jsonPath().get("job").toString(),"API Tester");
				
	}
	

}
																																								