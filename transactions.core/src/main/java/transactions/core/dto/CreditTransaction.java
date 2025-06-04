package transactions.core.dto;

import java.util.List;

public class CreditTransaction {
	private String actionStrategyPattern;
    private String occurredOn;
    private Loan loan;
    private String idBorrower;
    private String idCredit;
    
    public static class Loan {
        private int amountFinanced;
        private double interestRate;
        private double delinquencyInterestRate;
        private int installmentFeeCharge;
        private int dailyFeeCharge;
        private String originationDate;
        private String configId;
        private String owner;
        private List<String> dueDates;
        
        
		@Override
		public String toString() {
			return "{\"amountFinanced\":\"" + amountFinanced + "\", \"interestRate\":\"" + interestRate
					+ "\", \"delinquencyInterestRate\":\"" + delinquencyInterestRate + "\", \"installmentFeeCharge\":\""
					+ installmentFeeCharge + "\", \"dailyFeeCharge\":\"" + dailyFeeCharge + "\", \"originationDate\":\""
					+ originationDate + "\", \"configId\":\"" + configId + "\", \"owner\":\"" + owner
					+ "\", \"dueDates\":\"" + dueDates + "\"}";
		}
		public int getAmountFinanced() {
			return amountFinanced;
		}
		public void setAmountFinanced(int amountFinanced) {
			this.amountFinanced = amountFinanced;
		}
		public double getInterestRate() {
			return interestRate;
		}
		public void setInterestRate(double interestRate) {
			this.interestRate = interestRate;
		}
		public double getDelinquencyInterestRate() {
			return delinquencyInterestRate;
		}
		public void setDelinquencyInterestRate(double delinquencyInterestRate) {
			this.delinquencyInterestRate = delinquencyInterestRate;
		}
		public int getInstallmentFeeCharge() {
			return installmentFeeCharge;
		}
		public void setInstallmentFeeCharge(int installmentFeeCharge) {
			this.installmentFeeCharge = installmentFeeCharge;
		}
		public int getDailyFeeCharge() {
			return dailyFeeCharge;
		}
		public void setDailyFeeCharge(int dailyFeeCharge) {
			this.dailyFeeCharge = dailyFeeCharge;
		}
		public String getOriginationDate() {
			return originationDate;
		}
		public void setOriginationDate(String originationDate) {
			this.originationDate = originationDate;
		}
		public String getConfigId() {
			return configId;
		}
		public void setConfigId(String configId) {
			this.configId = configId;
		}
		public String getOwner() {
			return owner;
		}
		public void setOwner(String owner) {
			this.owner = owner;
		}
		public List<String> getDueDates() {
			return dueDates;
		}
		public void setDueDates(List<String> dueDates) {
			this.dueDates = dueDates;
		}
        
        
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

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
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

	public CreditTransaction() {
		super();
	}

	@Override
	public String toString() {
		return "{\"actionStrategyPattern\":\"" + actionStrategyPattern + "\", \"occurredOn\":\"" + occurredOn
				+ "\", \"loan\":\"" + loan + "\", \"idBorrower\":\"" + idBorrower + "\", \"idCredit\":\"" + idCredit
				+ "\"}";
	}
    
    
}
