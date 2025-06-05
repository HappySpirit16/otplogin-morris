package co.approbe.powwi.transaction.dto;

public class CancelarRetiro {
	private String tokenConvenio;
	private String idCuenta;
	private String idOperacion;
	private String nombreDispositivo;
	private String SO;
	private String IP;
	public CancelarRetiro() {
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
	public String getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
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
		return "{\"tokenConvenio\":\"" + tokenConvenio + "\", \"idCuenta\":\"" + idCuenta + "\", \"idOperacion\":\""
				+ idOperacion + "\", \"nombreDispositivo\":\"" + nombreDispositivo + "\", \"SO\":\"" + SO
				+ "\", \"IP\":\"" + IP + "\"}";
	}
	
	
}
