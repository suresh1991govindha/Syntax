package Soap;



	import java.io.ByteArrayInputStream;
	import java.io.IOException;
	import java.io.InputStream;

	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;
	import javax.xml.soap.MessageFactory;
	import javax.xml.soap.MimeHeaders;
	import javax.xml.soap.SOAPBody;
	import javax.xml.soap.SOAPConnection;
	import javax.xml.soap.SOAPConnectionFactory;
	import javax.xml.soap.SOAPElement;
	import javax.xml.soap.SOAPEnvelope;
	import javax.xml.soap.SOAPException;
	import javax.xml.soap.SOAPMessage;
	import javax.xml.soap.SOAPPart;

	import org.apache.log4j.Logger;
	import org.w3c.dom.Document;
	import org.xml.sax.SAXException;

	/**
	 * This is an example of a simple SOAP Client class to send request body to a
	 * SOAP Server.
	 * 
	 * Useful when you want to test a SOAP server and you don't want to generate all
	 * SOAP client class from the WSDL.
	 * 
	 * @author kdelfour
	 */
	public class ASimpleSOAPClient {

		// Default logger
		private static final Logger LOG = Logger.getLogger(ASimpleSOAPClient.class);

		// The SOAP server URI
		private String uriSOAPServer;
		// The SOAP connection
		private SOAPConnection soapConnection = null;

		// If you want to add namespace to the header, follow this constant
		private static final String PREFIX_NAMESPACE = "ns";
		private static final String NAMESPACE = "http://other.namespace.to.add.to.header";

		/**
		 * A constructor who create a SOAP connection
		 * 
		 * @param url
		 *            the SOAP server URI
		 */
		public ASimpleSOAPClient(final String url) {
			this.uriSOAPServer = url;

			try {
				createSOAPConnection();
			} catch ( SOAPException e) {
				LOG.error(e);
			}
		}

		/**
		 * Send a SOAP request for a specific operation
		 * 
		 * @param xmlRequestBody
		 *            the body of the SOAP message
		 * @param operation
		 *            the operation from the SOAP server invoked
		 * @return a response from the server
		 * @throws SOAPException
		 * @throws ParserConfigurationException
		 * @throws IOException
		 * @throws SAXException
		 */
		public String sendMessageToSOAPServer(String xmlRequestBody,
				String operation) throws SOAPException, SAXException, IOException,
				ParserConfigurationException {

			// Send SOAP Message to SOAP Server
			final SOAPElement stringToSOAPElement = stringToSOAPElement(xmlRequestBody);
			final SOAPMessage soapResponse = soapConnection.call(
					createSOAPRequest(stringToSOAPElement, operation),
					uriSOAPServer);

			// Print SOAP Response
			LOG.info("Response SOAP Message : " + soapResponse.toString());
			return soapResponse.toString();
		}

		/**
		 * Create a SOAP connection
		 * 
		 * @throws UnsupportedOperationException
		 * @throws SOAPException
		 */
		private void createSOAPConnection() throws UnsupportedOperationException,
				SOAPException {

			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory;
			soapConnectionFactory = SOAPConnectionFactory.newInstance();
			soapConnection = soapConnectionFactory.createConnection();
		}

		/**
		 * Create a SOAP request
		 * 
		 * @param body
		 *            the body of the SOAP message
		 * @param operation
		 *            the operation from the SOAP server invoked
		 * @return the SOAP message request completed
		 * @throws SOAPException
		 */
		private SOAPMessage createSOAPRequest(SOAPElement body, String operation)
				throws SOAPException {

			final MessageFactory messageFactory = MessageFactory.newInstance();
			final SOAPMessage soapMessage = messageFactory.createMessage();
			final SOAPPart soapPart = soapMessage.getSOAPPart();

			// SOAP Envelope
			final SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addNamespaceDeclaration(PREFIX_NAMESPACE, NAMESPACE);

			// SOAP Body
			final SOAPBody soapBody = envelope.getBody();
			soapBody.addChildElement(body);

			// Mime Headers
			final MimeHeaders headers = soapMessage.getMimeHeaders();
			LOG.info("SOAPAction : " + uriSOAPServer + operation);
			headers.addHeader("SOAPAction", uriSOAPServer + operation);

			soapMessage.saveChanges();

			/* Print the request message */
			LOG.info("Request SOAP Message :" + soapMessage.toString());
			return soapMessage;
		}

		/**
		 * Transform a String to a SOAP element
		 * 
		 * @param xmlRequestBody
		 *            the string body representation
		 * @return a SOAP element
		 * @throws SOAPException
		 * @throws SAXException
		 * @throws IOException
		 * @throws ParserConfigurationException
		 */
		private SOAPElement stringToSOAPElement(String xmlRequestBody)
				throws SOAPException, SAXException, IOException,
				ParserConfigurationException {

			// Load the XML text into a DOM Document
			final DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			builderFactory.setNamespaceAware(true);
			final InputStream stream = new ByteArrayInputStream(
					xmlRequestBody.getBytes());
			final Document doc = builderFactory.newDocumentBuilder().parse(stream);

			// Use SAAJ to convert Document to SOAPElement
			// Create SoapMessage
			final MessageFactory msgFactory = MessageFactory.newInstance();
			final SOAPMessage message = msgFactory.createMessage();
			final SOAPBody soapBody = message.getSOAPBody();

			// This returns the SOAPBodyElement that contains ONLY the Payload
			return soapBody.addDocument(doc);
		}
	}