package Soap;



import java.io.*;

import java.net.*;


public class Soap_basic4 {

public String CreateRequest()

{

String TXML = null;

TXML = "<ENVELOPE>"

+ "<HEADER>"

+ "<VERSION>1</VERSION>"

+ "</HEADER>"

+ "<BODY>"

+ "<DESC>"

+ "<STATICVARIABLES>"

+ "<SVFROMDATE TYPE=\&#&nbsp;34;Date\&#&nbsp;34;>2-JUN-2014</SVFROMDATE>"

+ "<SVTODATE TYPE=\&#&nbsp;34;Date\&#&nbsp;34;>2-JUN-2016</SVTODATE>"

+ "<EXPLODEFLAG>Yes</EXPLODEFLAG>"

+ "<SVEXPORTFORMAT>$$SysName:XML</SVEXPORTFORMAT>"

+ "</STATICVARIABLES>"

+ "</DESC>"

+ "</BODY>"

+ "</ENVELOPE>";

return TXML;

}


public void SendToTally() throws Exception{

String Url = "http://localhost:9000/";


String SOAPAction = "";


String Voucher = this.CreateRequest();


// Create the connection where we're going to send the file.

URL url = new URL(Url);

URLConnection connection = url.openConnection();

HttpURLConnection httpConn = (HttpURLConnection) connection;



ByteArrayInputStream bin = new ByteArrayInputStream(Voucher.getBytes());

ByteArrayOutputStream bout = new ByteArrayOutputStream();


// Copy the SOAP file to the open connection.


copy(bin, bout);


byte[] b = bout.toByteArray();


// Set the appropriate HTTP parameters.

httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));

httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

httpConn.setRequestProperty("SOAPAction", SOAPAction);

httpConn.setRequestMethod("POST");

httpConn.setDoOutput(true);

httpConn.setDoInput(true);


// Everything's set up; send the XML that was read in to b.

OutputStream out = httpConn.getOutputStream();

out.write(b);

out.close();


// Read the response and write it to standard out.


InputStreamReader isr =

new InputStreamReader(httpConn.getInputStream());

BufferedReader in = new BufferedReader(isr);


String inputLine;


while ((inputLine = in.readLine()) != null) {

System.out.println(inputLine);

}


in.close();

}


public static void copy(InputStream in, OutputStream out)

throws IOException {


// do not allow other threads to read from the

// input or write to the output while copying is

// taking place


synchronized (in) {

synchronized (out) {


byte[] buffer = new byte[256];

while (true) {

int bytesRead = in.read(buffer);

if (bytesRead == -1) {

break;

}

out.write(buffer, 0, bytesRead);

}

}

}

}


public static void main(String[] args) throws Exception {

TRequest r = new TRequest();

r.SendToTally();

}

}