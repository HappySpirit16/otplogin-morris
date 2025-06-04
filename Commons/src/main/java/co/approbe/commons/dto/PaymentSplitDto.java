package co.approbe.commons.dto;

public class PaymentSplitDto {

	private String idCode;
	private Long value;
	private String operation="pagos";

	public PaymentSplitDto() {

	}

	public PaymentSplitDto(String idCode, Long value) {
		super();
		this.idCode = idCode;
		this.value = value;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	

}
