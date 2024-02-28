package Request_Specification_Response_Class;

import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.ResponseBody;

public class POST_API {

	public static void main(String[] args) {
		// Collect All Needed inforamtion & Save Into Local Variable
		String req_body = "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"leader\"\r\n" + "}";
		String hostname = "https://reqres.in";
		String resourse = "/api/users";
		String headername = "Content-Type";
		String headervalue = "application/json";

		// Step 2 : Build the request Specification by the Request Specification Class

		RequestSpecification req_spec = RestAssured.given();

		// Step 2.1 : Set request header
		req_spec.header(headername, headervalue);

		// Step 2.2 : Set The request body
		req_spec.body(req_body);

		// Step2.3 Trigger the API request
		Response response = req_spec.post(hostname + resourse);
		System.out.println(response.getBody().asString());

		// Step 3: Parse the responsebody
		ResponseBody res_body = response.getBody();
		String res_name = res_body.jsonPath().getString("name");
		String res_job = res_body.jsonPath().getString("job");
		String res_id = res_body.jsonPath().getString("id");
		String res_createdAt = res_body.jsonPath().getString("createdAt").substring(0, 10);
		System.out.println(res_name);
		System.out.println(res_job);
		System.out.println(res_id);
		System.out.println(res_createdAt);

		// Step 4 : Parse request body and save into local variables

		JsonPath jsp_req = new JsonPath(req_body);
		String req_name = jsp_req.getString("name");
		String req_job = jsp_req.getString("job");

		// Step 4.1 : Generate expected date

		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 10);
		
		
		// Step 5 : Validate the responsebody And requestbody by Using TestNG's Assert

		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_createdAt, expecteddate);

	}

}
