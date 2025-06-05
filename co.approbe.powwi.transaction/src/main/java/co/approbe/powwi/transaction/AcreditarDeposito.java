package co.approbe.powwi.transaction;

public class AcreditarDeposito {
	private String tokenConvenio;
	private String idCuenta;
	private String numeroCelular;
	private String idTransaccionAliado;
	private String idAliado;
	private String monto;
	private String referencia1;
	private String referencia2;
	private String nombreDispositivo;
	private String SO;
	private String IP;
	
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
	public String getIdAliado() {
		return idAliado;
	}
	public void setIdAliado(String idAliado) {
		this.idAliado = idAliado;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getReferencia1() {
		return referencia1;
	}
	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}
	public String getReferencia2() {
		return referencia2;
	}
	public void setReferencia2(String referencia2) {
		this.referencia2 = referencia2;
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
		return "{\"tokenConvenio\":\"" + tokenConvenio + "\", \"idCuenta\":\"" + idCuenta + "\", \"numeroCelular\":\""
				+ numeroCelular + "\", \"idTransaccionAliado\":\"" + idTransaccionAliado + "\", \"idAliado\":\""
				+ idAliado + "\", \"monto\":\"" + monto + "\", \"referencia1\":\"" + referencia1
				+ "\", \"referencia2\":\"" + referencia2 + "\", \"nombreDispositivo\":\"" + nombreDispositivo
				+ "\", \"SO\":\"" + SO + "\", \"IP\":\"" + IP + "\"}";
	}
	public AcreditarDeposito(String tokenConvenio, String idCuenta, String numeroCelular, String idTransaccionAliado,
			String idAliado, String monto, String referencia1, String referencia2, String nombreDispositivo, String sO,
			String iP) {
		super();
		this.tokenConvenio = tokenConvenio;
		this.idCuenta = idCuenta;
		this.numeroCelular = numeroCelular;
		this.idTransaccionAliado = idTransaccionAliado;
		this.idAliado = idAliado;
		this.monto = monto;
		this.referencia1 = referencia1;
		this.referencia2 = referencia2;
		this.nombreDispositivo = nombreDispositivo;
		SO = sO;
		IP = iP;
	}
	public AcreditarDeposito() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
