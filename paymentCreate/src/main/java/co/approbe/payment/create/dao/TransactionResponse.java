package co.approbe.payment.create.dao;

import java.util.Arrays;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

public class TransactionResponse {

	@DynamoDBAttribute
	private String EntityCode;
	@DynamoDBAttribute
	private String SrvCode;
	@DynamoDBAttribute
	private String TranState;
	@DynamoDBAttribute
	private String TransValue;
	@DynamoDBAttribute
	private String TransVatValue;
	@DynamoDBAttribute
	private String BankProcessDate;
	@DynamoDBAttribute
	private String FICode;
	
	private String[] ReferenceArray;

	public String getEntityCode() {
		return EntityCode;
	}

	public void setEntityCode(String entityCode) {
		EntityCode = entityCode;
	}

	public String getSRVcode() {
		return SrvCode;
	}

	public void setSRVcode(String sRVcode) {
		SrvCode = sRVcode;
	}

	public String getTranState() {
		return TranState;
	}

	public void setTranState(String tranState) {
		TranState = tranState;
	}

	public String getTransValue() {
		return TransValue;
	}

	public void setTransValue(String transValue) {
		TransValue = transValue;
	}

	public String getTransVatValue() {
		return TransVatValue;
	}

	public void setTransVatValue(String transVatValue) {
		TransVatValue = transVatValue;
	}

	public String getBanckProcessDate() {
		return BankProcessDate;
	}

	public void setBanckProcessDate(String banckProcessDate) {
		BankProcessDate = banckProcessDate;
	}

	public String getFiCode() {
		return FICode;
	}

	public void setFiCode(String fiCode) {
		FICode = fiCode;
	}
	@DynamoDBAttribute(attributeName = "ReferenceArray")
	public String[] getReferenceArray() {
		return ReferenceArray;
	}

	public void setReferenceArray(String[] referenceArray) {
		ReferenceArray = referenceArray;
	}

	public TransactionResponse() {
		super();
	}

	@Override
	public String toString() {
		return "{\"EntityCode\":\"" + EntityCode + "\", \"SrvCode\":\"" + SrvCode + "\", \"TranState\":\"" + TranState
				+ "\", \"TransValue\":\"" + TransValue + "\", \"TransVatValue\":\"" + TransVatValue
				+ "\", \"BankProcessDate\":\"" + BankProcessDate + "\", \"FICode\":\"" + FICode
				+ "\", \"ReferenceArray\":\"" + Arrays.toString(ReferenceArray) + "\"}";
	}

}
