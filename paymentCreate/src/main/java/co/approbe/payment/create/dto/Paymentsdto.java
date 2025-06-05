package co.approbe.payment.create.dto;

public class Paymentsdto {

	private double financier;
	private double approbe;
	private String accountFinancier; 
	private String accountAproobe; 
	
	public Paymentsdto() {
		super();
	}
	
	public Paymentsdto(int financier, int approbe, String accountFinancier, String accountAproobe) {
		super();
		this.financier = financier;
		this.approbe = approbe;
		this.accountFinancier = accountFinancier;
		this.accountAproobe = accountAproobe;
	}

	public double getFinancier() {
		return financier;
	}

	public void setFinancier(double financier) {
		this.financier = financier;
	}

	public double getApprobe() {
		return approbe;
	}

	public void setApprobe(double approbe) {
		this.approbe = approbe;
	}

	public void setApprobe(int approbe) {
		this.approbe = approbe;
	}

	public String getAccountFinancier() {
		return accountFinancier;
	}
	public void setAccountFinancier(String accountFinancier) {
		this.accountFinancier = accountFinancier;
	}
	public String getAccountAproobe() {
		return accountAproobe;
	}
	public void setAccountAproobe(String accountAproobe) {
		this.accountAproobe = accountAproobe;
	}
	@Override
	public String toString() {
		return "{\"financier\":\"" + financier + "\", \"approbe\":\"" + approbe + "\", \"accountFinancier\":\""
				+ accountFinancier + "\", \"accountAproobe\":\"" + accountAproobe + "\"}";
	}

	
}
