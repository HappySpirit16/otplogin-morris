package transactions.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
	@JsonProperty("TransValue")
    private double transValue;
    @JsonProperty("SrvCode")
    private Integer srvCode;
	private String operation;
	private String idCode;
	private String ticketId;
	private String idPayment;
	private String Bank;
	private boolean complete;
	private String channel;
	private String ammount;
	
    private String id;

	public Integer getSrvCode() {
		return srvCode;
	}

	public void setSrvCode(Integer srvCode) {
		this.srvCode = srvCode;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getIdPayment() {
		return idPayment;
	}

	public void setIdPayment(String idPayment) {
		this.idPayment = idPayment;
	}

	public String getBank() {
		return Bank;
	}

	public void setBank(String bank) {
		Bank = bank;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmmount() {
		return ammount;
	}

	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public double getTransValue() {
		return transValue;
	}

	public void setTransValue(double transValue) {
		this.transValue = transValue;
	}

	@Override
	public String toString() {
		return "{\"transValue\":\"" + transValue + "\", \"srvCode\":\"" + srvCode + "\", \"operation\":\"" + operation
				+ "\", \"idCode\":\"" + idCode + "\", \"ticketId\":\"" + ticketId + "\", \"idPayment\":\"" + idPayment
				+ "\", \"Bank\":\"" + Bank + "\", \"complete\":\"" + complete + "\", \"channel\":\"" + channel
				+ "\", \"ammount\":\"" + ammount + "\", \"id\":\"" + id + "\"}";
	}

		
	
}
