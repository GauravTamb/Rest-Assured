package Request_Specification_Response_Class;

import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class PATCH_API {

	public static void main(String[] args) {
		// Step 1 : Collect all needed info And save into local variable
		String req_body = "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"zion resident\"\r\n" + "}";
		String hostname = "https://reqres.in";
		String resource = "/api/users/2";
		String headername = "Content-Type";
		String headervalue = "application/json";

		// Step : Build the Request Specification by the Request Specification Class
		RequestSpecification req_spec = RestAssured.given();

		// Step 2.1 : set the request header
		req_spec.header(headername, headervalue);

		// Step 2.2 : set the request body
		req_spec.body(req_body);

		// Step 2.3 : Trigger the API

		Response response = req_spec.patch(hostname + resource);
		System.out.println(response.getBody().asString());

		// Step 3 : Parse the response body
		ResponseBody res_body = response.getBody();
		String res_name = res_body.jsonPath().getString("name");
		String res_job = res_body.jsonPath().getString("job");
		String res_updatedAt = res_body.jsonPath().getString("updatedAt").substring(0, 10);
		System.out.println(res_name);
		System.out.println(res_job);
		System.out.println(res_updatedAt);

		// Step 4: parse the request body and save into local variable
		JsonPath jsp_req = new JsonPath(req_body);
		String req_name = jsp_req.getString("name");
		String req_job = jsp_req.getString("job");
		
		// Step 4.1 : Generate expected date

		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 10);

		// Step 5 : Validate the responsebody And requestbody by Using TestNG's Assert

		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_updatedAt, expecteddate);

	}

}
