package model;

public class Funcionario {
	private int idFunc;
	private String usuario;
	private String senha;
	private String cpf;
	private float salario;
	
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
