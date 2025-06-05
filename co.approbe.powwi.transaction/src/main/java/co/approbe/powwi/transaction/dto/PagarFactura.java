package co.approbe.powwi.transaction.dto;

public class PagarFactura {
	private String tokenConvenio;
	private String idCuenta;
	private String huellaDispositivo;
	private String tokenTransaccional;
	private String productId;
	private String idTransaccionAliado;
	private String tipoPago;
	private String codigoBarras;
	private String referenciaManual;
	private String valor;
	private String infoDispositivo;
	private String SO;
	private String IP;
	public PagarFactura() {
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getIdTransaccionAliado() {
		return idTransaccionAliado;
	}
	public void setIdTransaccionAliado(String idTransaccionAliado) {
		this.idTransaccionAliado = idTransaccionAliado;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getReferenciaManual() {
		return referenciaManual;
	}
	public void setReferenciaManual(String referenciaManual) {
		this.referenciaManual = referenciaManual;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getInfoDispositivo() {
		return infoDispositivo;
	}
	public void setInfoDispositivo(String infoDispositivo) {
		this.infoDispositivo = infoDispositivo;
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
	

}
