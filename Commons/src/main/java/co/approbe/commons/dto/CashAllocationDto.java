package co.approbe.commons.dto;

public class CashAllocationDto {

	private String idCredit;
	private Long ammount;

	public CashAllocationDto() {

	}

	public CashAllocationDto(Long ammount, String idCredit) {
		super();
		this.idCredit = idCredit;
		this.ammount = ammount;
	}

	public String getIdCredit() {
		return idCredit;
	}

	public void setIdCredit(String idCredit) {
		this.idCredit = idCredit;
	}

	public Long getAmmount() {
		return ammount;
	}

	public void setAmmount(Long ammount) {
		this.ammount = ammount;
	}

	@Override
	public String toString() {
		return "{\"idCredit\":\"" + idCredit + "\", \"ammount\":\"" + ammount + "\"}";
	}

}
