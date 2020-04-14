package Soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.unicommerce.uniware.services.*;
import utility.WSSecurityHeaderSOAPHandler;
/**
 * @author nikhil
 *
 */
public class SaleOrderResponse {
// Log Request and Response for XML
 @BeforeClass
 private void beforeClass() {
 // TODO Auto-generated method stub
 System.setProperty(“com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump”, “true”);
 System.setProperty(“com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump”, “true”);
 System.setProperty(“com.sun.xml.ws.transport.http.HttpAdapter.dump”, “true”);
 System.setProperty(“com.sun.xml.internal.ws.transport.http.HttpAdapter.dump”, “true”);
 }
@Test
 public void getSaleOrderResponse() {
 // TODO Auto-generated method stub
UnicommerceService service = new UnicommerceService();
Unicommerce unicommerce = service.getUnicommerceSoap11();
// This is the block that apply the Ws Security to the request
 BindingProvider bindingProvider = (BindingProvider) unicommerce;
 @SuppressWarnings(“rawtypes”)
 List<Handler> handlerChain = new ArrayList<Handler>();
 handlerChain.add(new WSSecurityHeaderSOAPHandler(“admin”, “*********-*********–*********”));
 bindingProvider.getBinding().setHandlerChain(handlerChain);
// getSaleOrderResponse
 GetSaleOrderRequest getSaleOrderRequest = new GetSaleOrderRequest();
 GetSaleOrderRequest.SaleOrder saleOrder = new GetSaleOrderRequest.SaleOrder();
 saleOrder.setCode(“1706000553903”);
 System.out.println(saleOrder.getCode());
 getSaleOrderRequest.setSaleOrder(saleOrder);
 System.out.println(getSaleOrderRequest.getSaleOrder());
 unicommerce.getSaleOrder(getSaleOrderRequest);
}
@AfterClass
 private void afterClass() {
 // TODO Auto-generated method stub
}
}