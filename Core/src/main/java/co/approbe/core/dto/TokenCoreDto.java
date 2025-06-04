package co.approbe.core.dto;

public class TokenCoreDto {

	private String clientId;
	private String clientSecret;
	private String audience;
	private String grantType = "client_credentials";

	public TokenCoreDto() {

	}

	public TokenCoreDto(String pClientId, String pClientSecret, String pAudience) {
		this.audience = pAudience;
		this.clientId = pClientId;
		this.clientSecret = pClientSecret;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	@Override
	public String toString() {
		return "{\"client_id\":\"" + clientId + "\", \"client_secret\":\"" + clientSecret + "\", \"audience\":\""
				+ audience + "\", \"grant_type\":\"" + grantType + "\"}";
	}
	
	

}
