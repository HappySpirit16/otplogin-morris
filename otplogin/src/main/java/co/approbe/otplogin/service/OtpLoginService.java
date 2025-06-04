package co.approbe.otplogin.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.approbe.clientcredits.service.ClientCreditsService;
import co.approbe.commons.dto.CustomerDto;
import co.approbe.commons.dto.OtpDto;
import co.approbe.commons.dto.OtpValidationDto;
import co.approbe.commons.dto.ValidationStrategyDto;
import co.approbe.commons.model.AuthData;
import co.approbe.commons.model.ClientOtpToken;
import co.approbe.commons.model.Credit;
import co.approbe.commons.model.Profile;
import co.approbe.commons.model.UserCredentials;
import co.approbe.core.dto.CreditDto;
import co.approbe.dto.DataCognitoDto;
import co.approbe.dto.ResponseCognitoDto;
import co.approbe.model.BodyMessage;
import co.approbe.service.CognitoService;

public class OtpLoginService {

	private AmazonDynamoDB db;
	private Context context;

	public static final String URL_GCP = System.getenv("URL_GCP") == null ? "" : System.getenv("URL_GCP");
	public static final String URL_AWS = System.getenv("URL_AWS") == null ? "" : System.getenv("URL_AWS");
	public static final String TYPE_OTP = System.getenv("TYPE_OTP") == null ? "" : System.getenv("TYPE_OTP");
	public static final String EMAIL = System.getenv("EMAIL") == null ? "" : System.getenv("EMAIL");
	public static final String PASS_EMAIL = System.getenv("PASS_EMAIL") == null ? "" : System.getenv("PASS_EMAIL");
	AWSCognitoIdentityProvider clientAws = AWSCognitoIdentityProviderClientBuilder.standard()
			.withRegion(Regions.US_EAST_1).build();

	// AWSCredentials cred = new BasicAWSCredentials("AKIA5SBAE4XKWXTF3K67",
	// "oIoJ8aKDOyUD5VysGXCLMfgIMH+VSx2/MBfifhli");// dev
	// AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);

	// AWSCognitoIdentityProvider clientAws =
	// AWSCognitoIdentityProviderClientBuilder.standard()
	// .withCredentials(credProvider).withRegion(Regions.US_EAST_1).build();

	private static String contentType = "Content-Type";
	private static String applicationJson = "application/json";
	private HttpClient client = HttpClient.newHttpClient();

	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	public static final String UP_T0_DATE = "UP_T0_DATE";

	private HttpResponse<String> response;
	private DynamoDBMapper mapper;
	private ValidationStrategyDto wValStrategy = new ValidationStrategyDto();

	public OtpLoginService(AmazonDynamoDB pDb, Context pContext) {
		context = pContext;
		db = pDb;
		mapper = new DynamoDBMapper(db);
	}

	/*
	 * public String sendOtpCustomerBnpl(CustomerDto pCustomer) {
	 * 
	 * context.getLogger().log("CustomerBNPL to find:" + pCustomer.toString());
	 * 
	 * HashMap<String, AttributeValue> eav = new HashMap<>();
	 * eav.put(":identificacion", new
	 * AttributeValue().withS(pCustomer.getIdentificacion()));
	 * eav.put(":tipoIdentificacion", new
	 * AttributeValue().withS(pCustomer.getTipoIdentificacion()));
	 * 
	 * DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
	 * .withFilterExpression("tipoIdentificacion=:tipoIdentificacion and identificacion=:identificacion"
	 * ) .withExpressionAttributeValues(eav);
	 * 
	 * DynamoDBMapper mapper = new DynamoDBMapper(db); List<CustomerBnpl>
	 * wListSalesPersons = mapper.scan(CustomerBnpl.class, scanExpression);
	 * context.getLogger().log("CustomerBnpl list:" + wListSalesPersons.size());
	 * 
	 * if (!wListSalesPersons.isEmpty()) { return sendOTP(wListSalesPersons.get(0),
	 * context); }
	 * 
	 * return SUCCESS;
	 * 
	 * }
	 */

	public AuthData getAuthorizationData(String pId, Context pContext) {

		AuthData data = mapper.load(AuthData.class, pId);
		pContext.getLogger().log(pId);
		return data;

	}

	public UserCredentials getUserCredentials(String pId, Context pContext) {
		pContext.getLogger().log("getUserCredentials: " + pId);
		UserCredentials wUserCredentials = mapper.load(UserCredentials.class, pId);
		if (wUserCredentials != null) {
			pContext.getLogger().log("Result getUserCredentials: " + wUserCredentials.toString());
		}
		return wUserCredentials;
	}

