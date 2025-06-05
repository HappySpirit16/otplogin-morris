package co.approbe.powwi.transaction.dao;

public class Department {
	private int id;
	private String departmentName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartment() {
		return departmentName;
	}
	public void setDepartment(String department) {
		this.departmentName = department;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + "]";
	}
	
	
	

}
