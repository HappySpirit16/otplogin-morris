package co.approbe.payment.create.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "ecollectToken")

public class TokenEcollect {
	
	@DynamoDBHashKey
	private String id;
	@DynamoDBAttribute
	private String token;
	@DynamoDBAttribute
	private Double time;
	
	LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.of("-05:00"));
	DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	@DynamoDBAttribute
	private String dateSave = dateTime.format(dtformat);
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Double getTime() {
		return time;
	}
	public void setTime(Double time) {
		this.time = time;
	}
	
	public String getDateSave() {
		return dateSave;
	}
	public void setDateSave(String dateSave) {
		this.dateSave = dateSave;
	}
	public TokenEcollect() {
		super();
	}
	
	

}
