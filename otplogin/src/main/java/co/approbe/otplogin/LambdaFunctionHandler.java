package co.approbe.otplogin;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import co.approbe.commons.dto.CustomerDto;
import co.approbe.otplogin.service.OtpLoginService;

public class LambdaFunctionHandler implements RequestHandler<CustomerDto, Object> {

	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	DynamoDB db = new DynamoDB(client);
	DynamoDBMapper mapper = new DynamoDBMapper(client);
	private static final String ACTION_ERROR = "ACTION_ERROR";
	private static final String SEND_OTP_CLIENT = "SEND_OTP_CLIENT";
	private static final String SEND_OTP_USER = "SEND_OTP_USER";
	private static final String CONFIRM_OTP = "CONFIRM_OTP";
	private static final String CONFIRM_USER_OTP = "CONFIRM_USER_OTP";
	private static final String CONFIRM_APR_OTP = "CONFIRM_APR_OTP";
	AWSCognitoIdentityProvider clientAws = AWSCognitoIdentityProviderClientBuilder.standard()
			.withRegion(Regions.US_EAST_1).build();

	@Override
	public Object handleRequest(CustomerDto input, Context context) {
		context.getLogger().log("Input: " + input);

		switch (input.getActionStrategyPattern()) {

		case SEND_OTP_CLIENT:
			return new OtpLoginService(client, context).sendOtpCustomer(input, context);

		case SEND_OTP_USER:
			return new OtpLoginService(client, context).sendOtpUser(input, context);

		case CONFIRM_OTP:
			return new OtpLoginService(client, context).validateOtpAndGetCreditsClient(input, context);

		case CONFIRM_USER_OTP:
			return new OtpLoginService(client, context).validateOtpAndUserGetUserPermission(input, context);

		case CONFIRM_APR_OTP:
			return new OtpLoginService(client, context).validateOtpUserApr(input, context);

		default:
			return ACTION_ERROR;
		}
	}

}
