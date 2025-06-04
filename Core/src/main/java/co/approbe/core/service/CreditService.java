package co.approbe.core.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import co.approbe.commons.dto.AES256;
import co.approbe.commons.model.Credit;
import co.approbe.core.dto.BorrowerStatusDto;
import co.approbe.core.dto.CoreAdapterDto;
import co.approbe.core.dto.CreditDto;
import co.approbe.core.dto.LoanDto;
import co.approbe.core.dto.LoanStatusDto;
import co.approbe.core.dto.Loan_allocation_dto;
import co.approbe.core.dto.PaymentDto;
import co.approbe.core.dto.PaymentsClientCredit;
import co.approbe.core.dto.PaymentsDto;
import co.approbe.core.dto.TokenCoreDto;
import co.approbe.core.model.TokenCore;

public class CreditService {

	private AmazonDynamoDB db;
	private Context context;
	private DynamoDBMapper mapper;

	AES256 aes256 = new AES256();

	public static final String KEY_SPEC = "AES";
	public static final String CIPHER_MODE = "AES/CBC/NoPadding";

	private static String contentType = "Content-Type";
	private static String applicationJson = "application/json";
	private HttpResponse<String> response;
	private HttpClient client = HttpClient.newHttpClient();

	public static final String URL_CORE = System.getenv("URL_CORE") == null ? "" : System.getenv("URL_CORE");
	public static final String CLIENT_ID = System.getenv("CLIENT_ID") == null ? "" : System.getenv("CLIENT_ID");
	public static final String SECRET_ID = System.getenv("SECRET_ID") == null ? "" : System.getenv("SECRET_ID");
	public static final String AUDIENCE = System.getenv("AUDIENCE") == null ? "" : System.getenv("AUDIENCE");
	public static final String URL_CORE_TOKEN = System.getenv("URL_CORE_TOKEN") == null ? ""
			: System.getenv("URL_CORE_TOKEN");

//	public static final String AUDIENCE = "https://sandbox.lms.kordev.io";
//	public static final String CLIENT_ID = "twMV6kgIwJItbGgHlPUMQTpTfNGgngcD";
//	public static final String SECRET_ID = "F4Vyj_10cNoOr1Mw2A2DSUCTVZJhdWFqLOuKgUJPS6ruRJM65c8S6ZKW2YYV_JTi";
//	public static final String URL_CORE_TOKEN = "https://auth.kordev.io";
//	public static final String URL_CORE = "https://sandbox.lms.kordev.io/";

	public static final String ENCRYPT_KEY = "approbe!199212220C2AA637DB782APR";

	public CreditService(AmazonDynamoDB pDb, Context pContext) {
		context = pContext;
		db = pDb;
		mapper = new DynamoDBMapper(db);
	}

	public TokenCore setCoreAuthToken() {

		TokenCore wTokenCore = null;

		TokenCoreDto wOtpDto = new TokenCoreDto(CLIENT_ID, SECRET_ID, AUDIENCE);
		String wRequestBody = wOtpDto.toString();
		context.getLogger().log("RequestBody token: " + URL_CORE_TOKEN);
		context.getLogger().log("url: " + wRequestBody);
		String wResponse;
		HttpRequest wRequestToken = HttpRequest.newBuilder().uri(URI.create("https://auth.kordev.io" + "/oauth/token"))
				.setHeader(contentType, applicationJson).POST(HttpRequest.BodyPublishers.ofString(wRequestBody))
				.build();

		try {

			response = client.send(wRequestToken, HttpResponse.BodyHandlers.ofString());
			wResponse = response.body();
			context.getLogger().log("Respuesta token :" + wResponse);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			LocalDateTime currentDate = LocalDateTime.now();
			String formattedDate = currentDate.format(formatter);

			LocalDateTime currentDateShort = LocalDateTime.now();
			DateTimeFormatter formatterShort = DateTimeFormatter.ofPattern("yyyyMMdd");
			String formattedDateShort = currentDateShort.format(formatterShort);

			context.getLogger().log("Fecha: " + formattedDate);

			wTokenCore = new Gson().fromJson(wResponse, TokenCore.class);
			wTokenCore.setId(Long.valueOf(formattedDate));
			wTokenCore.setDateToken(formattedDateShort);

			mapper.save(wTokenCore);

		} catch (IOException e) {
			context.getLogger().log("error generando token :" + e.getStackTrace());
			e.printStackTrace();
		} catch (InterruptedException e) {
			context.getLogger().log("error generando token :" + e.getStackTrace());
			e.printStackTrace();
		}
		return wTokenCore;

	}

