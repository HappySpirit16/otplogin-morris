package co.approbe.commons.dto;

public class OtpDto {

	private String operacion;
	private String idAutenticacionAliado;
	private String canal;
	private String otp;
	private String idCuenta;
	private String cellPhone;
	private String email;
	
	public OtpDto() {
		
	}

	public OtpDto(String pOperacion, String pIdAutenticacionAliado, String pCanal, String pOtp) {
		operacion = pOperacion;
		idAutenticacionAliado = pIdAutenticacionAliado;
		canal = pCanal;
		otp = pOtp;
		idCuenta = "1";
	}

	public OtpDto(String operacion, String idAutenticacionAliado, String canal, String otp, String idCuenta) {
		super();
		this.operacion = operacion;
		this.idAutenticacionAliado = idAutenticacionAliado;
		this.canal = canal;
		this.otp = otp;
		this.idCuenta = idCuenta;
	}

	public OtpDto(String operacion, String idAutenticacionAliado, String canal, String otp, String idCuenta,
			String cellPhone, String email) {
		super();
		this.operacion = operacion;
		this.idAutenticacionAliado = idAutenticacionAliado;
		this.canal = canal;
		this.otp = otp;
		this.idCuenta = idCuenta;
		this.cellPhone = cellPhone;
		this.email = email;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getIdAutenticacionAliado() {
		return idAutenticacionAliado;
	}

	public void setIdAutenticacionAliado(String idAutenticacionAliado) {
		this.idAutenticacionAliado = idAutenticacionAliado;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "{\"operacion\":\"" + operacion + "\", \"idAutenticacionAliado\":\"" + idAutenticacionAliado
				+ "\", \"canal\":\"" + canal + "\", \"otp\":\"" + otp + "\", \"idCuenta\":\"" + idCuenta
				+ "\", \"cellPhone\":\"" + cellPhone + "\", \"email\":\"" + email + "\"}";
	}

}
