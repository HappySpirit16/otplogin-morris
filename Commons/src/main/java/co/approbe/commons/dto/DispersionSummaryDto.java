package co.approbe.commons.dto;

public class DispersionSummaryDto {

	private String name;
	private String account;
	private Long ammount;
	

	public DispersionSummaryDto(String account, Long ammount) {
		super();
		this.account = account;
		this.ammount = ammount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getAmmount() {
		return ammount;
	}

	public void setAmmount(Long ammount) {
		this.ammount = ammount;
	}

}
