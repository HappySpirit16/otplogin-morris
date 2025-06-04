package transactions.core.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "customerbnpl")
public class Customerbnpl {
	@DynamoDBHashKey
	private String id;
	@DynamoDBAttribute
	private Integer fGA;
	@DynamoDBAttribute
	private Double platform;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getfGA() {
		return fGA;
	}

	public void setfGA(Integer fGA) {
		this.fGA = fGA;
	}

	public Double getPlatform() {
		return platform;
	}

	public void setPlatform(Double platform) {
		this.platform = platform;
	}

	public Customerbnpl() {
		super();
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"fGA\":\"" + fGA + "\", \"platform\":\"" + platform + "\"}";
	}
	
	
}
