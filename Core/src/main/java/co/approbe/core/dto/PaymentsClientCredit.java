package co.approbe.core.dto;

public class PaymentsClientCredit {
	private String effective_date; 
	private String payment_id; 
	private double total; 
	private double principal; 
	private double interest; 
	private double delinquency_interest; 
	private double installment_fee; 
	private double daily_fee; 
	private double late_fee;
	public PaymentsClientCredit() {
	}
	public PaymentsClientCredit(String effective_date, String payment_id, double total, double principal,
			double interest, double delinquency_interest, double installment_fee, double daily_fee, double late_fee) {
		super();
		this.effective_date = effective_date;
		this.payment_id = payment_id;
		this.total = total;
		this.principal = principal;
		this.interest = interest;
		this.delinquency_interest = delinquency_interest;
		this.installment_fee = installment_fee;
		this.daily_fee = daily_fee;
		this.late_fee = late_fee;
	}
	public String getEffective_date() {
		return effective_date;
	}
	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
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
	public double getDelinquency_interest() {
		return delinquency_interest;
	}
	public void setDelinquency_interest(double delinquency_interest) {
		this.delinquency_interest = delinquency_interest;
	}
	public double getInstallment_fee() {
		return installment_fee;
	}
	public void setInstallment_fee(double installment_fee) {
		this.installment_fee = installment_fee;
	}
	public double getDaily_fee() {
		return daily_fee;
	}
	public void setDaily_fee(double daily_fee) {
		this.daily_fee = daily_fee;
	}
	public double getLate_fee() {
		return late_fee;
	}
	public void setLate_fee(double late_fee) {
		this.late_fee = late_fee;
	}
	@Override
	public String toString() {
		return "{\"effective_date\":\"" + effective_date + "\", \"payment_id\":\"" + payment_id + "\", \"total\":\""
				+ total + "\", \"principal\":\"" + principal + "\", \"interest\":\"" + interest
				+ "\", \"delinquency_interest\":\"" + delinquency_interest + "\", \"installment_fee\":\""
				+ installment_fee + "\", \"daily_fee\":\"" + daily_fee + "\", \"late_fee\":\"" + late_fee + "\"}";
	} 
	
}
