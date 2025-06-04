package co.approbe.commons.dto;

public class DocumentDto {

	private String name; 
	private int pag; 
	private String data;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPag() {
		return pag;
	}
	public void setPag(int pag) {
		this.pag = pag;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public DocumentDto() {
		super();
	}
	public DocumentDto(String name, int pag, String data) {
		super();
		this.name = name;
		this.pag = pag;
		this.data = data;
	} 
	
	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\", \"pag\":\"" + pag + "\",\"data\":\""+ data +"\"}";
	}
}
