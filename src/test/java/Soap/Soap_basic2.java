package Soap;


	import java.io.BufferedReader;
	import java.io.ByteArrayOutputStream;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.io.OutputStream;
	import java.net.HttpURLConnection;
	import java.net.InetSocketAddress;
	import java.net.Proxy;
	import java.net.URL;
	import java.net.URLConnection;

	public class Soap_basic2 {


	 
	public static void sendSoapRequest(String cisId) throws Exception {
	//use this if you need proxy to connect
	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("YOUR PROXY", PORT NUMBER));
	String SOAPUrl = "WISDL URL HERE";
	String xmlFile2Send = ".\src\request.xml";
	String responseFileName = ".\src\response.xml";

	// Create the connection with http
	URL url = new URL(SOAPUrl);
	URLConnection connection = url.openConnection(proxy);
	HttpURLConnection httpConn = (HttpURLConnection) connection;
	FileInputStream fin = new FileInputStream(xmlFile2Send);
	ByteArrayOutputStream bout = new ByteArrayOutputStream();

	copy(fin, bout);
	fin.close();

	byte[] b = bout.toByteArray();
	StringBuffer buf=new StringBuffer();
	String s=new String(b);

	//replacing a sample value in Request XML
	s=s.replaceAll("VALUE", value);
	b=s.getBytes();


	 
	// Set the appropriate HTTP parameters.
	httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
	httpConn.setRequestProperty("Content-Type”, “text/xml; charset=utf-8”);
	httpConn.setRequestProperty("SOAPAction","d");
	httpConn.setRequestMethod("POST");
	httpConn.setDoOutput(true);

	// send the XML that was read in to b.
	OutputStream out = httpConn.getOutputStream();
	out.write(b);
	out.close();

	// Read the response.
	httpConn.connect();
	System.out.println("http connection status :"+ httpConn.getResponseMessage());
	InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
	BufferedReader in = new BufferedReader(isr);


	 
	while ((inputLine = in.readLine()) != null)
	System.out.println(inputLine);
	FileOutputStream fos=new FileOutputStream(responseFileName);
	copy(httpConn.getInputStream(),fos);
	in.close();
	}

	public static void copy(InputStream in, OutputStream out)
	throws IOException {

	synchronized (in) {
	synchronized (out) {
	byte[] buffer = new byte[256];
	while (true) {
	int bytesRead = in.read(buffer);
	if (bytesRead == -1)
	break;
	out.write(buffer, 0, bytesRead);
	}
	}
	}
	}
	}

