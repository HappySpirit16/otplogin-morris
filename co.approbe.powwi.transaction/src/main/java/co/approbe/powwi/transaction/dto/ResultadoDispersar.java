package co.approbe.powwi.transaction.dto;

public class ResultadoDispersar {
	private String codigoResultado;
	private String mensaje;
	private Transacciones[] transacciones;
	private String productoOrigen;
	
	public class Transacciones{
		private String codigoResultado;
		private String mensaje;
		private String idOperacionAliado;
		private String idOperacion;
		private String valorTotalTransaccion;
		private String costoComision;
		private String costoIVA;
		private String cobroGMF;
		private String fecha;
		
		public Transacciones() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getCodigoResultado() {
			return codigoResultado;
		}
		public void setCodigoResultado(String codigoResultado) {
			this.codigoResultado = codigoResultado;
		}
		public String getMensaje() {
			return mensaje;
		}
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		public String getIdOperacionAliado() {
			return idOperacionAliado;
		}
		public void setIdOperacionAliado(String idOperacionAliado) {
			this.idOperacionAliado = idOperacionAliado;
		}
		public String getIdOperacion() {
			return idOperacion;
		}
		public void setIdOperacion(String idOperacion) {
			this.idOperacion = idOperacion;
		}
		public String getValorTotalTransaccion() {
			return valorTotalTransaccion;
		}
		public void setValorTotalTransaccion(String valorTotalTransaccion) {
			this.valorTotalTransaccion = valorTotalTransaccion;
		}
		public String getCostoComision() {
			return costoComision;
		}
		public void setCostoComision(String costoComision) {
			this.costoComision = costoComision;
		}
		public String getCostoIVA() {
			return costoIVA;
		}
		public void setCostoIVA(String costoIVA) {
			this.costoIVA = costoIVA;
		}
		public String getCobroGMF() {
			return cobroGMF;
		}
		public void setCobroGMF(String cobroGMF) {
			this.cobroGMF = cobroGMF;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public Transacciones(String codigoResultado, String mensaje, String idOperacionAliado, String idOperacion,
				String valorTotalTransaccion, String costoComision, String costoIVA, String cobroGMF, String fecha) {
			super();
			this.codigoResultado = codigoResultado;
			this.mensaje = mensaje;
			this.idOperacionAliado = idOperacionAliado;
			this.idOperacion = idOperacion;
			this.valorTotalTransaccion = valorTotalTransaccion;
			this.costoComision = costoComision;
			this.costoIVA = costoIVA;
			this.cobroGMF = cobroGMF;
			this.fecha = fecha;
		}
		@Override
		public String toString() {
			return "{\"codigoResultado\":\"" + codigoResultado + "\", \"mensaje\":\"" + mensaje
					+ "\", \"idOperacionAliado\":\"" + idOperacionAliado + "\", \"idOperacion\":\"" + idOperacion
					+ "\", \"valorTotalTransaccion\":\"" + valorTotalTransaccion + "\", \"costoComision\":\""
					+ costoComision + "\", \"costoIVA\":\"" + costoIVA + "\", \"cobroGMF\":\"" + cobroGMF
					+ "\", \"fecha\":\"" + fecha + "\"}";
		}
		
		
		
	}

	public String getCodigoResultado() {
		return codigoResultado;
	}

	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	

	public Transacciones[] getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Transacciones[] transacciones) {
		this.transacciones = transacciones;
	}

	public String getProductoOrigen() {
		return productoOrigen;
	}

	public void setProductoOrigen(String productoOrigen) {
		this.productoOrigen = productoOrigen;
	}

	
	public ResultadoDispersar() {
		super();
	}

	@Override
	public String toString() {
		return "{\"codigoResultado\":\"" + codigoResultado + "\", \"mensaje\":\"" + mensaje + "\", \"transacciones\":\""
				+ transacciones + "\", \"productoOrigen\":\"" + productoOrigen + "\"}";
	}

	public ResultadoDispersar(String codigoResultado, String mensaje, Transacciones[] transacciones,
			String productoOrigen) {
		super();
		this.codigoResultado = codigoResultado;
		this.mensaje = mensaje;
		this.transacciones = transacciones;
		this.productoOrigen = productoOrigen;
	}
	

}
