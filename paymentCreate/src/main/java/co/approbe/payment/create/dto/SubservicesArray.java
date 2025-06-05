package co.approbe.payment.create.dto;

public class SubservicesArray {
	
	private String EntityCode;
    private String SrvCode;
    private Integer ValueType;
    private Double TransValue;
    private Double TransVatValue;
    private String AccountId;
    
	public SubservicesArray() {
		super();
	}

	public String getEntityCode() {
		return EntityCode;
	}

	public void setEntityCode(String entityCode) {
		this.EntityCode = entityCode;
	}

	public String getSrvCode() {
		return SrvCode;
	}

	public void setSrvCode(String srvCode) {
		this.SrvCode = srvCode;
	}

	public Integer getValueType() {
		return ValueType;
	}

	public void setValueType(Integer valueType) {
		this.ValueType = valueType;
	}

	public Double getTransValue() {
		return TransValue;
	}

	public void setTransValue(Double transValue) {
		this.TransValue = transValue;
	}

	public Double getTransVatValue() {
		return TransVatValue;
	}

	public void setTransVatValue(Double transVatValue) {
		this.TransVatValue = transVatValue;
	}

	
	public String getAccountId() {
		return AccountId;
	}

	public void setAccountId(String accountId) {
		AccountId = accountId;
	}

	@Override
	public String toString() {
		return "{\"EntityCode\":\"" + EntityCode + "\", \"SrvCode\":\"" + SrvCode + "\", \"ValueType\":\"" + ValueType
				+ "\", \"TransValue\":\"" + TransValue + "\", \"TransVatValue\":\"" + TransVatValue
				+ "\", \"AccountId\":\"" + AccountId + "\"}";
	}

	
	

}
