package model;

public class Pagamento {
	protected int idPag;
	protected String data;
	protected Cliente idCliente;
	protected Funcionario idFunc;
	protected String tipo; //Se o pgmt é mensal, semestral ou promocional
	protected float valor;
	
	public Pagamento() {
	
	}
	
	public Pagamento(String data, Cliente idCliente, Funcionario idFunc, String tipo,
			float valor) {
		super();
		this.data = data;
		this.idCliente = idCliente;
		this.idFunc = idFunc;
		this.tipo = tipo;
		this.valor = valor;
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
	
	public Cliente getCli() {
		return this.idCliente;
	}
	
	public Funcionario getFunc() {
		return this.idFunc;
	}
	
	public String getNomeFunc() {
		return this.idFunc.getNome();
	}
	
	public String getNomeCli() {
		return this.idCliente.getNome();
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