	public Profile getUserProfile(String pId, Context pContext) {
		pContext.getLogger().log("getUser Profile: " + pId);
		Profile wUserCredentials = mapper.load(Profile.class, pId);
		pContext.getLogger().log("Result DB: " + pId);
		if (wUserCredentials != null) {
			pContext.getLogger().log("Result get profile: " + wUserCredentials.toString());
		}
		return wUserCredentials;
	}

	public ValidationStrategyDto sendOtpCustomer(CustomerDto pCustomer, Context pContext) {

		String type; 
		if (TYPE_OTP.contentEquals("1")) {
			type = "SEND_EMAIL";
		} else if (TYPE_OTP.contentEquals("2")) {
			type = "SEND_MESSAGE";
		} else {
			type = "SEND_EMAIL_SMS";
		}

		BodyMessage body=new BodyMessage(); 
		body.setAction(type);
		body.setChannel(8);
		body.setNumberId(pCustomer.getIdentificacion());

		

		try {
			String wQuestionarie= sendMsn(context,body); 

			if (wQuestionarie.equals("ok")) {
				wValStrategy.setValidationStrategy(SUCCESS);
				AuthData wAuthData = getAuthorizationData(pCustomer.getIdentificacion(), pContext);
				wValStrategy.setName(wAuthData.getNames());
				wValStrategy.setPhone(wAuthData.getCellphone().substring(wAuthData.getCellphone().length() - 4,
						wAuthData.getCellphone().length()));
				wValStrategy.setMail(wAuthData.getEmail().substring(wAuthData.getEmail().length() - 12,
						wAuthData.getEmail().length()));

			} else {
				wValStrategy.setValidationStrategy(UP_T0_DATE);

			}

		} catch (Exception e) {
			pContext.getLogger().log(e.getMessage());
			wValStrategy.setValidationStrategy(UP_T0_DATE);

		}

		return wValStrategy;

	}

	public ValidationStrategyDto sendOtpUser(CustomerDto pCustomer, Context pContext) {

		pContext.getLogger().log(pCustomer.toString());
		UserCredentials wUserCredentials = getUserCredentials(pCustomer.getIdentificacion(), pContext);
		String type; 
		if (TYPE_OTP.contentEquals("1")) {
			type = "SEND_EMAIL";
		} else if (TYPE_OTP.contentEquals("2")) {
			type = "SEND_MESSAGE";
		} else {
			type = "SEND_EMAIL_SMS";
		}

		if (wUserCredentials != null) {
			pContext.getLogger().log("User found: " + wUserCredentials.getId());
			if (wUserCredentials.getStatus().equals("1")) {

				BodyMessage body=new BodyMessage(); 
				body.setAction(type);
				body.setCellphone(String.valueOf(wUserCredentials.getCellphone()));
				body.setEmail(wUserCredentials.getMail());
				body.setChannel(9);
				body.setNumberId(pCustomer.getIdentificacion());
				
			    String  result= sendMsn(context,body);  
			    
				if (result.equals("ok")) {
					wValStrategy.setValidationStrategy(SUCCESS);

					wValStrategy.setName(wUserCredentials.getUserName() + " " + wUserCredentials.getUserLastName());
					wValStrategy.setUrl(wUserCredentials.getClient());
					wValStrategy.setPhone(wUserCredentials.getCellphone().toString().substring(
							wUserCredentials.getCellphone().toString().length() - 4,
							wUserCredentials.getCellphone().toString().length()));
					wValStrategy.setMail(wUserCredentials.getMail().substring(wUserCredentials.getMail().length() - 12,
							wUserCredentials.getMail().length()));
					wValStrategy.setValidationStrategy(SUCCESS);
				} else {
					wValStrategy.setValidationStrategy(UP_T0_DATE);
				}
			} else {
				pContext.getLogger().log("user inactive: " + pCustomer.getIdentificacion());
				wValStrategy.setValidationStrategy(UP_T0_DATE);
			}

		} else {
			pContext.getLogger().log("user not found: " + pCustomer.getIdentificacion());
			wValStrategy.setValidationStrategy(UP_T0_DATE);
		}

		return wValStrategy;

	}

