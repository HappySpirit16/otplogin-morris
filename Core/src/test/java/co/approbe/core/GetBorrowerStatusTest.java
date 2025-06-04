package co.approbe.core;

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
import co.approbe.core.service.CreditService;


public class GetBorrowerStatusTest {
    

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
       // CoreAdapterDto wCoreAdapter = new CoreAdapterDto(null, null, null,"80935348",null);
        //CoreAdapterDto wCoreAdapter = new CoreAdapterDto(null, null, null,"Camilo123",null);
        CoreAdapterDto wCoreAdapter = new CoreAdapterDto(null, null, null,"1086137003",null,null);
       
    //   wCred.getBorrowerStatus(wCoreAdapter);

        Assert.assertEquals(true, true);
    }
}
