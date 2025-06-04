package co.approbe.clientcredits.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import co.approbe.commons.dto.CashAllocationDto;
import co.approbe.commons.dto.CashPaymentDto;
import co.approbe.commons.dto.PaymentSplitDto;
import co.approbe.commons.dto.ValidationStrategyDto;
import co.approbe.commons.model.CashPayment;
import co.approbe.commons.model.CashPaymentSplit;
import co.approbe.commons.model.ErrorCashPayment;
import co.approbe.commons.model.UserCredentials;
import co.approbe.core.dto.CoreAdapterDto;
import co.approbe.core.dto.PaymentAllocationDto;
import co.approbe.core.dto.PaymentDto;
import co.approbe.core.service.CreditService;

public class PaymentService {

	private AmazonDynamoDB db;
	private Context context;
	private DynamoDBMapper mapper;

	private static final String CASHEER_ERROR = "CASHEER_ERROR";
	private static final String CASHEER_INACTIVE = "CASHEER_INACTIVE";
	private static final String PAYMENT_ERROR = "PAYMENT_ERROR";
	private static final String SUCCESS = "SUCCESS";
	private static final String ERROR_GCP = "ERROR_GCP";
	ValidationStrategyDto validationStrategyDto;

	/*
	 * AWSCredentials cred = new BasicAWSCredentials("AKIA5SBAE4XKWXTF3K67",
	 * "oIoJ8aKDOyUD5VysGXCLMfgIMH+VSx2/MBfifhli");// dev AWSLambda wLambdaClient =
	 * AWSLambdaClientBuilder.standard().withCredentials(new
	 * AWSStaticCredentialsProvider(cred)) .withRegion(Regions.US_EAST_1).build();
	 */

	AWSLambda wLambdaClient = AWSLambdaClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

	public PaymentService(AmazonDynamoDB pDb, Context pContext) {
		context = pContext;
		db = pDb;
		mapper = new DynamoDBMapper(db);
	}

