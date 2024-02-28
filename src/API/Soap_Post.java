package API;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class Soap_Post {

	public static void main(String[] args) {

		// Step 1 : Collect all  information 

		RestAssured.useRelaxedHTTPSValidation();

		String req_body = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + "  <soap:Body>\r\n"
				+ "    <NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <dNum>45</dNum>\r\n" + "    </NumberToDollars>\r\n" + "  </soap:Body>\r\n"
				+ "</soap:Envelope>\r\n" + "";

		String hostname = "https://www.dataaccess.com";

		String resource = "/webservicesserver/NumberConversion.wso";

		String headername = "Content-Type";

		String headervalue = "text/xml; charset=utf-8";

		// Step 2 : Declare BaseURI

		RestAssured.baseURI = hostname;

		// Step 3 : Configure the API to execute
		String res_body = given().header(headername, headervalue).body(req_body).when().post(resource).then().extract()
				.response().getBody().asString();

		System.out.println(res_body);

		// Step 4 : Parse the response body

		// Step 4.1 : Create the object of JsonPath

		XmlPath xml_res = new XmlPath(res_body);

		// Step 4.2 : Parse individual parameters using xml res object

		String result = xml_res.getString("NumberToDollarsResult");

		// Step 5 : Validate the response body

		Assert.assertEquals(result, "forty five dollars");

	}

}
