package transactions.core;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import transactions.core.dto.Env;
import transactions.core.dto.Message;
import transactions.core.dto.RMQEvent;
import transactions.core.dto.Request;
import transactions.core.services.CoreService;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {
	AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	DynamoDBMapper mapper = new DynamoDBMapper(db);
	DynamoDB dynamoDB = new DynamoDB(db);
	String result = "";
	Map<String, String> resultMap = new HashMap<String, String>();

	@Override
	public String handleRequest(Object input, Context context) {
		try {
			Request request = new Request();
			ObjectMapper mapperOb = new ObjectMapper();
			CoreService core = new CoreService();
			context.getLogger().log("ingresa: " + input);
			String data = "";
			RMQEvent rmqEvent = mapperOb.readValue(mapperOb.writeValueAsString(input), RMQEvent.class);
			Map<String, List<Message>> rmqMessagesByQueue = rmqEvent.getRmqMessagesByQueue();
			List<Message> messagesForQueue = rmqMessagesByQueue.get(Env.QUEUE_NAME+"::/");
			for (Message message : messagesForQueue) {
				data = message.getData();
			}
			byte[] decodedBytes = Base64.getDecoder().decode(data);
			String decodedString = new String(decodedBytes);
			context.getLogger().log("ingresa: " + decodedString);

			request = mapperOb.readValue(decodedString, Request.class);
			if (request.getChannel() != null) {
				Object response = core.updateCore(request, mapper, db, context);
				return response.toString();

			} else if ((request.getId() != null)) {
				return core.saveCredit(request.getId(), mapper, db, context);
			}
			return "fail";
		} catch (Exception e) {
			context.getLogger().log("error kordev: " + e.getMessage());
			return "fail";
		}

	}

}
