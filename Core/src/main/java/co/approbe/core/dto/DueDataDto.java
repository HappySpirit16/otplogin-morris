package co.approbe.core.dto;

public class DueDataDto {
	
	private InformationDto principal; 
	private InformationDto interest; 
	private InformationDto delinquency_interest; 
	private InformationDto installment_fee; 
	private InformationDto daily_fee; 
	private InformationDto initial_fee; 
	private InformationDto late_fee;
	public InformationDto getPrincipal() {
		return principal;
	}
	public void setPrincipal(InformationDto principal) {
		this.principal = principal;
	}
	public InformationDto getInterest() {
		return interest;
	}
	public void setInterest(InformationDto interest) {
		this.interest = interest;
	}
	public InformationDto getDelinquency_interest() {
		return delinquency_interest;
	}
	public void setDelinquency_interest(InformationDto delinquency_interest) {
		this.delinquency_interest = delinquency_interest;
	}
	public InformationDto getInstallment_fee() {
		return installment_fee;
	}
	public void setInstallment_fee(InformationDto installment_fee) {
		this.installment_fee = installment_fee;
	}
	public InformationDto getDaily_fee() {
		return daily_fee;
	}
	public void setDaily_fee(InformationDto daily_fee) {
		this.daily_fee = daily_fee;
	}
	public InformationDto getInitial_fee() {
		return initial_fee;
	}
	public void setInitial_fee(InformationDto initial_fee) {
		this.initial_fee = initial_fee;
	}
	public InformationDto getLate_fee() {
		return late_fee;
	}
	public void setLate_fee(InformationDto late_fee) {
		this.late_fee = late_fee;
	}
	@Override
	public String toString() {
		return "{\"principal\":\"" + principal + "\", \"interest\":\"" + interest + "\", \"delinquency_interest\":\""
				+ delinquency_interest + "\", \"installment_fee\":\"" + installment_fee + "\", \"daily_fee\":\""
				+ daily_fee + "\", \"initial_fee\":\"" + initial_fee + "\", \"late_fee\":\"" + late_fee + "\"}";
	} 
	
	
	
	

}
