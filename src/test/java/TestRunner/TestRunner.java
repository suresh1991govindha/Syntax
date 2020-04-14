package TestRunner;

import java.io.IOException;
import java.util.Properties;

import org.apache.xmlbeans.XmlException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.DynamicGraph.Status;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCaseRunner;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.support.SoapUIException;

public class TestRunner {

	@Test
	public void basicTest() throws XmlException, IOException, SoapUIException {
		
		
		WsdlProject  project=new WsdlProject("C:\\Users\\rockstr\\Documents\\216-10-245-soapui-project.xml");
		
		
		WsdlTestSuite testSuite= project.getTestSuiteByName("Testing");
		
		
		for (int i = 0; i < testSuite.getTestCaseCount(); i++) {
			
			
			WsdlTestCase testCase = testSuite.getTestCaseAt(i);
			
			WsdlTestCaseRunner runner = testCase.run(new PropertiesMap(), false);
			
			Assert.assertEquals(Status.FINISHED, runner.getStatus());
			
		}
		
		
	}
}
