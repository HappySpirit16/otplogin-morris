package co.approbe.powwi.transaction.dto;

import java.util.Arrays;

public class DispersarOtra {
	private String tokenConvenio;
	private String tokenTransaccional;
	private String huellaDispositivo;
	private String idCuenta;
	private String tipoDispersion;
	private TransferenciaOtraEntidad[] transferenciaOtraEntidad;
	private String SO;
	private String nombreDispositivo;
	private String IP;
    	
    public DispersarOtra() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getTokenConvenio() {
		return tokenConvenio;
	}
	public void setTokenConvenio(String tokenConvenio) {
		this.tokenConvenio = tokenConvenio;
	}
	public String getTokenTransaccional() {
		return tokenTransaccional;
	}
	public void setTokenTransaccional(String tokenTransaccional) {
		this.tokenTransaccional = tokenTransaccional;
	}
	public String getHuellaDispositivo() {
		return huellaDispositivo;
	}
	public void setHuellaDispositivo(String huellaDispositivo) {
		this.huellaDispositivo = huellaDispositivo;
	}
	public String getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}
	public String getTipoDispersion() {
		return tipoDispersion;
	}
	public void setTipoDispersion(String tipoDispersion) {
		this.tipoDispersion = tipoDispersion;
	}
	
	public TransferenciaOtraEntidad[] getTransferenciaOtraEntidad() {
		return transferenciaOtraEntidad;
	}

	public void setTransferenciaOtraEntidad(TransferenciaOtraEntidad[] transferenciaOtraEntidad) {
		this.transferenciaOtraEntidad = transferenciaOtraEntidad;
	}

	public String getSO() {
		return SO;
	}
	public void setSO(String sO) {
		SO = sO;
	}
	public String getNombreDispositivo() {
		return nombreDispositivo;
	}
	public void setNombreDispositivo(String nombreDispositivo) {
		this.nombreDispositivo = nombreDispositivo;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}

	@Override
	public String toString() {
		return "{\"tokenConvenio\":\"" + tokenConvenio + "\", \"tokenTransaccional\":\"" + tokenTransaccional
				+ "\", \"huellaDispositivo\":\"" + huellaDispositivo + "\", \"idCuenta\":\"" + idCuenta
				+ "\", \"tipoDispersion\":\"" + tipoDispersion + "\", \"transferenciaOtraEntidad\":\""
				+ Arrays.toString(transferenciaOtraEntidad) + "\", \"SO\":\"" + SO + "\", \"nombreDispositivo\":\""
				+ nombreDispositivo + "\", \"IP\":\"" + IP + "\"}";
	}
	

	

	

	
    
}
