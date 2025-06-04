package co.approbe.core.dto;

public class Loan_allocation_dto {	
	private String loan_id; 
	private double total; 
	private DetailsPaymentsdto principal; 
	private DetailsPaymentsdto interest; 
	private DetailsPaymentsdto delinquency_interest;
	private DetailsPaymentsdto installment_fee; 
	private DetailsPaymentsdto daily_fee; 
	private DetailsPaymentsdto late_fee;
	
	public Loan_allocation_dto() {
	
	}

	public Loan_allocation_dto(String loan_id, double total, DetailsPaymentsdto principal, DetailsPaymentsdto interest,
			DetailsPaymentsdto delinquency_interest, DetailsPaymentsdto installment_fee, DetailsPaymentsdto daily_fee,
			DetailsPaymentsdto late_fee) {
		super();
		this.loan_id = loan_id;
		this.total = total;
		this.principal = principal;
		this.interest = interest;
		this.delinquency_interest = delinquency_interest;
		this.installment_fee = installment_fee;
		this.daily_fee = daily_fee;
		this.late_fee = late_fee;
	}

	public String getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(String loan_id) {
		this.loan_id = loan_id;
	}
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public DetailsPaymentsdto getPrincipal() {
		return principal;
	}

	public void setPrincipal(DetailsPaymentsdto principal) {
		this.principal = principal;
	}

	public DetailsPaymentsdto getInterest() {
		return interest;
	}

	public void setInterest(DetailsPaymentsdto interest) {
		this.interest = interest;
	}

	public DetailsPaymentsdto getDelinquency_interest() {
		return delinquency_interest;
	}

	public void setDelinquency_interest(DetailsPaymentsdto delinquency_interest) {
		this.delinquency_interest = delinquency_interest;
	}

	public DetailsPaymentsdto getInstallment_fee() {
		return installment_fee;
	}

	public void setInstallment_fee(DetailsPaymentsdto installment_fee) {
		this.installment_fee = installment_fee;
	}

	public DetailsPaymentsdto getDaily_fee() {
		return daily_fee;
	}

	public void setDaily_fee(DetailsPaymentsdto daily_fee) {
		this.daily_fee = daily_fee;
	}

	public DetailsPaymentsdto getLate_fee() {
		return late_fee;
	}

	public void setLate_fee(DetailsPaymentsdto late_fee) {
		this.late_fee = late_fee;
	}

	@Override
	public String toString() {
		return "{\"loan_id\":\"" + loan_id + "\", \"total\":\"" + total + "\", \"principal\":\"" + principal
				+ "\", \"interest\":\"" + interest + "\", \"delinquency_interest\":\"" + delinquency_interest
				+ "\", \"installment_fee\":\"" + installment_fee + "\", \"daily_fee\":\"" + daily_fee
				+ "\", \"late_fee\":\"" + late_fee + "\"}";
	} 
	
	
	
	
}
