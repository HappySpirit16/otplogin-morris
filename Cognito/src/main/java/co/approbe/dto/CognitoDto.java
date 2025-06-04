package co.approbe.dto;

public class CognitoDto {

	private String email; 
	private String password; 
	private String action;
	private String channel; 
	private String otp; 
	private String phoneNumber;
	private String data;
	
	
	public CognitoDto() {
	
	}

	public CognitoDto(String email, String password, String action, String channel, String otp, String phoneNumber,
			String data) {
		super();
		this.email = email;
		this.password = password;
		this.action = action;
		this.channel = channel;
		this.otp = otp;
		this.phoneNumber = phoneNumber;
		this.data = data;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "{\"email\":\"" + email + "\", \"password\":\"" + password + "\", \"action\":\"" + action
				+ "\", \"channel\":\"" + channel + "\", \"otp\":\"" + otp + "\", \"phoneNumber\":\"" + phoneNumber
				+ "\", \"data\":\"" + data + "\"}";
	}



}
