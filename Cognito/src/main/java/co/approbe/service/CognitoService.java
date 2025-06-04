package co.approbe.service;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesRequest;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordRequest;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpRequest;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.ForgotPasswordRequest;
import com.amazonaws.services.cognitoidp.model.ForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

import co.approbe.commons.dto.AES256;
import co.approbe.dto.CognitoDto;
import co.approbe.dto.DataCognitoDto;
import co.approbe.dto.ResponseCognitoDto;
import co.approbe.model.KeyCognito;

public class CognitoService {
	AmazonDynamoDB db;
	DynamoDBMapper mapper;
	AWSCognitoIdentityProvider client;
	public static final String KEY_SPEC = "AES";
	public static final String CIPHER_MODE = "AES/CBC/NoPadding";
	//public static final String CLIENT_ID = "2chkv23igopebaago269kbhc60";
	//public static final String POOL_ID = "us-east-1_wjgyWp8uE";
	public static final String CLIENT_ID_COGNITO = System.getenv("CLIENT_ID_COGNITO") == null ? "" : System.getenv("CLIENT_ID_COGNITO");
	public static final String POOL_ID = System.getenv("POOL_ID") == null ? "" : System.getenv("POOL_ID");

	public CognitoService(AWSCognitoIdentityProvider clientAws, AmazonDynamoDB pDb, DynamoDBMapper pMapper) {
		client = clientAws;
		db = pDb;
		mapper = pMapper;
	}

	// login Cognito, se necesita enviar email y password
	public ResponseCognitoDto login(DataCognitoDto dataCustomer, Context context) {
		
		
		context.getLogger().log("entra a login");
		ResponseCognitoDto result = new ResponseCognitoDto();
		Map<String, String> authParams = new HashMap<String, String>();
		authParams.put("USERNAME", dataCustomer.getEmail());
		authParams.put("PASSWORD", dataCustomer.getPassword());

		context.getLogger().log("map: " + authParams);
		context.getLogger().log("client id: " + CLIENT_ID_COGNITO+"pool id: "+POOL_ID );
		try {
			AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
					.withAuthFlow(AuthFlowType.ADMIN_USER_PASSWORD_AUTH).withUserPoolId(POOL_ID).withClientId(CLIENT_ID_COGNITO)
					.withAuthParameters(authParams);

			context.getLogger().log("pasa request " + authRequest.toString());
			AdminInitiateAuthResult authResult = client.adminInitiateAuth(authRequest);
			context.getLogger().log("pauthResult " + authResult);
			AuthenticationResultType resultType = authResult.getAuthenticationResult();
			context.getLogger().log("resultType " + resultType);
			result.setAccessToken(resultType.getAccessToken());
//			result.setIdToken(resultType.getIdToken());
//			result.setRefreshToken(resultType.getRefreshToken());
			result.setMessage("Successfully login");
			result.setHttpStatusCode(200);
			result.setUserConfirmed(true);

		} catch (Exception e) {
			context.getLogger().log("Error cognito: " + e.getMessage());
			result.setErrorMessage("incorrect data"+e.getMessage());
			result.setHttpStatusCode(401);
		}
		context.getLogger().log("Tokens: " + result);
		return result;
	}

	// registrar usuario en cognito y enviar otp a correo electronico, se necesita
	// enviar email y password
	public ResponseCognitoDto signUp(DataCognitoDto dataCustomer, Context context) {

		context.getLogger().log("Entra a singUp: ");
		context.getLogger().log("Cognito: " + dataCustomer);

		AttributeType phoneAttribute = new AttributeType().withName("phone_number")
				.withValue("+57" + dataCustomer.getPhoneNumber());

		ResponseCognitoDto result = new ResponseCognitoDto();
		SignUpRequest request = new SignUpRequest().withClientId(CLIENT_ID_COGNITO).withUsername(dataCustomer.getEmail())
				.withPassword(dataCustomer.getPassword()).withUserAttributes(phoneAttribute);

		try {
			SignUpResult sigUpCustomer = client.signUp(request);
			context.getLogger().log("Respuesta: " + sigUpCustomer);
			result.setUserConfirmed(sigUpCustomer.getUserConfirmed());
			result.setUserSub(sigUpCustomer.getUserSub());
			result.setHttpStatusCode(sigUpCustomer.getSdkHttpMetadata().getHttpStatusCode());

		} catch (Exception e) {
			result.setErrorMessage(e.getLocalizedMessage());
			result.setHttpStatusCode(400);
		}
		context.getLogger().log("Response SingUp: " + result);
		return result;
	}

