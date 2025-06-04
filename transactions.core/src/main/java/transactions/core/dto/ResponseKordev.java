package transactions.core.dto;

public class ResponseKordev {

	private String owner;
	private String kordev_id;
	private String id;

	public ResponseKordev() {
		super();
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getKordev_id() {
		return kordev_id;
	}

	public void setKordev_id(String kordev_id) {
		this.kordev_id = kordev_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{\"owner\":\"" + owner + "\", \"kordev_id\":\"" + kordev_id + "\", \"id\":\"" + id + "\"}";
	}

}
