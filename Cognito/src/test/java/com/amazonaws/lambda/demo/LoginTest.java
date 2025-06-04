package com.amazonaws.lambda.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import co.approbe.dto.DataCognitoDto;
import co.approbe.dto.ResponseCognitoDto;
import co.approbe.service.CognitoService;
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

public class LoginTest {

	TestContext ctx;
	private Context createContext() {
		 ctx = new TestContext();

		return ctx;
	}

	@Test
	public void test2() {
		
		
		AWSCredentials cred = new BasicAWSCredentials("AKIA5SBAE4XKWXTF3K67",
				"oIoJ8aKDOyUD5VysGXCLMfgIMH+VSx2/MBfifhli");// dev
		
		AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
		
		
		

		AWSCognitoIdentityProvider clientAws = AWSCognitoIdentityProviderClientBuilder.standard()
				.withCredentials(credProvider).withRegion(Regions.US_EAST_1).build();
		
		
		AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withCredentials(credProvider)
				.withRegion(Regions.US_EAST_1).build();

		DynamoDBMapper mapper = new DynamoDBMapper(db);
		
		
		

		CognitoService wCognitoService = new CognitoService(clientAws, db, mapper);
		DataCognitoDto dataCustomer = new DataCognitoDto();
		dataCustomer.setEmail("Sebastian.Timana@approbe.co");
		dataCustomer.setPassword("SEBASTIANgomez22@");
		//ResponseCognitoDto wTok = wCognitoService.login(dataCustomer, createContext());
	//	ctx.getLogger().log(wTok.getAccessToken());
	//	ctx.getLogger().log(wTok.getHttpStatusCode()+"");
		//assertEquals(200, wCognitoService.login(dataCustomer, createContext()).getHttpStatusCode());
		// assertArrayEquals(OtpLoginService.ERROR, wStrategy.sendOtpCustomer(wCustomer,
		// createContext()));
		// assertEquals("True","True");
	}

}
