package co.approbe.core.dto;

public class DetailsPaymentsdto {
	private double charged_off; 
	private double overdue;
	private double current;
	private double unpaid;
	private double total;

	
	public DetailsPaymentsdto() {

	}
	
	public DetailsPaymentsdto(double charged_off, double overdue, double current, double unpaid, double total) {
		super();
		this.charged_off = charged_off;
		this.overdue = overdue;
		this.current = current;
		this.unpaid = unpaid;
		this.total = total;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "{\"charged_off\":\"" + charged_off + "\", \"overdue\":\"" + overdue + "\", \"current\":\"" + current
				+ "\", \"unpaid\":\"" + unpaid + "\", \"total\":\"" + total + "\"}";
	}
	

}