	// metodo para confirmar otp y confirmar cuenta, se necesita enviar otp, email
	public ResponseCognitoDto confirmSignUp(DataCognitoDto dataCustomer, Context context) {
		context.getLogger().log("confirmSignUp");
		context.getLogger().log("Cognito: " + dataCustomer);

		ResponseCognitoDto result = new ResponseCognitoDto();
		ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest().withClientId(CLIENT_ID_COGNITO)
				.withUsername(dataCustomer.getEmail()).withConfirmationCode(dataCustomer.getOtp());

		try {
			ConfirmSignUpResult signUpResult = client.confirmSignUp(confirmSignUpRequest);
			context.getLogger().log("Respuesta: " + signUpResult);
			result.setHttpStatusCode(signUpResult.getSdkHttpMetadata().getHttpStatusCode());
			result.setMessage(signUpResult.getSdkResponseMetadata().getRequestId());
			result.setUserConfirmed(true);
		} catch (Exception e) {
			result.setErrorMessage(e.getLocalizedMessage());
			result.setHttpStatusCode(400);
		}
		context.getLogger().log("Response ComfirSingUp: " + result);
		return result;
	}

	// envar otp para cambio de password a correo electronico, se necesita enviar
	// email
	public ResponseCognitoDto sendOtp(DataCognitoDto dataCustomer, Context context) {
		context.getLogger().log("Send Otp para cambio de contraseña");
		context.getLogger().log("Cognito: " + dataCustomer);
		ResponseCognitoDto result = new ResponseCognitoDto();

		ForgotPasswordRequest request = new ForgotPasswordRequest().withClientId(CLIENT_ID_COGNITO)
				.withUsername(dataCustomer.getEmail());

		try {
			ForgotPasswordResult response = client.forgotPassword(request);
			context.getLogger().log("Respuesta: " + response);
			result.setHttpStatusCode(response.getSdkHttpMetadata().getHttpStatusCode());
			result.setMessage(response.getSdkResponseMetadata().getRequestId());
			result.setUserConfirmed(true);
		} catch (Exception e) {
			result.setErrorMessage(e.getLocalizedMessage());
			result.setHttpStatusCode(400);
		}
		context.getLogger().log("Response SendOtp: " + result);
		return result;
	}

	// Metodo para cambio de password con otp, se necesita enviar otp, email y
	// password
	public ResponseCognitoDto changePassword(DataCognitoDto dataCustomer, Context context) {
		context.getLogger().log("confirmOtp and change of password");
		context.getLogger().log("Cognito: " + dataCustomer);

		ResponseCognitoDto result = new ResponseCognitoDto();
		ConfirmForgotPasswordRequest confirmRequest = new ConfirmForgotPasswordRequest().withClientId(CLIENT_ID_COGNITO)
				.withUsername(dataCustomer.getEmail()).withConfirmationCode(dataCustomer.getOtp())
				.withPassword(dataCustomer.getPassword());

		try {
			ConfirmForgotPasswordResult response = client.confirmForgotPassword(confirmRequest);
			context.getLogger().log("Respuesta: " + response);
			result.setHttpStatusCode(response.getSdkHttpMetadata().getHttpStatusCode());
			result.setMessage(response.getSdkResponseMetadata().getRequestId());
			result.setUserConfirmed(true);
		} catch (Exception e) {
			result.setErrorMessage(e.getLocalizedMessage());
			result.setHttpStatusCode(400);
		}
		context.getLogger().log("Response changePassword: " + result);
		return result;
	}

	// metodo para encriptar data de cognito
	public String encryptarData(CognitoDto cognito, Context context) {

		KeyCognito key = mapper.load(KeyCognito.class, cognito.getChannel());
		context.getLogger().log("KeyCognito: " + key);

		AES256 aes256 = new AES256();
		DataCognitoDto dtaEncry = new DataCognitoDto(cognito.getEmail(), cognito.getPassword(), cognito.getOtp(),
				cognito.getPhoneNumber());
		String dataEncrip = aes256.encrypt(key.getKey(), KEY_SPEC, CIPHER_MODE, dtaEncry.toString());

		context.getLogger().log("Response encryptarData: " + dataEncrip);
		return dataEncrip;
	}

///------------------------------------------------------------pruebas con cognico ----------------------------------	

