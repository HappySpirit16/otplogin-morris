package co.approbe.payment.create.dto;

public class ResponseTokenSesion {
	
	private String ReturnCode;
	private String SessionToken;
	private Double LifetimeSecs;
	public String getReturnCode() {
		return ReturnCode;
	}
	public void setReturnCode(String returnCode) {
		this.ReturnCode = returnCode;
	}
	public String getSessionToken() {
		return SessionToken;
	}
	public void setSessionToken(String sessionToken) {
		SessionToken = sessionToken;
	}
	public Double getLifetimeSecs() {
		return LifetimeSecs;
	}
	public void setLifetimeSecs(Double lifetimeSecs) {
		this.LifetimeSecs = lifetimeSecs;
	}
	public ResponseTokenSesion() {
		super();
	}
	@Override
	public String toString() {
		return "{\"ReturnCode\":\"" + ReturnCode + "\", \"SessionToken\":\"" + SessionToken + "\", \"LifetimeSecs\":\""
				+ LifetimeSecs + "\"}";
	}
	
	

}
