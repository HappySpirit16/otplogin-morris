package transactions.core.strategy;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;

import transactions.core.dao.Credit;
import transactions.core.dao.Customerbnpl;
import transactions.core.dao.Data;
import transactions.core.dao.GeneralInformation;
import transactions.core.dto.CreditTransaction;
import transactions.core.dto.CreditTransaction.Loan;

public class WalletPayment implements IPaymentsMethod {

	@Override
	public String createCredit(Credit credit, GeneralInformation generalInformation, Context context,DynamoDBMapper mapper) {
		String dateString = credit.getCreationDate();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime localDate = LocalDateTime.parse(dateString, formatter);

		OffsetDateTime dateOffset = localDate.atOffset(ZoneOffset.of("-05:00"));

		DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
		String dateFormatter = dateOffset.format(formatterOut);
		System.out.println("fecha form" + dateFormatter);
		BiFunction<Integer, String, List<String>> calcularFechas = (months, date) -> {
			List<String> dates = new ArrayList<>();
			OffsetDateTime offsetDate = OffsetDateTime.parse(date);
			for (int i = 0; i < months; i++) {
				offsetDate = offsetDate.plusMonths(1);
				dates.add(offsetDate.format(formatterOut));
			}
			return dates;
		};
		CreditTransaction creditTransaction = new CreditTransaction();
		Data basicInformation=new Data();
		Loan loan = new Loan();
		Customerbnpl customerBnpl=new Customerbnpl();
		if(credit.getWarranty()!=null) {
			try {
				customerBnpl=mapper.load(Customerbnpl.class,credit.getWarranty());
				basicInformation=mapper.load(Data.class,credit.getNumberIdMoneylender());
			}catch (Exception e){
				context.getLogger().log("error: " + e.getMessage());
				return "fail";
			}
			
		}
		double installmentFeeCharge=customerBnpl.getId()!=null?(customerBnpl.getfGA()+customerBnpl.getPlatform()):0.00;
		creditTransaction.setActionStrategyPattern("CREATE_CREDIT");
		creditTransaction.setIdBorrower(credit.getNumberIdBorrower());
		creditTransaction.setIdCredit(credit.getId());
		creditTransaction.setOccurredOn(dateFormatter);
		loan.setAmountFinanced((int) (credit.getApproveValue()));
		loan.setConfigId("approbe_loan_config_bnpl_v1");
		loan.setDailyFeeCharge((int) (credit.getRateApprobe() / 12) / 30);
		loan.setDelinquencyInterestRate(generalInformation.getDelinquency_interest_rate());
		loan.setOwner(basicInformation.getNames()+" "+basicInformation.getLastName());
		loan.setOriginationDate(dateFormatter);
		loan.setInterestRate(credit.getRateApprobe());
		loan.setDueDates(calcularFechas.apply((int) credit.getFeesApprobe(), dateFormatter));
		loan.setInstallmentFeeCharge((int)installmentFeeCharge);
		creditTransaction.setLoan(loan);
		credit.setDailyFeeCharge(""+creditTransaction.getLoan().getDailyFeeCharge());
		credit.setConfigId("approbe_loan_config_bnpl_v1");
		credit.setDueDates(creditTransaction.getLoan().getDueDates().toString());
		credit.setInstallmentFeeCharge(""+installmentFeeCharge);
		String body = "";
		try {
			mapper.save(credit);
			ObjectMapper objectMapper = new ObjectMapper();
			body = objectMapper.writeValueAsString(creditTransaction);
			context.getLogger().log("envío Kordev: " +body);
		} catch (Exception e3) {
			context.getLogger().log("error: " + e3.getMessage());
			return "fail";
		}

		return body;

	}


}
