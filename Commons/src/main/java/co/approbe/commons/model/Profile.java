package co.approbe.commons.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "profile")
public class Profile {

	@DynamoDBHashKey
	@DynamoDBAttribute(attributeName = "id")
	private String id;
	@DynamoDBAttribute(attributeName = "access")
	private List<String> access;// permisos


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getAccess() {
		return access;
	}

	public void setAccess(List<String> access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"access\":\"" + access + "\"}";
	}
	
	

}
