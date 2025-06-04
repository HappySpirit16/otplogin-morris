package co.approbe.commons.dto;

import java.util.List;

import com.google.gson.Gson;

import co.approbe.commons.model.UserCredentials;

public class CashPaymentDto {

	private UserCredentials casheer;
	private String customer;
	private Double totalAmmount;
	private List<CashAllocationDto> wLstCredits;

	public CashPaymentDto() {

	}

	public UserCredentials getCasheer() {
		return casheer;
	}

	public void setCasheer(UserCredentials casheer) {
		this.casheer = casheer;
	}

	public Double getTotalAmmount() {
		return totalAmmount;
	}

	public void setTotalAmmount(Double totalAmmount) {
		this.totalAmmount = totalAmmount;
	}

	public List<CashAllocationDto> getwLstCredits() {
		return wLstCredits;
	}

	public void setwLstCredits(List<CashAllocationDto> wLstCredits) {
		this.wLstCredits = wLstCredits;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getStringCredits() {
		return new Gson().toJson(wLstCredits);
	}
	@Override
	public String toString() {
		return "{\"casheer\":\"" + casheer + "\", \"customer\":\"" + customer + "\", \"totalAmmount\":\"" + totalAmmount
				+ "\", \"wLstCredits\":\"" + getStringCredits() + "\"}";
	}

}
