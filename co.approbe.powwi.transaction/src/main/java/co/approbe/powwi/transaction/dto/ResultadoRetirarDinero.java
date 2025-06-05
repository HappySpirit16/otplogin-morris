package co.approbe.powwi.transaction.dto;

public class ResultadoRetirarDinero {


	private String codigoResultado;
	private String idOperacion;
	private String valorTotalTransaccion;
	private String costoTransaccion;
	private String costoIVA;
	private String cobroGMF;
	private String otp;
	private String fechaExpiracion;
	private String mensaje;
	public String getCodigoResultado() {
		return codigoResultado;
	}
	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
	}
	public String getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}
	public String getValorTotalTransaccion() {
		return valorTotalTransaccion;
	}
	public void setValorTotalTransaccion(String valorTotalTransaccion) {
		this.valorTotalTransaccion = valorTotalTransaccion;
	}
	public String getCostoTransaccion() {
		return costoTransaccion;
	}
	public void setCostoTransaccion(String costoTransaccion) {
		this.costoTransaccion = costoTransaccion;
	}
	public String getCostoIVA() {
		return costoIVA;
	}
	public void setCostoIVA(String costoIVA) {
		this.costoIVA = costoIVA;
	}
	public String getCobroGMF() {
		return cobroGMF;
	}
	public void setCobroGMF(String cobroGMF) {
		this.cobroGMF = cobroGMF;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public ResultadoRetirarDinero(String codigoResultado, String idOperacion, String valorTotalTransaccion,
			String costoTransaccion, String costoIVA, String cobroGMF, String otp, String fechaExpiracion,
			String mensaje) {
		super();
		this.codigoResultado = codigoResultado;
		this.idOperacion = idOperacion;
		this.valorTotalTransaccion = valorTotalTransaccion;
		this.costoTransaccion = costoTransaccion;
		this.costoIVA = costoIVA;
		this.cobroGMF = cobroGMF;
		this.otp = otp;
		this.fechaExpiracion = fechaExpiracion;
		this.mensaje = mensaje;
	}
	public ResultadoRetirarDinero() {
		super();
	}
	@Override
	public String toString() {
		return "{\"codigoResultado\":\"" + codigoResultado + "\", \"idOperacion\":\"" + idOperacion
				+ "\", \"valorTotalTransaccion\":\"" + valorTotalTransaccion + "\", \"costoTransaccion\":\""
				+ costoTransaccion + "\", \"costoIVA\":\"" + costoIVA + "\", \"cobroGMF\":\"" + cobroGMF
				+ "\", \"otp\":\"" + otp + "\", \"fechaExpiracion\":\"" + fechaExpiracion + "\", \"mensaje\":\""
				+ mensaje + "\"}";
	}
	
	

}
