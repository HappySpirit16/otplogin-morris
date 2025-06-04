package co.approbe.core.dto;

import java.util.List;

import com.google.gson.Gson;

public class LoanDto {

	private Long amountFinanced;
	private Double interestRate;
	private Double delinquencyInterestRate;
	private Double installmentFeeCharge;
	private Double dailyFeeCharge;
	private String originationDate;
	private String configId;
	private String config_id;
	private String owner;
	private double amount_financed;
	private boolean is_cancelled; 
	private String origination_date; 
	
	private String kordev_id;
	private String id;
	
	private List<String> dueDates;
	

	public LoanDto() {

	}

	public LoanDto(Long amountFinanced, Double interestRate, Double delinquencyInterestRate,
			Double installmentFeeCharge, Double dailyFeeCharge, String originationDate, String configId, String owner,
			List<String> dueDates) {
		super();
		this.amountFinanced = amountFinanced;
		this.interestRate = interestRate;
		this.delinquencyInterestRate = delinquencyInterestRate;
		this.installmentFeeCharge = installmentFeeCharge;
		this.dailyFeeCharge = dailyFeeCharge;
		this.originationDate = originationDate;
		this.configId = configId;
		this.owner = owner;
		this.dueDates = dueDates;
	}

	public Long getAmountFinanced() {
		return amountFinanced;
	}

	public void setAmountFinanced(Long amountFinanced) {
		this.amountFinanced = amountFinanced;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Double getDelinquencyInterestRate() {
		return delinquencyInterestRate;
	}

	public void setDelinquencyInterestRate(Double delinquencyInterestRate) {
		this.delinquencyInterestRate = delinquencyInterestRate;
	}

	public Double getInstallmentFeeCharge() {
		return installmentFeeCharge;
	}

	public void setInstallmentFeeCharge(Double installmentFeeCharge) {
		this.installmentFeeCharge = installmentFeeCharge;
	}

	public Double getDailyFeeCharge() {
		return dailyFeeCharge;
	}

	public void setDailyFeeCharge(Double dailyFeeCharge) {
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

	public List<String> getDueDates() {
		return dueDates;
	}

	public void setDueDates(List<String> dueDates) {
		this.dueDates = dueDates;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getKordev_id() {
		return kordev_id;
	}

	public void setKordev_id(String kordev_id) {
		this.kordev_id = kordev_id;
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
	public double getAmount_financed() {
		return amount_financed;
	}

	public void setAmount_financed(double amount_financed) {
		this.amount_financed = amount_financed;
	}

	public boolean isIs_cancelled() {
		return is_cancelled;
	}

	public void setIs_cancelled(boolean is_cancelled) {
		this.is_cancelled = is_cancelled;
	}

	public String getOrigination_date() {
		return origination_date;
	}

	public void setOrigination_date(String origination_date) {
		this.origination_date = origination_date;
	}

	@Override
	public String toString() {
		Gson wGson = new Gson();
		
		return "{\"amount_financed\":" + amountFinanced + ", \"interest_rate\":" + interestRate
				+ ", \"delinquency_interest_rate\":" + delinquencyInterestRate + ", \"installment_fee_charge\":"
				+ installmentFeeCharge + ", \"daily_fee_charge\":" + dailyFeeCharge + ", \"origination_date\":\""
				+ originationDate + "\", \"config_id\":\"" + configId +  "\",\"owner\":\"" + owner
				+ "\", \"koredev_id\":\"" + kordev_id + "\", \"id\":\"" + id + "\", \"due_dates\":" + wGson.toJson(dueDates)
				+ "}";
	}

}
