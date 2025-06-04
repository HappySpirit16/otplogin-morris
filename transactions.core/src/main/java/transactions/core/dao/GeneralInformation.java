package transactions.core.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "tableGeneralInformation")
public class GeneralInformation {
	@DynamoDBHashKey
	private Integer id;
	@DynamoDBAttribute
	private Integer coverageFGAPercentage;
	@DynamoDBAttribute
	private Integer coveragePlatformPercentage;
	@DynamoDBAttribute
	private Integer iva;
	@DynamoDBAttribute
	private Double montlyRate;
	@DynamoDBAttribute
	private Integer periodPlatform;
	@DynamoDBAttribute
	private Integer platform;
	@DynamoDBAttribute
	private Double yearRate;
	@DynamoDBAttribute
	private Double delinquency_interest_rate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCoverageFGAPercentage() {
		return coverageFGAPercentage;
	}
	public void setCoverageFGAPercentage(Integer coverageFGAPercentage) {
		this.coverageFGAPercentage = coverageFGAPercentage;
	}
	public Integer getCoveragePlatformPercentage() {
		return coveragePlatformPercentage;
	}
	public void setCoveragePlatformPercentage(Integer coveragePlatformPercentage) {
		this.coveragePlatformPercentage = coveragePlatformPercentage;
	}
	public Integer getIva() {
		return iva;
	}
	public void setIva(Integer iva) {
		this.iva = iva;
	}
	public Double getMontlyRate() {
		return montlyRate;
	}
	public void setMontlyRate(Double montlyRate) {
		this.montlyRate = montlyRate;
	}
	public Integer getPeriodPlatform() {
		return periodPlatform;
	}
	public void setPeriodPlatform(Integer periodPlatform) {
		this.periodPlatform = periodPlatform;
	}
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	public Double getYearRate() {
		return yearRate;
	}
	public void setYearRate(Double yearRate) {
		this.yearRate = yearRate;
	}
	public Double getDelinquency_interest_rate() {
		return delinquency_interest_rate;
	}
	public void setDelinquency_interest_rate(Double delinquency_interest_rate) {
		this.delinquency_interest_rate = delinquency_interest_rate;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"coverageFGAPercentage\":\"" + coverageFGAPercentage
				+ "\", \"coveragePlatformPercentage\":\"" + coveragePlatformPercentage + "\", \"iva\":\"" + iva
				+ "\", \"montlyRate\":\"" + montlyRate + "\", \"periodPlatform\":\"" + periodPlatform
				+ "\", \"platform\":\"" + platform + "\", \"yearRate\":\"" + yearRate
				+ "\", \"delinquency_interest_rate\":\"" + delinquency_interest_rate + "\"}";
	}
	
	
}
