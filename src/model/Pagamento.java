package model;

import java.util.Calendar;

public class Pagamento {
	protected int idPag;
	protected String data;
	protected Cliente idCliente;
	protected Funcionario idFunc;
	protected String tipo; //Se o pgmt é mensal, semestral ou promocional
	
	public Pagamento() {
	
	}
	
	public Pagamento(String data, Cliente idCliente, Funcionario idFunc, String tipo) {
		super();
		this.data = data;
		this.idCliente = idCliente;
		this.idFunc = idFunc;
		this.tipo = tipo;
	}
	
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
	
	public Cliente getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}
	
	public Funcionario getIdFunc() {
		return idFunc;
	}
	
	public void setIdFunc(Funcionario idFunc) {
		this.idFunc = idFunc;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
