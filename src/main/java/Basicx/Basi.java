package Basicx;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basi {

	public static void main(String[] args) {
		
	
	
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String reponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
	body(Payload.AddPlaces()).when().post("/maps/api/place/add/json").then().
	assertThat().statusCode(200).body("scope",equalTo("APP")).
	header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();
	

	System.out.println("ok"+reponse);
	JsonPath js=new JsonPath(reponse);
	
	
}
}

