package co.approbe.clientcredits;

import org.junit.Test;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;

import co.approbe.commons.dto.CustomerDto;

public class TestClientCredits {

	

	@Test
	public void test() {
		AWSCredentials cred = new BasicAWSCredentials("AKIA5SBAE4XKWXTF3K67",
				"oIoJ8aKDOyUD5VysGXCLMfgIMH+VSx2/MBfifhli");// dev
		AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
		AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withCredentials(credProvider)
				.withRegion(Regions.US_EAST_1).build();

		CustomerDto wCustomer = new CustomerDto();

		
		//wCustomer.setIdentificacion("52916207");
		//wCustomer.setIdentificacion("80935348");
		wCustomer.setIdentificacion("1032475972");
		

		//List<CreditDto> wLstCredits = new ClientCreditsService(db, ctx).getClientCredits(wCustomer);
		
		//ctx.getLogger().log("Creditos :"+ wLstCredits.size());

		// assertEquals(OtpLoginService.ERROR, wStrategy.sendOtpCustomer(wCustomer,
		// createContext()));
		//assertEquals("True", "True");

	}

}
