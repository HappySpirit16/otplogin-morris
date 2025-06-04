package co.approbe.commons.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import co.approbe.commons.dto.CashPaymentDto;

@DynamoDBTable(tableName = "cashpaymentsplit")
public class CashPaymentSplit {

	@DynamoDBHashKey
	@DynamoDBAttribute(attributeName = "id")
	private String id;
	@DynamoDBAttribute(attributeName = "idCredit")
	private String idCredit;
	@DynamoDBAttribute(attributeName = "client")
	private String client;
	@DynamoDBAttribute(attributeName = "casheer")
	private String casheer;
	@DynamoDBAttribute(attributeName = "paymentId")
	private String paymentId;
	@DynamoDBAttribute(attributeName = "totalAmmount")
	private Long totalAmmount;
	@DynamoDBAttribute(attributeName = "customer")
	private String customer;
	@DynamoDBAttribute(attributeName = "financier")
	private Long financier;
	@DynamoDBAttribute(attributeName = "approbe")
	private Long approbe;
	@DynamoDBAttribute(attributeName = "accountFinancier")
	private String accountFinancier;
	@DynamoDBAttribute(attributeName = "accountAproobe")
	private String accountAproobe;
	@DynamoDBAttribute(attributeName = "datePayment")
	private Integer datePayment;
	@DynamoDBAttribute(attributeName = "dateHourPayment")
	private String dateHourPayment;

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

	public String getCasheer() {
		return casheer;
	}

	public void setCasheer(String casheer) {
		this.casheer = casheer;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Long getTotalAmmount() {
		return totalAmmount;
	}

	public void setTotalAmmount(Long totalAmmount) {
		this.totalAmmount = totalAmmount;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Long getFinancier() {
		return financier;
	}

	public void setFinancier(Long financier) {
		this.financier = financier;
	}

	public Long getApprobe() {
		return approbe;
	}

	public void setApprobe(Long approbe) {
		this.approbe = approbe;
	}

	public String getAccountFinancier() {
		return accountFinancier;
	}

	public void setAccountFinancier(String accountFinancier) {
		this.accountFinancier = accountFinancier;
	}

	public String getAccountAproobe() {
		return accountAproobe;
	}

	public void setAccountAproobe(String accountAproobe) {
		this.accountAproobe = accountAproobe;
	}

	public Integer getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Integer datePayment) {
		this.datePayment = datePayment;
	}

	public String getDateHourPayment() {
		return dateHourPayment;
	}

	public void setDateHourPayment(String dateHourPayment) {
		this.dateHourPayment = dateHourPayment;
	}

	public String getIdCredit() {
		return idCredit;
	}

	public void setIdCredit(String idCredit) {
		this.idCredit = idCredit;
	}

	public void setCashPaymentDto(CashPaymentDto pCashPaymentDto) {
		this.casheer = pCashPaymentDto.getCasheer().getId();
		this.customer = pCashPaymentDto.getCustomer();
	}

}