	public ValidationStrategyDto validateOtpAndGetCreditsClient(CustomerDto pCustomer, Context pContext) {
		pContext.getLogger().log("Enters OTP client: " + pCustomer.getIdentificacion());
		validateOtpApr(pCustomer, pContext);
		if (wValStrategy.getValidationStrategy().equals(SUCCESS)) {
			pContext.getLogger().log(" OTP validated client: " + pCustomer.getIdentificacion());

			getClientOtpToken(pCustomer, pContext);
			pContext.getLogger().log("Search client credits: " + pCustomer.getIdentificacion());

			try {
				List<CreditDto> wLstCredits = new ClientCreditsService(db, pContext).getClientCredits(pCustomer);
				context.getLogger().log("Cantidad de Creditos: " + wLstCredits.size() + ".");
				context.getLogger().log("Creditos: " + new Gson().toJson(wLstCredits) + ".");
				Gson wGson = new Gson();
				String wLstCreditsJson = wGson.toJson(wLstCredits);	
		        Type targetListType = new TypeToken<List<Credit>>(){}.getType();
		        // Convert the JSON string to a list of TargetClass objects
		        List<Credit> targetList = wGson.fromJson(wLstCreditsJson, targetListType);
				
				wValStrategy.setCredits(targetList);
			} catch (Exception e) {
				pContext.getLogger().log(" Error: " + e.getMessage());
			}

		}

		return wValStrategy;
	}

	public ValidationStrategyDto validateOtpAndUserGetUserPermission(CustomerDto pCustomer, Context pContext) {
		pContext.getLogger().log("Enters OTP user: " + pCustomer.getIdentificacion());
		validateOtpApr(pCustomer, pContext);
		List<String> access = new ArrayList<String>();
		if (wValStrategy.getValidationStrategy().equals(SUCCESS)) {
			pContext.getLogger().log(" OTP validated user: " + pCustomer.getIdentificacion());

			getClientOtpToken(pCustomer, pContext);
			pContext.getLogger().log("Search user access: " + pCustomer.getIdentificacion());

			UserCredentials wUserCredentials = getUserCredentials(pCustomer.getIdentificacion(), pContext);
			pContext.getLogger().log("user access: " + pCustomer.getIdentificacion());

			
			Profile wProfile = new Profile();
			if (!wUserCredentials.getProfile().isEmpty() && !wUserCredentials.getClient().contains("APPR") ) {
				wProfile = getUserProfile(wUserCredentials.getProfile(), pContext);
				if (wProfile != null) {
					pContext.getLogger().log("Search user access: " + new Gson().toJson(wProfile.getAccess()));
					access=wProfile.getAccess();
				}
			} else {
				pContext.getLogger().log("No profile. ");
				if(!wUserCredentials.getClient().contains("APPR")) {
					wValStrategy.setValidationStrategy("NO_AUTHORIZATION");
				}
			}

			pContext.getLogger().log("user access: " + pCustomer.getIdentificacion());

			wValStrategy.setAccessList(access);
		}

		return wValStrategy;
	}
	
	public ValidationStrategyDto validateOtpUserApr(CustomerDto pCustomer, Context pContext) {
		pContext.getLogger().log("Enters OTP user: " + pCustomer.getIdentificacion());
		validateOtpApr(pCustomer, pContext);
		List<String> access = new ArrayList<String>();
		if (wValStrategy.getValidationStrategy().equals(SUCCESS)) {
			pContext.getLogger().log(" OTP validated user: " + pCustomer.getIdentificacion());

			getClientOtpToken(pCustomer, pContext);
			pContext.getLogger().log("Search user access: " + pCustomer.getIdentificacion());

			UserCredentials wUserCredentials = getUserCredentials(pCustomer.getIdentificacion(), pContext);
			pContext.getLogger().log("user access: " + pCustomer.getIdentificacion());

			
			Profile wProfile = new Profile();
			if (!wUserCredentials.getProfile().isEmpty() && wUserCredentials.getClient().equals("APPROBE")){
				wProfile = getUserProfile(wUserCredentials.getProfile(), pContext);
				if (wProfile != null) {
					pContext.getLogger().log("Search user access: " + new Gson().toJson(wProfile.getAccess()));
					access=wProfile.getAccess();
				}
			} else {
				pContext.getLogger().log("No profile. ");
				if(wUserCredentials.getClient().equals("APPROBE")) {
					wValStrategy.setValidationStrategy("NO_AUTHORIZATION");
				}
			}

			pContext.getLogger().log("user access: " + pCustomer.getIdentificacion());

			wValStrategy.setAccessList(access);
		}

		return wValStrategy;
	}

