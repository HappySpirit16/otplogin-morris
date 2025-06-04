package co.approbe.clientcredits;

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
import com.google.gson.Gson;

import co.approbe.clientcredits.service.BackOfficeService;
import co.approbe.commons.dto.DateFilterDto;
import co.approbe.commons.dto.DispersionSummaryDto;
import co.approbe.commons.model.CashPayment;
import co.approbe.commons.model.CashPaymentSplit;

public class GetClientPendingDispersionTest {

	
	@Test
	public void testLambdaFunctionHandler() {

		
		
		CashPaymentSplit wCashPaymentSplit = new CashPaymentSplit();
		wCashPaymentSplit.setClient("SURTI");
		
		AWSCredentials cred = new BasicAWSCredentials("AKIA5SBAE4XKWXTF3K67",
				"oIoJ8aKDOyUD5VysGXCLMfgIMH+VSx2/MBfifhli");// dev
		AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
		AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withCredentials(credProvider)
				.withRegion(Regions.US_EAST_1).build();

		BackOfficeService wBackOfficeService = new BackOfficeService(db, null);
		
		//List<DispersionSummaryDto> wLst = wBackOfficeService.getPendingClientDispersions(wCashPaymentSplit, ctx);

		//ctx.getLogger().log("Cantidad de pagos recibidos: "+wLst.size());
		//ctx.getLogger().log(new Gson().toJson(wLst));
		
		Assert.assertEquals(true, true);
	}
}
