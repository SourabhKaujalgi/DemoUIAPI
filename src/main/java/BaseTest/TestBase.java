package BaseTest;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.PrintStream;

import Resources.TestDataBuild;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class TestBase extends UiBase {
	//Added Comment to check pull 

	protected TestDataBuild td = new TestDataBuild();
	protected static RequestSpecification req;
	protected static RequestSpecification tokenreq;

	public static RequestSpecification requestspecification() throws FileNotFoundException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("Log.txt"));
			RestAssured.useRelaxedHTTPSValidation();
			req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).build();
		}
		return req;
	}

	public static RequestSpecification userspecification() throws FileNotFoundException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("Log.txt"));
			RestAssured.useRelaxedHTTPSValidation();
			tokenreq = new RequestSpecBuilder().setBaseUri("https://reqres.in")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))

					.build();
		}
		return tokenreq;
	}

	public static String getJsonPath(String response, String key) {
		JsonPath js = new JsonPath(response);
		return js.get(key);
	}

}
