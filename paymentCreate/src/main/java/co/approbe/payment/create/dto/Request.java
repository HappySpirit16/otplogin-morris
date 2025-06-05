package co.approbe.payment.create.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
	@JsonProperty("PaymentDesc")
    private String paymentDesc;
    @JsonProperty("PaymentInfoArray")
    private List<Object> paymentInfoArray;
    @JsonProperty("PaymentsArray")
    private List<Object> paymentsArray;
    @JsonProperty("SessionToken")
    private String sessionToken;
    @JsonProperty("Subscription")
    private Object subscription;
    @JsonProperty("SubservicesArray")
    private List<Object> subservicesArray;
    @JsonProperty("EntityCode")
    private long entityCode;
    @JsonProperty("TicketId")
    private Integer ticketIdEcollect;
    @JsonProperty("TrazabilityCode")
    private long trazabilityCode;
    @JsonProperty("TranState")
    private String tranState;
    @JsonProperty("ReturnCode")
    private String returnCode;
    @JsonProperty("TransValue")
    private double transValue;
    @JsonProperty("TransVatValue")
    private double transVatValue;
    @JsonProperty("PayCurrency")
    private String payCurrency;
    @JsonProperty("CurrencyRate")
    private double currencyRate;
    @JsonProperty("BankProcessDate")
    private String bankProcessDate;
    @JsonProperty("FICode")
    private int fiCode;
    @JsonProperty("FiName")
    private String fiName;
    @JsonProperty("PaymentSystem")
    private int paymentSystem;
    @JsonProperty("TransCycle")
    private int transCycle;
    @JsonProperty("Invoice")
    private String invoice;
    @JsonProperty("ReferenceArray")
    private List<Object> referenceArray;
    @JsonProperty("OperationArray")
    private List<Object> operationArray;
    @JsonProperty("SrvCode")
    private Integer srvCode;
	private String operation;
	private String idCode;
	private Long TicketId;
	private String ticketId;
	private String idPayment;
	private String ReturnCode;
	private String Bank;
	private boolean complete;
	private Double value;

	
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
	
	public Long getTticketId() {
		return TicketId;
	}
	public void setTticketId(Long TicketId) {
		this.TicketId = TicketId;
	}
	public Request() {
		super();
	}

	public String getIdPayment() {
		return idPayment;
	}
	public void setIdPayment(String idPayment) {
		this.idPayment = idPayment;
	}
	
	
	public String getReturnCode() {
		return ReturnCode;
	}
	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}
	public String getPaymentDesc() {
		return paymentDesc;
	}
	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}
	public List<Object> getPaymentInfoArray() {
		return paymentInfoArray;
	}
	public void setPaymentInfoArray(List<Object> paymentInfoArray) {
		this.paymentInfoArray = paymentInfoArray;
	}
	public List<Object> getPaymentsArray() {
		return paymentsArray;
	}
	public void setPaymentsArray(List<Object> paymentsArray) {
		this.paymentsArray = paymentsArray;
	}
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	public Object getSubscription() {
		return subscription;
	}
	public void setSubscription(Object subscription) {
		this.subscription = subscription;
	}
	public long getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(long entityCode) {
		this.entityCode = entityCode;
	}
	public Integer getTicketIdEcollect() {
		return ticketIdEcollect;
	}
	public void setTicketIdEcollect(Integer ticketIdEcollect) {
		this.ticketIdEcollect = ticketIdEcollect;
	}
	public long getTrazabilityCode() {
		return trazabilityCode;
	}
	public void setTrazabilityCode(long trazabilityCode) {
		this.trazabilityCode = trazabilityCode;
	}
	public String getTranState() {
		return tranState;
	}
	public void setTranState(String tranState) {
		this.tranState = tranState;
	}
	public double getTransValue() {
		return transValue;
	}
	public void setTransValue(double transValue) {
		this.transValue = transValue;
	}
	public double getTransVatValue() {
		return transVatValue;
	}
	public void setTransVatValue(double transVatValue) {
		this.transVatValue = transVatValue;
	}
	public String getPayCurrency() {
		return payCurrency;
	}
	public void setPayCurrency(String payCurrency) {
		this.payCurrency = payCurrency;
	}
	public double getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(double currencyRate) {
		this.currencyRate = currencyRate;
	}
	public String getBankProcessDate() {
		return bankProcessDate;
	}
	public void setBankProcessDate(String bankProcessDate) {
		this.bankProcessDate = bankProcessDate;
	}
	public int getFiCode() {
		return fiCode;
	}
	public void setFiCode(int fiCode) {
		this.fiCode = fiCode;
	}
	public String getFiName() {
		return fiName;
	}
	public void setFiName(String fiName) {
		this.fiName = fiName;
	}
	public int getPaymentSystem() {
		return paymentSystem;
	}
	public void setPaymentSystem(int paymentSystem) {
		this.paymentSystem = paymentSystem;
	}
	public int getTransCycle() {
		return transCycle;
	}
	public void setTransCycle(int transCycle) {
		this.transCycle = transCycle;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public List<Object> getReferenceArray() {
		return referenceArray;
	}
	public void setReferenceArray(List<Object> referenceArray) {
		this.referenceArray = referenceArray;
	}
	public List<Object> getOperationArray() {
		return operationArray;
	}
	public void setOperationArray(List<Object> operationArray) {
		this.operationArray = operationArray;
	}
	public int getSrvCode() {
		return srvCode;
	}
	public void setSrvCode(int srvCode) {
		this.srvCode = srvCode;
	}
	
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public String getBank() {
		return Bank;
	}
	public void setBank(String bank) {
		Bank = bank;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "{\"paymentDesc\":\"" + paymentDesc + "\", \"paymentInfoArray\":\"" + paymentInfoArray
				+ "\", \"paymentsArray\":\"" + paymentsArray + "\", \"sessionToken\":\"" + sessionToken
				+ "\", \"subscription\":\"" + subscription + "\", \"subservicesArray\":\"" + subservicesArray
				+ "\", \"entityCode\":\"" + entityCode + "\", \"ticketIdEcollect\":\"" + ticketIdEcollect
				+ "\", \"trazabilityCode\":\"" + trazabilityCode + "\", \"tranState\":\"" + tranState
				+ "\", \"returnCode\":\"" + returnCode + "\", \"transValue\":\"" + transValue
				+ "\", \"transVatValue\":\"" + transVatValue + "\", \"payCurrency\":\"" + payCurrency
				+ "\", \"currencyRate\":\"" + currencyRate + "\", \"bankProcessDate\":\"" + bankProcessDate
				+ "\", \"fiCode\":\"" + fiCode + "\", \"fiName\":\"" + fiName + "\", \"paymentSystem\":\""
				+ paymentSystem + "\", \"transCycle\":\"" + transCycle + "\", \"invoice\":\"" + invoice
				+ "\", \"referenceArray\":\"" + referenceArray + "\", \"operationArray\":\"" + operationArray
				+ "\", \"srvCode\":\"" + srvCode + "\", \"operation\":\"" + operation + "\", \"idCode\":\"" + idCode
				+ "\", \"TicketId\":\"" + TicketId + "\", \"ticketId\":\"" + ticketId + "\", \"idPayment\":\""
				+ idPayment + "\", \"ReturnCode\":\"" + ReturnCode + "\", \"Bank\":\"" + Bank + "\", \"complete\":\""
				+ complete + "\", \"value\":\"" + value + "\"}";
	}

	
	

}
