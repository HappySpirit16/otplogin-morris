package co.approbe.powwi.transaction.dto;

public class Relation {
	public String numberIdBorrower;
	public String numberIdMoneylender;
	public String id;
	public String getNumberIdBorrower() {
		return numberIdBorrower;
	}
	public void setNumberIdBorrower(String numberIdBorrower) {
		this.numberIdBorrower = numberIdBorrower;
	}
	public String getNumberIdMoneylender() {
		return numberIdMoneylender;
	}
	public void setNumberIdMoneylender(String numberIdMoneylender) {
		this.numberIdMoneylender = numberIdMoneylender;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Relation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Relation(String numberIdBorrower, String numberIdMoneylender, String id) {
		super();
		this.numberIdBorrower = numberIdBorrower;
		this.numberIdMoneylender = numberIdMoneylender;
		this.id = id;
	}
	@Override
	public String toString() {
		return "{\"numberIdBorrower\":\"" + numberIdBorrower + "\", \"numberIdMoneylender\":\"" + numberIdMoneylender
				+ "\", \"id\":\"" + id + "\"}";
	}
	
	

}
