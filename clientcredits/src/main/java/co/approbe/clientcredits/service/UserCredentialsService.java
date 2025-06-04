package co.approbe.clientcredits.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;

import co.approbe.commons.model.UserCredentials;

public class UserCredentialsService {

	private AmazonDynamoDB db;
	private Context context;
	private DynamoDBMapper mapper;

	public UserCredentialsService(AmazonDynamoDB pDb, Context pContext) {
		context = pContext;
		db = pDb;
		mapper = new DynamoDBMapper(db);
	}

	public UserCredentials getUserCredentials(String pId, Context pContext) {
		pContext.getLogger().log("getUserCredentials: " + pId);
		UserCredentials wUserCredentials = mapper.load(UserCredentials.class, pId);
		if (wUserCredentials != null) {
			pContext.getLogger().log("Result getUserCredentials: " + wUserCredentials.toString());
		}
		return wUserCredentials;
	}

	public List<UserCredentials> getClientUsers(String pId) {
		context.getLogger().log("getUserCredentials: " + pId);

		List<UserCredentials> wListUserCred;
		ArrayList<UserCredentials> wListConvertedUserCred= new ArrayList<>();

		UserCredentials wUserCred = getUserCredentials(pId, context);

		HashMap<String, AttributeValue> eav = new HashMap<>();
		eav.put(":value", new AttributeValue().withS(wUserCred.getClient()));

		
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression(" client= :value")
				.withExpressionAttributeValues(eav);
		wListUserCred = mapper.scan(UserCredentials.class, scanExpression);

		for (UserCredentials wUser : wListUserCred) {
			wListConvertedUserCred
					.add(new UserCredentials(wUser.getId(), wUser.getUserName() + " " + wUser.getUserLastName()));
		}

		return wListConvertedUserCred;
	}
}
