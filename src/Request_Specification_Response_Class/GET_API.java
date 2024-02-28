package Request_Specification_Response_Class;


import io.restassured.RestAssured;

import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;

public class GET_API {

	public static void main(String[] args) {
		// Step 1 : Collect all needed info And save into local variable
		String hostname = "https://reqres.in";
		String resource = "/api/users?page=2";
		String headername = "Content-Type";
		String headervalue = "application/json";

		// Step : Build the Request Specification by the Request Specification Class
		RequestSpecification req_spec = RestAssured.given();

		// Step 2.1 : set the request header
		req_spec.header(headername, headervalue);
		
		
		// Step 2.2 : Trigger the API

		Response response = req_spec.get(hostname + resource);
		System.out.println(response.getBody().asString());
		
		//Step 3 : Parse the response body 
		

	
		
	}

}
