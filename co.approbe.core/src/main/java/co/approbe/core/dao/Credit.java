package co.approbe.core.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="credit")
public class Credit {
	@DynamoDBHashKey
	private String id;
	
	@DynamoDBAttribute
	private String entityCode;
	@DynamoDBAttribute
	private String acountBankPartner;
	@DynamoDBAttribute
	private String numberIdBorrower;
	
	@DynamoDBAttribute
	private String numberIdMoneylender;
	
	LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.of("-05:00"));
	DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	@DynamoDBAttribute
	private String dateSaveBD = dateTime.format(dtformat);
	
	@DynamoDBAttribute
	private String fechaAprobacion;
	
	@DynamoDBAttribute
	private String lastPaymentDate;
	
	@DynamoDBAttribute
	private String nameBorrower;
	
	@DynamoDBAttribute
	private double ranking;
	
	@DynamoDBAttribute
	private double active;
	
	@DynamoDBAttribute
	private String gender;
	
	@DynamoDBAttribute
	private String activityBorrower;
	
	@DynamoDBAttribute
	private String warranty;
	
	@DynamoDBAttribute
	private String plannerPay;
	
	@DynamoDBAttribute
	private double saldoCredito;
	
	@DynamoDBAttribute
	private double approveValue;
	
	@DynamoDBAttribute
	private double rateApprobe;
	
	@DynamoDBAttribute
	private double feesApprobe;
	
	@DynamoDBAttribute
	private double gainAprrobe;
	
	@DynamoDBAttribute
	private int statusCredit;
	
	@DynamoDBAttribute
	private double feesPaid;
	
	@DynamoDBAttribute
	private String nextFeesDate;
	
	@DynamoDBAttribute
	private double nextPaid;
	
	@DynamoDBAttribute
	private double rentabilidad;
	
	@DynamoDBAttribute
	private double balancePaid;
	
	@DynamoDBAttribute
	private String customerId;
	
	@DynamoDBAttribute
	private String productNumber;
	
	@DynamoDBAttribute
	private String channel;
	
	@DynamoDBAttribute
	private String creditType;
	
	@DynamoDBAttribute
	private String productType;
	
	@DynamoDBAttribute
	private String datePayment;
	
	@DynamoDBAttribute
	private String ammountAprove;
	
	@DynamoDBAttribute
	private String value;
	
	@DynamoDBAttribute
	private String ratePost;
	
	@DynamoDBAttribute
	private String rateInvoice;
	
	@DynamoDBAttribute
	private String feesAgree;
	
	@DynamoDBAttribute
	private String feesRegularity;
	@DynamoDBAttribute
	private String feesFirstDate;
	@DynamoDBAttribute
	private String feesLastDate;
	@DynamoDBAttribute
	private String paidLastDate;
	@DynamoDBAttribute
	private String ammountNextDate;
	@DynamoDBAttribute
	private String balance;
	@DynamoDBAttribute
	private String coverageFGAValue;
	@DynamoDBAttribute
	private String coverageFGAPercentage;
	@DynamoDBAttribute
	private String chargeOValue;
	@DynamoDBAttribute
	private String chargeOPercentage;
	@DynamoDBAttribute
	private String causedInterest;
	@DynamoDBAttribute
	private String paidInterest;
	@DynamoDBAttribute
	private String defaultInterest;
	@DynamoDBAttribute
	private String causedDefaultInterest;
	@DynamoDBAttribute
	private String costCashing;
	@DynamoDBAttribute
	private String causedCommission;
	@DynamoDBAttribute
	private String paidCommission;
	@DynamoDBAttribute
	private String causedInsurance;
	@DynamoDBAttribute
	private String paidInsurance;
	
	@DynamoDBAttribute
	private String creationDate=dateTime.format(dtformat);
	@DynamoDBAttribute
	private String statusDate;
	@DynamoDBAttribute
	private String ammount;
	@DynamoDBAttribute
	private String status;
	@DynamoDBAttribute
	private String cycle;
	@DynamoDBAttribute
	private String accountingDate;
	@DynamoDBAttribute
	private String client;
	
	@DynamoDBAttribute
	private String accountId;
	
	public Credit() {
		super();
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNumberIdBorrower() {
		return numberIdBorrower;
	}


	public void setNumberIdBorrower(String numberIdBorrower) {
		this.numberIdBorrower = numberIdBorrower;
	}


	public String getNumberIdMoneylender() {
		return numberIdMoneylender;
	}


	public void setNumberIdMoneylender(String numberIdMoneylender) {
		this.numberIdMoneylender = numberIdMoneylender;
	}


	public String getDateSaveBD() {
		return dateSaveBD;
	}


	public void setDateSaveBD(String dateSaveBD) {
		this.dateSaveBD = dateSaveBD;
	}


	public String getFechaAprobacion() {
		return fechaAprobacion;
	}


	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}


	public String getLastPaymentDate() {
		return lastPaymentDate;
	}


	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}


	public String getNameBorrower() {
		return nameBorrower;
	}


	public void setNameBorrower(String nameBorrower) {
		this.nameBorrower = nameBorrower;
	}


	public double getRanking() {
		return ranking;
	}


	public void setRanking(double ranking) {
		this.ranking = ranking;
	}


	public double getActive() {
		return active;
	}


	public void setActive(double active) {
		this.active = active;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getActivityBorrower() {
		return activityBorrower;
	}


	public void setActivityBorrower(String activityBorrower) {
		this.activityBorrower = activityBorrower;
	}


	public String getWarranty() {
		return warranty;
	}


	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}


	public String getPlannerPay() {
		return plannerPay;
	}


	public void setPlannerPay(String plannerPay) {
		this.plannerPay = plannerPay;
	}


	public double getSaldoCredito() {
		return saldoCredito;
	}


	public void setSaldoCredito(double saldoCredito) {
		this.saldoCredito = saldoCredito;
	}


	public double getApproveValue() {
		return approveValue;
	}


	public void setApproveValue(double approveValue) {
		this.approveValue = approveValue;
	}


	public double getRateApprobe() {
		return rateApprobe;
	}


	public void setRateApprobe(double rateApprobe) {
		this.rateApprobe = rateApprobe;
	}


	public double getFeesApprobe() {
		return feesApprobe;
	}


	public void setFeesApprobe(double feesApprobe) {
		this.feesApprobe = feesApprobe;
	}


	public double getGainAprrobe() {
		return gainAprrobe;
	}


	public void setGainAprrobe(double gainAprrobe) {
		this.gainAprrobe = gainAprrobe;
	}


	public int getStatusCredit() {
		return statusCredit;
	}


	public void setStatusCredit(int statusCredit) {
		this.statusCredit = statusCredit;
	}


	public double getFeesPaid() {
		return feesPaid;
	}


	public void setFeesPaid(double feesPaid) {
		this.feesPaid = feesPaid;
	}


	public String getNextFeesDate() {
		return nextFeesDate;
	}


	public void setNextFeesDate(String nextFeesDate) {
		this.nextFeesDate = nextFeesDate;
	}


	public double getNextPaid() {
		return nextPaid;
	}


	public void setNextPaid(double nextPaid) {
		this.nextPaid = nextPaid;
	}


	public double getRentabilidad() {
		return rentabilidad;
	}


	public void setRentabilidad(double rentabilidad) {
		this.rentabilidad = rentabilidad;
	}


	public double getBalancePaid() {
		return balancePaid;
	}


	public void setBalancePaid(double balancePaid) {
		this.balancePaid = balancePaid;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getProductNumber() {
		return productNumber;
	}


	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}


	public String getChannel() {
		return channel;
	}


	public void setChannel(String channel) {
		this.channel = channel;
	}


	public String getCreditType() {
		return creditType;
	}


	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getDatePayment() {
		return datePayment;
	}


	public void setDatePayment(String datePayment) {
		this.datePayment = datePayment;
	}


	public String getAmmountAprove() {
		return ammountAprove;
	}


	public void setAmmountAprove(String ammountAprove) {
		this.ammountAprove = ammountAprove;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getRatePost() {
		return ratePost;
	}


	public void setRatePost(String ratePost) {
		this.ratePost = ratePost;
	}


	public String getRateInvoice() {
		return rateInvoice;
	}


	public void setRateInvoice(String rateInvoice) {
		this.rateInvoice = rateInvoice;
	}


	public String getFeesAgree() {
		return feesAgree;
	}


	public void setFeesAgree(String feesAgree) {
		this.feesAgree = feesAgree;
	}


	public String getFeesRegularity() {
		return feesRegularity;
	}


	public void setFeesRegularity(String feesRegularity) {
		this.feesRegularity = feesRegularity;
	}


	public String getFeesFirstDate() {
		return feesFirstDate;
	}


	public void setFeesFirstDate(String feesFirstDate) {
		this.feesFirstDate = feesFirstDate;
	}


	public String getFeesLastDate() {
		return feesLastDate;
	}


	public void setFeesLastDate(String feesLastDate) {
		this.feesLastDate = feesLastDate;
	}


	public String getPaidLastDate() {
		return paidLastDate;
	}


	public void setPaidLastDate(String paidLastDate) {
		this.paidLastDate = paidLastDate;
	}


	public String getAmmountNextDate() {
		return ammountNextDate;
	}


	public void setAmmountNextDate(String ammountNextDate) {
		this.ammountNextDate = ammountNextDate;
	}


	public String getBalance() {
		return balance;
	}


	public void setBalance(String balance) {
		this.balance = balance;
	}


	public String getCoverageFGAValue() {
		return coverageFGAValue;
	}


	public void setCoverageFGAValue(String coverageFGAValue) {
		this.coverageFGAValue = coverageFGAValue;
	}


	public String getCoverageFGAPercentage() {
		return coverageFGAPercentage;
	}


	public void setCoverageFGAPercentage(String coverageFGAPercentage) {
		this.coverageFGAPercentage = coverageFGAPercentage;
	}


	public String getChargeOValue() {
		return chargeOValue;
	}


	public void setChargeOValue(String chargeOValue) {
		this.chargeOValue = chargeOValue;
	}


	public String getChargeOPercentage() {
		return chargeOPercentage;
	}


	public void setChargeOPercentage(String chargeOPercentage) {
		this.chargeOPercentage = chargeOPercentage;
	}


	public String getCausedInterest() {
		return causedInterest;
	}


	public void setCausedInterest(String causedInterest) {
		this.causedInterest = causedInterest;
	}


	public String getPaidInterest() {
		return paidInterest;
	}


	public void setPaidInterest(String paidInterest) {
		this.paidInterest = paidInterest;
	}


	public String getDefaultInterest() {
		return defaultInterest;
	}


	public void setDefaultInterest(String defaultInterest) {
		this.defaultInterest = defaultInterest;
	}


	public String getCausedDefaultInterest() {
		return causedDefaultInterest;
	}


	public void setCausedDefaultInterest(String causedDefaultInterest) {
		this.causedDefaultInterest = causedDefaultInterest;
	}


	public String getCostCashing() {
		return costCashing;
	}


	public void setCostCashing(String costCashing) {
		this.costCashing = costCashing;
	}


	public String getCausedCommission() {
		return causedCommission;
	}


	public void setCausedCommission(String causedCommission) {
		this.causedCommission = causedCommission;
	}


	public String getPaidCommission() {
		return paidCommission;
	}


	public void setPaidCommission(String paidCommission) {
		this.paidCommission = paidCommission;
	}


	public String getCausedInsurance() {
		return causedInsurance;
	}


	public void setCausedInsurance(String causedInsurance) {
		this.causedInsurance = causedInsurance;
	}


	public String getPaidInsurance() {
		return paidInsurance;
	}


	public void setPaidInsurance(String paidInsurance) {
		this.paidInsurance = paidInsurance;
	}

	

	public String getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}


	public String getStatusDate() {
		return statusDate;
	}


	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}


	public String getAmmount() {
		return ammount;
	}


	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCycle() {
		return cycle;
	}


	public void setCycle(String cycle) {
		this.cycle = cycle;
	}


	public String getAccountingDate() {
		return accountingDate;
	}


	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}


	public String getEntityCode() {
		return entityCode;
	}


	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}


	public String getAcountBankPartner() {
		return acountBankPartner;
	}


	public void setAcountBankPartner(String acountBankPartner) {
		this.acountBankPartner = acountBankPartner;
	}


	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public Credit(String id, String entityCode, String acountBankPartner, String numberIdBorrower,
			String numberIdMoneylender, String dateSaveBD, String fechaAprobacion, String lastPaymentDate,
			String nameBorrower, double ranking, double active, String gender, String activityBorrower, String warranty,
			String plannerPay, double saldoCredito, double approveValue, double rateApprobe, double feesApprobe,
			double gainAprrobe, int statusCredit, double feesPaid, String nextFeesDate, double nextPaid,
			double rentabilidad, double balancePaid, String customerId, String productNumber, String channel,
			String creditType, String productType, String datePayment, String ammountAprove, String value,
			String ratePost, String rateInvoice, String feesAgree, String feesRegularity, String feesFirstDate,
			String feesLastDate, String paidLastDate, String ammountNextDate, String balance, String coverageFGAValue,
			String coverageFGAPercentage, String chargeOValue, String chargeOPercentage, String causedInterest,
			String paidInterest, String defaultInterest, String causedDefaultInterest, String costCashing,
			String causedCommission, String paidCommission, String causedInsurance, String paidInsurance,
			String creationDate, String statusDate, String ammount, String status, String cycle, String accountingDate,
			String client, String accountId) {
		super();
		this.id = id;
		this.entityCode = entityCode;
		this.acountBankPartner = acountBankPartner;
		this.numberIdBorrower = numberIdBorrower;
		this.numberIdMoneylender = numberIdMoneylender;
		this.dateSaveBD = dateSaveBD;
		this.fechaAprobacion = fechaAprobacion;
		this.lastPaymentDate = lastPaymentDate;
		this.nameBorrower = nameBorrower;
		this.ranking = ranking;
		this.active = active;
		this.gender = gender;
		this.activityBorrower = activityBorrower;
		this.warranty = warranty;
		this.plannerPay = plannerPay;
		this.saldoCredito = saldoCredito;
		this.approveValue = approveValue;
		this.rateApprobe = rateApprobe;
		this.feesApprobe = feesApprobe;
		this.gainAprrobe = gainAprrobe;
		this.statusCredit = statusCredit;
		this.feesPaid = feesPaid;
		this.nextFeesDate = nextFeesDate;
		this.nextPaid = nextPaid;
		this.rentabilidad = rentabilidad;
		this.balancePaid = balancePaid;
		this.customerId = customerId;
		this.productNumber = productNumber;
		this.channel = channel;
		this.creditType = creditType;
		this.productType = productType;
		this.datePayment = datePayment;
		this.ammountAprove = ammountAprove;
		this.value = value;
		this.ratePost = ratePost;
		this.rateInvoice = rateInvoice;
		this.feesAgree = feesAgree;
		this.feesRegularity = feesRegularity;
		this.feesFirstDate = feesFirstDate;
		this.feesLastDate = feesLastDate;
		this.paidLastDate = paidLastDate;
		this.ammountNextDate = ammountNextDate;
		this.balance = balance;
		this.coverageFGAValue = coverageFGAValue;
		this.coverageFGAPercentage = coverageFGAPercentage;
		this.chargeOValue = chargeOValue;
		this.chargeOPercentage = chargeOPercentage;
		this.causedInterest = causedInterest;
		this.paidInterest = paidInterest;
		this.defaultInterest = defaultInterest;
		this.causedDefaultInterest = causedDefaultInterest;
		this.costCashing = costCashing;
		this.causedCommission = causedCommission;
		this.paidCommission = paidCommission;
		this.causedInsurance = causedInsurance;
		this.paidInsurance = paidInsurance;
		this.creationDate = creationDate;
		this.statusDate = statusDate;
		this.ammount = ammount;
		this.status = status;
		this.cycle = cycle;
		this.accountingDate = accountingDate;
		this.client = client;
		this.accountId=accountId;
	}


	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"entityCode\":\"" + entityCode + "\", \"acountBankPartner\":\""
				+ acountBankPartner + "\", \"numberIdBorrower\":\"" + numberIdBorrower
				+ "\", \"numberIdMoneylender\":\"" + numberIdMoneylender + "\", \"dateTime\":\"" + dateTime
				+ "\", \"dtformat\":\"" + dtformat + "\", \"dateSaveBD\":\"" + dateSaveBD + "\", \"fechaAprobacion\":\""
				+ fechaAprobacion + "\", \"lastPaymentDate\":\"" + lastPaymentDate + "\", \"nameBorrower\":\""
				+ nameBorrower + "\", \"ranking\":\"" + ranking + "\", \"active\":\"" + active + "\", \"gender\":\""
				+ gender + "\", \"activityBorrower\":\"" + activityBorrower + "\", \"warranty\":\"" + warranty
				+ "\", \"plannerPay\":\"" + plannerPay + "\", \"saldoCredito\":\"" + saldoCredito
				+ "\", \"approveValue\":\"" + approveValue + "\", \"rateApprobe\":\"" + rateApprobe
				+ "\", \"feesApprobe\":\"" + feesApprobe + "\", \"gainAprrobe\":\"" + gainAprrobe
				+ "\", \"statusCredit\":\"" + statusCredit + "\", \"feesPaid\":\"" + feesPaid
				+ "\", \"nextFeesDate\":\"" + nextFeesDate + "\", \"nextPaid\":\"" + nextPaid
				+ "\", \"rentabilidad\":\"" + rentabilidad + "\", \"balancePaid\":\"" + balancePaid
				+ "\", \"customerId\":\"" + customerId + "\", \"productNumber\":\"" + productNumber
				+ "\", \"channel\":\"" + channel + "\", \"creditType\":\"" + creditType + "\", \"productType\":\""
				+ productType + "\", \"datePayment\":\"" + datePayment + "\", \"ammountAprove\":\"" + ammountAprove
				+ "\", \"value\":\"" + value + "\", \"ratePost\":\"" + ratePost + "\", \"rateInvoice\":\"" + rateInvoice
				+ "\", \"feesAgree\":\"" + feesAgree + "\", \"feesRegularity\":\"" + feesRegularity
				+ "\", \"feesFirstDate\":\"" + feesFirstDate + "\", \"feesLastDate\":\"" + feesLastDate
				+ "\", \"paidLastDate\":\"" + paidLastDate + "\", \"ammountNextDate\":\"" + ammountNextDate
				+ "\", \"balance\":\"" + balance + "\", \"coverageFGAValue\":\"" + coverageFGAValue
				+ "\", \"coverageFGAPercentage\":\"" + coverageFGAPercentage + "\", \"chargeOValue\":\"" + chargeOValue
				+ "\", \"chargeOPercentage\":\"" + chargeOPercentage + "\", \"causedInterest\":\"" + causedInterest
				+ "\", \"paidInterest\":\"" + paidInterest + "\", \"defaultInterest\":\"" + defaultInterest
				+ "\", \"causedDefaultInterest\":\"" + causedDefaultInterest + "\", \"costCashing\":\"" + costCashing
				+ "\", \"causedCommission\":\"" + causedCommission + "\", \"paidCommission\":\"" + paidCommission
				+ "\", \"causedInsurance\":\"" + causedInsurance + "\", \"paidInsurance\":\"" + paidInsurance
				+ "\", \"creationDate\":\"" + creationDate + "\", \"statusDate\":\"" + statusDate + "\", \"ammount\":\""
				+ ammount + "\", \"status\":\"" + status + "\", \"cycle\":\"" + cycle + "\", \"accountingDate\":\""
				+ accountingDate + "\", \"client\":\"" + client + "\"}";
	}




	
		
}
