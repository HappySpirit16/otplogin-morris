package co.approbe.powwi.transaction.dto;

public class SolicitarExtracto {

	private String tokenConvenio;
	private String tokenConsulta;
	private String idCuenta;
	private String huellaDispositivo;
	private String mes;
	private String ano;
	public String getTokenConvenio() {
		return tokenConvenio;
	}
	public void setTokenConvenio(String tokenConvenio) {
		this.tokenConvenio = tokenConvenio;
	}
	public String getTokenConsulta() {
		return tokenConsulta;
	}
	public void setTokenConsulta(String tokenConsulta) {
		this.tokenConsulta = tokenConsulta;
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
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	@Override
	public String toString() {
		return "{\"tokenConvenio\":\"" + tokenConvenio + "\", \"tokenConsulta\":\"" + tokenConsulta
				+ "\", \"idCuenta\":\"" + idCuenta + "\", \"huellaDispositivo\":\"" + huellaDispositivo
				+ "\", \"mes\":\"" + mes + "\", \"ano\":\"" + ano + "\"}";
	}
	
}
