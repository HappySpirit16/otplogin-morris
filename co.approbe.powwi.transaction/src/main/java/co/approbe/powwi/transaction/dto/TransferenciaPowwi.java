package co.approbe.powwi.transaction.dto;

public class TransferenciaPowwi {
	private String idTransaccionAliado;
	private String numeroDeposito;
	private String monto;
	private String referencia1;
	private String referencia2;
	public TransferenciaPowwi() {
		super();
	}
	public String getIdTransaccionAliado() {
		return idTransaccionAliado;
	}
	public void setIdTransaccionAliado(String idTransaccionAliado) {
		this.idTransaccionAliado = idTransaccionAliado;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getReferencia1() {
		return referencia1;
	}
	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}
	public String getReferencia2() {
		return referencia2;
	}
	public void setReferencia2(String referencia2) {
		this.referencia2 = referencia2;
	}
	public String getNumeroDeposito() {
		return numeroDeposito;
	}
	public void setNumeroDeposito(String numeroDeposito) {
		this.numeroDeposito = numeroDeposito;
	}
	
	
	public TransferenciaPowwi(String idTransaccionAliado, String numeroDeposito, String monto, String referencia1,
			String referencia2) {
		super();
		this.idTransaccionAliado = idTransaccionAliado;
		this.numeroDeposito = numeroDeposito;
		this.monto = monto;
		this.referencia1 = referencia1;
		this.referencia2 = referencia2;
	}
	@Override
	public String toString() {
		return "{\"idTransaccionAliado\":\"" + idTransaccionAliado + "\", \"numeroDeposito\":\"" + numeroDeposito
				+ "\", \"monto\":\"" + monto + "\", \"referencia1\":\"" + referencia1 + "\", \"referencia2\":\""
				+ referencia2 + "\"}";
	}
	
	
}
