package co.approbe.payment.create.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "partner")
public class PartnerDto {

		@DynamoDBHashKey
		@DynamoDBAttribute
		private String id;
		@DynamoDBAttribute
		private String acountBank;
		public PartnerDto() {
			super();
		}
		public PartnerDto(String id, String acountBank) {
			super();
			this.id = id;
			this.acountBank = acountBank;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getAcountBank() {
			return acountBank;
		}
		public void setAcountBank(String acountBank) {
			this.acountBank = acountBank;
		}
		@Override
		public String toString() {
			return "{\"id\":\"" + id + "\", \"acountBank\":\"" + acountBank + "\"}";
		} 
		
}
