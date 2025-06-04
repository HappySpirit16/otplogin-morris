package co.approbe.clientcredits.service;

import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

import co.approbe.commons.dto.CustomerDto;
import co.approbe.commons.model.AuthData;
import co.approbe.core.dto.CoreAdapterDto;
import co.approbe.core.dto.CreditDto;
import co.approbe.core.service.CreditService;

public class ClientCreditsService {

	private AmazonDynamoDB db;
	private Context context;
	private DynamoDBMapper mapper;
	

	public ClientCreditsService(AmazonDynamoDB pDb, Context pContext) {
		context = pContext;
		db = pDb;
		mapper = new DynamoDBMapper(db);
		
	}

	public List<CreditDto> getClientCredits(CustomerDto pCustomer) {

		List<CreditDto> wListCreditDto;

		CoreAdapterDto wCoreAdapterDto = new CoreAdapterDto(null, null, null, pCustomer.getIdentificacion(), null, null);
		
		CreditService wCredService = new CreditService(db, context);
		
		wListCreditDto= wCredService.getBorrowerStatus(wCoreAdapterDto);
		
		context.getLogger().log("Creditos en clientCredits: tamaño "+wListCreditDto.size()+" datos"+wListCreditDto.toString()); 
		
		if (wListCreditDto.size()>0) {
			//Get client info
			AuthData data = mapper.load(AuthData.class, pCustomer.getIdentificacion());
			if (data!=null) {
				wListCreditDto.get(0).setClient(data.getNames() +" "+data.getLastName());
			}else {
				wListCreditDto.get(0).setClient(" ");
			}
				
		}
		
		return wListCreditDto;
	}

	
}