	public TokenCore getTokenCore() {

		List<TokenCore> wLstTokenConverted = new ArrayList<TokenCore>();

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime currentDateShort = now.minus(1, ChronoUnit.DAYS);
		DateTimeFormatter formatterShort = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String formattedDateShort = currentDateShort.format(formatterShort);
		List<TokenCore> wListTokenCore;

		context.getLogger().log("tokencore id:" + formatterShort);

		HashMap<String, AttributeValue> eav = new HashMap<>();
		eav.put(":value", new AttributeValue().withN(formattedDateShort));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression(" id > :value")
				.withExpressionAttributeValues(eav);
		wListTokenCore = mapper.scan(TokenCore.class, scanExpression);
		context.getLogger().log("tokencore list:" + wListTokenCore.size());

		for (TokenCore tokenCore : wListTokenCore) {
			wLstTokenConverted
					.add(new TokenCore(tokenCore.getId(), tokenCore.getAccessToken(), tokenCore.getDateToken()));
		}

		if (!wLstTokenConverted.isEmpty()) {
			Collections.sort(wLstTokenConverted, Comparator.comparingLong(TokenCore::getId).reversed());
			context.getLogger().log(
					"ultimo:" + wLstTokenConverted.get(0).getId() + " - " + wLstTokenConverted.get(0).getAccessToken());
			return wLstTokenConverted.get(0);
		} else {
			return setCoreAuthToken();
		}

	}

	public LoanDto setCredit(CoreAdapterDto pCoreAdapterDto) {

		LoanDto wLoan = null;
		String wResponse;
		pCoreAdapterDto.setIdBorrower(getEncrypedData(pCoreAdapterDto.getIdBorrower(), context));
		String wRequestBody = pCoreAdapterDto.toString();

		context.getLogger().log("Create credit in core:" + wRequestBody);

		context.getLogger().log("Json:" + new Gson().toJson(pCoreAdapterDto));

		String wUrl = URL_CORE + "v1/borrowers/" + pCoreAdapterDto.getIdBorrower() + "/loans/"
				+ pCoreAdapterDto.getIdCredit() + "/create";
		context.getLogger().log("URL:" + wUrl);
		HttpRequest wRequestCredit = HttpRequest.newBuilder().uri(URI.create(wUrl))
				.setHeader(contentType, applicationJson)
				.header("Authorization", "Bearer " + getTokenCore().getAccessToken())
				.POST(HttpRequest.BodyPublishers.ofString(wRequestBody)).build();

		try {

			response = client.send(wRequestCredit, HttpResponse.BodyHandlers.ofString());
			wResponse = response.body();
			context.getLogger().log("Answer create credit in core:" + wResponse);
			wLoan = new Gson().fromJson(wResponse, LoanDto.class);

		} catch (Exception e) {
			context.getLogger().log("error creating credit:" + e.getStackTrace());
			e.printStackTrace();
		}
		return wLoan;

	}

	public PaymentDto setPayment(CoreAdapterDto pCoreAdapterDto) {
		PaymentDto wLoan = null;
		String wResponse;
		pCoreAdapterDto.setIdBorrower(getEncrypedData(pCoreAdapterDto.getIdBorrower(), context));
		String wRequestBody = pCoreAdapterDto.toStringPayment();

		context.getLogger().log("Create payment in core:" + wRequestBody);

		context.getLogger().log("Json:" + new Gson().toJson(pCoreAdapterDto));

		String wUrl = URL_CORE + "v1/borrowers/payments/create";
		HttpRequest wRequestCredit = HttpRequest.newBuilder().uri(URI.create(wUrl))
				.setHeader(contentType, applicationJson)
				.header("Authorization", "Bearer " + getTokenCore().getAccessToken())
				.POST(HttpRequest.BodyPublishers.ofString(wRequestBody)).build();

		try {

			response = client.send(wRequestCredit, HttpResponse.BodyHandlers.ofString());
			wResponse = response.body();
			context.getLogger().log("Answer create payment in core:" + wResponse);
			wResponse = wResponse.substring(14);
			wResponse = wResponse.substring(0, wResponse.length() - 15);
			context.getLogger().log("Answer short create payment in core:" + wResponse);

			wLoan = new Gson().fromJson(wResponse, PaymentDto.class);
			context.getLogger().log("Kordev Payment id:" + wLoan.getKordev_id());

		} catch (Exception e) {
			context.getLogger()
					.log("Payment couldn't be created in core:" + wRequestBody + " Reason: " + response.body());
		}
		return wLoan;
	}

