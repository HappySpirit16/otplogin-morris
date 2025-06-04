package transactions.core.strategy;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

import transactions.core.dao.Credit;
import transactions.core.dao.GeneralInformation;

public class SelectPaymentsMethod {
	private IPaymentsMethod iPaymentsMethod;
	
	public void setPaymentMethod(IPaymentsMethod iPaymentsMethod) {
		this.iPaymentsMethod=iPaymentsMethod;
	}
	
	public String createCredit(Credit credit,GeneralInformation generalInformation, Context context,DynamoDBMapper mapper) {
		return iPaymentsMethod.createCredit(credit, generalInformation, context, mapper);
	}
	
	
}
