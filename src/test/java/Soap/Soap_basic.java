package Soap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.Test;

public class Soap_basic {

	
	@Test
	public  void TestRun() throws Throwable , ClientProtocolException, IOException {
		
		 File requestfile=new File((System.getProperty("user.dir")+"\\src\\test\\resource\\request.xml"));
		  // File requestfile=new File("C:\\Users\\rockstr\\eclipse-workspace\\exeeell\\src\\test\\resource\\Request_XML\\Request.xml");
			HttpClient client=HttpClientBuilder.create().build();
			
			//HttpPost post=new HttpPost("http://216.10.245.166:8080/axis2/services/EmployeeManagementService.EmployeeManagementServiceHttpSoap12Endpoint/");
			HttpPost post=new HttpPost("https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx?WSDL");
			
			
			post.setEntity(new InputStreamEntity(new FileInputStream(requestfile)));

			post.setHeader("Content-Type","text/xml");
			
			
			Header[] allHeaders = post.getAllHeaders();
		
			for (Header header : allHeaders) {
				
				System.out.println(header.getName()  +"  == "+header.getValue());
			}
			
			
			HttpResponse reponse=client.execute(post);
			
			
			
			BufferedReader br=new BufferedReader(new InputStreamReader(reponse.getEntity().getContent()));
			String line=" ";
			
			StringBuffer sb=new StringBuffer();
			System.out.println(reponse.getStatusLine().getStatusCode());
			while((line=br.readLine())!=null)
			{
				sb.append( line);
			}
			PrintWriter pw=new PrintWriter(System.getProperty("user.dir")+"\\src\\test\\resource\\Reponse.xml");
			
		
			pw.write(sb.toString());
			pw.close();
			pw.flush();
			
			System.out.println("done");
		
	}}
