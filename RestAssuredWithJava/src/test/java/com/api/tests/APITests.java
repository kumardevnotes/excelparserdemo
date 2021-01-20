package com.api.tests;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITests {
	public static String successMessage  = "Request verified without any issues";
	public static String errorMessage  = "Request verification failed";
	
	@Test
	public void demoTest() {
		RestAssured.baseURI = "https://reqres.in/api/users/";
		RequestSpecification httpReq  = RestAssured.given();
		Response response  = httpReq.request(Method.GET, "2");
		int statusCode = response.getStatusCode();
		Assert.assertEquals("Failed to verify the API", 200, statusCode);
		System.out.println(successMessage);
	}

}
