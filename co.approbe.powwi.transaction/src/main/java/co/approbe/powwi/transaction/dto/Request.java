package co.approbe.powwi.transaction.dto;

public class Request {
	private String operacion;
	private String celular;
	private String canal;
	private String tipoTransaccion;
	private String codigoTransaccion;
	
	private int tipoValidacion;
	private String otp;
	private String idEnrolamiento;
	private String idValidacion;
	private double scoreValidacion;
	private String idAutenticacionAliado;
	private String idCuenta;
	private String idAliado;
	private String monto;
	private String referencia1;
	private String referencia2;
	private String productId;
	private String idTransaccionAliado;
	private String tipoPago;
	private String codigoBarras;
	private String referenciaManual;
	private String valor;
	private String motivo;
	private String canalRetiro;
	private String idOperacion;
	private String tipoDispersion;
	private TransferenciaOtraEntidad[] transferenciaOtraEntidad;
	private TransferenciaPowwi[] transferenciaPowwi;
	
	
	private String correoElectronico;
	private boolean checkBiometria;
	private boolean checkDatos;
	private boolean checkInformacionTr;
	private boolean checkHabbeasData;
	private boolean checkProducto;
	private String fechaExpedicion;
	private String fechaNacimiento;
	private String huellaDispositivo;
	private String IP;
	private String nombreDispositivo;
	private String numeroDocumento;
	private String sexo;
	private String SO;
	private String tipoDocumento;
	private String mes;
	private String anio;
	
	private String idRelationPay;
		
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public String getCodigoTransaccion() {
		return codigoTransaccion;
	}
	public void setCodigoTransaccion(String codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}
	public int getTipoValidacion() {
		return tipoValidacion;
	}
	public void setTipoValidacion(int tipoValidacion) {
		this.tipoValidacion = tipoValidacion;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getIdEnrolamiento() {
		return idEnrolamiento;
	}
	public void setIdEnrolamiento(String idEnrolamiento) {
		this.idEnrolamiento = idEnrolamiento;
	}
	public String getIdValidacion() {
		return idValidacion;
	}
	public void setIdValidacion(String idValidacion) {
		this.idValidacion = idValidacion;
	}
	public double getScoreValidacion() {
		return scoreValidacion;
	}
	public void setScoreValidacion(double scoreValidacion) {
		this.scoreValidacion = scoreValidacion;
	}
	public String getIdAutenticacionAliado() {
		return idAutenticacionAliado;
	}
	public void setIdAutenticacionAliado(String idAutenticacionAliado) {
		this.idAutenticacionAliado = idAutenticacionAliado;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public boolean isCheckBiometria() {
		return checkBiometria;
	}
	public void setCheckBiometria(boolean checkBiometria) {
		this.checkBiometria = checkBiometria;
	}
	public boolean isCheckDatos() {
		return checkDatos;
	}
	public void setCheckDatos(boolean checkDatos) {
		this.checkDatos = checkDatos;
	}
	public boolean isCheckInformacionTr() {
		return checkInformacionTr;
	}
	public void setCheckInformacionTr(boolean checkInformacionTr) {
		this.checkInformacionTr = checkInformacionTr;
	}
	public boolean isCheckHabbeasData() {
		return checkHabbeasData;
	}
	public void setCheckHabbeasData(boolean checkHabbeasData) {
		this.checkHabbeasData = checkHabbeasData;
	}
	public boolean isCheckProducto() {
		return checkProducto;
	}
	public void setCheckProducto(boolean checkProducto) {
		this.checkProducto = checkProducto;
	}
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}
	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getHuellaDispositivo() {
		return huellaDispositivo;
	}
	public void setHuellaDispositivo(String huellaDispositivo) {
		this.huellaDispositivo = huellaDispositivo;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getNombreDispositivo() {
		return nombreDispositivo;
	}
	public void setNombreDispositivo(String nombreDispositivo) {
		this.nombreDispositivo = nombreDispositivo;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getSO() {
		return SO;
	}
	public void setSO(String sO) {
		SO = sO;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}
	public String getIdAliado() {
		return idAliado;
	}
	public void setIdAliado(String idAliado) {
		this.idAliado = idAliado;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getIdTransaccionAliado() {
		return idTransaccionAliado;
	}
	public void setIdTransaccionAliado(String idTransaccionAliado) {
		this.idTransaccionAliado = idTransaccionAliado;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getReferenciaManual() {
		return referenciaManual;
	}
	public void setReferenciaManual(String referenciaManual) {
		this.referenciaManual = referenciaManual;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getCanalRetiro() {
		return canalRetiro;
	}
	public void setCanalRetiro(String canalRetiro) {
		this.canalRetiro = canalRetiro;
	}
	public String getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}
	public String getTipoDispersion() {
		return tipoDispersion;
	}
	public void setTipoDispersion(String tipoDispersion) {
		this.tipoDispersion = tipoDispersion;
	}
	public TransferenciaOtraEntidad[] getTransferenciaOtraEntidad() {
		return transferenciaOtraEntidad;
	}
	public void setTransferenciaOtraEntidad(TransferenciaOtraEntidad[] transferenciaOtraEntidad) {
		this.transferenciaOtraEntidad = transferenciaOtraEntidad;
	}
	
	public TransferenciaPowwi[] getTransferenciaPowwi() {
		return transferenciaPowwi;
	}
	public void setTransferenciaPowwi(TransferenciaPowwi[] transferenciaPowwi) {
		this.transferenciaPowwi = transferenciaPowwi;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getIdRelationPay() {
		return idRelationPay;
	}
	public void setIdRelationPay(String idRelationPay) {
		this.idRelationPay = idRelationPay;
	}

	
    
    
}
