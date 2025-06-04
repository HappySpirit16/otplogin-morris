package co.approbe.dto;

public class DataCognitoDto {
	private String email; 
	private String password; 
	private String otp; 
	private String phoneNumber;
	private String value; 
	private String name; 
	
	public DataCognitoDto() {

	}
	public DataCognitoDto(String email, String password, String otp, String phoneNumber) {
		super();
		this.email = email;
		this.password = password;
		this.otp = otp;
		this.phoneNumber = phoneNumber;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "{\"email\":\"" + email + "\", \"password\":\"" + password + "\", \"otp\":\"" + otp
				+ "\", \"phoneNumber\":\"" + phoneNumber + "\", \"value\":\"" + value + "\", \"name\":\"" + name
				+ "\"}";
	}
	
}
