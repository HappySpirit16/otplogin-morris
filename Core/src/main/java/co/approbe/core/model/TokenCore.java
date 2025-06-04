package co.approbe.core.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "tokenCore")
public class TokenCore {
	
	@DynamoDBHashKey
	@DynamoDBAttribute
	private Long id;
	
	@DynamoDBAttribute
	private String access_token;
	
	@DynamoDBAttribute
	private String dateToken;

	public TokenCore() {
		
	}
	
	public TokenCore(Long pId, String pToken, String pDateToken) {
		
		this.id=pId;
		this.access_token=pToken;
		this.dateToken= pDateToken;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessToken() {
		return access_token;
	}

	public void setAccessToken(String accessToken) {
		this.access_token = accessToken;
	}
	
	public String getDateToken() {
		return dateToken;
	}

	public void setDateToken(String dateToken) {
		this.dateToken = dateToken;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"access_token\":\"" + access_token + "\", \"dateToken\":\"" + dateToken
				+ "\"}";
	}

	
}
