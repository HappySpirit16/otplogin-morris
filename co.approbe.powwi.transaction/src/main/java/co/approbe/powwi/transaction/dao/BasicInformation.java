package co.approbe.powwi.transaction.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "personalInformation")
public class BasicInformation {
	@DynamoDBHashKey
	private String numberId;
	@DynamoDBAttribute
	private String idCuenta;
	@DynamoDBAttribute
	private String tokenTransaccional;
	@DynamoDBAttribute
	private String dateUpdate;
	@DynamoDBAttribute
	private String dateCreate;
	@DynamoDBAttribute
	private String huella;
	@DynamoDBAttribute
	private String names;
	@DynamoDBAttribute
	private String lastname;
	@DynamoDBAttribute
	private String expeditionDate;
	@DynamoDBAttribute
	private String birthDate;
	@DynamoDBAttribute
	private String email;
	@DynamoDBAttribute
	private String tokenConsulta;
	@DynamoDBAttribute
	private String idOperacion;
	@DynamoDBAttribute
	private String respuestaDispersion;

	public String getNumberId() {
		return numberId;
	}



	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}



	public String getIdCuenta() {
		return idCuenta;
	}



	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}



	public String getTokenTransaccional() {
		return tokenTransaccional;
	}



	public void setTokenTransaccional(String tokenTransaccional) {
		this.tokenTransaccional = tokenTransaccional;
	}



	public String getDateUpdate() {
		return dateUpdate;
	}



	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}



	public String getDateCreate() {
		return dateCreate;
	}



	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}



	public String getHuella() {
		return huella;
	}



	public void setHuella(String huella) {
		this.huella = huella;
	}



	public String getNames() {
		return names;
	}



	public void setNames(String names) {
		this.names = names;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	

	public String getExpeditionDate() {
		return expeditionDate;
	}



	public void setExpeditionDate(String expeditionDate) {
		this.expeditionDate = expeditionDate;
	}



	public String getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


	public String getTokenConsulta() {
		return tokenConsulta;
	}



	public void setTokenConsulta(String tokenConsulta) {
		this.tokenConsulta = tokenConsulta;
	}

	

	public String getIdOperacion() {
		return idOperacion;
	}



	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}




	public String getRespuestaDispersion() {
		return respuestaDispersion;
	}



	public void setRespuestaDispersion(String respuestaDispersion) {
		this.respuestaDispersion = respuestaDispersion;
	}



	


	public BasicInformation(String numberId, String idCuenta, String tokenTransaccional, String dateUpdate,
			String dateCreate, String huella, String names, String lastname, String expeditionDate, String birthDate,
			String email, String tokenConsulta, String idOperacion, String respuestaDispersion) {
		super();
		this.numberId = numberId;
		this.idCuenta = idCuenta;
		this.tokenTransaccional = tokenTransaccional;
		this.dateUpdate = dateUpdate;
		this.dateCreate = dateCreate;
		this.huella = huella;
		this.names = names;
		this.lastname = lastname;
		this.expeditionDate = expeditionDate;
		this.birthDate = birthDate;
		this.email = email;
		this.tokenConsulta = tokenConsulta;
		this.idOperacion = idOperacion;
		this.respuestaDispersion = respuestaDispersion;
	}



	public BasicInformation() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "{\"numberId\":\"" + numberId + "\", \"idCuenta\":\"" + idCuenta + "\", \"tokenTransaccional\":\""
				+ tokenTransaccional + "\", \"dateUpdate\":\"" + dateUpdate + "\", \"dateCreate\":\"" + dateCreate
				+ "\", \"huella\":\"" + huella + "\", \"names\":\"" + names + "\", \"lastname\":\"" + lastname
				+ "\", \"expeditionDate\":\"" + expeditionDate + "\", \"birthDate\":\"" + birthDate + "\", \"email\":\""
				+ email + "\", \"tokenConsulta\":\"" + tokenConsulta + "\", \"idOperacion\":\"" + idOperacion
				+ "\", \"respuestaDispersion\":\"" + respuestaDispersion + "\"}";
	}






	
}
