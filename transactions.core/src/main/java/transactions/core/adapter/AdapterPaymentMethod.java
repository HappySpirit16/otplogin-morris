package transactions.core.adapter;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

import transactions.core.dto.Request;

public class AdapterPaymentMethod implements IUpdateCore {
	private IP2p iP2p;
	private IBnpl iBnpl;
	private IWallet iWallet;

	public AdapterPaymentMethod(IP2p iP2p, IBnpl iBnpl, IWallet iWallet) {
		this.iP2p = iP2p;
		this.iBnpl = iBnpl;
		this.iWallet = iWallet;
	}

	@Override
	public String updateCredit(Request request, String source, DynamoDBMapper mapper, AmazonDynamoDB db,
			Context context) {
		if (source.equals("P2P")) {
			return iP2p.saveTransaction(request, mapper, db, context,"default_payment_config");
		} else if (source.equals("BNPL")) {
			return iBnpl.saveTransaction(request, mapper, db, context,"default_payment_config");
		} else if (source.equals("WALLET")) {
			return iWallet.saveTransaction(request, mapper, db, context,"default_payment_config");
		} else {
			return "fail";
		}

	}

}
