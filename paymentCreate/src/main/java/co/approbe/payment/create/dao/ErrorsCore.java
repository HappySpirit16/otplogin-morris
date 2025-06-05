package co.approbe.payment.create.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "errorsCore")
public class ErrorsCore {

	@DynamoDBHashKey
	private String tickedId;
	@DynamoDBAttribute
	private String numberId;
	@DynamoDBAttribute
	private String idCredit;
	@DynamoDBAttribute
	private String ammount;
	@DynamoDBAttribute
	private String saveDate;
	@DynamoDBAttribute
	private String channel;
	
	public ErrorsCore() {
		super();
	}

	public ErrorsCore(String tickedId, String numberId, String idCredit, String ammount, String saveDate,
			String channel) {
		super();
		this.tickedId = tickedId;
		this.numberId = numberId;
		this.idCredit = idCredit;
		this.ammount = ammount;
		this.saveDate = saveDate;
		this.channel = channel;
	}




	public String getTickedId() {
		return tickedId;
	}

	public void setTickedId(String tickedId) {
		this.tickedId = tickedId;
	}

	public String getNumberId() {
		return numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getIdCredit() {
		return idCredit;
	}

	public void setIdCredit(String idCredit) {
		this.idCredit = idCredit;
	}

	public String getAmmount() {
		return ammount;
	}

	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}

	public String getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(String saveDate) {
		this.saveDate = saveDate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "{\"tickedId\":\"" + tickedId + "\", \"numberId\":\"" + numberId + "\", \"idCredit\":\"" + idCredit
				+ "\", \"ammount\":\"" + ammount + "\", \"saveDate\":\"" + saveDate + "\", \"channel\":\"" + channel
				+ "\"}";
	}

}
