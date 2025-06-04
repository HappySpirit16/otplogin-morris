package co.approbe.core.dto;

public class PaymentDto {

	private double amountPaid;
	private String paymentDate;
	private String configId;
	private String id;
	private Double amount_financed; 
	private String kordev_id;
	private Boolean isCancelled;

	public PaymentDto() {

	}
	public PaymentDto(double amountPaid, String paymentDate, String configId, String id) {
		super();
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate;
		this.configId = configId;
		this.id = id;
	}

	public void setAmount_financed(double amount_financed) {
		this.amount_financed = amount_financed;
	}

	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public void setAmountPaid(float amountPaid) {
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
	
	public String getKordev_id() {
		return kordev_id;
	}

	public void setKordev_id(String kordev_id) {
		this.kordev_id = kordev_id;
	}


	public double getAmount_financed() {
		return amount_financed;
	}
	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	@Override
	public String toString() {
		return "{\"amount_paid\":" + amountPaid + ", \"payment_date\":\"" + paymentDate + "\", \"config_id\":\""
				+ configId + "\", \"id\":\"" + id + "\"}";
	}


	




}
