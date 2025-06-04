package co.approbe.dto;

public class ResponseCognitoDto {

	private String idToken;
	private String accessToken;
	private String refreshToken; 
	private String errorMessage; 
	private String message; 
	private String statusCode;
	private int httpStatusCode; 
	private boolean userConfirmed; 
	private String userSub; 
	
	
	public ResponseCognitoDto() {

	}

	public ResponseCognitoDto(String idToken, String accessToken, String refreshToken, String errorMessage,
			String message, String statusCode, int httpStatusCode, boolean userConfirmed, String userSub) {
		super();
		this.idToken = idToken;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.errorMessage = errorMessage;
		this.message = message;
		this.statusCode = statusCode;
		this.httpStatusCode = httpStatusCode;
		this.userConfirmed = userConfirmed;
		this.userSub = userSub;
	}


	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public boolean isUserConfirmed() {
		return userConfirmed;
	}

	public void setUserConfirmed(boolean userConfirmed) {
		this.userConfirmed = userConfirmed;
	}

	public String getUserSub() {
		return userSub;
	}

	public void setUserSub(String userSub) {
		this.userSub = userSub;
	}

	@Override
	public String toString() {
		return "{\"idToken\":\"" + idToken + "\", \"accessToken\":\"" + accessToken + "\", \"refreshToken\":\""
				+ refreshToken + "\", \"errorMessage\":\"" + errorMessage + "\", \"message\":\"" + message
				+ "\", \"statusCode\":\"" + statusCode + "\", \"httpStatusCode\":\"" + httpStatusCode
				+ "\", \"userConfirmed\":\"" + userConfirmed + "\", \"userSub\":\"" + userSub + "\"}";
	}


}
