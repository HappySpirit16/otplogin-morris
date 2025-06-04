package co.approbe.commons.dto;

public class OtpValidationDto {

	private String msj;
	private int code;
	private String idTransaction;

	public String getMsj() {
		return msj;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}

	@Override
	public String toString() {
		return "{\"msj\":\"" + msj + "\", \"code\":\"" + code + "\", \"idTransaction\":\"" + idTransaction + "\"}";
	}

}
