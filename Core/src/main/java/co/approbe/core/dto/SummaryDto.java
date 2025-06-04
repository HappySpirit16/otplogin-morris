package co.approbe.core.dto;

public class SummaryDto {
	private double positive_balance;

	public SummaryDto() {
	}

	public SummaryDto(double positive_balance) {
		super();
		this.positive_balance = positive_balance;
	}

	public double getPositive_balance() {
		return positive_balance;
	}

	public void setPositive_balance(double positive_balance) {
		this.positive_balance = positive_balance;
	}

	@Override
	public String toString() {
		return "{\"positive_balance\":\"" + positive_balance + "\"}";
	} 
	
	

}
