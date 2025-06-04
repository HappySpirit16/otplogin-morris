package co.approbe.core.dto;

import java.time.*;
import java.time.format.*;

public class LoanSummaryDto {

	private PaymentSummaryDto next_payment;
	private PaymentSummaryDto full_payment;
	private PaymentSummaryDto immediate_payment;
	private Integer number_of_elapsed_due_dates;
	private Integer number_of_covered_installments;	
	private Integer simple_days_past_due; 
	
	
	public LoanSummaryDto() {
	}

	public PaymentSummaryDto getNext_payment() {
		return next_payment;
	}
	public Integer getSimple_days_past_due() {
		return simple_days_past_due;
	}

	public void setSimple_days_past_due(Integer simple_days_past_due) {
		this.simple_days_past_due = simple_days_past_due;
	}
	public void setNext_payment(PaymentSummaryDto next_payment) {
		this.next_payment = next_payment;
	}

	public PaymentSummaryDto getFull_payment() {
		return full_payment;
	}

	public void setFull_payment(PaymentSummaryDto full_payment) {
		this.full_payment = full_payment;
	}

	public PaymentSummaryDto getImmediate_payment() {
		return immediate_payment;
	}

	public void setImmediate_payment(PaymentSummaryDto immediate_payment) {
		this.immediate_payment = immediate_payment;
	}

	public Double getNextPaid(int mora) {
		if (next_payment != null && immediate_payment != null && next_payment.getAmount() != null
				&& immediate_payment.getAmount() > 0) {
				if(mora==0) {
					return immediate_payment.getAmount() + next_payment.getAmount();
				}else {
					return immediate_payment.getAmount();
				}
				
		} else {
			if(next_payment==null) {
				return immediate_payment.getAmount();
			}else {
				return next_payment.getAmount();
			}
			
		}
	}

	public String getNextFeeDate(int mora) {

		String wDate = "";
		String formattedDate = "";

		if (immediate_payment != null && immediate_payment.getAmount() > 0 && mora>0) {
			wDate = immediate_payment.getDate();
		} else if (next_payment != null) {
			wDate = next_payment.getDate();
		}

		if (!wDate.isEmpty()) {
			OffsetDateTime offsetDateTime = OffsetDateTime.parse(wDate);

			LocalDate localDate = offsetDateTime.toLocalDate();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			formattedDate = localDate.format(formatter);
		}

		return formattedDate;

	}

	public Integer getNumber_of_elapsed_due_dates() {
		return number_of_elapsed_due_dates;
	}

	public void setNumber_of_elapsed_due_dates(Integer number_of_elapsed_due_dates) {
		this.number_of_elapsed_due_dates = number_of_elapsed_due_dates;
	}

	public Integer getNumber_of_covered_installments() {
		return number_of_covered_installments;
	}

	public void setNumber_of_covered_installments(Integer number_of_covered_installments) {
		this.number_of_covered_installments = number_of_covered_installments;
	}

	@Override
	public String toString() {
		return "{\"next_payment\":\"" + next_payment + "\", \"full_payment\":\"" + full_payment
				+ "\", \"immediate_payment\":\"" + immediate_payment + "\", \"number_of_elapsed_due_dates\":\""
				+ number_of_elapsed_due_dates + "\", \"number_of_covered_installments\":\""
				+ number_of_covered_installments + "\"}";
	}


	

}
