package co.approbe.commons.dto;


public class CreditDto {

	private String id;  
	private String numberIdBorrower= " ";  
	private String numberIdMoneylender=" ";
	private String lastPaymentDate;
	private String nameBorrower;
	private String ranking;
	private String active="6";
	private String gender;
	private String activityBorrower;
	private String warranty;
	private String plannerPay;
	private String nextFeesDate;
	private String rentabilidad;
	private String customerId;
	private String productNumber;
	private String channel;
	private String creditType; 
	private String productType;
	private String datePayment;
	private String ammountAprove;
	private String value;
	private String ratePost;
	private String rateInvoice;
	private String feesAgree;
	private String feesRegularity;
	private String feesFirstDate;
	private String feesLastDate;
	private String paidLastDate;
	private String ammountNextDate;
	private String balance;
	private String coverageFGAValue;
	private String coverageFGAPercentage;
	private String chargeOValue;
	private String chargeOPercentage;
	private String causedInterest;
	private String paidInterest;
	private String defaultInterest;
	private String causedDefaultInterest;
	private String costCashing;
	private String causedCommission;
	private String paidCommission;
	private String causedInsurance;
	private String paidInsurance;

	private String fechaAprobacion;
	private String feesApprobe;
	private String feesPaid;
	private String approveValue;
	private String rateApprobe;
	private String balancePaid;
	private String saldoCredito;
	private String nextPaid;
	private String idCredit;
	private String statusCredit;
	private String gainAprrobe;
	private String cycle; 
	private String client;

	
	public CreditDto() {
	
	}


	public CreditDto(String id, String numberIdBorrower, String numberIdMoneylender, String lastPaymentDate,
			String nameBorrower, String ranking, String active, String gender, String activityBorrower, String warranty,
			String plannerPay, String nextFeesDate, String rentabilidad, String customerId, String productNumber,
			String channel, String creditType, String productType, String datePayment, String ammountAprove,
			String value, String ratePost, String rateInvoice, String feesAgree, String feesRegularity,
			String feesFirstDate, String feesLastDate, String paidLastDate, String ammountNextDate, String balance,
			String coverageFGAValue, String coverageFGAPercentage, String chargeOValue, String chargeOPercentage,
			String causedInterest, String paidInterest, String defaultInterest, String causedDefaultInterest,
			String costCashing, String causedCommission, String paidCommission, String causedInsurance,
			String paidInsurance, String fechaAprobacion, String feesApprobe, String feesPaid, String approveValue,
			String rateApprobe, String balancePaid, String saldoCredito, String nextPaid, String idCredit,
			String statusCredit, String gainAprrobe, ResultSignature resultSignature, String cycle, String client) {
		super();
		this.id = id;
		this.numberIdBorrower = numberIdBorrower;
		this.numberIdMoneylender = numberIdMoneylender;
		this.lastPaymentDate = lastPaymentDate;
		this.nameBorrower = nameBorrower;
		this.ranking = ranking;
		this.active = active;
		this.gender = gender;
		this.activityBorrower = activityBorrower;
		this.warranty = warranty;
		this.plannerPay = plannerPay;
		this.nextFeesDate = nextFeesDate;
		this.rentabilidad = rentabilidad;
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
		this.fechaAprobacion = fechaAprobacion;
		this.feesApprobe = feesApprobe;
		this.feesPaid = feesPaid;
		this.approveValue = approveValue;
		this.rateApprobe = rateApprobe;
		this.balancePaid = balancePaid;
		this.saldoCredito = saldoCredito;
		this.nextPaid = nextPaid;
		this.idCredit = idCredit;
		this.statusCredit = statusCredit;
		this.gainAprrobe = gainAprrobe;
		this.cycle = cycle;
		this.client=client; 
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}


	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
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

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
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

	public String getNextFeesDate() {
		return nextFeesDate;
	}

	public void setNextFeesDate(String nextFeesDate) {
		this.nextFeesDate = nextFeesDate;
	}

	public String getRentabilidad() {
		return rentabilidad;
	}

