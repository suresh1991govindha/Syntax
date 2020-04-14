package Soap;

import java.io.File;
import java.io.FileInputStream;

import javax.wsdl.extensions.soap.SOAPBody;

import org.apache.xmlbeans.impl.soap.SOAPConnection;
import org.apache.xmlbeans.impl.soap.SOAPConnectionFactory;
import org.apache.xmlbeans.impl.soap.SOAPMessage;

import Basicx.Payload;


public class Soap_basic5 {

	

private static SOAPMessage createSOAPRequest(String strPath) throws Exception {
         
    // Create a SOAP message from the XML file located at the given path
    FileInputStream fis = new FileInputStream(new File(strPath));
    MessageFactory factory = MessageFactory.newInstance();
    SOAPMessage message = factory.createMessage(new MimeHeaders(), fis);
    return message;
}


private static SOAPMessage getSOAPResponse(SOAPMessage soapRequest, String strEndpoint) throws Exception, SOAPException {
         
    // Send the SOAP request to the given endpoint and return the corresponding response
    SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
    
    
    SOAPConnection soapConnection = soapConnectionFactory.createConnection();
    
    
    SOAPMessage soapResponse = soapConnection.call(soapRequest, strEndpoint);
    return soapResponse;    
}


private static void validateValue(SOAPMessage soapMsg, String strEl, String strExpected) throws Exception {
         
    // Get all elements with the requested element tag from the SOAP message
    SOAPBody soapBody = soapMsg.getSOAPBody()y();
    NodeList elements = soapBody.getElementsByTagName(strEl);
         
    // Check whether there is exactly one element with the given tag
    if (elements.getLength() != 1){
        System.out.println("Expected exactly one element " + strEl + "in message, but found " + Integer.toString(elements.getLength()));
    } else {
        // Validate the element value against the expected value
        String strActual = elements.item(0).getTextContent();
        if (strActual.equals(strExpected)) {
            System.out.println("Actual value " + strActual + " for element " + strEl + " matches expected value");
        } else {
            System.out.println("Expected value " + strExpected + " for element " + strEl + ", but found " + strActual + " instead");
        }
    }
}


public static void main(String[] args) {
	Soap_basic5 soapss=new Soap_basic5();
	String add = Payload.Add();

	
}
}
