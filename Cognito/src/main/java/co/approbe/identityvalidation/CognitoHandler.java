package co.approbe.identityvalidation;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import co.approbe.commons.dto.AES256;
import co.approbe.dto.CognitoDto;
import co.approbe.dto.DataCognitoDto;
import co.approbe.dto.ResponseCognitoDto;
import co.approbe.dto.ValidationCognitoDto;
import co.approbe.model.KeyCognito;
import co.approbe.service.CognitoService;

public class CognitoHandler implements RequestHandler<CognitoDto, Object> {

	AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	DynamoDBMapper mapper = new DynamoDBMapper(db);

	AWSCognitoIdentityProvider clientAws = AWSCognitoIdentityProviderClientBuilder.standard()
			.withRegion(Regions.US_EAST_1).build();

	ValidationCognitoDto validationCognitoDto;
	public static final String KEY_SPEC = "AES";
	public static final String CIPHER_MODE = "AES/CBC/NoPadding";
	private static final String ACTION_ERROR = "ACTION_ERROR";
	private static final String LOGIN = "LOGIN";
	private static final String SIGNUP = "SIGNUP";
	private static final String SEND_OTP = "SEND_OTP";
	private static final String CONFIRM_SIGNUP = "CONFIRM_SIGNUP";
	private static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
	private static final String ENCRYPT = "ENCRYPT";

	@Override
	public Object handleRequest(CognitoDto cognito, Context context) {
		context.getLogger().log("Cognito: " + cognito.toString());
		validationCognitoDto = new ValidationCognitoDto();
		CognitoService wCognitoService = new CognitoService(clientAws, db, mapper);

		DataCognitoDto dataCustomer = desEncryptarData(cognito, context);
		if (dataCustomer == null && !cognito.getAction().equals(ENCRYPT)) {
			ResponseCognitoDto result = new ResponseCognitoDto();
			context.getLogger().log("entra por datos mal encriptados");
			result.setErrorMessage("incorrect data");
			result.setHttpStatusCode(400);
			context.getLogger().log("Response "+result);
			return result;
		}

		switch (cognito.getAction()) {
		case LOGIN:
			return wCognitoService.login(dataCustomer, context);
		case SIGNUP:
			return wCognitoService.signUp(dataCustomer, context);
		case CONFIRM_SIGNUP:
			return wCognitoService.confirmSignUp(dataCustomer, context);
		case SEND_OTP:
			return wCognitoService.sendOtp(dataCustomer, context);
		case CHANGE_PASSWORD:
			return wCognitoService.changePassword(dataCustomer, context);
		case ENCRYPT:
			return wCognitoService.encryptarData(cognito, context);
		default:
			validationCognitoDto.setValidation(ACTION_ERROR);
		}
		return validationCognitoDto;
	}

// Metodo para des-encryptar data 	
	public DataCognitoDto desEncryptarData(CognitoDto cognito, Context context) {
		context.getLogger().log("data entrada a desencriptar: " + cognito.getData());
		KeyCognito key = mapper.load(KeyCognito.class, cognito.getChannel());
		context.getLogger().log("KeyCognito: " + key);
		DataCognitoDto dataCustomer = new DataCognitoDto();
		AES256 aes256 = new AES256();
		try {
			String desEncrip = (aes256.decrypt(key.getKey(), KEY_SPEC, CIPHER_MODE, cognito.getData())).trim();
			context.getLogger().log("data des Encry: " + desEncrip);
			dataCustomer = new Gson().fromJson(desEncrip, DataCognitoDto.class);
		} catch (Exception e) {
			dataCustomer = null;
		}
		context.getLogger().log("Response desencryptar: " + dataCustomer);
		return dataCustomer;
	}

}