	public void setRentabilidad(String rentabilidad) {
		this.rentabilidad = rentabilidad;
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

	public String getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public String getFeesApprobe() {
		return feesApprobe;
	}

	public void setFeesApprobe(String feesApprobe) {
		this.feesApprobe = feesApprobe;
	}

	public String getFeesPaid() {
		return feesPaid;
	}

	public void setFeesPaid(String feesPaid) {
		this.feesPaid = feesPaid;
	}

	public String getApproveValue() {
		return approveValue;
	}

	public void setApproveValue(String approveValue) {
		this.approveValue = approveValue;
	}

	public String getRateApprobe() {
		return rateApprobe;
	}

	public void setRateApprobe(String rateApprobe) {
		this.rateApprobe = rateApprobe;
	}

	public String getBalancePaid() {
		return balancePaid;
	}

	public void setBalancePaid(String balancePaid) {
		this.balancePaid = balancePaid;
	}

	public String getSaldoCredito() {
		return saldoCredito;
	}

	public void setSaldoCredito(String saldoCredito) {
		this.saldoCredito = saldoCredito;
	}

	public String getNextPaid() {
		return nextPaid;
	}

	public void setNextPaid(String nextPaid) {
		this.nextPaid = nextPaid;
	}

	public String getIdCredit() {
		return idCredit;
	}

	public void setIdCredit(String idCredit) {
		this.idCredit = idCredit;
	}

	public String getStatusCredit() {
		return statusCredit;
	}

	public void setStatusCredit(String statusCredit) {
		this.statusCredit = statusCredit;
	}

	public String getGainAprrobe() {
		return gainAprrobe;
	}

	public void setGainAprrobe(String gainAprrobe) {
		this.gainAprrobe = gainAprrobe;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"numberIdBorrower\":\"" + numberIdBorrower + "\", \"numberIdMoneylender\":\""
				+ numberIdMoneylender + "\", \"lastPaymentDate\":\"" + lastPaymentDate + "\", \"nameBorrower\":\""
				+ nameBorrower + "\", \"ranking\":\"" + ranking + "\", \"active\":\"" + active + "\", \"gender\":\""
				+ gender + "\", \"activityBorrower\":\"" + activityBorrower + "\", \"warranty\":\"" + warranty
				+ "\", \"plannerPay\":\"" + plannerPay + "\", \"nextFeesDate\":\"" + nextFeesDate
				+ "\", \"rentabilidad\":\"" + rentabilidad + "\", \"customerId\":\"" + customerId
				+ "\", \"productNumber\":\"" + productNumber + "\", \"channel\":\"" + channel + "\", \"creditType\":\""
				+ creditType + "\", \"productType\":\"" + productType + "\", \"datePayment\":\"" + datePayment
				+ "\", \"ammountAprove\":\"" + ammountAprove + "\", \"value\":\"" + value + "\", \"ratePost\":\""
				+ ratePost + "\", \"rateInvoice\":\"" + rateInvoice + "\", \"feesAgree\":\"" + feesAgree
				+ "\", \"feesRegularity\":\"" + feesRegularity + "\", \"feesFirstDate\":\"" + feesFirstDate
				+ "\", \"feesLastDate\":\"" + feesLastDate + "\", \"paidLastDate\":\"" + paidLastDate
				+ "\", \"ammountNextDate\":\"" + ammountNextDate + "\", \"balance\":\"" + balance
				+ "\", \"coverageFGAValue\":\"" + coverageFGAValue + "\", \"coverageFGAPercentage\":\""
				+ coverageFGAPercentage + "\", \"chargeOValue\":\"" + chargeOValue + "\", \"chargeOPercentage\":\""
				+ chargeOPercentage + "\", \"causedInterest\":\"" + causedInterest + "\", \"paidInterest\":\""
				+ paidInterest + "\", \"defaultInterest\":\"" + defaultInterest + "\", \"causedDefaultInterest\":\""
				+ causedDefaultInterest + "\", \"costCashing\":\"" + costCashing + "\", \"causedCommission\":\""
				+ causedCommission + "\", \"paidCommission\":\"" + paidCommission + "\", \"causedInsurance\":\""
				+ causedInsurance + "\", \"paidInsurance\":\"" + paidInsurance + "\", \"fechaAprobacion\":\""
				+ fechaAprobacion + "\", \"feesApprobe\":\"" + feesApprobe + "\", \"feesPaid\":\"" + feesPaid
				+ "\", \"approveValue\":\"" + approveValue + "\", \"rateApprobe\":\"" + rateApprobe
				+ "\", \"balancePaid\":\"" + balancePaid + "\", \"saldoCredito\":\"" + saldoCredito
				+ "\", \"nextPaid\":\"" + nextPaid + "\", \"idCredit\":\"" + idCredit + "\", \"statusCredit\":\""
				+ statusCredit + "\", \"gainAprrobe\":\"" + gainAprrobe + "\",\"cycle\":\""+cycle+"\",\"client\":\""+client+"\"}";
	}

//	@Override
//	public String toString() {
//		return "{\"fechaAprobacion\":\"" + fechaAprobacion + "\", \"feesApprobe\":\"" + feesApprobe
//				+ "\", \"feesPaid\":\"" + feesPaid + "\", \"approveValue\":\"" + approveValue + "\", \"rateApprobe\":\""
//				+ rateApprobe + "\", \"balancePaid\":\"" + balancePaid + "\", \"saldoCredito\":\"" + saldoCredito
//				+ "\", \"nextPaid\":\"" + nextPaid + "\", \"idCredit\":\"" + idCredit + "\", \"statusCredit\":\""
//				+ statusCredit + "\", \"gainAprrobe\":\"" + gainAprrobe + "\"}";
//	}
	
}
