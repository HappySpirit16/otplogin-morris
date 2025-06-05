package co.approbe.powwi.transaction.dto;


public class ResponseTx {

	private String codigoResultado;
	private String mensaje;
	private Transacciones[] transacciones;
	
	public class Transacciones{
		private String codigoResultado;
		private String mensaje;
		private String costoTransaccion;
		private String montoTarifa;
		private String gmfTotal;
		private String montoImpuesto;
		private String fecha;
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
		public String getCostoTransaccion() {
			return costoTransaccion;
		}
		public void setCostoTransaccion(String costoTransaccion) {
			this.costoTransaccion = costoTransaccion;
		}
		public String getMontoTarifa() {
			return montoTarifa;
		}
		public void setMontoTarifa(String montoTarifa) {
			this.montoTarifa = montoTarifa;
		}
		public String getGmfTotal() {
			return gmfTotal;
		}
		public void setGmfTotal(String gmfTotal) {
			this.gmfTotal = gmfTotal;
		}
		public String getMontoImpuesto() {
			return montoImpuesto;
		}
		public void setMontoImpuesto(String montoImpuesto) {
			this.montoImpuesto = montoImpuesto;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		@Override
		public String toString() {
			return "{\"codigoResultado\":\"" + codigoResultado + "\", \"mensaje\":\"" + mensaje
					+ "\", \"costoTransaccion\":\"" + costoTransaccion + "\", \"montoTarifa\":\"" + montoTarifa
					+ "\", \"gmfTotal\":\"" + gmfTotal + "\", \"montoImpuesto\":\"" + montoImpuesto + "\", \"fecha\":\""
					+ fecha + "\"}";
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

	
	
	public ResponseTx(String codigoResultado, String mensaje, Transacciones[] transacciones) {
		super();
		this.codigoResultado = codigoResultado;
		this.mensaje = mensaje;
		this.transacciones = transacciones;
	}

	
	public ResponseTx() {
		super();
	}

	@Override
	public String toString() {
		return "{\"codigoResultado\":\"" + codigoResultado + "\", \"mensaje\":\"" + mensaje + "\", \"transacciones\":\""
				+ transacciones + "\"}";
	}
	
	
		
}
