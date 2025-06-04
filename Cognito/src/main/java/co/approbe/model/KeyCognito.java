package co.approbe.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "keyCognito")
public class KeyCognito {
	@DynamoDBHashKey
	@DynamoDBAttribute
	private String partner;
	@DynamoDBAttribute
	private String key;
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "{\"partner\":\"" + partner + "\", \"key\":\"" + key + "\"}";
	}
	
	
}
