package co.approbe.core.dto;

public class PaymentAllocationDto {
	
	private double amount;
    private String loan_id;

	public PaymentAllocationDto() {
		}
	
	

	public PaymentAllocationDto(double amount, String loan_id) {
		this.amount = amount;
		this.loan_id = loan_id;
	}



	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(String loan_id) {
		this.loan_id = loan_id;
	}

	@Override
	public String toString() {
		return "{\"amount\":\"" + amount + "\", \"loan_id\":\"" + loan_id + "\"}";
	}
	
	

}
