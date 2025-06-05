package co.approbe.payment.create.service;

import java.io.IOException;
import java.net.URI;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.function.Consumer;

import co.approbe.payment.create.dao.ApprobeCommission;
import co.approbe.payment.create.dao.Consecutives;
import co.approbe.payment.create.dao.Credit;
import co.approbe.payment.create.dao.Data;
import co.approbe.payment.create.dao.DataBorrower;
import co.approbe.payment.create.dao.ErrorsCore;
import co.approbe.payment.create.dao.FailedTransactions;
import co.approbe.payment.create.dao.GeneralInformation;
import co.approbe.payment.create.dao.Payments;
import co.approbe.payment.create.dao.TokenEcollect;
import co.approbe.payment.create.dao.TransactionResponse;
import co.approbe.payment.create.dto.BodyClienCredistDto;
import co.approbe.payment.create.dto.ClientCreditsDto;
import co.approbe.payment.create.dto.CreateTransactionResponseType;
import co.approbe.payment.create.dto.CreateTransactionType;
import co.approbe.payment.create.dto.PartnerDto;
import co.approbe.payment.create.dto.Paymentsdto;
import co.approbe.payment.create.dto.Request;
import co.approbe.payment.create.dto.RequestTokenSesion;
import co.approbe.payment.create.dto.ResponseTokenSesion;
import co.approbe.payment.create.dto.ResponseTransactionInformation;
import co.approbe.payment.create.dto.ResponseTransactionInformation.Payment;
import co.approbe.payment.create.dto.SubservicesArray;
import co.approbe.payment.create.dto.TransactionInformation;
import sun.java2d.loops.DrawGlyphListAA.General;

public class Transaction {
	private Boolean status = false;
	private static final String URLRESPONSE = Env.API_URLRESPONSE; // "htt webhook";
	private static final String URLREDIRECT = Env.API_URLREDIRECT; // "https://www.approbe.co/";
	private static final String CURRENCY = Env.API_CURRENCY; // "COP";
	private static final String QUEUE_NAME = Env.QUEUE_NAME;
	private static final String EXCHANGE = Env.EXCHANGE_NAME;
	private Connection connection;
	private Channel channel;
	String path;
	String requestBody;
	String message;
	ResponseTokenSesion responseTokenSesion = new ResponseTokenSesion();
	HttpClient client = HttpClient.newHttpClient();
	HttpRequest request;
	HttpResponse<String> responseToken;
	HttpResponse<String> responseTransaction;
	TokenEcollect token = new TokenEcollect();
	Payments payments = new Payments();

