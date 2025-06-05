package co.approbe.powwi.transaction.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="creditLine")
public class CreditLine {
	@DynamoDBHashKey(attributeName = "id")
	private int id;	
	@DynamoDBAttribute
	private int minValue;
	@DynamoDBAttribute
	private int maxValue;
	@DynamoDBAttribute
	private int minInstallments;
	@DynamoDBAttribute
	private int maxInstallments;
	@DynamoDBAttribute
	private String rate;
	@DynamoDBAttribute
	private String platformUse;
	@DynamoDBAttribute
	private String insurance;
	@DynamoDBAttribute
	private double commissionFGA;
	@DynamoDBAttribute
	private double commission;
	@DynamoDBAttribute
	private double tax;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public CreditLine() {
		super();
	}

	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public int getMinInstallments() {
		return minInstallments;
	}
	public void setMinInstallments(int minInstallments) {
		this.minInstallments = minInstallments;
	}
	public int getMaxInstallments() {
		return maxInstallments;
	}
	public void setMaxInstallments(int maxInstallments) {
		this.maxInstallments = maxInstallments;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getPlatformUse() {
		return platformUse;
	}
	public void setPlatformUse(String platformUse) {
		this.platformUse = platformUse;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public double getCommissionFGA() {
		return commissionFGA;
	}
	public void setCommissionFGA(double commissionFGA) {
		this.commissionFGA = commissionFGA;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public CreditLine(int id, int minValue, int maxValue, int minInstallments, int maxInstallments, String rate,
			String platformUse, String insurance, double commissionFGA, double commission, double tax) {
		super();
		this.id = id;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.minInstallments = minInstallments;
		this.maxInstallments = maxInstallments;
		this.rate = rate;
		this.platformUse = platformUse;
		this.insurance = insurance;
		this.commissionFGA = commissionFGA;
		this.commission = commission;
		this.tax = tax;
	}
	
	
	
}
