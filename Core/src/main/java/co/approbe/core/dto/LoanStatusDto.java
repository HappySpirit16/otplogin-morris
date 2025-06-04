package co.approbe.core.dto;

public class LoanStatusDto {

	private LoanDto attributes;
	private LoanSummaryDto summary;
	private BalanceDetailDto balance_detail; 
	private PaymentDto payment; 
	
	
	public BalanceDetailDto getBalance_detail() {
		return balance_detail;
	}

	public void setBalance_detail(BalanceDetailDto balance_detail) {
		this.balance_detail = balance_detail;
	}


	private int simple_days_past_due; 

	public LoanStatusDto() {
		
	}

	public int getSimple_days_past_due() {
		return simple_days_past_due;
	}


	public void setSimple_days_past_due(int simple_days_past_due) {
		this.simple_days_past_due = simple_days_past_due;
	}

	public LoanDto getAttributes() {
		return attributes;
	}


	public void setAttributes(LoanDto attributes) {
		this.attributes = attributes;
	}


	public LoanSummaryDto getSummary() {
		return summary;
	}


	public void setSummary(LoanSummaryDto summary) {
		this.summary = summary;
	}

	public PaymentDto getPayment() {
		return payment;
	}

	public void setPayment(PaymentDto payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "{\"attributes\":\"" + attributes + "\", \"summary\":\"" + summary + "\", \"balance_detail\":\""
				+ balance_detail + "\"}";
	}
	


}
