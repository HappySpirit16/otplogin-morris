package co.approbe.payment.create.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "consecutives")
public class Consecutives {
	@DynamoDBHashKey
	private String id;
	@DynamoDBAttribute
	private Long number;
	@DynamoDBAttribute
	private String consecutive;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	
	public String getConsecutive() {
		return consecutive;
	}
	public void setConsecutive(String consecutive) {
		this.consecutive = consecutive;
	}
	public Consecutives() {
		super();
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"number\":\"" + number + "\", \"consecutive\":\"" + consecutive + "\"}";
	}
	
	

}
