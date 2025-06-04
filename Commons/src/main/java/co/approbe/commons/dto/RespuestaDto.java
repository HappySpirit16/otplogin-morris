package co.approbe.commons.dto;

public class RespuestaDto {
private String descripcion;
	
	/**
	 * orden = es el orden de la respuesta dentro de la pregunta. 
	 */

	private String orden;

	/**
	 * pregunta = es la pregunta a la que corresponde la respuesta. 
	 */

	private PreguntaDto pregunta;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public PreguntaDto getPregunta() {
		return pregunta;
	}

	public void setPregunta(PreguntaDto pregunta) {
		this.pregunta = pregunta;
	}
	
	
}
