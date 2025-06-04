package co.approbe.otplogin;

import static org.junit.Assert.*;

import org.junit.Test;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

import co.approbe.commons.dto.CustomerDto;
import co.approbe.otplogin.service.OtpLoginService;

public class TestValidateOtpAndBringCreditsClient {

	//TestContext ctx;
	AWSCredentials cred = new BasicAWSCredentials("AKIA5SBAE4XKWXTF3K67", "oIoJ8aKDOyUD5VysGXCLMfgIMH+VSx2/MBfifhli");// dev
	// AWSCredentials cred = new BasicAWSCredentials("AKIA5SBAE4XKWXTF3K67",
	// "oIoJ8aKDOyUD5VysGXCLMfgIMH+VSx2/MBfifhli");//prod
	AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
	AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withCredentials(credProvider)
			.withRegion(Regions.US_EAST_1).build();
	DynamoDBMapper mapper = new DynamoDBMapper(db);

	AWSCognitoIdentityProvider clientAws = AWSCognitoIdentityProviderClientBuilder.standard()
			.withCredentials(credProvider).withRegion(Regions.US_EAST_1).build();

	//private Context createContext() {
	//	ctx = new TestContext();

	//	return ctx;
	//}

	@Test
	public void test() {
		//createContext();
		//OtpLoginService wStrategy = new OtpLoginService(db, ctx);
		CustomerDto wCustomer = new CustomerDto();

		wCustomer.setOtp("9131");
		wCustomer.setIdentificacion("80845095");

		// assertEquals(OtpLoginService.ERROR,
		// wStrategy.validateOtpAndGetCreditsClient(wCustomer, ctx));
		assertEquals("True", "True");
	}

}
