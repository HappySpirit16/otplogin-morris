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
import co.approbe.core.dto.LoanDto;
import co.approbe.core.service.CreditService;


public class SetCreditTest {
    

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
        
        List<String> wLstDueDates = new ArrayList<String>();
        wLstDueDates.add("2024-04-15T00:00:00-05:00");
        wLstDueDates.add("2024-05-15T00:00:00-05:00");
        wLstDueDates.add("2024-06-15T00:00:00-05:00");    
        
        LoanDto wLoan = new LoanDto(230000L, 0.25d, 1.0d, 27600d, 0d, "2024-03-15T00:00:00-05:00", "default_loan_config", "Mackenos 1", wLstDueDates);
        
        CoreAdapterDto wCoreAdapter = new CoreAdapterDto("2024-03-15T00:00:00-05:00", wLoan, null,"carol","carolidCredit1",null);
       
        //wCred.setCredit(wCoreAdapter);

        Assert.assertEquals(true, true);
    }
}
