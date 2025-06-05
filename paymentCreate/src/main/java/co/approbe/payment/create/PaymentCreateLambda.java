package co.approbe.payment.create;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;

import co.approbe.payment.create.dto.Paymentsdto;
import co.approbe.payment.create.dto.Request;
import co.approbe.payment.create.dto.SubservicesArray;
import co.approbe.payment.create.service.Env;
import co.approbe.payment.create.service.Transaction;                                  


public class PaymentCreateLambda implements RequestHandler<Object, Object> {
	private ConnectionFactory factory;
	ObjectMapper objectMapper = new ObjectMapper();
	AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	DynamoDBMapper mapper = new DynamoDBMapper(db);
	DynamoDB dynamoDB = new DynamoDB(db);
	Transaction transaction=new Transaction();
	String result="";
	Map<String, String> resultMap=new HashMap<String, String>();
	
    @Override
    public Object handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        ObjectMapper mapperOb = new ObjectMapper();
        Request request=new Request();
		try {
			String requestBody = mapperOb.writeValueAsString(input);
			context.getLogger().log("Antes de readValue");
			request = mapperOb.readValue(requestBody, Request.class);
			context.getLogger().log("despues de readValue y paso a crear factory");
				factory = new ConnectionFactory();
				factory.setUsername(Env.MQ_USER);
				factory.setPassword(Env.MQ_SECUR);
				factory.setHost(Env.MQ_HOST);
				factory.setPort(Integer.parseInt(Env.MQ_PORT));
				factory.useSslProtocol();
				context.getLogger().log("Crea factory");
		} catch (Exception e) {
			context.getLogger().log("error: " + e.getMessage());
			context.getLogger().log("error: " + e.getLocalizedMessage());
		}
        if (request.getOperation()!=null) {
	        switch (request.getOperation()) {
	        case "getToken":
	        	result=transaction.getToken( mapper, db, context);
	        	return result;
			case "create":
				Optional<String> ticket=Optional.of(request.getTicketId());
				resultMap=transaction.create(request.getIdCode(),ticket,request.getValue(),request.getBank(), mapper,  context);
				return resultMap;
		    case "transaction":
		    	Map<String, String> response=new HashMap<String, String>();
		    	response=transaction.transaction(request.getTicketId(),request.getIdPayment(), mapper,  context);
				return response;
		    case "DISPERSION":
		    	List<SubservicesArray> responseDispersion=new ArrayList<SubservicesArray>();
		    	Map<String,List<SubservicesArray>> responseDispersionMap=new HashMap<>();
		    	responseDispersion=transaction.dispersion(request.getIdCode(),request.getValue(), mapper,  context);
		    	responseDispersionMap.put("subservicesArray", responseDispersion);
				return responseDispersionMap;
		    case "pagos":
		    	Paymentsdto pagos=new Paymentsdto(); 
		    	pagos=transaction.searhClientCore(request.getIdCode(),request.getValue(),  context, mapper);
				return pagos;
		    }
        }else if (request.getTicketIdEcollect()!=null){
        	Map<String, String> response=new HashMap<String, String>();
        	response=transaction.webhook(""+request.getTicketIdEcollect(),request, mapper,  context,factory);
			return response;
        }
        
        return null;
    }
    
}
