package co.approbe.payment.create.dto;

import co.approbe.payment.create.dao.TransactionResponse;

public class CreateTransactionResponseType {

	private String ReturnCode;
    private Integer TicketId;
    private String eCollectUrl;
    private String LifetimeSecs;
    private TransactionResponse TransactionResponse;
    
	public CreateTransactionResponseType() {
		super();
	}

	public String getReturnCode() {
		return ReturnCode;
	}

	public void setReturnCode(String returnCode) {
		this.ReturnCode = returnCode;
	}

	public Integer getTicketId() {
		return TicketId;
	}

	public void setTicketId(Integer ticketId) {
		this.TicketId = ticketId;
	}

	public String geteCollectUrl() {
		return eCollectUrl;
	}

	public void seteCollectUrl(String eCollectUrl) {
		this.eCollectUrl = eCollectUrl;
	}

	public String getLifetimeSecs() {
		return LifetimeSecs;
	}

	public void setLifetimeSecs(String lifetimeSecs) {
		this.LifetimeSecs = lifetimeSecs;
	}

	public TransactionResponse getTransactionResponse() {
		return TransactionResponse;
	}

	public void setTransactionResponse(TransactionResponse transactionResponse) {
		TransactionResponse = transactionResponse;
	}

	@Override
	public String toString() {
		return "{\"ReturnCode\":\"" + ReturnCode + "\", \"TicketId\":\"" + TicketId + "\", \"eCollectUrl\":\""
				+ eCollectUrl + "\", \"LifetimeSecs\":\"" + LifetimeSecs + "\", \"TransactionResponse\":\""
				+ TransactionResponse + "\"}";
	}

	
    
	
    
}
