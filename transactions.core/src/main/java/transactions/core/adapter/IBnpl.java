package transactions.core.adapter;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

import transactions.core.dto.Request;

public interface IBnpl {
	String saveTransaction(Request request, DynamoDBMapper mapper, AmazonDynamoDB db, Context context, String configId);
}
