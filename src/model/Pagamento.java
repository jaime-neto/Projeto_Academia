package model;

public class Pagamento {
	protected int idPag;
	protected String data;
	protected int idCliente;
	protected int idFunc;
	protected String tipo; //Se o pgmt é mensal, semestral ou promocional
	
	public int getIdPag() {
		return idPag;
	}
	
	public void setIdPag(int idPag) {
		this.idPag = idPag;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public int getIdFunc() {
		return idFunc;
	}
	
	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
