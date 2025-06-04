package transactions.core.services;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import transactions.core.adapter.AdapterPaymentMethod;
import transactions.core.adapter.BnplImpl;
import transactions.core.adapter.IBnpl;
import transactions.core.adapter.IP2p;
import transactions.core.adapter.IUpdateCore;
import transactions.core.adapter.IWallet;
import transactions.core.adapter.P2pImpl;
import transactions.core.adapter.WalletImpl;
import transactions.core.dao.Credit;
import transactions.core.dao.GeneralInformation;
import transactions.core.dao.Payments;
import transactions.core.dao.SuccessfulStatusQueries;
import transactions.core.dao.TransactionErrorLogs;
import transactions.core.dto.PaymentEvent;
import transactions.core.dto.Request;
import transactions.core.dto.ResponseKordev;
import transactions.core.strategy.BnplPayment;
import transactions.core.strategy.P2pPayment;
import transactions.core.strategy.SelectPaymentsMethod;
import transactions.core.strategy.WalletPayment;

public class CoreService {
	HttpClient client = HttpClient.newHttpClient();
	HttpRequest request;
	HttpResponse<String> responseToken;
	HttpResponse<String> responseTransaction;
	SuccessfulStatusQueries successfulStatusQueries=new SuccessfulStatusQueries();
	TransactionErrorLogs transactionErrorLogs=new TransactionErrorLogs();
	

	public String saveCredit(String id, DynamoDBMapper mapper, AmazonDynamoDB db, Context context) {
		SelectPaymentsMethod payments = new SelectPaymentsMethod();
		GeneralInformation generalInformation = new GeneralInformation();
		ObjectMapper mapperOb = new ObjectMapper();
		generalInformation = mapper.load(GeneralInformation.class, 0);
		P2pPayment p2pPayment = new P2pPayment();
		WalletPayment walletPayment = new WalletPayment();
		BnplPayment bnplPayment = new BnplPayment();
		context.getLogger().log("id resultado: " + id);
		Credit credit = new Credit();
		ResponseKordev responseKordev = new ResponseKordev();
		credit = mapper.load(Credit.class, id);
		context.getLogger().log("credit: " + credit.toString());
		switch (credit.getChannel()) {
		case "BNPL":
			payments.setPaymentMethod(bnplPayment);
			String bodyBnpl = payments.createCredit(credit, generalInformation, context,mapper);
			Optional<String> resultBnpl = Optional.of(consumerLambda(bodyBnpl));
			if (resultBnpl.isEmpty()) {
				return "fail process";
			}
			try {
				responseKordev = mapperOb.readValue(resultBnpl.get(), ResponseKordev.class);
				context.getLogger().log("kordev answerd"+responseKordev.toString());
				credit.setKordev_id(responseKordev.getKordev_id());
				mapper.save(credit);
				successfulStatusQueries.setCreditId(credit.getId());
				successfulStatusQueries.setInformation(bodyBnpl);
				successfulStatusQueries.setNumberIdBorrower(credit.getNumberIdBorrower());
				successfulStatusQueries.setNumberIdLender(credit.getNumberIdMoneylender());
				successfulStatusQueries.setResult(responseKordev.toString());
				successfulStatusQueries.setTypeTx("CREACION CREDITO BNPL");
				mapper.save(successfulStatusQueries);
				return responseKordev.toString();
			} catch (Exception e) {
				transactionErrorLogs.setCreditId(credit.getId());
				transactionErrorLogs.setInformation(bodyBnpl);
				transactionErrorLogs.setNumberIdBorrower(credit.getNumberIdBorrower());
				transactionErrorLogs.setNumberIdLender(credit.getNumberIdMoneylender());
				transactionErrorLogs.setResult("error en resultado -> coreService " + e.getMessage());
				transactionErrorLogs.setTypeTx("CREACION CREDITO BNPL");
				mapper.save(transactionErrorLogs);
				context.getLogger().log("error en resultado -> coreService " + e.getMessage());
				return "fail";
			}

		case "P2P":
			payments.setPaymentMethod(p2pPayment);
			String bodyP2p = payments.createCredit(credit, generalInformation, context,mapper);
			Optional<String> resultP2p = Optional.of(consumerLambda(bodyP2p));
			if (resultP2p.isEmpty()) {
				return "fail process";
			}
			try {
				responseKordev = mapperOb.readValue(resultP2p.get(), ResponseKordev.class);
				context.getLogger().log("kordev answerd"+responseKordev.toString());
				credit.setKordev_id(responseKordev.getKordev_id());
				mapper.save(credit);
				successfulStatusQueries.setCreditId(credit.getId());
				successfulStatusQueries.setInformation(bodyP2p);
				successfulStatusQueries.setNumberIdBorrower(credit.getNumberIdBorrower());
				successfulStatusQueries.setNumberIdLender(credit.getNumberIdMoneylender());
				successfulStatusQueries.setResult(responseKordev.toString());
				successfulStatusQueries.setTypeTx("CREACION CREDITO P2P");
				mapper.save(successfulStatusQueries);
				return responseKordev.toString();
			} catch (Exception e) {
				transactionErrorLogs.setCreditId(credit.getId());
				transactionErrorLogs.setInformation(bodyP2p);
				transactionErrorLogs.setNumberIdBorrower(credit.getNumberIdBorrower());
				transactionErrorLogs.setNumberIdLender(credit.getNumberIdMoneylender());
				transactionErrorLogs.setResult("error en resultado -> coreService " + e.getMessage());
				transactionErrorLogs.setTypeTx("CREACION CREDITO P2P");
				mapper.save(transactionErrorLogs);
				context.getLogger().log("error en resultado -> coreService " + e.getMessage());
				return "fail";
				
			}
		case "WALLET":
			payments.setPaymentMethod(walletPayment);
			String bodyWallet = payments.createCredit(credit, generalInformation, context,mapper);
			Optional<String> resultWallet = Optional.of(consumerLambda(bodyWallet));
			if (resultWallet.isEmpty()) {
				return "fail process";
			}
			try {
				responseKordev = mapperOb.readValue(resultWallet.get(), ResponseKordev.class);
				context.getLogger().log("kordev answerd"+responseKordev.toString());
				credit.setKordev_id(responseKordev.getKordev_id());
				mapper.save(credit);
				successfulStatusQueries.setCreditId(credit.getId());
				successfulStatusQueries.setInformation(bodyWallet);
				successfulStatusQueries.setNumberIdBorrower(credit.getNumberIdBorrower());
				successfulStatusQueries.setNumberIdLender(credit.getNumberIdMoneylender());
				successfulStatusQueries.setResult(responseKordev.toString());
				successfulStatusQueries.setTypeTx("CREACION CREDITO WALLET");
				mapper.save(successfulStatusQueries);
				return responseKordev.toString();
			} catch (Exception e) {
				transactionErrorLogs.setCreditId(credit.getId());
				transactionErrorLogs.setInformation(bodyWallet);
				transactionErrorLogs.setNumberIdBorrower(credit.getNumberIdBorrower());
				transactionErrorLogs.setNumberIdLender(credit.getNumberIdMoneylender());
				transactionErrorLogs.setResult("error en resultado -> coreService " + e.getMessage());
				transactionErrorLogs.setTypeTx("CREACION CREDITO WALLET");
				mapper.save(transactionErrorLogs);
				context.getLogger().log("error en resultado -> coreService " + e.getMessage());
				return "fail";
			}
		}
		return "fail";

	}

