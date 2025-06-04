package transactions.core.strategy;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

import transactions.core.dao.Credit;
import transactions.core.dao.GeneralInformation;

public interface IPaymentsMethod {
String createCredit(Credit credit,GeneralInformation generalInformation, Context context,DynamoDBMapper mapper);

}
