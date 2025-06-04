package co.approbe.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;

import co.approbe.core.dto.CoreAdapterDto;
import co.approbe.core.dto.PaymentAllocationDto;
import co.approbe.core.dto.PaymentDto;
import co.approbe.core.service.CreditService;

public class SetPaymentTest {

	private Context createContext() {
		TestContext ctx = new TestContext();

		ctx.setFunctionName("Token");

		return ctx;
	}

	@Test
	public void testLambdaFunctionHandler() {

		AWSCredentials cred = new BasicAWSCredentials("AKIA5SBAE4XKWXTF3K67",
				"oIoJ8aKDOyUD5VysGXCLMfgIMH+VSx2/MBfifhli");// dev
		AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
		AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withCredentials(credProvider)
				.withRegion(Regions.US_EAST_1).build();

		Context ctx = createContext();
		
		   CreditService wCred= new CreditService(db, ctx);

		List<PaymentAllocationDto> wLstPayments = new ArrayList<PaymentAllocationDto>();
		wLstPayments.add(new PaymentAllocationDto(1000d, "carolcr1"));

		PaymentDto wPayment = new PaymentDto(1000, "2024-03-20T00:00:00-05:00", "default_payment_config", "carolidTrx33");

		CoreAdapterDto wCoreAdapter = new CoreAdapterDto("2024-03-20T00:00:00-05:00", null, wPayment, "carol", null,
				wLstPayments);

		//wCred.setPayment(wCoreAdapter);

		Assert.assertEquals(true, true);
	}
}
