package Soap;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import Basicx.Payload;
public class Soap_basic1 {
	 public static void main(String[] args) throws IOException {
	
	String url="https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx?WSDL";
	
	 URL obj = new URL(url);
	 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
	 con.setRequestMethod("POST");
	 con.setRequestProperty("Content-Type","text/xml");
	
	 
	 
	 String xml = Payload.Add();
			 
			 
			 
			 /*"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\r\n" + 
	 		"   <soapenv:Header/>\r\n" + 
	 		"   <soapenv:Body>\r\n" + 
	 		"      <tem:Add>\r\n" + 
	 		"         <tem:a>15</tem:a>\r\n" + 
	 		"         <tem:b>5</tem:b>\r\n" + 
	 		"      </tem:Add>\r\n" + 
	 		"   </soapenv:Body>\r\n" + 
	 		"</soapenv:Envelope>";*/
	 
	// System.out.print("request: "+xml);
	 con.setDoOutput(true);
	 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	 wr.writeBytes(xml);
	 wr.flush();
	 
	 int responseCode = con.getResponseCode();
	 System.out.println(responseCode);
	 
	 BufferedReader in = new BufferedReader(new InputStreamReader(
			 con.getInputStream()));
			 String inputLine;
			 StringBuffer response = new StringBuffer();
			 while ((inputLine = in.readLine()) != null) {
			 response.append(inputLine);
			 }
			 in.close();
			 System.out.println("response:" + response.toString());
}
}