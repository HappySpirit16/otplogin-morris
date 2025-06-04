package co.approbe.commons.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "clientotptoken")
public class ClientOtpToken {

	
	@DynamoDBHashKey
	@DynamoDBAttribute
	private String id;
	@DynamoDBAttribute
	private String identificacion;
	@DynamoDBAttribute
	private String token;
	@DynamoDBAttribute
	private String fecha;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"identificacion\":\"" + identificacion+ "\", \"token\":\"" + token +"\"}";
	}
	
	

}
