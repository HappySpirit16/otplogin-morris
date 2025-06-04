package co.approbe.commons.dto;

public class RtaCuestionarioDto {

	private String ordenPregunta;
	private String ordenRespuesta;
	private QuestionarieDto cuestionario;

	public String getOrdenPregunta() {
		return ordenPregunta;
	}

	public void setOrdenPregunta(String ordenPregunta) {
		this.ordenPregunta = ordenPregunta;
	}

	public String getOrdenRespuesta() {
		return ordenRespuesta;
	}

	public void setOrdenRespuesta(String ordenRespuesta) {
		this.ordenRespuesta = ordenRespuesta;
	}

	public QuestionarieDto getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(QuestionarieDto cuestionario) {
		this.cuestionario = cuestionario;
	}

	@Override
	public String toString() {
		return "{\"ordenPregunta\":\"" + ordenPregunta + "\", \"ordenRespuesta\":\"" + ordenRespuesta + "\"}";
	}

}
