package co.approbe.otplogin;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import co.approbe.commons.dto.CustomerDto;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

	private static CustomerDto input;

	/*
	 * private Context createContext() { TestContext ctx = new TestContext();
	 * 
	 * ctx.setFunctionName("Your Function Name");
	 * 
	 * return ctx; }
	 */

	@Test
	public void testLambdaFunctionHandler() {
		LambdaFunctionHandler handler = new LambdaFunctionHandler();
		// Context ctx = createContext();
		Assert.assertEquals("Hello from Lambda!", "Hello from Lambda!");
	}
}
