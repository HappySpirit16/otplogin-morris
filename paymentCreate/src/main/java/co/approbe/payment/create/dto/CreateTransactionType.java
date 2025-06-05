package co.approbe.payment.create.dto;

import java.util.List;

public class CreateTransactionType {
	private Long EntityCode;
    private String SessionToken;
    private Integer SrvCode;
    private Double TransValue;
    private Double TransVatValue;
    private String SrvCurrency;
    private String URLRedirect;
    private String URLResponse;
    private String[] ReferenceArray;
    private List<SubservicesArray> SubservicesArray;

	public CreateTransactionType() {
		super();
	}

	public Long getEntityCode() {
		return EntityCode;
	}

	public void setEntityCode(Long entityCode) {
		this.EntityCode = entityCode;
	}

	public String getSessionToken() {
		return SessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.SessionToken = sessionToken;
	}

	public Integer getSrvCode() {
		return SrvCode;
	}

	public void setSrvCode(Integer srvCode) {
		this.SrvCode = srvCode;
	}

	public Double getTransValue() {
		return TransValue;
	}

	public void setTransValue(Double transValue) {
		this.TransValue = transValue;
	}

	public Double getTransVatValue() {
		return TransVatValue;
	}

	public void setTransVatValue(Double transVatValue) {
		this.TransVatValue = transVatValue;
	}

	public String getSrvCurrency() {
		return SrvCurrency;
	}

	public void setSrvCurrency(String srvCurrency) {
		this.SrvCurrency = srvCurrency;
	}

	public String getuRLRedirect() {
		return URLRedirect;
	}

	public void setuRLRedirect(String uRLRedirect) {
		this.URLRedirect = uRLRedirect;
	}

	public String getuRLResponse() {
		return URLResponse;
	}

	public void setuRLResponse(String uRLResponse) {
		this.URLResponse = uRLResponse;
	}

	

	public String[] getReferenceArray() {
		return ReferenceArray;
	}

	public void setReferenceArray(String[] referenceArray) {
		ReferenceArray = referenceArray;
	}

	public List<SubservicesArray> getSubservicesArray() {
		return SubservicesArray;
	}

	public void setSubservicesArray(List<SubservicesArray> subservicesArray) {
		this.SubservicesArray = subservicesArray;
	}




    
	
    
    

}
