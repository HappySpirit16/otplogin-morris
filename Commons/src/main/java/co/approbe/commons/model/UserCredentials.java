package co.approbe.commons.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "UserCredentials")
public class UserCredentials {

	@DynamoDBHashKey
	@DynamoDBAttribute
	private String id;// userId - Número de cédula del usuario.
	@DynamoDBAttribute
	private String client;// comercio
	@DynamoDBAttribute
	private String userName;// Nombre del usuario.
	@DynamoDBAttribute
	private String userLastName;// Apellido usuario.
	@DynamoDBAttribute
	private Long cellphone;// Número celular del usuario.
	@DynamoDBAttribute
	private String mail;// Correo electrónico del usuario.
	@DynamoDBAttribute
	private String creationDate;// Fecha de creación del usuario.
	@DynamoDBAttribute
	private String status;// Estado activo o inactivo.
	@DynamoDBAttribute
	private String profile;// Perfil de usuario (admin, cajero…)

	public UserCredentials() {

	}	
	
	public UserCredentials(String pName, String pId) {
		this.id=pId;
		this.userName=pName;
	}

	public UserCredentials(String id, String client, String userName, String userLastName, Long cellphone, String mail,
			String creationDate, String status, String profile) {
		super();
		this.id = id;
		this.client = client;
		this.userName = userName;
		this.userLastName = userLastName;
		this.cellphone = cellphone;
		this.mail = mail;
		this.creationDate = creationDate;
		this.status = status;
		this.profile = profile;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public Long getCellphone() {
		return cellphone;
	}

	public void setCellphone(Long cellphone) {
		this.cellphone = cellphone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"client\":\"" + client + "\", \"userName\":\"" + userName
				+ "\", \"userLastName\":\"" + userLastName + "\", \"cellphone\":\"" + cellphone + "\", \"mail\":\""
				+ mail + "\", \"creationDate\":\"" + creationDate + "\", \"status\":\"" + status + "\", \"profile\":\""
				+ profile + "\"}";
	}

}
