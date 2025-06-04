package co.approbe.core.dto;

import java.util.List;

import co.approbe.commons.model.Credit;

public class CreditDto extends Credit {

	private String configId;
	private int simpleDaysPastDue;
	private double principal;
	private double interest;
	private double deliquency_interest;
	private double installment_fee;
	private double TotalInstallment_fee;
	private double late_fee;
	private double unpaid;
	private double number_of_covered_installments;
	private double amount_financed; 
	private boolean is_cancelled;
	private String comercio; 
	private String origination_date; 
	private double fees; 
	private List<PaymentsClientCredit> payments;
	

	public CreditDto() {

	}

	public CreditDto(String pId, Double pSaldoCredito, Double pNextPaid, String pNextFeesDate, Integer pFeesPaid,
			String configId, int simpleDaysPastDue, double principal, double interest,
			double deliquency_interest, double installment_fee, double totalInstallment_fee, double late_fee,
			double unpaid, double number_of_covered_installments, double amount_financed, boolean is_cancelled,String comercio,String origination_date,
			double fees,List<PaymentsClientCredit> payments) {
		setId(pId);
		setSaldoCredito(pSaldoCredito);
		setNextPaid(pNextPaid);
		setNextFeesDate(pNextFeesDate);
		setFeesPaid(pFeesPaid);
		this.configId = configId;
		this.simpleDaysPastDue = simpleDaysPastDue;
		this.principal = principal;
		this.interest = interest;
		this.deliquency_interest = deliquency_interest;
		this.installment_fee = installment_fee;
		TotalInstallment_fee = totalInstallment_fee;
		this.late_fee = late_fee;
		this.unpaid = unpaid;
		this.number_of_covered_installments = number_of_covered_installments;
		this.amount_financed = amount_financed;
		this.is_cancelled = is_cancelled;
		this.comercio=comercio; 
		this.origination_date=origination_date; 
		this.fees=fees; 
		this.payments = payments;
	}



	public int getSimpleDaysPastDue() {
		return simpleDaysPastDue;
	}

	public void setSimpleDaysPastDue(int simpleDaysPastDue) {
		this.simpleDaysPastDue = simpleDaysPastDue;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
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

	public double getNumber_of_covered_installments() {
		return number_of_covered_installments;
	}

	public void setNumber_of_covered_installments(double number_of_covered_installments) {
		this.number_of_covered_installments = number_of_covered_installments;
	}

	public List<PaymentsClientCredit> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentsClientCredit> payments) {
		this.payments = payments;
	}

	public double getTotalInstallment_fee() {
		return TotalInstallment_fee;
	}

	public void setTotalInstallment_fee(double totalInstallment_fee) {
		TotalInstallment_fee = totalInstallment_fee;
	}

	public double getAmount_financed() {
		return amount_financed;
	}

	public void setAmount_financed(double amount_financed) {
		this.amount_financed = amount_financed;
	}

	public boolean isIs_cancelled() {
		return is_cancelled;
	}

	public void setIs_cancelled(boolean is_cancelled) {
		this.is_cancelled = is_cancelled;
	}
	public String getOrigination_date() {
		return origination_date;
	}

	public void setOrigination_date(String origination_date) {
		this.origination_date = origination_date;
	}

	public String getComercio() {
		return comercio;
	}

	public void setComercio(String comercio) {
		this.comercio = comercio;
	}
	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + getId() + "\", \"identificacion\":\"" + getIdentificacion() + "\", \"client\":\""
				+ getClient() + "\", \"creditType\":\"" + getCreditType() + "\", \"simpleDaysPastDue\":\""
				+ simpleDaysPastDue + "\", \"saldoCredito\":\"" + getSaldoCredito() + "\", \"nextPaid\":\""
				+ getNextPaid() + "\", \"configId\":\"" + configId + "\",\"nextFeesDate\":\"" + getNextFeesDate()
				+ "\", \"feesPaid\":\"" + getFeesPaid() + "\"}";
	}

}
