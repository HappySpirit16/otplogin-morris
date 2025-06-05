package co.approbe.powwi.transaction.dto;

public class TransferenciaOtraEntidad {
	private String idTransaccionAliado;
	private String nombreBanco;
	private String tipoCuenta;
	private String numeroCuenta;
	private String nombresDestino;
	private String apellidosDestino;
	private String tipoIdTitularDestino;
	private String monto;
	private String numeroIdentificacion;
	private String referencia1;
	private String referencia2;
	public TransferenciaOtraEntidad() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getIdTransaccionAliado() {
		return idTransaccionAliado;
	}
	public void setIdTransaccionAliado(String idTransaccionAliado) {
		this.idTransaccionAliado = idTransaccionAliado;
	}
	public String getNombreBanco() {
		return nombreBanco;
	}
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getNombresDestino() {
		return nombresDestino;
	}
	public void setNombresDestino(String nombresDestino) {
		this.nombresDestino = nombresDestino;
	}
	public String getApellidosDestino() {
		return apellidosDestino;
	}
	public void setApellidosDestino(String apellidosDestino) {
		this.apellidosDestino = apellidosDestino;
	}
	public String getTipoIdTitularDestino() {
		return tipoIdTitularDestino;
	}
	public void setTipoIdTitularDestino(String tipoIdTitularDestino) {
		this.tipoIdTitularDestino = tipoIdTitularDestino;
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
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public TransferenciaOtraEntidad(String idTransaccionAliado, String nombreBanco, String tipoCuenta,
			String numeroCuenta, String nombresDestino, String apellidosDestino, String tipoIdTitularDestino,
			String monto, String numeroIdentificacion, String referencia1, String referencia2) {
		super();
		this.idTransaccionAliado = idTransaccionAliado;
		this.nombreBanco = nombreBanco;
		this.tipoCuenta = tipoCuenta;
		this.numeroCuenta = numeroCuenta;
		this.nombresDestino = nombresDestino;
		this.apellidosDestino = apellidosDestino;
		this.tipoIdTitularDestino = tipoIdTitularDestino;
		this.monto = monto;
		this.numeroIdentificacion = numeroIdentificacion;
		this.referencia1 = referencia1;
		this.referencia2 = referencia2;
	}
	@Override
	public String toString() {
		return "{\"idTransaccionAliado\":\"" + idTransaccionAliado + "\", \"nombreBanco\":\"" + nombreBanco
				+ "\", \"tipoCuenta\":\"" + tipoCuenta + "\", \"numeroCuenta\":\"" + numeroCuenta
				+ "\", \"nombresDestino\":\"" + nombresDestino + "\", \"apellidosDestino\":\"" + apellidosDestino
				+ "\", \"tipoIdTitularDestino\":\"" + tipoIdTitularDestino + "\", \"monto\":\"" + monto
				+ "\", \"numeroIdentificacion\":\"" + numeroIdentificacion + "\", \"referencia1\":\"" + referencia1
				+ "\", \"referencia2\":\"" + referencia2 + "\"}";
	}
	
	
	
}
