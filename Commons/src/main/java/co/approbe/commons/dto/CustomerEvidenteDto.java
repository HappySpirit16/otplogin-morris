package co.approbe.commons.dto;

public class CustomerEvidenteDto {

	private String tipoDocumento;
	private String numeroDocumento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String fechaExpedicionDocumento;
	private String consulta="3222"; 

	public CustomerEvidenteDto() {

	}

	public CustomerEvidenteDto(String pTipoIdentificacion, String pIdentificacion, String pName, String pPrimerApellido,
			String pFechaExpedicion) {
		
		this.tipoDocumento=pTipoIdentificacion;
		this.numeroDocumento= pIdentificacion;
		this.primerNombre=pName;
		this.primerApellido=pPrimerApellido;
		this.fechaExpedicionDocumento=pFechaExpedicion;
		

	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getFechaExpedicionDocumento() {
		return fechaExpedicionDocumento;
	}

	public void setFechaExpedicionDocumento(String fechaExpedicionDocumento) {
		this.fechaExpedicionDocumento = fechaExpedicionDocumento;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	@Override
	public String toString() {
		return "{\"tipoDocumento\":\"" + tipoDocumento + "\", \"numeroDocumento\":\"" + numeroDocumento
				+ "\", \"primerNombre\":\"" + primerNombre + "\", \"segundoNombre\":\"" + segundoNombre
				+ "\", \"primerApellido\":\"" + primerApellido + "\", \"segundoApellido\":\"" + segundoApellido
				+ "\", \"fechaExpedicionDocumento\":\"" + fechaExpedicionDocumento + "\", \"consulta\":\"" + consulta
				+ "\"}";
	}

}
