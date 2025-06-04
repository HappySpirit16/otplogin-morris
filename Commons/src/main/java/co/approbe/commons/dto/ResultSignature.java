package co.approbe.commons.dto;

public class ResultSignature {
	private String transactionId;
	private String type;
	private String operationCode;
	private String operationMsg;
	private String processedDate;
	private String urlDocuments;
	private String account;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getOperationMsg() {
		return operationMsg;
	}

	public void setOperationMsg(String operationMsg) {
		this.operationMsg = operationMsg;
	}

	public String getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(String processedDate) {
		this.processedDate = processedDate;
	}

	public String getUrlDocuments() {
		return urlDocuments;
	}

	public void setUrlDocuments(String urlDocuments) {
		this.urlDocuments = urlDocuments;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public ResultSignature() {
		super();
	}

	public static class FileIds {
		private String fileName;
		private String fileId;

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileId() {
			return fileId;
		}

		public void setFileId(String fileId) {
			this.fileId = fileId;
		}

		public FileIds(String fileName, String fileId) {
			super();
			this.fileName = fileName;
			this.fileId = fileId;
		}

		@Override
		public String toString() {
			return "{\"fileName\":\"" + fileName + "\", \"fileId\":\"" + fileId + "\"}";
		}

	}

	@Override
	public String toString() {
		return "{\"transactionId\":\"" + transactionId + "\", \"type\":\"" + type + "\", \"operationCode\":\""
				+ operationCode + "\", \"operationMsg\":\"" + operationMsg + "\", \"processedDate\":\"" + processedDate
				+ "\", \"urlDocuments\":\"" + urlDocuments + "\", \"account\":\"" + account + "\"}";
	}

}
