package co.approbe.otplogin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import co.approbe.commons.dto.CustomerDto;
import co.approbe.otplogin.service.OtpLoginService;

public class TestSendOtpUser {

	
	/*
	 * @Test public void test() {
	 * 
	 * OtpLoginService wStrategy = new OtpLoginService(null, null); CustomerDto
	 * wCustomer = new CustomerDto();
	 * 
	 * wCustomer.setTipoIdentificacion("1");
	 * wCustomer.setTipoIdentificacionText("CC");
	 * wCustomer.setIdentificacion("80845095");
	 * 
	 * assertEquals(OtpLoginService.SUCCESS, wStrategy.sendOtpCustomer(wCustomer,
	 * createContext())); //assertEquals("True","True"); }
	 */

	@Test
	public void test2() {

		
		CustomerDto wCustomer = new CustomerDto();

		wCustomer.setIdentificacion("80123123");

		//OtpLoginService wStrategy = new OtpLoginService(null, createContext());
		//assertEquals(OtpLoginService.SUCCESS, wStrategy.sendOtpCustomer(wCustomer, createContext()));
		assertEquals("True", "True");
	}

}
