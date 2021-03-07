package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.ValidatableResponse;

public class RestAssured_GET_POST {
	public static void main(String args[]) {
		
		String body = given().get("https://jsonplaceholder.typicode.com/posts/1")
				.then()
				.log()
				.body().toString();
		System.out.println(body);
		
		Assert.assertEquals(body.contains("userId"), false);
		
		JsonPath jsonBody = given().get("https://jsonplaceholder.typicode.com/posts")
				.jsonPath();
		 int Id = jsonBody.get("[3].id");
		 System.out.println("Id " + Id);
		 
			
			String endPath = "http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";
			ValidatableResponse response = given().get(endPath)
					.then()
					.log()
					.body();
			System.out.println(response);
			
			XmlPath xmlBody = given().get(endPath)
					.xmlPath();
			 int amount = xmlBody.get("result.statements[0].AMOUNT");
			 System.out.println("Amount " + amount);
	
//		System.out.println("Headers:\n"+given().when().get(endPath).then().extract().headers()+"\n");
//		System.out.println("contentType:"+given().when().get(endPath).then().extract().contentType()+"\n");
//		System.out.println("statusCode: "+given().when().get(endPath).then().extract().statusCode()+"\n");
//		System.out.println("statusLine: "+given().when().get(endPath).then().extract().statusLine()+"\n");
//		System.out.println("Response time in ms: "+given().when().get(endPath).timeIn(TimeUnit.MILLISECONDS)+"\n");
		
		//System.out.println("Header->Server:\n"+given().when().get("").then().extract().header("Server")+"\n");
		
		given().queryParam("CUSTOMER_ID", "68195")
				.queryParam("PASSWORD", "1234!")
				.queryParam("Account_No", "1")
				.when().get("http://demo.guru99.com/V4/sinkministatement.php")
				.then()
				.assertThat()
				.statusCode(200);
		
		int statucCode  = given().queryParam("CUSTOMER_ID", "68195")
		.queryParam("PASSWORD", "1234!")
		.queryParam("Account_No", "1")
		.when().get("http://demo.guru99.com/V4/sinkministatement.php")
		.getStatusCode();
		
		System.out.println("The response status Code is: "+statucCode);

	}
}
