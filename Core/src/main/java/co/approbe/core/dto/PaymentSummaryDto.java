package co.approbe.core.dto;

public class PaymentSummaryDto {

	private Double amount;
	private String date;
	private BreakdownDto breakdown_by_bucket; 

	public BreakdownDto getBreakdown_by_bucket() {
		return breakdown_by_bucket;
	}


	public void setBreakdown_by_bucket(BreakdownDto breakdown_by_bucket) {
		this.breakdown_by_bucket = breakdown_by_bucket;
	}


	public PaymentSummaryDto() {
		
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "{\"amount\":\"" + amount + "\", \"date\":\"" + date + "\", \"breakdown_by_bucket\":\""
				+ breakdown_by_bucket + "\"}";
	}
}
