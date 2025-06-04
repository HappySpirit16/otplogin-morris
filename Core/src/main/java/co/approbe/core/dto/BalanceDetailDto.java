package co.approbe.core.dto;

public class BalanceDetailDto {

	private DueDataDto at_next_due_date;
	private CalculationDate at_calculation_date;
	
	public DueDataDto getAt_next_due_date() {
		return at_next_due_date;
	}
	public void setAt_next_due_date(DueDataDto at_next_due_date) {
		this.at_next_due_date = at_next_due_date;
	}
	public CalculationDate getAt_calculation_date() {
		return at_calculation_date;
	}
	public void setAt_calculation_date(CalculationDate at_calculation_date) {
		this.at_calculation_date = at_calculation_date;
	}
	@Override
	public String toString() {
		return "{\"at_next_due_date\":\"" + at_next_due_date + "\", \"at_calculation_date\":\"" + at_calculation_date
				+ "\"}";
	} 

}
