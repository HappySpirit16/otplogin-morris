package co.approbe.payment.create.dto;

public class BodyClienCredistDto {

	private String actionStrategyPattern; 
	private String identificacion;
	
	public BodyClienCredistDto() {
		super();
	}
	public BodyClienCredistDto(String actionStrategyPattern, String identificacion) {
		super();
		this.actionStrategyPattern = actionStrategyPattern;
		this.identificacion = identificacion;
	}
	public String getActionStrategyPattern() {
		return actionStrategyPattern;
	}
	public void setActionStrategyPattern(String actionStrategyPattern) {
		this.actionStrategyPattern = actionStrategyPattern;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	@Override
	public String toString() {
		return "{\"actionStrategyPattern\":\"" + actionStrategyPattern + "\", \"identificacion\":\"" + identificacion
				+ "\"}";
	} 
	
	
}
