package co.approbe.payment.create.dto;

public class RequestTokenSesion {
	private String EntityCode;
	private String ApiKey;
	
	
	public String getEntityCode() {
		return EntityCode;
	}


	public void setEntityCode(String entityCode) {
		this.EntityCode = entityCode;
	}


	public String getApiKey() {
		return ApiKey;
	}


	public void setApiKey(String apiKey) {
		this.ApiKey = apiKey;
	}


	public RequestTokenSesion() {
		super();
	}


	@Override
	public String toString() {
		return "{\"EntityCode\":\"" + EntityCode + "\", \"ApiKey\":\"" + ApiKey + "\"}";
	}
	
	

}
