package transactions.core.adapter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import transactions.core.dao.Credit;
import transactions.core.dto.PaymentTransaction;
import transactions.core.dto.PaymentTransaction.Payment;
import transactions.core.dto.PaymentTransaction.PaymentAllocations;
import transactions.core.dto.Request;

public class P2pImpl implements IP2p {

	@Override
	public String saveTransaction(Request request, DynamoDBMapper mapper, AmazonDynamoDB db, Context context, String configId) {
		PaymentTransaction paymentTransaction=new PaymentTransaction();
		Payment payment=new Payment();
		PaymentAllocations paymentAllocations=new PaymentAllocations();
		List<PaymentAllocations> paymentList=new ArrayList<>();
		Credit credit=new Credit();
		credit=mapper.load(Credit.class,request.getIdCode());
		
		
		
		
		LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.of("-05:00"));
		DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String dateUpdateBD = dateTime.format(dtformat);
		
		String dateString = formatDate(dateUpdateBD);
		
		context.getLogger().log("fechas de transaccio: " +dateString );
		
//		BiFunction<Integer, Double,  Integer> diff = (d, i) -> (int) (d - i);
//		credit.setSaldoCredito(diff.apply(credit.getSaldoCredito(),request.getTransValue()));
		paymentTransaction.setIdBorrower(credit.getNumberIdBorrower());
		paymentTransaction.setActionStrategyPattern("CREATE_PAYMENT");
		paymentTransaction.setOccurredOn(dateString);
		//paymentTransaction.setId(request.getTicketId());
		payment.setAmountPaid((int)Double.parseDouble(request.getAmmount()));
		payment.setConfigId(configId);
		payment.setId(credit.getId());
		payment.setPaymentDate(dateString);
		paymentAllocations.setAmount((int)Double.parseDouble(request.getAmmount()));
		paymentAllocations.setLoan_id(request.getIdCode());
		paymentList.add(paymentAllocations);
		paymentTransaction.setPaymentAllocations(paymentList);
		paymentTransaction.setPayment(payment);
		String body = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			body = objectMapper.writeValueAsString(paymentTransaction);
		} catch (JsonProcessingException e3) {
			 context.getLogger().log("error: " + e3.getMessage());
			 return "fail";
		}
		return body;
	}
	
	public String formatDate(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime localDate = LocalDateTime.parse(dateString, formatter);
		OffsetDateTime dateOffset = localDate.atOffset(ZoneOffset.of("-05:00"));
		DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
		return dateOffset.format(formatterOut);
	}


}
