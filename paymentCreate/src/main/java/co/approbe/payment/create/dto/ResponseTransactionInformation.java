package co.approbe.payment.create.dto;

import java.util.List;

public class ResponseTransactionInformation {
	private String EntityCode;
    private String TicketId;
    private String TrazabilityCode;
    private String TranState;
    private String ReturnCode;
    private double TransValue;
    private double TransVatValue;
    private String PayCurrency;
    private double CurrencyRate;
    private String BankProcessDate;
    private String FICode;
    private String FiName;
    private String PaymentSystem;
    private String TransCycle;
    private String Invoice;
    private List<String> ReferenceArray;
    private List<String> OperationArray;
    private String SrvCode;
    private String PaymentDesc;
    private List<String> PaymentInfoArray;
    private List<Payment> PaymentsArray;
    private String SessionToken;
    private String Subscription;

    // Constructor y métodos de acceso según sea necesario

    // Clase anidada para representar la información de pago
    public static class Payment {
        private String PaymentSystem;
        private String FICode;
        private String FIName;
        private String BankProcessDate;
        private String TrazabilityCode;
        private double TransValue;
        private double TransVatValue;
        private String TransCycle;
        private String PayCurrency;
        private double CurrencyRate;
        private String AccountingDate;
        private String AccountType;
        private String AccountNumber;
        private String MaskedCard;
        private String EntityCode;
        private String TicketId;
        private String TranState;
        private int Terms;
        private String RespMessage;
		public String getPaymentSystem() {
			return PaymentSystem;
		}
		public void setPaymentSystem(String paymentSystem) {
			PaymentSystem = paymentSystem;
		}
		public String getFICode() {
			return FICode;
		}
		public void setFICode(String fICode) {
			FICode = fICode;
		}
		public String getFIName() {
			return FIName;
		}
		public void setFIName(String fIName) {
			FIName = fIName;
		}
		public String getBankProcessDate() {
			return BankProcessDate;
		}
		public void setBankProcessDate(String bankProcessDate) {
			BankProcessDate = bankProcessDate;
		}
		public String getTrazabilityCode() {
			return TrazabilityCode;
		}
		public void setTrazabilityCode(String trazabilityCode) {
			TrazabilityCode = trazabilityCode;
		}
		public double getTransValue() {
			return TransValue;
		}
		public void setTransValue(double transValue) {
			TransValue = transValue;
		}
		public double getTransVatValue() {
			return TransVatValue;
		}
		public void setTransVatValue(double transVatValue) {
			TransVatValue = transVatValue;
		}
		public String getTransCycle() {
			return TransCycle;
		}
		public void setTransCycle(String transCycle) {
			TransCycle = transCycle;
		}
		public String getPayCurrency() {
			return PayCurrency;
		}
		public void setPayCurrency(String payCurrency) {
			PayCurrency = payCurrency;
		}
		public double getCurrencyRate() {
			return CurrencyRate;
		}
		public void setCurrencyRate(double currencyRate) {
			CurrencyRate = currencyRate;
		}
		public String getAccountingDate() {
			return AccountingDate;
		}
		public void setAccountingDate(String accountingDate) {
			AccountingDate = accountingDate;
		}
		public String getAccountType() {
			return AccountType;
		}
		public void setAccountType(String accountType) {
			AccountType = accountType;
		}
		public String getAccountNumber() {
			return AccountNumber;
		}
		public void setAccountNumber(String accountNumber) {
			AccountNumber = accountNumber;
		}
		public String getMaskedCard() {
			return MaskedCard;
		}
		public void setMaskedCard(String maskedCard) {
			MaskedCard = maskedCard;
		}
		public String getEntityCode() {
			return EntityCode;
		}
		public void setEntityCode(String entityCode) {
			EntityCode = entityCode;
		}
		public String getTicketId() {
			return TicketId;
		}
		public void setTicketId(String ticketId) {
			TicketId = ticketId;
		}
		public String getTranState() {
			return TranState;
		}
		public void setTranState(String tranState) {
			TranState = tranState;
		}
		public int getTerms() {
			return Terms;
		}
		public void setTerms(int terms) {
			Terms = terms;
		}
		public String getRespMessage() {
			return RespMessage;
		}
		public void setRespMessage(String respMessage) {
			RespMessage = respMessage;
		}
		public Payment() {
			super();
		}
		@Override
		public String toString() {
			return "{\"PaymentSystem\":\"" + PaymentSystem + "\", \"FICode\":\"" + FICode + "\", \"FIName\":\"" + FIName
					+ "\", \"BankProcessDate\":\"" + BankProcessDate + "\", \"TrazabilityCode\":\"" + TrazabilityCode
					+ "\", \"TransValue\":\"" + TransValue + "\", \"TransVatValue\":\"" + TransVatValue
					+ "\", \"TransCycle\":\"" + TransCycle + "\", \"PayCurrency\":\"" + PayCurrency
					+ "\", \"CurrencyRate\":\"" + CurrencyRate + "\", \"AccountingDate\":\"" + AccountingDate
					+ "\", \"AccountType\":\"" + AccountType + "\", \"AccountNumber\":\"" + AccountNumber
					+ "\", \"MaskedCard\":\"" + MaskedCard + "\", \"EntityCode\":\"" + EntityCode
					+ "\", \"TicketId\":\"" + TicketId + "\", \"TranState\":\"" + TranState + "\", \"Terms\":\"" + Terms
					+ "\", \"RespMessage\":\"" + RespMessage + "\"}";
		}
    }

