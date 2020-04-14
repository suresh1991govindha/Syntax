package Soap;

public class Soap_basic3 {


	   String url = "http://www.webservicex.net"; 
	        HttpClient client = new DefaultHttpClient();
	        HttpPost post = new HttpPost(url);

	        post.setHeader("Host", "www.webservicex.net");
	        post.setHeader("Content-Type", "text/xml;charset=utf-8");
	        post.setHeader("SOAPAction", "http://www.webservicex.net/GetGeoIP");

	        String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
	                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
	                "  <soap:Body>\r\n" + 
	                "    <GetGeoIP xmlns=\"http://www.webservicex.net/\">\r\n" + 
	                "      <IPAddress>50.207.31.216</IPAddress>\r\n" + 
	                "    </GetGeoIP>\r\n" + 
	                "  </soap:Body>\r\n" + 
	                "</soap:Envelope>";

	        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	        urlParameters.add(new BasicNameValuePair("xml", xmlString));

	        post.setEntity(new UrlEncodedFormEntity(urlParameters));
	        HttpResponse response;
	        try {
	            response = client.execute(post);
	            System.out.println("Response Code : " + 
	                                            response.getStatusLine().getStatusCode());

	            BufferedReader rd = new BufferedReader(
	                                new InputStreamReader(response.getEntity().getContent()));

	            StringBuffer result = new StringBuffer();
	            String line = "";
	            while ((line = rd.readLine()) != null) {
	                result.append(line);
	            }
	            System.out.println(result.toString());

	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
}
