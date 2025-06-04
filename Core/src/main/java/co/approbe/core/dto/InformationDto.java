package co.approbe.core.dto;

public class InformationDto {

	private double initial_balance;
	private double paid;
	private double waived_off;
	private double charged_off;
	private double overdue;
	private double current;
	private double unpaid;

	public double getInitial_balance() {
		return initial_balance;
	}

	public void setInitial_balance(double initial_balance) {
		this.initial_balance = initial_balance;
	}

	public double getPaid() {
		return paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}

	public double getWaived_off() {
		return waived_off;
	}

	public void setWaived_off(double waived_off) {
		this.waived_off = waived_off;
	}

	public double getCharged_off() {
		return charged_off;
	}

	public void setCharged_off(double charged_off) {
		this.charged_off = charged_off;
	}

	public double getOverdue() {
		return overdue;
	}

	public void setOverdue(double overdue) {
		this.overdue = overdue;
	}

	public double getCurrent() {
		return current;
	}

	public void setCurrent(double current) {
		this.current = current;
	}

	public double getUnpaid() {
		return unpaid;
	}

	public void setUnpaid(double unpaid) {
		this.unpaid = unpaid;
	}

	@Override
	public String toString() {
		return "{\"initial_balance\":\"" + initial_balance + "\", \"paid\":\"" + paid + "\", \"waived_off\":\""
				+ waived_off + "\", \"charged_off\":\"" + charged_off + "\", \"overdue\":\"" + overdue
				+ "\", \"current\":\"" + current + "\", \"unpaid\":\"" + unpaid + "\"}";
	}


	
}
