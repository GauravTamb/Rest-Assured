package API;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Rest_Post_RegisterSuccessful {

	public static void main(String[] args) {
		// Step1 : Collect the all Information And Save in it local Variable
		String req_body = "{\r\n" + "    \"email\": \"eve.holt@reqres.in\",\r\n" + "    \"password\": \"pistol\"\r\n"
				+ "}";
		String hostname = "https://reqres.in/";
		String resource = "/api/register";
		String headername = "Content-Type";
		String headervalue = "application/json";

		// Step 2:- Declare the BaseURI
		RestAssured.baseURI = hostname;

		// Step 3 : configure the API To execute
		String res_body = given().header(headername, headervalue).body(req_body).when().post(resource).then().extract()
				.response().asString();
		System.out.println(res_body);

		// Step4 : Parse/Convert the responsebody into String

		// Step4.1 : Create the object of JsonPath
		JsonPath jsp_res = new JsonPath(res_body);
		String res_id = jsp_res.getString("id");
		String res_token = jsp_res.getString("token");
		System.out.println(res_id);
		System.out.println(res_token);

		// Step 5 : Parse/Convert the requestbody into String

		// Step5.1 : Create the object of JsonPath
		JsonPath jsp_req = new JsonPath(req_body);
		String req_email = jsp_req.getString("email");
		String req_password = jsp_req.getString("password");
		System.out.println(req_email);
		System.out.println(req_password);

		// Step6 : validate the requestbody And responsebody using TestNG Asserts
		// In response we get id&token so will validate only those parameter.
		Assert.assertNotEquals(res_id, 0); // id shouldnot be 0
		Assert.assertNotNull(res_id); // id shouldnot be NULL
		Assert.assertEquals(res_token, "QpwL5tke4Pnpja7X4"); // token value Always constant

	}

}