	public List<CreditDto> getBorrowerStatus(CoreAdapterDto pCoreAdapterDto) {
		context.getLogger().log("Entra a buscar el cliente");
		String wResponse;
		String id;
		double saldoCredito, nextPaid, principalM, principalD, principal, interest, deliquency_interest,
				installment_fee, late_fee, unpaid;
		String dateNextPaid;
		Integer feesPaid;
		String configId;
		int simpleDaysPastDue;
		double totalIntallmetFee;
		String comercio;
		double ammountFinace;
		double fees;
		boolean cancel;

		pCoreAdapterDto.setIdBorrower(getEncrypedData(pCoreAdapterDto.getIdBorrower(), context));
		String wRequestBody = pCoreAdapterDto.toStringPayment();
		List<CreditDto> wLstCredits = new ArrayList<>();

		context.getLogger().log("get borrower status from core:" + wRequestBody);

		context.getLogger().log("Json:" + new Gson().toJson(pCoreAdapterDto));
		String wUrl = URL_CORE + "v1/borrowers/" + pCoreAdapterDto.getIdBorrower() + "/get_status";
		context.getLogger().log("url de consulta" + wUrl);
		HttpRequest wRequestCredit = HttpRequest.newBuilder().uri(URI.create(wUrl))
				.setHeader(contentType, applicationJson)
				.header("Authorization", "Bearer " + getTokenCore().getAccessToken()).GET().build();

		try {

			response = client.send(wRequestCredit, HttpResponse.BodyHandlers.ofString());
			wResponse = response.body();
			context.getLogger().log("Answer get borrower status:" + wResponse);
			BorrowerStatusDto wBorrowerStatus = new Gson().fromJson(wResponse, BorrowerStatusDto.class);
			context.getLogger().log(wBorrowerStatus.toString());
			context.getLogger().log("Tamaño: " + wBorrowerStatus.getLoans().size());

			if (wBorrowerStatus.getLoans() != null) {
				for (Iterator<LoanStatusDto> i = wBorrowerStatus.getLoans().iterator(); i.hasNext();) {
					LoanStatusDto wLoanStatusDto = i.next();
					List<PaymentsClientCredit> payments = new ArrayList<>();
					id = (wLoanStatusDto.getAttributes().getId() == null) ? " "
							: wLoanStatusDto.getAttributes().getId();
					saldoCredito = (wLoanStatusDto.getSummary().getFull_payment().getAmount() == null) ? 0
							: wLoanStatusDto.getSummary().getFull_payment().getAmount();
					context.getLogger().log("id: " + id);

					feesPaid = (wLoanStatusDto.getSummary().getNumber_of_covered_installments() == null) ? 0
							: wLoanStatusDto.getSummary().getNumber_of_covered_installments();
					configId = (wLoanStatusDto.getAttributes().getConfig_id() == null) ? " "
							: wLoanStatusDto.getAttributes().getConfig_id();
					simpleDaysPastDue = (wLoanStatusDto.getSummary().getSimple_days_past_due() == null) ? 0
							: wLoanStatusDto.getSummary().getSimple_days_past_due();

					principalD = (wLoanStatusDto.getBalance_detail().getAt_calculation_date() == null
							|| wLoanStatusDto.getBalance_detail().getAt_calculation_date().getPrincipal() == null) ? 0
									: wLoanStatusDto.getBalance_detail().getAt_calculation_date().getPrincipal()
											.getCurrent();

					principalM = (wLoanStatusDto.getSummary().getImmediate_payment() == null
							|| wLoanStatusDto.getSummary().getImmediate_payment().getBreakdown_by_bucket() == null) ? 0
									: wLoanStatusDto.getSummary().getImmediate_payment().getBreakdown_by_bucket()
											.getPrincipal();
					
					principal = (simpleDaysPastDue > 0) ? principalM : principalD;

					interest = (wLoanStatusDto.getSummary().getFull_payment() == null
							|| wLoanStatusDto.getSummary().getFull_payment().getBreakdown_by_bucket() == null) ? 0
									: wLoanStatusDto.getSummary().getFull_payment().getBreakdown_by_bucket()
											.getInterest();
					late_fee = (wLoanStatusDto.getSummary().getFull_payment() == null
							|| wLoanStatusDto.getSummary().getFull_payment().getBreakdown_by_bucket() == null) ? 0
									: wLoanStatusDto.getSummary().getFull_payment().getBreakdown_by_bucket()
											.getLate_fee();
					deliquency_interest = (wLoanStatusDto.getSummary().getFull_payment() == null
							|| wLoanStatusDto.getSummary().getFull_payment().getBreakdown_by_bucket() == null) ? 0
									: wLoanStatusDto.getSummary().getFull_payment().getBreakdown_by_bucket()
											.getDelinquency_interest();
					installment_fee = (wLoanStatusDto.getBalance_detail().getAt_calculation_date() == null
							|| wLoanStatusDto.getBalance_detail().getAt_calculation_date().getInstallment_fee() == null)
									? 0
									: wLoanStatusDto.getBalance_detail().getAt_calculation_date().getInstallment_fee()
											.getCurrent();
					unpaid = (wLoanStatusDto.getBalance_detail().getAt_calculation_date() == null
							|| wLoanStatusDto.getBalance_detail().getAt_calculation_date().getPrincipal() == null) ? 0
									: wLoanStatusDto.getBalance_detail().getAt_calculation_date().getPrincipal()
											.getUnpaid();

					nextPaid = Math.ceil(wLoanStatusDto.getSummary().getNextPaid(simpleDaysPastDue) / 100.0) * 100.0;
					dateNextPaid = wLoanStatusDto.getSummary().getNextFeeDate(simpleDaysPastDue);
					payments = payments(wBorrowerStatus, id);
					totalIntallmetFee = (wLoanStatusDto.getBalance_detail().getAt_calculation_date() == null
							|| wLoanStatusDto.getBalance_detail().getAt_calculation_date().getInstallment_fee() == null)
									? 0
									: wLoanStatusDto.getBalance_detail().getAt_calculation_date().getInstallment_fee()
											.getUnpaid();

					ammountFinace = (wLoanStatusDto.getAttributes() == null) ? 0
							: wLoanStatusDto.getAttributes().getAmount_financed();
					cancel = (wLoanStatusDto.getAttributes() == null) ? true
							: wLoanStatusDto.getAttributes().isIs_cancelled();

					Credit credit = searchClient(id);
					comercio = credit.getClient();
					fees = credit.getFeesApprobe();

					CreditDto wCredit = new CreditDto(id, saldoCredito, nextPaid, dateNextPaid, feesPaid, configId,
							simpleDaysPastDue, principal, interest, deliquency_interest, installment_fee,
							totalIntallmetFee, late_fee, unpaid,
							wLoanStatusDto.getSummary().getNumber_of_covered_installments(), ammountFinace, cancel,
							comercio, wLoanStatusDto.getAttributes().getOrigination_date(), fees, payments);
					wLstCredits.add(wCredit);
				}
			}

			context.getLogger().log("Creditos que se envian al front: " + new Gson().toJson(wLstCredits));
			context.getLogger().log("tamañp Creditos que se envian al front: " + wLstCredits.size());
			context.getLogger().log("Creditos: " + wLstCredits.toString());

		} catch (Exception e) {
			context.getLogger().log("Error starus:" + wRequestBody + " Reason: " + response.body());
		}
		context.getLogger().log(wLstCredits.toString());
		return wLstCredits;

	}

