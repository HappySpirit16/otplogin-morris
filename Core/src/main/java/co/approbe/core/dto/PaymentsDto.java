package co.approbe.core.dto;

import java.util.List;

public class PaymentsDto {

	private String payment_id; 
	private String effective_date; 
	private double amount_paid; 
	private List<Loan_allocation_dto> loan_allocation;
	
	public PaymentsDto() {
	}

	public PaymentsDto(String payment_id, String effective_date, double amount_paid,
			List<Loan_allocation_dto> loan_allocation) {
		super();
		this.payment_id = payment_id;
		this.effective_date = effective_date;
		this.amount_paid = amount_paid;
		this.loan_allocation = loan_allocation;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}

	public double getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(double amount_paid) {
		this.amount_paid = amount_paid;
	}

	public List<Loan_allocation_dto> getLoan_allocation() {
		return loan_allocation;
	}

	public void setLoan_allocation(List<Loan_allocation_dto> loan_allocation) {
		this.loan_allocation = loan_allocation;
	}

	@Override
	public String toString() {
		return "{\"payment_id\":\"" + payment_id + "\", \"effective_date\":\"" + effective_date
				+ "\", \"amount_paid\":\"" + amount_paid + "\", \"loan_allocation\":\"" + loan_allocation + "\"}";
	} 
	
	
	
	
}
