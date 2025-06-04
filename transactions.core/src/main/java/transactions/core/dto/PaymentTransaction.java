package transactions.core.dto;

import java.util.List;

public class PaymentTransaction {
	private String actionStrategyPattern;
    private String occurredOn;
    private Payment payment;
    private String idBorrower;
    private List<PaymentAllocations> paymentAllocations;
    private String id;
    
    
    
    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public List<PaymentAllocations> getPaymentAllocations() {
		return paymentAllocations;
	}


	public void setPaymentAllocations(List<PaymentAllocations> paymentAllocations) {
		this.paymentAllocations = paymentAllocations;
	}


	public PaymentTransaction() {
		super();
	}


	public String getActionStrategyPattern() {
		return actionStrategyPattern;
	}



	public void setActionStrategyPattern(String actionStrategyPattern) {
		this.actionStrategyPattern = actionStrategyPattern;
	}



	public String getOccurredOn() {
		return occurredOn;
	}



	public void setOccurredOn(String occurredOn) {
		this.occurredOn = occurredOn;
	}



	public Payment getPayment() {
		return payment;
	}



	public void setPayment(Payment payment) {
		this.payment = payment;
	}



	public String getIdBorrower() {
		return idBorrower;
	}



	public void setIdBorrower(String idBorrower) {
		this.idBorrower = idBorrower;
	}



	public static class Payment {
        private int amountPaid;
        private String paymentDate;
        private String configId;
        private String id;
		public int getAmountPaid() {
			return amountPaid;
		}
		public void setAmountPaid(int amountPaid) {
			this.amountPaid = amountPaid;
		}
		public String getPaymentDate() {
			return paymentDate;
		}
		public void setPaymentDate(String paymentDate) {
			this.paymentDate = paymentDate;
		}
		public String getConfigId() {
			return configId;
		}
		public void setConfigId(String configId) {
			this.configId = configId;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		@Override
		public String toString() {
			return "{\"amountPaid\":\"" + amountPaid + "\", \"paymentDate\":\"" + paymentDate + "\", \"configId\":\""
					+ configId + "\", \"id\":\"" + id + "\"}";
		}
        
    }
	
	public static class PaymentAllocations{
		private Integer amount;
		private String loan_id;
		public Integer getAmount() {
			return amount;
		}
		public void setAmount(Integer amount) {
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

	@Override
	public String toString() {
		return "{\"actionStrategyPattern\":\"" + actionStrategyPattern + "\", \"occurredOn\":\"" + occurredOn
				+ "\", \"payment\":\"" + payment + "\", \"idBorrower\":\"" + idBorrower
				+ "\", \"paymentAllocations\":\"" + paymentAllocations + "\", \"id\":\"" + id + "\"}";
	}

	
	
}
