package transactions.core.dto;

import java.util.List;

public class PaymentEvent {
	private String occurred_on;
    private Object loan;
    private Payment payment;
    private String borrower_id;
    private String request_id;
    private List<PaymentAllocation> payment_allocations;

    

    public String getOccurred_on() {
		return occurred_on;
	}

	public void setOccurred_on(String occurred_on) {
		this.occurred_on = occurred_on;
	}

	public Object getLoan() {
		return loan;
	}

	public void setLoan(Object loan) {
		this.loan = loan;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getBorrower_id() {
		return borrower_id;
	}

	public void setBorrower_id(String borrower_id) {
		this.borrower_id = borrower_id;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public List<PaymentAllocation> getPayment_allocations() {
		return payment_allocations;
	}

	public void setPayment_allocations(List<PaymentAllocation> payment_allocations) {
		this.payment_allocations = payment_allocations;
	}

	// Clase interna Payment
    public static class Payment {
        private int amount_paid;
        private String payment_date;
        private String id;
        private String config_id;
		public int getAmount_paid() {
			return amount_paid;
		}
		public void setAmount_paid(int amount_paid) {
			this.amount_paid = amount_paid;
		}
		public String getPayment_date() {
			return payment_date;
		}
		public void setPayment_date(String payment_date) {
			this.payment_date = payment_date;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getConfig_id() {
			return config_id;
		}
		public void setConfig_id(String config_id) {
			this.config_id = config_id;
		}

    }

    // Clase interna PaymentAllocation
    public static class PaymentAllocation {
        private int amount;
        private String loan_id;
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public String getLoan_id() {
			return loan_id;
		}
		public void setLoan_id(String loan_id) {
			this.loan_id = loan_id;
		}

    }

	public PaymentEvent() {
		super();
	}
    
    
}
