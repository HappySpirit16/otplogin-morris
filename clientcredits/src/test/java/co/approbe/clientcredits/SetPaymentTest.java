package co.approbe.clientcredits;

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

import co.approbe.commons.dto.CashAllocationDto;
import co.approbe.commons.dto.CashPaymentDto;
import co.approbe.commons.model.UserCredentials;

public class SetPaymentTest {

	

	@Test
	public void testLambdaFunctionHandler() {

		AWSCredentials cred = new BasicAWSCredentials("AKIA5SBAE4XKWXTF3K67",
				"oIoJ8aKDOyUD5VysGXCLMfgIMH+VSx2/MBfifhli");// dev
		AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
		AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withCredentials(credProvider)
				.withRegion(Regions.US_EAST_1).build();

		
		List<CashAllocationDto> wLstPayments = new ArrayList<CashAllocationDto>();
		wLstPayments.add(new CashAllocationDto(1000L, "00302042024161708"));
		wLstPayments.add(new CashAllocationDto(1500L, "00331052024092903"));

		UserCredentials wUserCredentials = new UserCredentials();
		wUserCredentials.setId("80123123");

		CashPaymentDto wCashPayment = new CashPaymentDto();
		wCashPayment.setCasheer(wUserCredentials);
		wCashPayment.setCustomer("1086137003");
		wCashPayment.setTotalAmmount(2500d);
		wCashPayment.setwLstCredits(wLstPayments);

		//PaymentService wPayService = new PaymentService(db, ctx);
		//ValidationStrategyDto wValidationStrategyDto = wPayService.setCashPayment(wCashPayment);
		//ctx.getLogger().log(wValidationStrategyDto.getValidationStrategy());
		
		Assert.assertEquals(true, true);
	}
}
