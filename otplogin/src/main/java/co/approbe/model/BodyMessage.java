package co.approbe.model;

public class BodyMessage {

	private String description; 
	private String cellphone; 
	private String email; 
	private String body; 
	private Integer channel; 
	private String action;
	private String numberId; 
	private String otp; 
	private int day; 
	private double ammount; 
	
	public BodyMessage() {
		super();
	}

	public BodyMessage(String description, String cellphone, String email, String body, Integer channel, String action,
			String numberId, String otp, int day, double ammount) {
		super();
		this.description = description;
		this.cellphone = cellphone;
		this.email = email;
		this.body = body;
		this.channel = channel;
		this.action = action;
		this.numberId = numberId;
		this.otp = otp;
		this.day = day;
		this.ammount = ammount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	public String getNumberId() {
		return numberId;
	}
	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	@Override
	public String toString() {
		return "{\"description\":\"" + description + "\", \"cellphone\":\"" + cellphone + "\", \"email\":\"" + email
				+ "\", \"body\":\"" + body + "\", \"channel\":\"" + channel + "\", \"action\":\"" + action
				+ "\", \"numberId\":\"" + numberId + "\", \"otp\":\"" + otp + "\", \"day\":\"" + day
				+ "\", \"ammount\":\"" + ammount + "\"}";
	}
	
}
