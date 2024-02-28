package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
public class Rest_Post_LoginUnSuccessful {

	public static void main(String[] args) {
		String req_body = "{\r\n" + "    \"email\": \"peter@klaven\"\r\n" + "}";
		String hostname = "https://reqres.in/";
		String resourse = "api/login";
		String headername = "Content-Type";
		String headervalue = "application/json";
		
		// Step2 : Declare BaseURI
		RestAssured.baseURI=hostname;
		
		// Step 3 : configure the API to Execute 
		 String res_body = given().header(headername,headervalue).body(req_body).when().post(resourse).then().extract().response().asString();
		 
		 //Step 4 : Parse/Convert the responsebody into String
		 
		 //Step 4.1 : Create the object of JsonPath & Store  value in string
		 
		 JsonPath jsp_res = new JsonPath(res_body);
		 String res_error = jsp_res.getString("error");
		 System.out.println(res_error);
		 
		 //Step 5 : Parse the requestbody into String
		 
		 //Step 5.1: Create the object of JsonPath & Store value in String
		 
		 JsonPath jsp_req = new JsonPath(req_body);
		 String req_email = jsp_req.getString("email");
		 System.out.println(req_email);
		 
		 // Step 6 : Validate requestbody & responsebody using TestNG Assert
		 
		 Assert.assertEquals(res_error, "Missing password"); // in respose body we get only error massage that's why we validate only error massage
		 
		 
		 
		

	}

}