	public Object updateCore(Request request, DynamoDBMapper mapper, AmazonDynamoDB db, Context context) {
		Credit credit = new Credit();
		ResponseKordev responseKordev = new ResponseKordev();
		PaymentEvent paymentEvent=new PaymentEvent();
		Payments payments=new Payments();
		ObjectMapper mapperOb = new ObjectMapper();
		IP2p p2p = new P2pImpl();
		IBnpl iBnpl = new BnplImpl();
		IWallet iWallet = new WalletImpl();
		IUpdateCore adapter = new AdapterPaymentMethod(p2p, iBnpl, iWallet);
		String source = request.getChannel();
		String resultBody = adapter.updateCredit(request, source, mapper, db, context);
		context.getLogger().log("enviado " + resultBody);
		Optional<String> resultOptionalBody = Optional.ofNullable(consumerLambda(resultBody));
		context.getLogger().log("resultado string kordev " + resultOptionalBody.get());
		if (resultOptionalBody.isEmpty()) {
			return "fail process";
		}
		try {
			credit = mapper.load(Credit.class, request.getIdCode());
			responseKordev = mapperOb.readValue(resultOptionalBody.get(), ResponseKordev.class);
			if (request.getChannel().equals("BNPL")) {
				payments=mapper.load(Payments.class,request.getTicketId());
				payments.setKordevId(responseKordev.getKordev_id());
				mapper.save(payments);
			}
			context.getLogger().log("kordev answerd"+responseKordev.toString());
			LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.of("-05:00"));
			DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String dateSave = dateTime.format(dtformat);
			credit.setKordev_id(responseKordev.getKordev_id());
			credit.setBalancePaid(0);
			credit.setDateUpdateBD(dateSave);
			credit.setFeesPaid(0);
			credit.setLastPaymentDate("");
			credit.setNextFeesDate("");
			credit.setNextPaid(0.0);
			credit.setSaldoCredito(0);
			mapper.save(credit);
			successfulStatusQueries.setCreditId(credit.getId());
			successfulStatusQueries.setInformation(resultBody);
			successfulStatusQueries.setNumberIdBorrower(credit.getNumberIdBorrower());
			successfulStatusQueries.setNumberIdLender(credit.getNumberIdMoneylender());
			successfulStatusQueries.setResult(responseKordev.toString());
			successfulStatusQueries.setTypeTx("PAGO CUOTA");
			mapper.save(successfulStatusQueries);
			return responseKordev;
		} catch (Exception e) {
			transactionErrorLogs.setCreditId(credit.getId());
			transactionErrorLogs.setInformation(resultBody);
			transactionErrorLogs.setNumberIdBorrower(credit.getNumberIdBorrower());
			transactionErrorLogs.setNumberIdLender(credit.getNumberIdMoneylender());
			transactionErrorLogs.setResult("error en resultado -> coreService " + e.getMessage());
			transactionErrorLogs.setTypeTx("PAGO CUOTA");
			mapper.save(transactionErrorLogs);
			context.getLogger().log("error en resultado -> coreService " + e.getMessage());
			return "fail";
		}

	}

	private String consumerLambda(String body) {
		AWSLambda lambdaClient1 = AWSLambdaClientBuilder.defaultClient();
		InvokeRequest invokeRequest1 = new InvokeRequest().withFunctionName("core").withPayload(body);
		InvokeResult invokeResult1 = lambdaClient1.invoke(invokeRequest1);
		return new String(invokeResult1.getPayload().array());
	}
	
	
}
