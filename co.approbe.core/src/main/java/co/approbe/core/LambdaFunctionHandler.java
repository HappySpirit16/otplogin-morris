package co.approbe.core;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import co.approbe.core.dao.Credit;
import co.approbe.core.dto.Env;
import co.approbe.core.dto.Request;

public class LambdaFunctionHandler implements RequestHandler<Request, Object> {
	private static final Logger logger = Logger.getLogger(LambdaFunctionHandler.class.getName());

	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	DynamoDB db = new DynamoDB(client);
	DynamoDBMapper mapper = new DynamoDBMapper(client);
	private static final String QUEUE_NAME = Env.QUEUE_NAME;
	private static final String EXCHANGE = Env.EXCHANGE_NAME;

	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;
	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String handleRequest(Request request, Context context) {
		try {
			context.getLogger().log("Objeto entrada: " + request.toString());
			factory = new ConnectionFactory();
			factory.setUsername(Env.MQ_USER);
			factory.setPassword(Env.MQ_SECUR);

			// Replace the URL with your information
			factory.setHost(Env.MQ_HOST);
			factory.setPort(Integer.parseInt(Env.MQ_PORT));

			// Allows client to establish a connection over TLS

			factory.useSslProtocol();
		} catch (Exception e1) {
			context.getLogger().log("error conectando a la cola: " + e1.getMessage());
		}
		try {
			Map<String, String> values = new HashMap<>();
			Credit credit = mapper.load(Credit.class, request.getId());
			if (credit != null) {
				credit.setId(request.getId());
				credit.setAcountBankPartner(request.getAcountBankPartner());
				credit.setPlannerPay(request.getPlannerPay());
				credit.setFechaAprobacion(request.getFechaAprobacion());
				credit.setSaldoCredito(Double.parseDouble(request.getSaldoCredito()));
				credit.setApproveValue(Double.parseDouble(request.getApproveValue()));
				credit.setRateApprobe(Double.parseDouble(request.getRateApprobe()));
				credit.setFeesApprobe(Double.parseDouble(request.getFeesApprobe()));
				credit.setGainAprrobe(Double.parseDouble(request.getGainAprrobe()));
				credit.setStatusCredit(Integer.parseInt(request.getStatusCredit()));
				credit.setFeesPaid(Double.parseDouble(request.getFeesPaid()));
				credit.setNextFeesDate(request.getNextFeesDate());
				credit.setNextPaid(Double.parseDouble(request.getNextPaid()));
				credit.setRentabilidad(Double.parseDouble(request.getRentabilidad()));
				credit.setBalancePaid(Double.parseDouble(request.getBalancePaid()));
				credit.setCustomerId(request.getCustomerId());
				credit.setProductNumber(request.getProductNumber());
				credit.setChannel(request.getChannel());
				credit.setCreditType(request.getCreditType());
				credit.setProductType(request.getProductType());
				credit.setDatePayment(request.getDatePayment());
				credit.setAmmountAprove(request.getAmmountAprove());
				credit.setValue(request.getValue());
				credit.setRatePost(request.getRatePost());
				credit.setRateInvoice(request.getRateInvoice());
				credit.setFeesAgree(request.getFeesAgree());
				credit.setFeesRegularity(request.getFeesRegularity());
				credit.setFeesFirstDate(request.getFeesFirstDate());
				credit.setFeesLastDate(request.getFeesLastDate());
				credit.setPaidLastDate(request.getPaidLastDate());
				credit.setAmmountNextDate(request.getAmmountNextDate());
				credit.setBalance(request.getBalance());
				credit.setCoverageFGAValue(request.getCoverageFGAValue());
				credit.setCoverageFGAPercentage(request.getCoverageFGAPercentage());
				credit.setChargeOValue(request.getChargeOValue());
				credit.setChargeOPercentage(request.getChargeOPercentage());
				credit.setCausedInterest(request.getCausedInterest());
				credit.setPaidInterest(request.getPaidInterest());
				credit.setDefaultInterest(request.getDefaultInterest());
				credit.setCausedDefaultInterest(request.getCausedDefaultInterest());
				credit.setCostCashing(request.getCostCashing());
				credit.setCausedCommission(request.getCausedCommission());
				credit.setPaidCommission(request.getPaidCommission());
				credit.setCausedInsurance(request.getCausedInsurance());
				credit.setPaidInsurance(request.getPaidInsurance());
				try {
					credit.setAccountId(request.getAccountId());
					credit.setEntityCode(request.getEntityCode());
				}catch (Exception e) {
					context.getLogger().log("entityCode o AccountID null "+e.getMessage());
				}
				mapper.save(credit);
				HashMap<String, String> value = new HashMap<String, String>();
				String body = "";
				try {
					if (request.getPartial() != null) {
						value.put("idCode", request.getId());
						value.put("channel", "P2P");
						value.put("ammount", request.getNextPaid());
						value.put("ticketId", request.getEntityCode());
						try {
							ObjectMapper objectMapper = new ObjectMapper();
							body = objectMapper.writeValueAsString(value);
							connection = factory.newConnection();
							channel = connection.createChannel();
							channel.basicPublish(EXCHANGE, QUEUE_NAME, null, body.getBytes("UTF-8"));
							context.getLogger().log("objeto cola: " + body);
							channel.close();
							connection.close();
						} catch (Exception e3) {
							logger.severe(e3.getMessage());
						}
					} else {
						value.put("id", request.getId());
						try {
							ObjectMapper objectMapper = new ObjectMapper();
							body = objectMapper.writeValueAsString(value);
							connection = factory.newConnection();
							channel = connection.createChannel();
							channel.basicPublish(EXCHANGE, QUEUE_NAME, null, body.getBytes("UTF-8"));
							context.getLogger().log("objeto cola: " + body);
							channel.close();
							connection.close();
						} catch (Exception e3) {
							logger.severe(e3.getMessage());
						}
					}
				} catch (NullPointerException e) {
					logger.severe(e.getMessage());
					value.put("id", request.getId());
					try {
						ObjectMapper objectMapper = new ObjectMapper();
						body = objectMapper.writeValueAsString(value);
						connection = factory.newConnection();
						channel = connection.createChannel();
						channel.basicPublish(EXCHANGE, QUEUE_NAME, null, body.getBytes("UTF-8"));
						context.getLogger().log("objeto cola: " + body);
						channel.close();
						connection.close();
					} catch (Exception e3) {
						logger.severe(e3.getMessage());
					}
				}
				return "ok";
			} else {
				LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.of("-05:00"));
				DateTimeFormatter dtformat0 = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
				String texto = request.getNumberIdBorrower();
				String ultimosTresCaracteres = texto.substring(texto.length() - 3);
				String date0 = ultimosTresCaracteres + dateTime.format(dtformat0);
				credit = new Credit();
				request.setId(date0);
				credit.setId(date0);
				credit.setAcountBankPartner(request.getAcountBankPartner());
				credit.setWarranty(request.getWarranty());
				credit.setNumberIdBorrower(request.getNumberIdBorrower());
				credit.setNumberIdMoneylender(request.getNumberIdMoneylender());
				credit.setFechaAprobacion(request.getFechaAprobacion());
				credit.setLastPaymentDate(request.getLastPaymentDate());
				credit.setNameBorrower(request.getNameBorrower());
				credit.setRanking(Double.parseDouble(request.getRanking()));
				credit.setActive(Integer.parseInt(request.getActive()));
				credit.setGender(request.getGender());
				credit.setActivityBorrower(request.getActivityBorrower());
				credit.setPlannerPay(request.getPlannerPay());
				credit.setSaldoCredito(Double.parseDouble(request.getSaldoCredito()));
				credit.setApproveValue(Double.parseDouble(request.getApproveValue()));
				credit.setRateApprobe(Double.parseDouble(request.getRateApprobe()));
				credit.setFeesApprobe(Double.parseDouble(request.getFeesApprobe()));
				credit.setGainAprrobe(Double.parseDouble(request.getGainAprrobe()));// "pedirgananciadesdefront"
				credit.setStatusCredit(Integer.parseInt(request.getStatusCredit()));
				credit.setFeesPaid(Double.parseDouble(request.getFeesPaid()));
				credit.setNextFeesDate(request.getNextFeesDate());
				credit.setNextPaid(Double.parseDouble(request.getNextPaid()));
				credit.setRentabilidad(Double.parseDouble(request.getRentabilidad()));// pedir rentabilidad front
				credit.setBalancePaid(Double.parseDouble(request.getBalancePaid()));
				credit.setCustomerId(request.getCustomerId());
				credit.setProductNumber(request.getProductNumber());
				credit.setChannel(request.getChannel());
				credit.setCreditType(request.getCreditType());
				credit.setProductType(request.getProductType());
				credit.setDatePayment(request.getDatePayment());
				credit.setAmmountAprove(request.getAmmountAprove());
				credit.setValue(request.getValue());
				credit.setRatePost(request.getRatePost());
				credit.setRateInvoice(request.getRateInvoice());
				credit.setFeesAgree(request.getFeesAgree());
				credit.setFeesRegularity(request.getFeesRegularity());
				credit.setFeesFirstDate(request.getFeesFirstDate());
				credit.setFeesLastDate(request.getFeesLastDate());
				credit.setPaidLastDate(request.getPaidLastDate());
				credit.setAmmountNextDate(request.getAmmountNextDate());
				credit.setBalance(request.getBalance());
				credit.setCoverageFGAValue(request.getCoverageFGAValue());
				credit.setCoverageFGAPercentage(request.getCoverageFGAPercentage());
				credit.setChargeOValue(request.getChargeOValue());
				credit.setChargeOPercentage(request.getChargeOPercentage());
				credit.setCausedInterest(request.getCausedInterest());
				credit.setPaidInterest(request.getPaidInterest());
				credit.setDefaultInterest(request.getDefaultInterest());
				credit.setCausedDefaultInterest(request.getCausedDefaultInterest());
				credit.setCostCashing(request.getCostCashing());
				credit.setCausedCommission(request.getCausedCommission());
				credit.setPaidCommission(request.getPaidCommission());
				credit.setCausedInsurance(request.getCausedInsurance());
				credit.setPaidInsurance(request.getPaidInsurance());
				credit.setStatusDate(request.getStatusDate());
				credit.setAmmount(request.getAmmount());
				credit.setStatus(request.getStatus());
				credit.setCycle(request.getCycle());
				credit.setAccountingDate(request.getAccountingDate());
				credit.setClient(request.getClient());
				try {
					credit.setAccountId(request.getAccountId());
					credit.setEntityCode(request.getEntityCode());
				}catch (Exception e) {
					context.getLogger().log("entityCode o AccountID null "+e.getMessage());
				}
				mapper.save(credit);
				HashMap<String, String> value = new HashMap<String, String>();
				String body = "";
				value.put("id", request.getId());
				try {
					ObjectMapper objectMapper = new ObjectMapper();
					body = objectMapper.writeValueAsString(value);
					connection = factory.newConnection();
					channel = connection.createChannel();
					channel.basicPublish(EXCHANGE, QUEUE_NAME, null, body.getBytes("UTF-8"));
					context.getLogger().log("objeto cola: " +body);
					channel.close();
					connection.close();
				} catch (Exception e3) {
					logger.severe(e3.getMessage());
				}
				return date0;
			}

		} catch (Exception e) {
			logger.severe(e.getMessage());
			return "fail";
		}
	}

}
