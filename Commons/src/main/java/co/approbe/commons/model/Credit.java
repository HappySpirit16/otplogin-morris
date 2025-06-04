package co.approbe.commons.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "credit")
public class Credit {

	@DynamoDBHashKey
	@DynamoDBAttribute
	private String id;
	@DynamoDBAttribute
	private String identificacion;
	@DynamoDBAttribute
	private String client;
	@DynamoDBAttribute
	private String creditType;
	@DynamoDBAttribute
	private Double saldoCredito;
	@DynamoDBAttribute
	private Double nextPaid;
	@DynamoDBAttribute
	private String nextFeesDate;
	@DynamoDBAttribute
	private Integer feesPaid;
	@DynamoDBAttribute
	private Integer feesApprobe;
	
	public Credit() {
		
	}
	
	public Credit(String id, String identificacion, String client, String creditType, Double saldoCredito,
			Double nextPaid, String nextFeesDate, Integer feesPaid, Integer feesApprobe) {
		super();
		this.id = id;
		this.identificacion = identificacion;
		this.client = client;
		this.creditType = creditType;
		this.saldoCredito = saldoCredito;
		this.nextPaid = nextPaid;
		this.nextFeesDate = nextFeesDate;
		this.feesPaid = feesPaid;
		this.feesApprobe = feesApprobe;
	}




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

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public Double getSaldoCredito() {
		return saldoCredito;
	}

	public void setSaldoCredito(Double saldoCredito) {
		this.saldoCredito = saldoCredito;
	}

	public Double getNextPaid() {
		return nextPaid;
	}

	public void setNextPaid(Double nextPaid) {
		this.nextPaid = nextPaid;
	}

	public String getNextFeesDate() {
		return nextFeesDate;
	}

	public void setNextFeesDate(String nextFeesDate) {
		this.nextFeesDate = nextFeesDate;
	}

	public Integer getFeesPaid() {
		return feesPaid;
	}

	public void setFeesPaid(Integer feesPaid) {
		this.feesPaid = feesPaid;
	}

	public Integer getFeesApprobe() {
		return feesApprobe;
	}

	public void setFeesApprobe(Integer feesApprobe) {
		this.feesApprobe = feesApprobe;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"identificacion\":\"" + identificacion + "\", \"client\":\"" + client
				+ "\", \"creditType\":\"" + creditType + "\", \"saldoCredito\":\"" + saldoCredito
				+ "\", \"nextPaid\":\"" + nextPaid + "\", \"nextFeesDate\":\"" + nextFeesDate + "\", \"feesPaid\":\""
				+ feesPaid + "\", \"feesApprobe\":\"" + feesApprobe + "\"}";
	}

}