	public String getToken(DynamoDBMapper mapper, AmazonDynamoDB db, Context context) {
		context.getLogger().log("Entra a pedir token ");
		token = new TokenEcollect();
		token = mapper.load(TokenEcollect.class, Env.API_CLIENT);
		Boolean isTrue = (token != null) ? isTokenVigente(token.getTime(), token.getDateSave()) : false;
		context.getLogger().log("token vigente: " + isTrue);
		if (!isTrue) {
			RequestTokenSesion requestToken = new RequestTokenSesion();
			requestToken.setEntityCode(Env.API_CLIENT);
			requestToken.setApiKey(Env.API_KEY);
			requestBody = requestToken.toString();
			context.getLogger().log("peticion: " + requestBody);
			path = "api/getSessionToken";
			request = HttpRequest.newBuilder().uri(URI.create(Env.API_PATH + path))
					.setHeader("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();
			try {
				context.getLogger().log("peticion: " + request);
				responseToken = client.send(request, HttpResponse.BodyHandlers.ofString());
				message = responseToken.body();
				context.getLogger().log("mensaje: " + message);
				responseTokenSesion = new Gson().fromJson(message, ResponseTokenSesion.class);
				context.getLogger().log("respuesta: " + responseTokenSesion.toString());
				if (responseTokenSesion.getReturnCode().equals("SUCCESS")) {
					token = new TokenEcollect();
					token.setTime(responseTokenSesion.getLifetimeSecs());
					token.setId(Env.API_CLIENT);
					token.setToken(responseTokenSesion.getSessionToken());
					mapper.save(token);
					return "ok";
				} else {
					return "fail";
				}
			} catch (IOException | InterruptedException | IllegalStateException | JsonSyntaxException e) {
				context.getLogger().log("error request create: " + e.getMessage());
				return "fail";
			}
		} else {
			return "ok";
		}
	}

	public Map<String, String> create(String idCode, Optional<String> ticketId, Double value, String bank,

			DynamoDBMapper mapper, Context context) {
		Map<String, String> resultMap = new HashMap<String, String>();
		value = Math.floor(value);
		resultMap.put("status", "pending");
		String ticket = ticketId.get().isEmpty() ? "" : ticketId.get();
		Payments paymentsStatus = new Payments();
		context.getLogger().log("Entra a create paymente");
		try {
			paymentsStatus = mapper.load(Payments.class, ticket);
			status = paymentsStatus.getStatus();
		} catch (NullPointerException | DynamoDBMappingException e) {
			status = false;
		}
		if (status)
			return resultMap;
		CreateTransactionType createTransaction = new CreateTransactionType();
		SubservicesArray subservices = new SubservicesArray();
		Credit credit = new Credit();
		Data data = new Data();
		DataBorrower dataBorrower = new DataBorrower();
		ApprobeCommission commission = new ApprobeCommission();
		token = new TokenEcollect();
		try {
			credit = mapper.load(Credit.class, idCode);
			data = mapper.load(Data.class, credit.getNumberIdMoneylender());
			dataBorrower = mapper.load(DataBorrower.class, credit.getNumberIdBorrower());
			commission = mapper.load(ApprobeCommission.class, "1");
			token = mapper.load(TokenEcollect.class, Env.API_CLIENT);
		} catch (NullPointerException e) {
			context.getLogger()
					.log("error obteniendo los datos de BD credit, data, commission o token: " + e.getMessage());
			resultMap.put("status", "fail");
			return resultMap;
		}
		if (credit == null || data == null || commission == null || dataBorrower == null) {
			resultMap.put("status", "fail");
			return resultMap;
		}
		payments = new Payments();
		Boolean isTrue = (token != null) ? isTokenVigente(token.getTime(), token.getDateSave()) : false;
		Consecutives consecutives = new Consecutives();
		consecutives = mapper.load(Consecutives.class, "1");
		String transactionId = generateTransactionId(consecutives.getNumber() + 1);

		if (isTrue) {

			Paymentsdto pagos = searhClientCore(idCode, value, context, mapper);
			context.getLogger().log("Pagos: " + pagos.toString());
			context.getLogger().log("Resta de ammount con financiero: " + (value - pagos.getFinancier()));
			double approbe = value - pagos.getFinancier();
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			double roundedNumber = Double.parseDouble(decimalFormat.format(value));
			createTransaction.setEntityCode(Long.parseLong(commission.getEntityCodeApprobe()));
			createTransaction.setSessionToken(token.getToken());
			createTransaction.setSrvCode(commission.getSrvCode());
			createTransaction.setTransValue(roundedNumber);
			createTransaction.setuRLRedirect(URLREDIRECT);
			createTransaction.setTransVatValue(0.0);
			createTransaction.setSrvCurrency(CURRENCY);
			createTransaction.setuRLResponse(URLRESPONSE);
			ArrayList<String> referenceArray = new ArrayList<>();
			referenceArray.add(dataBorrower.getIdentification());
			referenceArray.add(dataBorrower.getNumberId());
			referenceArray.add(transactionId);
			referenceArray.add(dataBorrower.getNames() + " " + dataBorrower.getLastName());
			referenceArray.add(dataBorrower.getEmail());
			referenceArray.add(dataBorrower.getCellphone());
//			referenceArray.add(""+calculateTax(complete?credit.getSaldoCredito():credit.getNextPaid(), commission.getCommissionPercentage()));
//			referenceArray.add(credit.getLenderDocumentType());
//			referenceArray.add(credit.getNumberIdMoneylender());
//			referenceArray.add(credit.getLenderName());
//			referenceArray.add(credit.getLenderEmail());
//			referenceArray.add(credit.getLenderAccountType());
//			referenceArray.add(credit.getLenderNumberAccount());
//			referenceArray.add(bank);
//			referenceArray.add(""+calculateTax(complete?credit.getSaldoCredito():credit.getNextPaid(), commission.getCommissionExt()));

			String[] dynamicArray = referenceArray.toArray(new String[0]);
			createTransaction.setReferenceArray(dynamicArray);
			List<SubservicesArray> listaSubservices = new ArrayList<>();
			subservices.setEntityCode(commission.getEntityCodeApprobe());
			subservices.setSrvCode(commission.getSrvCode() + "01");
			subservices.setTransValue(approbe);
			subservices.setTransVatValue(0.0);
			subservices.setValueType(0);
			subservices.setAccountId(Env.ACCOUNTID_APPROBE);
			listaSubservices.add(subservices);
			subservices = new SubservicesArray();
			subservices.setEntityCode(credit.getEntityCode());
			subservices.setSrvCode(commission.getSrvCode() + "02");
			subservices.setTransValue(pagos.getFinancier());
			subservices.setTransVatValue(0.0);
			subservices.setValueType(0);
			subservices.setAccountId(credit.getAccountId());
			listaSubservices.add(subservices);

			createTransaction.setSubservicesArray(listaSubservices);
			ObjectMapper objectMapper = new ObjectMapper();
			String requestBody = "";
			try {
				requestBody = objectMapper.writeValueAsString(createTransaction);
			} catch (Exception e) {
				context.getLogger().log("Error al convertir el objeto a JSON: " + e.getMessage());
				resultMap.put("status", "fail");
				return resultMap;
			}
			context.getLogger().log("peticion: " + requestBody);
			path = "api/createTransactionPayment";
			request = HttpRequest.newBuilder().uri(URI.create(Env.API_PATH + path))
					.setHeader("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();
			try {
				responseTransaction = client.send(request, HttpResponse.BodyHandlers.ofString());
				message = responseTransaction.body();
				CreateTransactionResponseType createTransactionResponse = new CreateTransactionResponseType();
				TransactionResponse transactionResponse = new TransactionResponse();
				context.getLogger().log("mensaje: " + message);
				createTransactionResponse = new Gson().fromJson(message, CreateTransactionResponseType.class);
				context.getLogger().log("respuesta: " + createTransactionResponse.toString());
				if (createTransactionResponse.getReturnCode().equals("SUCCESS")) {

					payments.setId(createTransactionResponse.getTicketId() + "");
					payments.setReturnCode(createTransactionResponse.getReturnCode());
					payments.seteCollectUrl(createTransactionResponse.geteCollectUrl());
					payments.setLifetimeSecs(createTransactionResponse.getLifetimeSecs());
					payments.setTicketId(createTransactionResponse.getTicketId() + "");

					transactionResponse.setBanckProcessDate(
							createTransactionResponse.getTransactionResponse().getBanckProcessDate());
					transactionResponse
							.setEntityCode(createTransactionResponse.getTransactionResponse().getEntityCode());
					transactionResponse.setFiCode(createTransactionResponse.getTransactionResponse().getFiCode());
					transactionResponse
							.setReferenceArray(createTransactionResponse.getTransactionResponse().getReferenceArray());
					transactionResponse.setSRVcode(createTransactionResponse.getTransactionResponse().getSRVcode());
					transactionResponse.setTranState(createTransactionResponse.getTransactionResponse().getTranState());
					transactionResponse
							.setTransValue(createTransactionResponse.getTransactionResponse().getTransVatValue());
					transactionResponse
							.setTransVatValue(createTransactionResponse.getTransactionResponse().getTransVatValue());
					payments.setTransactionResponse(transactionResponse);
					payments.setCreditNumber(credit.getId());
					payments.setNumberId(credit.getNumberIdBorrower());
					payments.setStatus(true);
					try {
						mapper.save(payments);
						consecutives.setNumber(consecutives.getNumber() + 1);
						consecutives.setConsecutive(transactionId);
						consecutives.setId("1");
						mapper.save(consecutives);
					} catch (IllegalStateException | JsonSyntaxException e) {
						resultMap.put("status", "fail");
						context.getLogger().log("error request create: " + e.getMessage());
						return resultMap;
					}
					resultMap.put("status", "ok");
					resultMap.put("ticketId", "" + createTransactionResponse.getTicketId());
					resultMap.put("resultId", "" + payments.getId());
					resultMap.put("eCollectUrl", createTransactionResponse.geteCollectUrl());
					return resultMap;
				} else if (!createTransactionResponse.getReturnCode().equals("SUCCESS")
						&& createTransactionResponse.getReturnCode() != null) {
					try {
						LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.of("-05:00"));
						DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
						String dateSave = dateTime.format(dtformat);
						FailedTransactions failedTransactions = new FailedTransactions();
						failedTransactions.setAmount(credit.getNextPaid() + "");
						failedTransactions.setChannel("BNPL");
						failedTransactions.setDescription("error creando pago");
						failedTransactions.setIdentification(credit.getNumberIdBorrower());
						failedTransactions.setStatus(createTransactionResponse.getReturnCode());
						failedTransactions.setStatusDate(
								createTransactionResponse.getTransactionResponse().getBanckProcessDate());
						failedTransactions.setStatusUpdate(dateSave);
						failedTransactions.setTicketId(createTransactionResponse.getTicketId() + "");
						failedTransactions.setTypeIdentification(data.getIdentification());
						context.getLogger().log("failedTransactions " + failedTransactions);
						mapper.save(failedTransactions);
					} catch (Exception e) {
						resultMap.put("status", "fail");
						return resultMap;
					}
					resultMap.put("status", "error");
					return resultMap;

				} else {
					resultMap.put("status", "fail");
					return resultMap;
				}
			} catch (IOException | InterruptedException | IllegalStateException | JsonSyntaxException e) {
				context.getLogger().log("error request create: " + e.getMessage());
				resultMap.put("status", "fail");
				return resultMap;
			}
		}
		resultMap.put("status", "token expired");
		return resultMap;
	}

	public Map<String, String> webhook(String tiketId, Request request, DynamoDBMapper mapper, Context context,
			ConnectionFactory factory) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("ReturnCode", "FAIL_SYSTEM ");
		payments = new Payments();
		context.getLogger().log("Entra a webhook");
		try {
			payments = mapper.load(Payments.class, tiketId);
			request.setIdCode(payments.getCreditNumber());
			status = payments.getStatus();
		} catch (NullPointerException e) {
			context.getLogger().log("error obteniendo los datos de BD payments: " + e.getMessage());
			resultMap.put("ReturnCode", "FAIL_SYSTEM ");
			return resultMap;
		}
		if (request.getTranState().equals("OK") || request.getTranState().equals("NOT_AUTHORIZED")
				|| request.getTranState().equals("EXPIRED") || request.getTranState().equals("FAILED")
				|| request.getTranState().equals("ENROLLED")) {
			payments.setStatus(false);
			payments.setStatusPayment(request.getReturnCode());
			payments.setResultTransaction(request.toString());
			mapper.save(payments);
			HashMap<String, String> value = new HashMap<String, String>();
			String body = "";
			if (request.getTranState().equals("OK")) {
				value.put("idCode", request.getIdCode());
				value.put("channel", "BNPL");
				value.put("ammount", "" + request.getTransValue());
				value.put("ticketId", "" + request.getTicketIdEcollect());
				try {
					ObjectMapper objectMapper = new ObjectMapper();
					body = objectMapper.writeValueAsString(value);
					context.getLogger().log("Se convierte en string el body"); // comentariosebas
					if (factory == null) {
						context.getLogger().log("Entra a factory null");
					}
					connection = factory.newConnection();
					context.getLogger().log("Crea factory.connection "); // comentariosebas
					channel = connection.createChannel();
					context.getLogger().log("crea factory.createChanel:  Entra a comunicar con la cola."); // comentarios
																											// sebas
					channel.basicPublish(EXCHANGE, QUEUE_NAME, null, body.getBytes("UTF-8"));
					context.getLogger().log("objeto cola: " + body);
				} catch (Exception e3) {
					LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.of("-05:00"));
					DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					String dateSave = dateTime.format(dtformat);
					context.getLogger().log("Entra a catch de la cola");
					context.getLogger().log(e3.getLocalizedMessage());
					context.getLogger().log(e3.getMessage());
					ErrorsCore error = new ErrorsCore();
					error.setTickedId(payments.getTicketId());
					error.setNumberId(payments.getNumberId());
					error.setIdCredit(payments.getCreditNumber());
					error.setAmmount(payments.getTransactionResponse().getTransValue());
					error.setSaveDate(payments.getTransactionResponse().getBanckProcessDate());
					error.setChannel(payments.toString());
					error.setAmmount(payments.getResultTransaction());
					mapper.save(error);
					e3.printStackTrace();
				} finally {
					try {
						if (channel != null) {
							channel.close();
						}
						if (connection != null) {
							connection.close();
						}
					} catch (Exception ignore) {
						context.getLogger().log("Eroor de cierre de cola: " + ignore);
					}
				}
			} else {
				context.getLogger().log("ingresa pago fallido ");
				try {
					LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.of("-05:00"));
					DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					String dateSave = dateTime.format(dtformat);
					FailedTransactions failedTransactions = new FailedTransactions();
					failedTransactions.setAmount(request.getTransValue() + "");
					failedTransactions.setChannel("BNPL");
					failedTransactions.setDescription(request.toString());
					failedTransactions.setIdentification(request.getReferenceArray().get(1) + "");
					failedTransactions.setStatus(request.getReturnCode());
					failedTransactions.setStatusDate(request.getBankProcessDate());
					failedTransactions.setStatusUpdate(dateSave);
					failedTransactions.setTicketId(request.getTicketIdEcollect() + "");
					failedTransactions.setTypeIdentification(request.getReferenceArray().get(0) + "");
					context.getLogger().log("failedTransactions " + failedTransactions);
					mapper.save(failedTransactions);

				} catch (Exception e) {
					resultMap.put("status", "fail");
					return resultMap;
				}
			}
		} else {
			payments.setStatus(true);
			payments.setStatusPayment(request.getReturnCode());
			payments.setResultTransaction(request.toString());
			mapper.save(payments);
		}
		resultMap.put("ReturnCode", "SUCCESS");
		return resultMap;

	}

	public Map<String, String> transaction(String tiketId, String idPayments, DynamoDBMapper mapper, Context context) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("status", "pending");
		context.getLogger().log("Entra a create transaction");
		token = mapper.load(TokenEcollect.class, Env.API_CLIENT);
		Boolean isTrue = (token != null) ? isTokenVigente(token.getTime(), token.getDateSave()) : false;
		payments = new Payments();
		try {
			payments = mapper.load(Payments.class, idPayments);
			status = payments.getStatus();
		} catch (NullPointerException e) {
			context.getLogger().log("error obteniendo los datos de BD payments: " + e.getMessage());
			resultMap.put("status", "fail");
			return resultMap;
		}

		if (isTrue) {
			TransactionInformation transactionInformation = new TransactionInformation();
			transactionInformation.setEntityCode(Env.API_CLIENT);
			transactionInformation.setSessionToken(token.getToken());
			transactionInformation.setTicketId(tiketId);
			ObjectMapper objectMapper = new ObjectMapper();
			String requestBody = "";
			try {
				requestBody = objectMapper.writeValueAsString(transactionInformation);
			} catch (Exception e) {
				context.getLogger().log("Error al convertir el objeto a JSON: " + e.getMessage());
				return null;
			}
			context.getLogger().log("peticion: " + requestBody);
			path = "api/GetTransactionInformation";
			request = HttpRequest.newBuilder().uri(URI.create(Env.API_PATH + path))
					.setHeader("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();
			try {
				responseTransaction = client.send(request, HttpResponse.BodyHandlers.ofString());
				message = responseTransaction.body();
				ResponseTransactionInformation responseTransactionInformation = new ResponseTransactionInformation();
				context.getLogger().log("mensaje: " + message);
				responseTransactionInformation = new Gson().fromJson(message, ResponseTransactionInformation.class);
				for (Payment payment : responseTransactionInformation.getPaymentsArray()) {
					if (payment.getTranState().equals("BANK") || payment.getTranState().equals("PENDING")
							|| payment.getTranState().equals("CAPTURED") || payment.getTranState().equals("CREATED")) {
						payments.setStatus(true);
						payments.setStatusPayment(payment.getTranState());
						mapper.save(payments);
						status = true;
					}
					if (payment.getTranState().equals("OK") || payment.getTranState().equals("NOT_AUTHORIZED")
							|| payment.getTranState().equals("EXPIRED") || payment.getTranState().equals("FAILED")
							|| payment.getTranState().equals("ENROLLED")) {
						payments.setStatus(false);
						payments.setStatusPayment(payment.getTranState());
						mapper.save(payments);
						status = false;
					}
				}
				resultMap.put("status", "ok");
				resultMap.put("referenceArray", responseTransactionInformation.getReferenceArray().toString());
				resultMap.put("paymentsArray", responseTransactionInformation.getPaymentsArray().toString());
				resultMap.put("srvCode", responseTransactionInformation.getSrvCode());
				resultMap.put("paymentDesc", responseTransactionInformation.getPaymentDesc());
				resultMap.put("referenceArray", responseTransactionInformation.getReferenceArray().toString());
				resultMap.put("EntityCode", responseTransactionInformation.getEntityCode());
				resultMap.put("ticketId", responseTransactionInformation.getTicketId());
				resultMap.put("tranState", responseTransactionInformation.getTranState());
				resultMap.put("returnCode", responseTransactionInformation.getReturnCode());
				resultMap.put("transValue", responseTransactionInformation.getTransValue() + "");
				resultMap.put("payCurrency", responseTransactionInformation.getPayCurrency());
				resultMap.put("bankProcessDate", responseTransactionInformation.getBankProcessDate());
				context.getLogger().log("Response STATUS: " + responseTransactionInformation.toString());
				return resultMap;
			} catch (IOException | InterruptedException | IllegalStateException | JsonSyntaxException e) {
				context.getLogger().log("error request create: " + e.getMessage());
				resultMap.put("status", "fail");
				return resultMap;
			}

		} else {
			resultMap.put("status", "token expired");
			return resultMap;
		}

	}

	public List<SubservicesArray> dispersion(String idCode, Double value, DynamoDBMapper mapper, Context context) {
		CreateTransactionType createTransaction = new CreateTransactionType();
		SubservicesArray subservices = new SubservicesArray();
		Credit credit = new Credit();
		Data data = new Data();
		ApprobeCommission commission = new ApprobeCommission();
		token = new TokenEcollect();
		context.getLogger().log("Entra a dispérsion");

		try {
			credit = mapper.load(Credit.class, idCode);
			data = mapper.load(Data.class, credit.getNumberIdBorrower());
			ClientCreditsDto core = searhClientCore(credit.getNumberIdBorrower(), idCode, context);
			context.getLogger().log("Informacion del Core: " + core.toString());
			commission = mapper.load(ApprobeCommission.class, "1");
			token = mapper.load(TokenEcollect.class, Env.API_CLIENT);
			List<SubservicesArray> listaSubservices = new ArrayList<>();
			subservices.setEntityCode(commission.getEntityCodeApprobe());
			subservices.setSrvCode(commission.getSrvCode() + "01");
			subservices.setTransValue(calculateTax(value, commission.getCommissionPercentage()));
			subservices.setTransVatValue(0.0);
			subservices.setValueType(0);
			listaSubservices.add(subservices);
			subservices = new SubservicesArray();
			subservices.setEntityCode(credit.getEntityCode());
			subservices.setSrvCode(commission.getSrvCode() + "02");
			subservices.setTransValue(calculateTax(value, commission.getCommissionExt()));
			subservices.setTransVatValue(0.0);
			subservices.setValueType(0);
			listaSubservices.add(subservices);
			return listaSubservices;
		} catch (NullPointerException e) {
			context.getLogger()
					.log("error obteniendo los datos de BD credit, data, commission o token: " + e.getMessage());
			return null;
		}
	}

	public boolean isTokenVigente(Double tiempoVigenciaSegundos, String fechaAlmacenada) {
		String fechaActual = obtenerFechaActualEnFormatoDeseado();
		System.out.println("fechaActual " + fechaActual);
		long diferenciaSegundos = calcularDiferenciaSegundos(fechaActual, fechaAlmacenada);
		System.out.println("diferenciaSegundos " + diferenciaSegundos);
		return diferenciaSegundos < tiempoVigenciaSegundos;
	}

	private String obtenerFechaActualEnFormatoDeseado() {
		LocalDateTime fechaActual = LocalDateTime.now(ZoneOffset.of("-05:00"));
		return fechaActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

	private long calcularDiferenciaSegundos(String fechaActual, String fechaAlmacenada) {
		LocalDateTime localFechaActual = LocalDateTime.parse(fechaActual,
				DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		LocalDateTime localFechaAlmacenada = LocalDateTime.parse(fechaAlmacenada,
				DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		return java.time.Duration.between(localFechaAlmacenada, localFechaActual).getSeconds();
	}

	public static double calculateTax(double amount, double taxPercentage) {
		double taxFactor = (taxPercentage / 100.0);
		UnaryOperator<Double> taxFunction = value -> Math.round(value * taxFactor * 100.0) / 100.0;
		return taxFunction.apply(amount);
	}

	private String generateTransactionId(Long consecutive) {
		return String.format(Locale.US, "CR%08d", consecutive);
	}

	private ClientCreditsDto searhClientCore(String numberId, String idCode, Context context) {

		String core = " ";
		double dias = 0;
		ClientCreditsDto coreTotal = new ClientCreditsDto();
		List<ClientCreditsDto> responseCore = null;
		BodyClienCredistDto wRequestBody = new BodyClienCredistDto("GET_CLIENT_CREDITS", numberId);

		try {
			AWSLambda lambdaClient = AWSLambdaClientBuilder.defaultClient();
			InvokeRequest invokeRequest = new InvokeRequest().withFunctionName("clientcredits")
					.withPayload(wRequestBody.toString());
			InvokeResult invokeResult = lambdaClient.invoke(invokeRequest);
			core = new String(invokeResult.getPayload().array());
			context.getLogger().log("respuesta a consulta: " + core);
			Type listType = new TypeToken<ArrayList<ClientCreditsDto>>() {
			}.getType();
			responseCore = new Gson().fromJson(core, listType);
		} catch (Exception e) {
			context.getLogger().log("falla en consulta a core" + e);
		}

		for (ClientCreditsDto coreList : responseCore) {
			if (idCode.equals(coreList.getId())) {
				coreTotal = coreList;
			}
		}

		coreTotal.setSimpleDaysPastDue(dias);
		return coreTotal;

	}

	private ClientCreditsDto newCore(ClientCreditsDto core, double ammount, Context context) {
		context.getLogger().log("Entra a calcular nuevo core");
		context.getLogger().log("entra a for");
		for (int i = 0; ammount > 0; i++) {
			context.getLogger().log("ammount: " + ammount);
			if (i == 0) {
				if (ammount - core.getLate_fee() > 0) {
					ammount -= core.getLate_fee();
				} else {
					core.setLate_fee(ammount);
					core.setInstallment_fee(0);
					core.setDeliquency_interest(0);
					core.setInterest(0);
					core.setPrincipal(0);
					core.setPositiveBalance(0);
					core.setSaldoCredito(0);
					core.setTotalInstallment_fee(0);
					ammount = 0;
				}

			}
			if (i == 1) {
				if (ammount - core.getInstallment_fee() > 0) {
					ammount -= core.getInstallment_fee();
				} else {
					core.setInstallment_fee(ammount);
					core.setDeliquency_interest(0);
					core.setInterest(0);
					core.setPrincipal(0);
					core.setPositiveBalance(0);
					core.setSaldoCredito(0);
					ammount = 0;
					core.setTotalInstallment_fee(0);
				}

			}

			if (i == 2) {

				if (ammount - core.getDeliquency_interest() > 0) {
					ammount -= core.getDeliquency_interest();
				} else {
					core.setDeliquency_interest(ammount);
					core.setInterest(0);
					core.setPrincipal(0);
					core.setPositiveBalance(0);
					core.setSaldoCredito(0);
					ammount = 0;
					core.setTotalInstallment_fee(0);
				}

			}
		
			if (i == 3) {
				if (ammount - core.getInterest() > 0) {
					ammount -= core.getInterest();
				} else {
					core.setInterest(ammount);
					core.setPrincipal(0);
					core.setPositiveBalance(0);
					core.setSaldoCredito(0);
					ammount = 0;
					core.setTotalInstallment_fee(0);
				}
			}
			
			if (i == 4) {
				if (ammount - core.getPrincipal() > 0) {
					ammount -= core.getPrincipal();
				} else {
					core.setPrincipal(ammount);
					core.setPositiveBalance(0);
					core.setSaldoCredito(0);
					core.setTotalInstallment_fee(0);
					ammount = 0;
				}
			}
			if (i == 5) {
				if (ammount - core.getTotalInstallment_fee()> 0) {
					ammount -= core.getTotalInstallment_fee();
				} else {
					core.setTotalInstallment_fee(ammount);
					core.setSaldoCredito(0);
					core.setPositiveBalance(0);
					ammount = 0;
				}
			}
			
			if (i == 6) {
				if (ammount - core.getSaldoCredito()> 0) {
					ammount -= core.getSaldoCredito();
				} else {
					core.setSaldoCredito(ammount);
					core.setPositiveBalance(0);
					ammount = 0;
				}
			}
			if(i==7) {
				core.setPositiveBalance(ammount);
				ammount=0; 
			}
	
				
		}
		

		context.getLogger().log("sale de for");
		context.getLogger().log("calculo de la cascada: " + core.toString());

		return core;
	}

	public Paymentsdto searhClientCore(String idCode, double ammount, Context context, DynamoDBMapper mapper) {
		context.getLogger().log("entra aconsultar nuevo pago");
		GeneralInformation general = new GeneralInformation();
		Credit credit = new Credit();
		Paymentsdto pagos = new Paymentsdto();

		try {
			credit = mapper.load(Credit.class, idCode);
			try {
				context.getLogger().log("Entra al segubndo try: " + general.toString());
				PartnerDto partnerFinancier = mapper.load(PartnerDto.class, credit.getClient());
				PartnerDto partnerApprobe = mapper.load(PartnerDto.class, "APR");
				pagos.setAccountAproobe(partnerApprobe.getAcountBank());
				pagos.setAccountFinancier(partnerFinancier.getAcountBank());
				// context.getLogger().log("GENERAL: "+general.toString());

			} catch (Exception e) {
				context.getLogger().log("no se encuentra el financiaor o generalInformation");
			}

		} catch (Exception e) {
			context.getLogger().log("no se encuentra en credit el id");
		}
		general = mapper.load(GeneralInformation.class, 0);
		ClientCreditsDto core = searhClientCore(credit.getNumberIdBorrower(), idCode, context);
		context.getLogger().log("Informacion del Core: " + core.toString());

		double app;
		double iva;
		double x;
		int ivaTable = general.getIva();

		int financier;
		int approbe;
		double intereses;
		
		core =newCore(core, ammount, context);
		context.getLogger()
				.log("Despues de consultar nuevos valores, comision: " + general.getPorcentajeComisionAdmi());
		context.getLogger().log("iva en table  " + ivaTable);
		
		intereses = core.getDeliquency_interest() + core.getInterest();
		app = intereses * (general.getPorcentajeComisionAdmi() / 100);
		iva = (app * ivaTable) / 100;
		context.getLogger().log("commision : " + app + "    iva: " + iva);
		x = app + iva;
		context.getLogger().log("suma de intereses: " + intereses);
		context.getLogger().log("Valor de X: " + x);
		financier = (int) Math.ceil((core.getPrincipal() + core.getInterest() + core.getDeliquency_interest()) - x+core.getSaldoCredito()+core.getPositiveBalance());
		context.getLogger().log("Valor del financiador: " + financier);
		approbe = (int) Math.floor((core.getInstallment_fee() + core.getLate_fee()) + x+core.getTotalInstallment_fee());
		context.getLogger().log("Valor del approbe: " + approbe);

		pagos.setApprobe(approbe);
		pagos.setFinancier(financier);

		return pagos;
	}
}
