package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

public class Rest_Post_Create {

	public static void main(String[] args) {
		// Step 1 :- collect All info. and save in local variables.
		String Req_body = "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"leader\"\r\n" + "}";
		String Hostname = "https://reqres.in";
		String Resource = "/api/users";
		String headername = "Content-Type";
		String headervalue = "application/json";

		// Step2 :- Declare base URI
		RestAssured.baseURI = Hostname;

		// Step3 :- Configure The API to execute.
		String res_body = given().header(headername, headervalue).body(Req_body).when().post(Resource).then().extract()
				.response().asString();
		System.out.println(res_body);

		// Step 4 : Parse the responsebody

		// Step 4.1 :create the object of json path

		JsonPath jsp_res = new JsonPath(res_body);
		String res_name = jsp_res.getString("name");
		String res_job = jsp_res.getString("job");
		int res_id = jsp_res.getInt("id");
		String res_createdAt = jsp_res.getString("createdAt").substring(0, 10);
		System.out.println(res_name);
		System.out.println(res_job);
		System.out.println(res_id);
		System.out.println(res_createdAt);

		// Step 5 : Parse the Request Body

		// Step 5.1 create object of JsonPath

		JsonPath jsp_req = new JsonPath(Req_body);
		String req_name = jsp_req.getString("name");
		String req_job = jsp_req.getString("job");
		System.out.println(req_name);
		System.out.println(req_job);

		// Step 6 validate API using TestNG assert

		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertNotNull(res_id);

	}

}
