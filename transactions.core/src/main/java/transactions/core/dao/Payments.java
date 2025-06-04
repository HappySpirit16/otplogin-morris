package transactions.core.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

@DynamoDBTable(tableName = "payments")
public class Payments {
	@DynamoDBHashKey
	private String id;
	@DynamoDBAttribute
	private String numberId;
	@DynamoDBAttribute
	private String creditNumber;
	@DynamoDBAttribute
	private String TicketId;
	@DynamoDBAttribute
	private String ReturnCode;
	@DynamoDBAttribute
	private String eCollectUrl;
	@DynamoDBAttribute
	private String statusPayment;
	@DynamoDBAttribute
	private String LifetimeSecs;
	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
	@DynamoDBAttribute
	private Boolean status;
	@DynamoDBAttribute
	private String resultTransaction;
	@DynamoDBAttribute
	private String kordevId;
	
	
	LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.of("-05:00"));
	DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	@DynamoDBAttribute
	private String dateSave = dateTime.format(dtformat);
	
	public Payments() {
		super();
	}

	public String getTicketId() {
		return TicketId;
	}

	public void setTicketId(String ticketId) {
		this.TicketId = ticketId;
	}

	public String getReturnCode() {
		return ReturnCode;
	}

	public void setReturnCode(String returnCode) {
		this.ReturnCode = returnCode;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateSave() {
		return dateSave;
	}

	public void setDateSave(String dateSave) {
		this.dateSave = dateSave;
	}

	public String getNumberId() {
		return numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getStatusPayment() {
		return statusPayment;
	}

	public void setStatusPayment(String statusPayment) {
		this.statusPayment = statusPayment;
	}

	public String getResultTransaction() {
		return resultTransaction;
	}

	public void setResultTransaction(String resultTransaction) {
		this.resultTransaction = resultTransaction;
	}

	public String getKordevId() {
		return kordevId;
	}

	public void setKordevId(String kordevId) {
		this.kordevId = kordevId;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"numberId\":\"" + numberId + "\", \"creditNumber\":\"" + creditNumber
				+ "\", \"TicketId\":\"" + TicketId + "\", \"ReturnCode\":\"" + ReturnCode + "\", \"eCollectUrl\":\""
				+ eCollectUrl + "\", \"statusPayment\":\"" + statusPayment + "\", \"LifetimeSecs\":\"" + LifetimeSecs
				+ "\", \"status\":\"" + status + "\", \"resultTransaction\":\"" + resultTransaction
				+ "\", \"kordevId\":\"" + kordevId + "\", \"dateSave\":\"" + dateSave + "\"}";
	}

	
}
