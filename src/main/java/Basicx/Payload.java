package Basicx;

public class Payload {

	
	public static  String AddPlaces()
	{
		
		
		return  "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"";
		
		
		
	}
	
	public static  String Add()
	{
		
return	
	"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\r\n" + 
	"   <soapenv:Header/>\r\n" + 
	"   <soapenv:Body>\r\n" + 
	"      <tem:Add>\r\n" + 
	"         <tem:a>15</tem:a>\r\n" + 
	"         <tem:b>5</tem:b>\r\n" + 
	"      </tem:Add>\r\n" + 
	"   </soapenv:Body>\r\n" + 
	"</soapenv:Envelope>";
}
}