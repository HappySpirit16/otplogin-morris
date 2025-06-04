package co.approbe.commons.dto;

public class DateFilterDto {

	private String startDate;
	private String endDate;
	private String userId;
	private String casheer;
	private int start;
	private int end;
	private Long ammount;
	
	public DateFilterDto() {
		
	}

	public DateFilterDto(String startDate, String endDate, String userId, int start, int end) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.start = start;
		this.end = end;
	}
	
	public DateFilterDto(String startDate, String endDate, String userId, int start, int end, String pCasheer) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.start = start;
		this.end = end;
		this.casheer= pCasheer;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getAmmount() {
		return ammount;
	}

	public void setAmmount(Long ammount) {
		this.ammount = ammount;
	}

	public String getCasheer() {
		return casheer;
	}

	public void setCasheer(String casheer) {
		this.casheer = casheer;
	}

	
}