	public ValidationStrategyDto setCashPayment(CashPaymentDto pCashPaymentDto) {
		validationStrategyDto = new ValidationStrategyDto();
		context.getLogger().log("CashPayment Object " + new Gson().toJson(pCashPaymentDto));

		BackOfficeService wGCPDatabaseConnection = new BackOfficeService(db, context);

		UserCredentials wCred = new UserCredentialsService(db, context)
				.getUserCredentials(pCashPaymentDto.getCasheer().getId(), context);
		pCashPaymentDto.setCasheer(wCred);
		context.getLogger().log("User Credentials" + new Gson().toJson(wCred));
		context.getLogger().log("Client casheer "+ pCashPaymentDto.getCasheer().getClient());
		

		ZonedDateTime wCurrentDateTime = ZonedDateTime.now(ZoneId.of("America/Bogota"));
		DateTimeFormatter wFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
		String wFormattedDateTime = wCurrentDateTime.format(wFormatter);

		if (wCred == null) {
			validationStrategyDto.setValidationStrategy(CASHEER_ERROR);
			setErrorCashPayment(pCashPaymentDto, CASHEER_ERROR, wFormattedDateTime);
		} else if (wCred.getStatus().equals("1")) {
			context.getLogger().log("Casheer is active");
			// create payment in core
			List<PaymentAllocationDto> wLstPayments = new ArrayList<>();

			for (Iterator<CashAllocationDto> iterator = pCashPaymentDto.getwLstCredits().iterator(); iterator
					.hasNext();) {
				CashAllocationDto wCashAllocationDto = iterator.next();
				wLstPayments.add(
						new PaymentAllocationDto(wCashAllocationDto.getAmmount(), wCashAllocationDto.getIdCredit()));
			}

			PaymentDto wPayment = new PaymentDto(pCashPaymentDto.getTotalAmmount(), wFormattedDateTime,
					"default_payment_config", pCashPaymentDto.getCasheer().getId() + wFormattedDateTime);

			CoreAdapterDto wCoreAdapter = new CoreAdapterDto(wFormattedDateTime, null, wPayment,
					pCashPaymentDto.getCustomer(), null, wLstPayments);

			CreditService wCreditService = new CreditService(db, context);
			context.getLogger().log("Payment is going to be excuted");
			// creates payment in core
			PaymentDto wLoan = wCreditService.setPayment(wCoreAdapter);

			context.getLogger().log("Payment was excuted");

			DateTimeFormatter wFormatterShort = DateTimeFormatter.ofPattern("yyyyMMdd");
			String wFormattedDateTimeShort = wCurrentDateTime.format(wFormatterShort);

			if (wLoan == null) {
				validationStrategyDto.setValidationStrategy(PAYMENT_ERROR);
				context.getLogger().log(PAYMENT_ERROR);
				// unsuccessful payments create in log table
				setErrorCashPayment(pCashPaymentDto, PAYMENT_ERROR, wFormattedDateTime);

			} else {
				context.getLogger().log("Before creating cashpayment");
				// create successful payments in database associated to user and partner
				CashPayment wCashPayment = new CashPayment(pCashPaymentDto.getCasheer().getId() + wFormattedDateTime,
						pCashPaymentDto.getCasheer().getId(), pCashPaymentDto.getCustomer(),
						pCashPaymentDto.getTotalAmmount(), new Gson().toJson(pCashPaymentDto.getwLstCredits()),
						wFormattedDateTime, wLoan.getKordev_id(), Integer.valueOf(wFormattedDateTimeShort),
						pCashPaymentDto.getCasheer().getClient());

				mapper.save(wCashPayment);
				context.getLogger().log("Saved in dynamo cashpayment");
				wGCPDatabaseConnection.saveCashCollection(wCashPayment, context);
				context.getLogger().log("Saved in gcp cashpayment");
				validationStrategyDto.setId(wLoan.getKordev_id());
				validationStrategyDto.setFechaCreacion(wFormattedDateTime);
				validationStrategyDto.setValidationStrategy(SUCCESS);

				// for successful payments call payment split
				context.getLogger().log("start generatin split");
				for (Iterator<CashAllocationDto> it = pCashPaymentDto.getwLstCredits().iterator(); it.hasNext();) {
					CashAllocationDto wCashAllocationDto = it.next();

					PaymentSplitDto wPaymentSplit = new PaymentSplitDto(wCashAllocationDto.getIdCredit(),
							wCashAllocationDto.getAmmount());

					// AWSLambda lambdaClient = AWSLambdaClientBuilder.defaultClient();
					String wRequest = new Gson().toJson(wPaymentSplit);
					InvokeRequest invokeRequest = new InvokeRequest().withFunctionName("paymentCreateEcollect")
							.withPayload(wRequest);
					context.getLogger().log("Payment split " + wRequest);
					InvokeResult invokeResult = wLambdaClient.invoke(invokeRequest);
					String wResultSplit = new String(invokeResult.getPayload().array());
					context.getLogger().log("Payment split " + wResultSplit);

					// for successful payments create dispersion payment based on payment

					CashPaymentSplit wCashPaymentSplit = new Gson().fromJson(wResultSplit, CashPaymentSplit.class);
					wCashPaymentSplit.setCashPaymentDto(pCashPaymentDto);
					wCashPaymentSplit.setId(wLoan.getKordev_id() + wCashAllocationDto.getIdCredit());
					wCashPaymentSplit.setClient(wCred.getClient());
					wCashPaymentSplit.setDateHourPayment(wFormattedDateTime);
					wCashPaymentSplit.setDatePayment(Integer.valueOf(wFormattedDateTimeShort));
					wCashPaymentSplit.setPaymentId(wLoan.getKordev_id());
					wCashPaymentSplit.setTotalAmmount(wCashAllocationDto.getAmmount());
					wCashPaymentSplit.setIdCredit(wCashAllocationDto.getIdCredit());
					wCashPaymentSplit.setCustomer(pCashPaymentDto.getCustomer());
					mapper.save(wCashPaymentSplit);
					//if (!wGCPDatabaseConnection.saveCashCollectionSplit(wCashPaymentSplit, context)) {
					//	setErrorCashPayment(pCashPaymentDto, ERROR_GCP, wFormattedDateTime);
					//}
				}

			}

		} else {
			validationStrategyDto.setValidationStrategy(CASHEER_INACTIVE);
			setErrorCashPayment(pCashPaymentDto, CASHEER_INACTIVE, wFormattedDateTime);

		}

		return validationStrategyDto;
	}

	public void setErrorCashPayment(CashPaymentDto pCashPaymentDto, String pMessage, String pDate) {
		// unsuccessful payments create in log table
		context.getLogger().log("Payment error " + pMessage + " " + new Gson().toJson(pCashPaymentDto));
		ErrorCashPayment wErrorCashPayment = new ErrorCashPayment(pCashPaymentDto.getCasheer().getId() + pDate,
				new Gson().toJson(pCashPaymentDto), pDate, pMessage);
		mapper.save(wErrorCashPayment);
	}

}
