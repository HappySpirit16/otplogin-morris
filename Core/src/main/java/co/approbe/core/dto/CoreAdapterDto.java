package co.approbe.core.dto;

import java.util.List;

import com.google.gson.Gson;

public class CoreAdapterDto {

	private String occurredOn;
	private LoanDto loan;
	private PaymentDto payment;
	private List<PaymentAllocationDto> paymentAllocations;
	private String idBorrower;
	private String idCredit;
	private String actionStrategyPattern;

	public CoreAdapterDto() {

	}

	public CoreAdapterDto(String occurredOn, LoanDto loan, PaymentDto payment, String idBorrower, String idCredit,
			List<PaymentAllocationDto> paymentallocations) {
		super();
		this.occurredOn = occurredOn;
		this.loan = loan;
		this.payment = payment;
		this.idBorrower = idBorrower;
		this.idCredit = idCredit;
		this.paymentAllocations = paymentallocations;

	}

	public String getOccurredOn() {
		return occurredOn;
	}

	public void setOccurredOn(String occurredOn) {
		this.occurredOn = occurredOn;
	}

	public LoanDto getLoan() {
		return loan;
	}

	public void setLoan(LoanDto loan) {
		this.loan = loan;
	}

	public PaymentDto getPayment() {
		return payment;
	}

	public void setPayment(PaymentDto payment) {
		this.payment = payment;
	}

	public String getIdBorrower() {
		return idBorrower;
	}

	public void setIdBorrower(String idBorrower) {
		this.idBorrower = idBorrower;
	}

	public String getIdCredit() {
		return idCredit;
	}

	public void setIdCredit(String idCredit) {
		this.idCredit = idCredit;
	}

	public String getLoanJson() {
		if (loan == null) {
			return null;
		} else {
			return loan.toString();
		}
	}

	public String getPaymentJson() {
		if (payment == null) {
			return null;
		} else {
			return payment.toString();
		}
	}

	public List<PaymentAllocationDto> getPaymentAllocations() {
		return paymentAllocations;
	}

	public void setPaymentAllocations(List<PaymentAllocationDto> paymentAllocations) {
		this.paymentAllocations = paymentAllocations;
	}

	public String getLstPaymentAllocationJson() {
		if (paymentAllocations == null) {
			return "[]";
		} else {
			return new Gson().toJson(paymentAllocations);
		}
	}

	public void setLstPaymentAllocation(List<PaymentAllocationDto> lstPaymentAllocation) {
		this.paymentAllocations = lstPaymentAllocation;
	}

	public String getActionStrategyPattern() {
		return actionStrategyPattern;
	}

	public void setActionStrategyPattern(String actionStrategyPattern) {
		this.actionStrategyPattern = actionStrategyPattern;
	}

	@Override
	public String toString() {
		return "{\"occurred_on\":\"" + occurredOn + "\", \"loan\":" + getLoanJson() + ", \"payment\":"
				+ getPaymentJson() + ", \"borrower_id\":\"" + idBorrower + "\", \"request_id\":\"" + idCredit + "\"}";
	}

	public String toStringPayment() {
		return "[{\"occurred_on\":\"" + occurredOn + "\", \"loan\":" + getLoanJson() + ", \"payment\":"
				+ getPaymentJson() + ", \"borrower_id\":\"" + idBorrower + "\", \"request_id\":\"" + idCredit
				+ "\",\"payment_allocations\": " + getLstPaymentAllocationJson() + "}]";
	}

}
