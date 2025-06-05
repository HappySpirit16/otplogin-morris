package co.approbe.payment.create.dto;

public class TransactionInformation {
	private String EntityCode;
    private String SessionToken;
    private String TicketId;
    
	public String getEntityCode() {
		return EntityCode;
	}
	public void setEntityCode(String entityCode) {
		EntityCode = entityCode;
	}
	public String getSessionToken() {
		return SessionToken;
	}
	public void setSessionToken(String sessionToken) {
		SessionToken = sessionToken;
	}
	public String getTicketId() {
		return TicketId;
	}
	public void setTicketId(String ticketId) {
		TicketId = ticketId;
	}
	public TransactionInformation() {
		super();
	}
	@Override
	public String toString() {
		return "{\"EntityCode\":\"" + EntityCode + "\", \"SessionToken\":\"" + SessionToken + "\", \"TicketId\":\""
				+ TicketId + "\"}";
	}
    
    
}
