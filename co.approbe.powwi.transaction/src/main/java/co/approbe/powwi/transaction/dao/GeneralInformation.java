package co.approbe.powwi.transaction.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;

public class GeneralInformation {
	@DynamoDBTypeConvertedJson
	private Department department;
	@DynamoDBTypeConvertedJson
	private City city;
	@DynamoDBAttribute
	private String address;
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}


	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public class City {

		private int id;
		private String city;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		@Override
		public String toString() {
			return "City [id=" + id + ", city=" + city + "]";
		}

	}
	
}
