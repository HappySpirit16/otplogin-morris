package co.approbe.clientcredits;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import co.approbe.clientcredits.service.BackOfficeService;
import co.approbe.clientcredits.service.ClientCreditsService;
import co.approbe.clientcredits.service.PaymentService;
import co.approbe.clientcredits.service.UserCredentialsService;
import co.approbe.commons.dto.CustomerDto;

public class LambdaFunctionHandler implements RequestHandler<CustomerDto, Object> {

	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	DynamoDB db = new DynamoDB(client);
	DynamoDBMapper mapper = new DynamoDBMapper(client);
	private static final String ACTION_ERROR = "ACTION_ERROR";
	private static final String GET_CLIENT_CREDITS = "GET_CLIENT_CREDITS";
	private static final String SET_CASH_PAYMENT = "SET_CASH_PAYMENT";
	private static final String GET_CASHIER_COLLECTIONS = "GET_CASHIER_COLLECTIONS";
	private static final String GET_TOTALS_CASHIER_COLLECTIONS = "GET_TOTALS_CASHIER_COLLECTIONS";
	private static final String GET_CLIENT_COLLECTIONS = "GET_CLIENT_COLLECTIONS";
	private static final String GET_TOTALS_CLIENT_COLLECTIONS = "GET_TOTALS_CLIENT_COLLECTIONS";
	private static final String GET_CLIENT_USERS = "GET_CLIENT_USERS";

	@Override
	public Object handleRequest(CustomerDto input, Context context) {
		context.getLogger().log("Input: " + input.toString());

		switch (input.getActionStrategyPattern()) {

		case GET_CLIENT_CREDITS:
			return new ClientCreditsService(client, context).getClientCredits(input);

		case SET_CASH_PAYMENT:
			return new PaymentService(client, context).setCashPayment(input.getCashPayment());

		case GET_CASHIER_COLLECTIONS:
			return new BackOfficeService(client, context).getCasheerCashPayments(input.getDateFilter(), context);

		case GET_TOTALS_CASHIER_COLLECTIONS:
			return new BackOfficeService(client, context).getTotalsCasheerCashPayments(input.getDateFilter(), context);

		case GET_CLIENT_USERS:
			return new UserCredentialsService(client, context).getClientUsers(input.getDateFilter().getUserId());
		
		case GET_TOTALS_CLIENT_COLLECTIONS:
			return new BackOfficeService(client, context).getTotalsClientCashPayments(input.getDateFilter(), context);
		
		case GET_CLIENT_COLLECTIONS:
			return new BackOfficeService(client, context).getClientCashPayments(input.getDateFilter(), context);

		default:
			return ACTION_ERROR;
		}
	}

}
