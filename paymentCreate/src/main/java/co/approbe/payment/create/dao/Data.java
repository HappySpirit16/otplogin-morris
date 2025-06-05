package co.approbe.payment.create.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "award")
public class Data {
	@DynamoDBAttribute
	private String identification;
	@DynamoDBHashKey
	private String numberId;
	@DynamoDBAttribute
	private String names;
	@DynamoDBAttribute
	private String accountId;
	@DynamoDBAttribute
	private String lastName;
	@DynamoDBAttribute
	private String cellphone;
	@DynamoDBAttribute
	private String gender;
	@DynamoDBAttribute
	private String email;
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getNumberId() {
		return numberId;
	}
	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Data() {
		super();
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	@Override
	public String toString() {
		return "{\"identification\":\"" + identification + "\", \"numberId\":\"" + numberId + "\", \"names\":\"" + names
				+ "\", \"accountId\":\"" + accountId + "\", \"lastName\":\"" + lastName + "\", \"cellphone\":\""
				+ cellphone + "\", \"gender\":\"" + gender + "\", \"email\":\"" + email + "\"}";
	}
	

}
