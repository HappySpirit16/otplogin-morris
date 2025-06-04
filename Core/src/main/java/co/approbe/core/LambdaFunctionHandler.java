package co.approbe.core;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import co.approbe.core.dto.CoreAdapterDto;
import co.approbe.core.service.CreditService;

public class LambdaFunctionHandler implements RequestHandler<CoreAdapterDto, Object> {

	private static final String ACTION_ERROR = "ACTION_ERROR";
	private static final String CREATE_CREDIT = "CREATE_CREDIT";
	private static final String CREATE_PAYMENT = "CREATE_PAYMENT";
	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	DynamoDB db = new DynamoDB(client);
	DynamoDBMapper mapper = new DynamoDBMapper(client);

	@Override
	public Object handleRequest(CoreAdapterDto input, Context context) {
		context.getLogger().log("Input: " + input);

		switch (input.getActionStrategyPattern()) {

		case CREATE_CREDIT:
			return new CreditService(client, context).setCredit(input);

		case CREATE_PAYMENT:
			return new CreditService(client, context).setPayment(input);

		default:
			return ACTION_ERROR;
		}
	}
}