package co.approbe.payment.create.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "failedTransactions")
public class FailedTransactions {
	@DynamoDBHashKey
	private String ticketId;
	@DynamoDBAttribute
    private String status;
	@DynamoDBAttribute
    private String description;
    @DynamoDBAttribute
    private String statusDate;
    @DynamoDBAttribute
    private String statusUpdate;
    @DynamoDBAttribute
    private String channel;
    @DynamoDBAttribute
    private String typeIdentification;
    @DynamoDBAttribute
    private String identification;
    @DynamoDBAttribute
    private String amount;
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}
	public String getStatusUpdate() {
		return statusUpdate;
	}
	public void setStatusUpdate(String statusUpdate) {
		this.statusUpdate = statusUpdate;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getTypeIdentification() {
		return typeIdentification;
	}
	public void setTypeIdentification(String typeIdentification) {
		this.typeIdentification = typeIdentification;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public FailedTransactions() {
		super();
	}
	@Override
	public String toString() {
		return "{\"ticketId\":\"" + ticketId + "\", \"status\":\"" + status + "\", \"description\":\"" + description
				+ "\", \"statusDate\":\"" + statusDate + "\", \"statusUpdate\":\"" + statusUpdate + "\", \"channel\":\""
				+ channel + "\", \"typeIdentification\":\"" + typeIdentification + "\", \"identification\":\""
				+ identification + "\", \"amount\":\"" + amount + "\"}";
	}
    
    

}