	public String getEntityCode() {
		return EntityCode;
	}

	public void setEntityCode(String entityCode) {
		EntityCode = entityCode;
	}

	public String getTicketId() {
		return TicketId;
	}

	public void setTicketId(String ticketId) {
		TicketId = ticketId;
	}

	public String getTrazabilityCode() {
		return TrazabilityCode;
	}

	public void setTrazabilityCode(String trazabilityCode) {
		TrazabilityCode = trazabilityCode;
	}

	public String getTranState() {
		return TranState;
	}

	public void setTranState(String tranState) {
		TranState = tranState;
	}

	public String getReturnCode() {
		return ReturnCode;
	}

	public void setReturnCode(String returnCode) {
		ReturnCode = returnCode;
	}

	public double getTransValue() {
		return TransValue;
	}

	public void setTransValue(double transValue) {
		TransValue = transValue;
	}

	public double getTransVatValue() {
		return TransVatValue;
	}

	public void setTransVatValue(double transVatValue) {
		TransVatValue = transVatValue;
	}

	public String getPayCurrency() {
		return PayCurrency;
	}

	public void setPayCurrency(String payCurrency) {
		PayCurrency = payCurrency;
	}

	public double getCurrencyRate() {
		return CurrencyRate;
	}

	public void setCurrencyRate(double currencyRate) {
		CurrencyRate = currencyRate;
	}

	public String getBankProcessDate() {
		return BankProcessDate;
	}

	public void setBankProcessDate(String bankProcessDate) {
		BankProcessDate = bankProcessDate;
	}

	public String getFICode() {
		return FICode;
	}

	public void setFICode(String fICode) {
		FICode = fICode;
	}

	public String getFiName() {
		return FiName;
	}

	public void setFiName(String fiName) {
		FiName = fiName;
	}

	public String getPaymentSystem() {
		return PaymentSystem;
	}

	public void setPaymentSystem(String paymentSystem) {
		PaymentSystem = paymentSystem;
	}

	public String getTransCycle() {
		return TransCycle;
	}

	public void setTransCycle(String transCycle) {
		TransCycle = transCycle;
	}

	public String getInvoice() {
		return Invoice;
	}

	public void setInvoice(String invoice) {
		Invoice = invoice;
	}

	public List<String> getReferenceArray() {
		return ReferenceArray;
	}

	public void setReferenceArray(List<String> referenceArray) {
		ReferenceArray = referenceArray;
	}

	public List<String> getOperationArray() {
		return OperationArray;
	}

	public void setOperationArray(List<String> operationArray) {
		OperationArray = operationArray;
	}

	public String getSrvCode() {
		return SrvCode;
	}

	public void setSrvCode(String srvCode) {
		SrvCode = srvCode;
	}

	public String getPaymentDesc() {
		return PaymentDesc;
	}

	public void setPaymentDesc(String paymentDesc) {
		PaymentDesc = paymentDesc;
	}

	public List<String> getPaymentInfoArray() {
		return PaymentInfoArray;
	}

	public void setPaymentInfoArray(List<String> paymentInfoArray) {
		PaymentInfoArray = paymentInfoArray;
	}

	public List<Payment> getPaymentsArray() {
		return PaymentsArray;
	}

	public void setPaymentsArray(List<Payment> paymentsArray) {
		PaymentsArray = paymentsArray;
	}

	public String getSessionToken() {
		return SessionToken;
	}

	public void setSessionToken(String sessionToken) {
		SessionToken = sessionToken;
	}

	public String getSubscription() {
		return Subscription;
	}

	public void setSubscription(String subscription) {
		Subscription = subscription;
	}

	public ResponseTransactionInformation() {
		super();
	}

	@Override
	public String toString() {
		return "{\"EntityCode\":\"" + EntityCode + "\", \"TicketId\":\"" + TicketId + "\", \"TrazabilityCode\":\""
				+ TrazabilityCode + "\", \"TranState\":\"" + TranState + "\", \"ReturnCode\":\"" + ReturnCode
				+ "\", \"TransValue\":\"" + TransValue + "\", \"TransVatValue\":\"" + TransVatValue
				+ "\", \"PayCurrency\":\"" + PayCurrency + "\", \"CurrencyRate\":\"" + CurrencyRate
				+ "\", \"BankProcessDate\":\"" + BankProcessDate + "\", \"FICode\":\"" + FICode + "\", \"FiName\":\""
				+ FiName + "\", \"PaymentSystem\":\"" + PaymentSystem + "\", \"TransCycle\":\"" + TransCycle
				+ "\", \"Invoice\":\"" + Invoice + "\", \"ReferenceArray\":\"" + ReferenceArray
				+ "\", \"OperationArray\":\"" + OperationArray + "\", \"SrvCode\":\"" + SrvCode
				+ "\", \"PaymentDesc\":\"" + PaymentDesc + "\", \"PaymentInfoArray\":\"" + PaymentInfoArray
				+ "\", \"PaymentsArray\":\"" + PaymentsArray + "\", \"SessionToken\":\"" + SessionToken
				+ "\", \"Subscription\":\"" + Subscription + "\"}";
	}
    

}
