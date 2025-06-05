package co.approbe.payment.create.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "credit")
public class Credit {
	@DynamoDBHashKey
	private String id;
	@DynamoDBAttribute
	private Double nextPaid;
	@DynamoDBAttribute
	private String entityCode;
	@DynamoDBAttribute
	private String nextFeesDate;
	@DynamoDBAttribute
	private String nameBorrower;
	@DynamoDBAttribute
	private String numberIdBorrower;
	@DynamoDBAttribute
	private String lenderDocumentType;
	@DynamoDBAttribute
	private String numberIdMoneylender;
	@DynamoDBAttribute
	private String lenderAccountType;
	@DynamoDBAttribute
	private String lenderNumberAccount;
	@DynamoDBAttribute
	private String lenderName;
	@DynamoDBAttribute
	private String lenderEmail;
	@DynamoDBAttribute
	private Integer saldoCredito;
	@DynamoDBAttribute
	private String accountId;
	@DynamoDBAttribute
	private String client;
	
	public Credit() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getNextPaid() {
		return nextPaid;
	}
	public void setNextPaid(Double nextPaid) {
		this.nextPaid = nextPaid;
	}
	public String getNextFeesDate() {
		return nextFeesDate;
	}
	public void setNextFeesDate(String nextFeesDate) {
		this.nextFeesDate = nextFeesDate;
	}
	public String getNameBorrower() {
		return nameBorrower;
	}
	public void setNameBorrower(String nameBorrower) {
		this.nameBorrower = nameBorrower;
	}
	public String getNumberIdBorrower() {
		return numberIdBorrower;
	}
	public void setNumberIdBorrower(String numberIdBorrower) {
		this.numberIdBorrower = numberIdBorrower;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	
	
	public Integer getSaldoCredito() {
		return saldoCredito;
	}
	public void setSaldoCredito(Integer saldoCredito) {
		this.saldoCredito = saldoCredito;
	}
	public String getLenderDocumentType() {
		return lenderDocumentType;
	}
	public void setLenderDocumentType(String lenderDocumentType) {
		this.lenderDocumentType = lenderDocumentType;
	}
	
	public String getLenderAccountType() {
		return lenderAccountType;
	}
	public void setLenderAccountType(String lenderAccountType) {
		this.lenderAccountType = lenderAccountType;
	}
	public String getLenderNumberAccount() {
		return lenderNumberAccount;
	}
	public void setLenderNumberAccount(String lenderNumberAccount) {
		this.lenderNumberAccount = lenderNumberAccount;
	}
	public String getNumberIdMoneylender() {
		return numberIdMoneylender;
	}
	public void setNumberIdMoneylender(String numberIdMoneylender) {
		this.numberIdMoneylender = numberIdMoneylender;
	}
	public String getLenderName() {
		return lenderName;
	}
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	public String getLenderEmail() {
		return lenderEmail;
	}
	public void setLenderEmail(String lenderEmail) {
		this.lenderEmail = lenderEmail;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"nextPaid\":\"" + nextPaid + "\", \"entityCode\":\"" + entityCode
				+ "\", \"nextFeesDate\":\"" + nextFeesDate + "\", \"nameBorrower\":\"" + nameBorrower
				+ "\", \"numberIdBorrower\":\"" + numberIdBorrower + "\", \"lenderDocumentType\":\""
				+ lenderDocumentType + "\", \"numberIdMoneylender\":\"" + numberIdMoneylender
				+ "\", \"lenderAccountType\":\"" + lenderAccountType + "\", \"lenderNumberAccount\":\""
				+ lenderNumberAccount + "\", \"lenderName\":\"" + lenderName + "\", \"lenderEmail\":\"" + lenderEmail
				+ "\", \"saldoCredito\":\"" + saldoCredito + "\", \"accountId\":\"" + accountId + "\"}";
	}
	
	

}