	// metodo para enviar password temporal y envia al email y al correo l password
	// temporal, para verificar celular y correo
	// , se necesita enviar otp, email
	public ResponseCognitoDto signUp2(DataCognitoDto dataCustomer, Context context) {
		context.getLogger().log("send Password Temporal");
		context.getLogger().log("Cognito: " + dataCustomer);

		ResponseCognitoDto result = new ResponseCognitoDto();

		AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest().withUserPoolId(POOL_ID)
				.withUsername(dataCustomer.getEmail())
				.withUserAttributes(new AttributeType().withName("email").withValue(dataCustomer.getEmail()),
						new AttributeType().withName("phone_number").withValue("+57" + dataCustomer.getPhoneNumber()))
				.withDesiredDeliveryMediums("EMAIL", "SMS");

		try {
			AdminCreateUserResult signUpResult = client.adminCreateUser(createUserRequest);
			context.getLogger().log("Respuesta: " + signUpResult);
			result.setHttpStatusCode(signUpResult.getSdkHttpMetadata().getHttpStatusCode());
			result.setMessage(signUpResult.getSdkResponseMetadata().getRequestId());
			result.setUserConfirmed(true);
		} catch (Exception e) {
			result.setErrorMessage(e.getLocalizedMessage());
			result.setHttpStatusCode(400);
		}
		context.getLogger().log("Response ComfirSingUp: " + result);
		return result;
	}

// reenviar un otp, aun no funciona 	
	public ResponseCognitoDto resendOTP(DataCognitoDto dataCustomer, Context context) {
		context.getLogger().log("Resend OTP");
		context.getLogger().log("Cognito: " + dataCustomer);
		ResponseCognitoDto result = new ResponseCognitoDto();

		AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest().withUserPoolId(POOL_ID)
				.withUsername(dataCustomer.getEmail()).withMessageAction("RESEND");

		try {
			AdminCreateUserResult signUpResult = client.adminCreateUser(createUserRequest);
			context.getLogger().log("Respuesta: " + signUpResult);
			result.setHttpStatusCode(signUpResult.getSdkHttpMetadata().getHttpStatusCode());
			result.setMessage(signUpResult.getSdkResponseMetadata().getRequestId());
			result.setUserConfirmed(true);
		} catch (Exception e) {
			result.setErrorMessage(e.getLocalizedMessage());
			result.setHttpStatusCode(400);
		}
		context.getLogger().log("Response ComfirSingUp: " + result);
		return result;
	}

	// habilitar o deshabilitar correo o celular
	public ResponseCognitoDto confirmEmailOrPhone(DataCognitoDto dataCustomer, Context context) {
		context.getLogger().log("habilitar o deshabilitar device");
		context.getLogger().log("Cognito: " + dataCustomer);
		ResponseCognitoDto result = new ResponseCognitoDto();

		AttributeType phoneAttribute = new AttributeType().withName(dataCustomer.getName())
				.withValue(dataCustomer.getValue());
		AdminUpdateUserAttributesRequest updateAttributesRequest = new AdminUpdateUserAttributesRequest()
				.withUserPoolId(POOL_ID).withUsername(dataCustomer.getEmail()).withUserAttributes(phoneAttribute);

		try {
			AdminUpdateUserAttributesResult signUpResult = client.adminUpdateUserAttributes(updateAttributesRequest);
			context.getLogger().log("Respuesta: " + signUpResult);
			result.setHttpStatusCode(signUpResult.getSdkHttpMetadata().getHttpStatusCode());
			result.setMessage(signUpResult.getSdkResponseMetadata().getRequestId());
			result.setUserConfirmed(true);
		} catch (Exception e) {
			result.setErrorMessage(e.getLocalizedMessage());
			result.setHttpStatusCode(400);
		}
		context.getLogger().log("Response ComfirSingUp: " + result);
		return result;
	}

}
