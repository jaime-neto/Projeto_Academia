package model;

public class Pagamento {
	protected int idPag;
	protected String data;
	protected Cliente idCliente;
	protected Funcionario idFunc;
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
	
	public int getIdCli() {
		return idCliente.getIdCliente();
	}
	
	public void setIdCli(int idCli) {
		this.idCliente.setIdCliente(idCli);
	}
	
	public int getIdFunc() {
		return idFunc.getIdFunc();
	}
	
	public void setIdFunc(int idFunc) {
		this.idFunc.setIdFunc(idFunc);;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
