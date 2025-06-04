package co.approbe.commons.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionarieDto {

private String cuestionario;
	
	/**
	 * registroCuestionario = es el número generado en la validacion de evidente 
	 */

	private String registroCuestionario;
	
	/**
	 * fecha = es la fecha en la que se solicita el cuestionario. 
	 */

	private Date fecha;
	

    private CustomerEvidenteDto cliente;
	

    private List<RtaCuestionarioDto> rtaCuestionario = new ArrayList<RtaCuestionarioDto>();
	

    private List<PreguntaDto> preguntas = new ArrayList<PreguntaDto>();


	public String getCuestionario() {
		return cuestionario;
	}


	public void setCuestionario(String cuestionario) {
		this.cuestionario = cuestionario;
	}


	public String getRegistroCuestionario() {
		return registroCuestionario;
	}


	public void setRegistroCuestionario(String registroCuestionario) {
		this.registroCuestionario = registroCuestionario;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public CustomerEvidenteDto getCliente() {
		return cliente;
	}


	public void setCliente(CustomerEvidenteDto cliente) {
		this.cliente = cliente;
	}


	public List<RtaCuestionarioDto> getRtaCuestionario() {
		return rtaCuestionario;
	}


	public void setRtaCuestionario(List<RtaCuestionarioDto> rtaCuestionario) {
		this.rtaCuestionario = rtaCuestionario;
	}


	public List<PreguntaDto> getPreguntas() {
		return preguntas;
	}


	public void setPreguntas(List<PreguntaDto> preguntas) {
		this.preguntas = preguntas;
	}

    
}
