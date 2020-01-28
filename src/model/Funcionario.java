package model;

public class Funcionario {
	private int idFunc;
	private String nomeFunc;
	private String usuario;
	private String senha;
	private String cpf;
	private float salario;
	
	public Funcionario(String nome, String usuario, String senha, String cpf, float salario) {
		super();
		this.nomeFunc = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.cpf = cpf;
		this.salario = salario;
	}

	public Funcionario() {
		
	}
	
	public String getNome() {
		return nomeFunc;
	}

	public void setNome(String nome) {
		this.nomeFunc = nome;
	}
	
	public int getIdFunc() {
		return idFunc;
	}
	
	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public float getSalario() {
		return salario;
	}
	
	public void setSalario(float salario) {
		this.salario = salario;
	}
}
