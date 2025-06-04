package co.approbe.commons.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "cashpayment")
public class CashPayment {

	@DynamoDBHashKey
	@DynamoDBAttribute(attributeName = "id")
	private String id;
	@DynamoDBAttribute(attributeName = "casheer")
	private String casheer;
	@DynamoDBAttribute(attributeName = "customer")
	private String customer;
	@DynamoDBAttribute(attributeName = "totalAmmount")
	private Double totalAmmount;
	@DynamoDBAttribute(attributeName = "credits")
	private String credits;
	@DynamoDBAttribute(attributeName = "datehour")
	private String datehour;
	@DynamoDBAttribute(attributeName = "coreId")
	private String coreId;
	@DynamoDBAttribute(attributeName = "paymentdate")
	private Integer paymentDate;
	@DynamoDBAttribute(attributeName = "client")
	private String client;

	public CashPayment() {

	}

	public CashPayment(String id, String casheer, String customer, Double totalAmmount, String credits, String datehour,
			String coreId, Integer paymentDate, String pClient) {
		super();
		this.id = id;
		this.casheer = casheer;
		this.customer = customer;
		this.totalAmmount = totalAmmount;
		this.credits = credits;
		this.datehour = datehour;
		this.coreId = coreId;
		this.paymentDate = paymentDate;
		this.client= pClient;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCasheer() {
		return casheer;
	}

	public void setCasheer(String casheer) {
		this.casheer = casheer;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Double getTotalAmmount() {
		return totalAmmount;
	}

	public void setTotalAmmount(Double totalAmmount) {
		this.totalAmmount = totalAmmount;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getDatehour() {
		return datehour;
	}

	public void setDatehour(String datehour) {
		this.datehour = datehour;
	}

	public String getCoreId() {
		return coreId;
	}

	public void setCoreId(String coreId) {
		this.coreId = coreId;
	}

	public Integer getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Integer paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"casheer\":\"" + casheer + "\", \"customer\":\"" + customer
				+ "\", \"totalAmmount\":\"" + totalAmmount + "\", \"credits\":\"" + credits + "\", \"datehour\":\""
				+ datehour + "\", \"coreId\":\"" + coreId + "\", \"paymentDate\":\"" + paymentDate + "\", \"client\":\""
				+ client + "\"}";
	}

}
