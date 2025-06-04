package co.approbe.commons.dto;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDto {

private String descripcion;
	
	/**
	 * orden = es el orden de la pregunta dentro del cuestionario. 
	 */	

	private String orden;
	
	/**
	 * cuestionario = es el cuestionario al que pertenece la pregunta. 
	 */	

	private CuestionarioDto cuestionario;
	
	/**
	 * respuestas = son las posibles respuestas que pertenecen a la preguntas. 
	 */	

    private List<RespuestaDto> respuestas = new ArrayList<RespuestaDto>();

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

	public CuestionarioDto getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(CuestionarioDto cuestionario) {
		this.cuestionario = cuestionario;
	}

	public List<RespuestaDto> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<RespuestaDto> respuestas) {
		this.respuestas = respuestas;
	}
    
    
}
