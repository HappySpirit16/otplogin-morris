package co.approbe.commons.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "ErrorCashPayment")
public class ErrorCashPayment {

	@DynamoDBHashKey
	@DynamoDBAttribute(attributeName = "id")
	private String id;
	@DynamoDBAttribute(attributeName = "casheer")
	private String cashPayment;
	@DynamoDBAttribute(attributeName = "datehour")
	private String datehour;
	@DynamoDBAttribute(attributeName = "reason")
	private String reason;
	

	public ErrorCashPayment() {

	}
	
	


	public ErrorCashPayment(String id, String cashPayment, String datehour, String reason) {
		super();
		this.id = id;
		this.cashPayment = cashPayment;
		this.datehour = datehour;
		this.reason = reason;
	}




	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCashPayment() {
		return cashPayment;
	}


	public void setCashPayment(String cashPayment) {
		this.cashPayment = cashPayment;
	}


	public String getDatehour() {
		return datehour;
	}


	public void setDatehour(String datehour) {
		this.datehour = datehour;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}



	

}
