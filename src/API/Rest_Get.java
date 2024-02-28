package API;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;

public class Rest_Get {

	public static void main(String[] args) {
		// Step1 : Collect all information and save in local variable
		String hostname = "https://reqres.in/";
		String resource = "/api/users/2";
		String headername = "Content-Type";
		String headervalue = "application/json";

		// Step 2 : Declare BaseURI
		RestAssured.baseURI = hostname;

		// Step 3 : Configure the API to execute
		String res_body = given().header(headername, headervalue).when().get(resource).then().extract().response()
				.asString();
		System.out.println(res_body);

		// Step 4 : Parse/Convert the response body into String

		// Step 4.1 : Create the Object of JsonPath
		JsonPath jsp_res = new JsonPath(res_body);
		String data = jsp_res.getString("data");
		System.out.println(data);

		String res_id = jsp_res.getString("id");
		String res_email = jsp_res.getString("email");
		String res_first_name = jsp_res.getString("first_name");
		String res_last_name = jsp_res.getString("last_name");
		System.out.println(res_id);
		System.out.println(res_email);
		System.out.println(res_first_name);
		System.out.println(res_last_name);

	}

}