	public String getEncrypedData(String pIdBorrower, Context pContext) {

		pContext.getLogger().log("Data para encriptar: " + pIdBorrower);
		return (aes256.encrypt(ENCRYPT_KEY, KEY_SPEC, CIPHER_MODE, pIdBorrower));
	}

	public Credit searchClient(String id) {

		context.getLogger().log("Entra a buscar client");
		String response = "APR";
		Credit credit = new Credit();

		try {
			credit = mapper.load(Credit.class, id);
			context.getLogger().log(credit.getClient());
			if (credit != null) {
				context.getLogger().log(credit.toString());
				credit.setClient((credit.getClient() == null) ? response : credit.getClient());
			}
		} catch (Exception e) {
			Credit credit2 = new Credit();
			credit2.setClient(response);
			credit2.setFeesApprobe(0);
			return credit2;
		}
		context.getLogger().log(response);
		return credit;
	}

	private List<PaymentsClientCredit> payments(BorrowerStatusDto paymentsCore, String idCredit) {

		List<PaymentsClientCredit> payments = new ArrayList<>();
		if (idCredit != null && paymentsCore.getPayments() != null) {
			for (Iterator<PaymentsDto> i = paymentsCore.getPayments().iterator(); i.hasNext();) {
				PaymentsDto wPaymentsDto = i.next();
				for (Iterator<Loan_allocation_dto> j = wPaymentsDto.getLoan_allocation().iterator(); j.hasNext();) {
					Loan_allocation_dto wLoanAllocation = j.next();
					if (wLoanAllocation != null && wLoanAllocation.getLoan_id().equals(idCredit)) {
						PaymentsClientCredit onePaymnet = new PaymentsClientCredit();
						onePaymnet.setPrincipal((wLoanAllocation.getPrincipal().getTotal()));
						onePaymnet.setTotal(wLoanAllocation.getTotal());
						onePaymnet.setDaily_fee(wLoanAllocation.getDaily_fee().getTotal());
						onePaymnet.setDelinquency_interest(wLoanAllocation.getDelinquency_interest().getTotal());
						onePaymnet.setInstallment_fee(wLoanAllocation.getInstallment_fee().getTotal());
						onePaymnet.setInterest(wLoanAllocation.getInterest().getTotal());
						onePaymnet.setLate_fee(wLoanAllocation.getLate_fee().getTotal());
						onePaymnet.setPayment_id(wPaymentsDto.getPayment_id());
						onePaymnet.setEffective_date(wPaymentsDto.getEffective_date());
						payments.add(onePaymnet);

					}

				}

			}
		}

		return payments;
	}

}
