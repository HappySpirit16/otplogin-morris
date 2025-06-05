package co.approbe.payment.create.dto;

public class ClientCreditsDto {
	
	private String id; 
	private double saldoCredito; 
	private double nextPaid; 
	private String nextFeesDate; 
	private double feesPaid;
	private String configId; 
	private double simpleDaysPastDue;
	private double principal; 
	private double interest; 
	private double deliquency_interest; 
	private double installment_fee; 
	private double late_fee; 
	private double unpaid; 
	private double totalInstallment_fee;
	private double positiveBalance; 
	
	public ClientCreditsDto() {
		super();
	}

	public ClientCreditsDto(String id, double saldoCredito, double nextPaid, String nextFeesDate, double feesPaid,
			String configId, double simpleDaysPastDue, double principal, double interest, double deliquency_interest,
			double installment_fee, double late_fee, double unpaid,double totalInstallment_fee,double positiveBalance) {
		super();
		this.id = id;
		this.saldoCredito = saldoCredito;
		this.nextPaid = nextPaid;
		this.nextFeesDate = nextFeesDate;
		this.feesPaid = feesPaid;
		this.configId = configId;
		this.simpleDaysPastDue = simpleDaysPastDue;
		this.principal = principal;
		this.interest = interest;
		this.deliquency_interest = deliquency_interest;
		this.installment_fee = installment_fee;
		this.late_fee = late_fee;
		this.unpaid = unpaid;
		this.totalInstallment_fee=totalInstallment_fee; 
		this.positiveBalance=positiveBalance; 
	}

	public String getId() {
		return id;
	}
	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public double getSimpleDaysPastDue() {
		return simpleDaysPastDue;
	}

	public void setSimpleDaysPastDue(double simpleDaysPastDue) {
		this.simpleDaysPastDue = simpleDaysPastDue;
	}

	public void setId(String id) {
		this.id = id;
	}
	public double getSaldoCredito() {
		return saldoCredito;
	}
	public void setSaldoCredito(double saldoCredito) {
		this.saldoCredito = saldoCredito;
	}
	public double getNextPaid() {
		return nextPaid;
	}
	public void setNextPaid(double nextPaid) {
		this.nextPaid = nextPaid;
	}
	public String getNextFeesDate() {
		return nextFeesDate;
	}
	public void setNextFeesDate(String nextFeesDate) {
		this.nextFeesDate = nextFeesDate;
	}
	public double getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(double feesPaid) {
		this.feesPaid = feesPaid;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getDeliquency_interest() {
		return deliquency_interest;
	}

	public void setDeliquency_interest(double deliquency_interest) {
		this.deliquency_interest = deliquency_interest;
	}

	public double getInstallment_fee() {
		return installment_fee;
	}

	public void setInstallment_fee(double installment_fee) {
		this.installment_fee = installment_fee;
	}

	public double getLate_fee() {
		return late_fee;
	}

	public void setLate_fee(double late_fee) {
		this.late_fee = late_fee;
	}

	public double getUnpaid() {
		return unpaid;
	}

	public void setUnpaid(double unpaid) {
		this.unpaid = unpaid;
	}
	public double getTotalInstallment_fee() {
		return totalInstallment_fee;
	}

	public void setTotalInstallment_fee(double totalInstallment_fee) {
		this.totalInstallment_fee = totalInstallment_fee;
	}

	public double getPositiveBalance() {
		return positiveBalance;
	}

	public void setPositiveBalance(double positiveBalance) {
		this.positiveBalance = positiveBalance;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"saldoCredito\":\"" + saldoCredito + "\", \"nextPaid\":\"" + nextPaid
				+ "\", \"nextFeesDate\":\"" + nextFeesDate + "\", \"feesPaid\":\"" + feesPaid + "\", \"configId\":\""
				+ configId + "\", \"simpleDaysPastDue\":\"" + simpleDaysPastDue + "\", \"principal\":\"" + principal
				+ "\", \"interest\":\"" + interest + "\", \"deliquency_interest\":\"" + deliquency_interest
				+ "\", \"installment_fee\":\"" + installment_fee + "\", \"late_fee\":\"" + late_fee
				+ "\", \"unpaid\":\"" + unpaid + "\", \"totalInstallment_fee\":\"" + totalInstallment_fee
				+ "\", \"positiveBalance\":\"" + positiveBalance + "\"}";
	}




}
