package co.approbe.core.dto;

public class BreakdownDto {

	private double principal;
	private double interest;
	private double delinquency_interest;
	private double installment_fee;
	private double daily_fee;
	private double initial_fee;
	private double late_fee;
	private double benefit;
	
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
	public double getInitial_fee() {
		return initial_fee;
	}
	public void setInitial_fee(double initial_fee) {
		this.initial_fee = initial_fee;
	}
	public double getLate_fee() {
		return late_fee;
	}
	public void setLate_fee(double late_fee) {
		this.late_fee = late_fee;
	}
	public double getBenefit() {
		return benefit;
	}
	public void setBenefit(double benefit) {
		this.benefit = benefit;
	}
	@Override
	public String toString() {
		return "{\"principal\":\"" + principal + "\", \"interest\":\"" + interest + "\", \"delinquency_interest\":\""
				+ delinquency_interest + "\", \"installment_fee\":\"" + installment_fee + "\", \"daily_fee\":\""
				+ daily_fee + "\", \"initial_fee\":\"" + initial_fee + "\", \"late_fee\":\"" + late_fee
				+ "\", \"benefit\":\"" + benefit + "\"}";
	}
	
	
}
