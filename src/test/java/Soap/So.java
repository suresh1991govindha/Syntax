package Soap;

import java.io.File;
import java.io.FileInputStream;

import javax.wsdl.extensions.soap.SOAPBody;

import org.apache.xmlbeans.impl.soap.MessageFactory;
import org.apache.xmlbeans.impl.soap.MimeHeaders;
import org.apache.xmlbeans.impl.soap.SOAPConnection;
import org.apache.xmlbeans.impl.soap.SOAPConnectionFactory;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPMessage;

import groovy.util.NodeList;

public class So {


private static SOAPMessage createSOAPRequest(String strPath) throws Exception {
         
	SOAPConnectionFactory soapConnectionFactory =  SOAPConnectionFactory.newInstance();
	SOAPConnection connection = soapConnectionFactory.createConnection();
	java.net.URL endpoint = new URL("url");
	SOAPMessage message = xmlStringParser.getSoapMessageFromString(XMLString);
	SOAPMessage response = connection.call(message, endpoint);
/*    // Create a SOAP message from the XML file located at the given path
    FileInputStream fis = new FileInputStream(new File(strPath));
    MessageFactory factory = MessageFactory.newInstance();
    SOAPMessage message = factory.createMessage(new MimeHeaders(), fis);
    return message;*/
}


private static SOAPMessage getSOAPResponse(SOAPMessage soapRequest, String strEndpoint) throws Exception, SOAPException {
         
    // Send the SOAP request to the given endpoint and return the corresponding response
    SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
    SOAPConnection soapConnection = soapConnectionFactory.createConnection();
    SOAPMessage soapResponse = soapConnection.call(soapRequest, strEndpoint);
    return soapResponse;    
}



public static void main(String[] args) throws Exception {
	
	So s=new So();
s.createSOAPRequest("C:\\Users\\rockstr\\eclipse-workspace\\RestAssured\\src\\test\\resource\\Reponse.xml");
	
}
}
	
