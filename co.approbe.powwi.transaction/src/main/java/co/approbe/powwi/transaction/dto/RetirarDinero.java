package co.approbe.powwi.transaction.dto;

public class RetirarDinero {
	private String tokenConvenio;
	private String idCuenta;
	private String huellaDispositivo;
	private String tokenTransaccional;
	private String numeroCelular;
	private String idTransaccionAliado;
	private String canalRetiro;
	private String monto;
	private String nombreDispositivo;
	private String SO;
	private String IP;
	public RetirarDinero() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTokenConvenio() {
		return tokenConvenio;
	}
	public void setTokenConvenio(String tokenConvenio) {
		this.tokenConvenio = tokenConvenio;
	}
	public String getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}
	public String getHuellaDispositivo() {
		return huellaDispositivo;
	}
	public void setHuellaDispositivo(String huellaDispositivo) {
		this.huellaDispositivo = huellaDispositivo;
	}
	public String getTokenTransaccional() {
		return tokenTransaccional;
	}
	public void setTokenTransaccional(String tokenTransaccional) {
		this.tokenTransaccional = tokenTransaccional;
	}
	public String getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	public String getIdTransaccionAliado() {
		return idTransaccionAliado;
	}
	public void setIdTransaccionAliado(String idTransaccionAliado) {
		this.idTransaccionAliado = idTransaccionAliado;
	}
	public String getCanalRetiro() {
		return canalRetiro;
	}
	public void setCanalRetiro(String canalRetiro) {
		this.canalRetiro = canalRetiro;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getNombreDispositivo() {
		return nombreDispositivo;
	}
	public void setNombreDispositivo(String nombreDispositivo) {
		this.nombreDispositivo = nombreDispositivo;
	}
	public String getSO() {
		return SO;
	}
	public void setSO(String sO) {
		SO = sO;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	@Override
	public String toString() {
		return "{\"tokenConvenio\":\"" + tokenConvenio + "\", \"idCuenta\":\"" + idCuenta
				+ "\", \"huellaDispositivo\":\"" + huellaDispositivo + "\", \"tokenTransaccional\":\""
				+ tokenTransaccional + "\", \"numeroCelular\":\"" + numeroCelular + "\", \"idTransaccionAliado\":\""
				+ idTransaccionAliado + "\", \"canalRetiro\":\"" + canalRetiro + "\", \"monto\":\"" + monto
				+ "\", \"nombreDispositivo\":\"" + nombreDispositivo + "\", \"SO\":\"" + SO + "\", \"IP\":\"" + IP
				+ "\"}";
	}
	
}
