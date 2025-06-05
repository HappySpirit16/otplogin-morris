package co.approbe.payment.create.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "approbeCommission")
public class ApprobeCommission {
	@DynamoDBHashKey
	private String id;
	@DynamoDBAttribute
	private String account;
	@DynamoDBAttribute
	private String entityCodeApprobe;
	@DynamoDBAttribute
	private Double commissionPercentage;
	@DynamoDBAttribute
	private Double commissionExt;
	@DynamoDBAttribute
	private Integer srvCode;
	
	public ApprobeCommission() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Double getCommissionPercentage() {
		return commissionPercentage;
	}
	public void setCommissionPercentage(Double commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}
	public Double getCommissionExt() {
		return commissionExt;
	}
	public void setCommissionExt(Double commissionExt) {
		this.commissionExt = commissionExt;
	}
	public String getEntityCodeApprobe() {
		return entityCodeApprobe;
	}
	public void setEntityCodeApprobe(String entityCodeApprobe) {
		this.entityCodeApprobe = entityCodeApprobe;
	}
	public Integer getSrvCode() {
		return srvCode;
	}
	public void setSrvCode(Integer srvCode) {
		this.srvCode = srvCode;
	}

	
	

}
