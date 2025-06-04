package co.approbe.core.dto;

import java.util.List;

public class BorrowerStatusDto {

	private String borrower_id;
	private List<LoanStatusDto> loans;
	private List<PaymentsDto> payments;
	private SummaryDto summary;
	
	public BorrowerStatusDto() {
		
	}

	public SummaryDto getSummary() {
		return summary;
	}

	public void setSummary(SummaryDto summary) {
		this.summary = summary;
	}

	public String getBorrower_id() {
		return borrower_id;
	}

	public void setBorrower_id(String borrower_id) {
		this.borrower_id = borrower_id;
	}

	public List<LoanStatusDto> getLoans() {
		return loans;
	}

	public void setLoans(List<LoanStatusDto> loans) {
		this.loans = loans;
	}

	public List<PaymentsDto> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentsDto> payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "{\"borrower_id\":\"" + borrower_id + "\", \"loans\":\"" + loans + "\", \"payments\":\"" + payments
				+ "\", \"summary\":\"" + summary + "\"}";
	}

	



}