	private ClientOtpToken getClientOtpToken(CustomerDto pCustomer, Context pContext) {

		pContext.getLogger().log("Ask token cognito");

		CognitoService wCognitoService = new CognitoService(clientAws, db, mapper);
		DataCognitoDto dataCustomer = new DataCognitoDto();

		dataCustomer.setEmail(EMAIL);
		dataCustomer.setPassword(PASS_EMAIL);
		pContext.getLogger().log("Email: " + EMAIL + " Pass: " + PASS_EMAIL);
		ResponseCognitoDto wResponsecognito = wCognitoService.login(dataCustomer, context);
		pContext.getLogger().log("Login Cognito" + wResponsecognito);

		LocalDateTime currentDate = LocalDateTime.now();

		// Format the current date (optional)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
		String formattedDate = currentDate.format(formatter);

		pContext.getLogger().log("Fecha: " + formattedDate);

		ClientOtpToken wClientOtpToken = new ClientOtpToken();
		wClientOtpToken.setId(pCustomer.getIdentificacion() + formattedDate);
		wClientOtpToken.setIdentificacion(pCustomer.getIdentificacion());
		wClientOtpToken.setToken(wResponsecognito.getAccessToken());
		wClientOtpToken.setFecha(formattedDate);
		wValStrategy.setToken(wResponsecognito.getAccessToken());

		mapper.save(wClientOtpToken);
		return wClientOtpToken;
	}

	public ValidationStrategyDto validateOtp(CustomerDto pCustomer, Context pContext) {

		OtpDto wOtpDto = new OtpDto("validarOtp", pCustomer.getIdentificacion(), null, pCustomer.getOtp());
		String wRequestBody = wOtpDto.toString();
		pContext.getLogger().log("RequestBody otp: " + wRequestBody);
		HttpRequest requestReconocerAndEvidente = HttpRequest.newBuilder().uri(URI.create(URL_AWS + "autentic"))
				.setHeader(contentType, applicationJson).POST(HttpRequest.BodyPublishers.ofString(wRequestBody))
				.build();

		try {
			response = client.send(requestReconocerAndEvidente, HttpResponse.BodyHandlers.ofString());
			String wResponse = response.body().replace("\\", "");
			wResponse = wResponse.substring(1, wResponse.length() - 1);

			pContext.getLogger().log("Respuesta validacion Otp " + wResponse);

			OtpValidationDto wOtpValidation = new Gson().fromJson(wResponse, OtpValidationDto.class);

			if (wOtpValidation.getCode() == 200 && wOtpValidation.getMsj().equals("validated")) {

				pContext.getLogger().log("Otp was validated" + wResponse);
				wValStrategy.setValidationStrategy(SUCCESS);
			} else {
				wValStrategy.setValidationStrategy(ERROR);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			wValStrategy.setValidationStrategy(ERROR);
		}

		return wValStrategy;
	}
	
	public ValidationStrategyDto validateOtpApr(CustomerDto pCustomer, Context pContext) {

		context.getLogger().log("Entra a validar otp de approbe");

		BodyMessage body=new BodyMessage(); 
		body.setAction("VALIDATION_OTP");
		body.setOtp(pCustomer.getOtp());
		body.setNumberId(pCustomer.getIdentificacion());

		String wOtpValidation=sendMsn(pContext,body);  
		wOtpValidation=(wOtpValidation.equals("ok"))?"validated":wOtpValidation;

		if (wOtpValidation.equals("validated")) {
			pContext.getLogger().log("Otp was validated");
			wValStrategy.setValidationStrategy(SUCCESS);
		} else {
			wValStrategy.setValidationStrategy(ERROR);
		}

		return wValStrategy;
	}
	
	
	public String sendMsn(Context context, BodyMessage body) {
		
		AWSLambda lambdaClient = AWSLambdaClientBuilder.defaultClient();
		context.getLogger().log("Entra para enviar para comunicar con envio de mensajes");
		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = "";
		String response="ok";
		try {
			jsonPayload = mapper.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			context.getLogger().log(" -- Body para sendEmail: " + body);
			InvokeRequest invokeRequest = new InvokeRequest().withFunctionName("sendAwsSns").withPayload(jsonPayload);
			InvokeResult invokeResult = lambdaClient.invoke(invokeRequest);
			String responsePayload = StandardCharsets.UTF_8.decode(invokeResult.getPayload()).toString();
			context.getLogger().log("Respuesta: "+responsePayload);
			
			if(responsePayload.contains("ERROR")) {  
				response="FAIL";
			}

		} catch (Exception e) {
			context.getLogger().log(" ENVIO FALLA : " + e);
			response="FAIL";
		}
		
		return  response; 
	}
	
	
	
}
