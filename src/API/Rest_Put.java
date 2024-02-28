package API;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Rest_Put {

	public static void main(String[] args) {
		// Step 1 : Collect all Info. and save it in local variable.
				String Req_body = "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"zion resident\"\r\n" + "}";
				String hostname = "https://reqres.in/";
				String Resource = "/api/users/2";
				String headername = "Content-Type";
				String headervalue = "application/json";

				// Step 2 : Declare BaseURI
				RestAssured.baseURI = hostname;

				// Step 3 : Configure the API to execute
				String res_body = given().header(headername, headervalue).body(Req_body).when().put(Resource).then().extract()
						.response().asString();
				System.out.println(res_body);

				// Step 4 : Parse/Convert the responsebody into String

				// Step 4.1 : Create the object of JsonPath
				JsonPath jsp_res = new JsonPath(res_body);
				String res_name = jsp_res.getString("name");
				String res_job = jsp_res.getString("job");
				String res_updatedAt = jsp_res.getString("updatedAt").substring(0, 10);
				System.out.println(res_name);
				System.out.println(res_job);
				System.out.println(res_updatedAt);

				// Step 5 : Parse/Convert the requestbody into String

				// Step 5.1 : Create object of JsonPath And Store all Value in String
				JsonPath jsp_req = new JsonPath(Req_body);
				String req_name = jsp_req.getString("name");
				String req_job = jsp_req.getString("job");
				System.out.println(req_name);
				System.out.println(req_job);

				// Step 6 : Validate request body and responsebody using TestNG Asserts.

				Assert.assertEquals(res_name, req_name);
				Assert.assertEquals(res_job, req_job);

	}

}
