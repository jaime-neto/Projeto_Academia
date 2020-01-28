package model;

public class Cliente {
	private int idCliente;
	private String cpf;
	private String nomeCli;
	private Endereco endereco;
	private String telefone;
	
	public Cliente() {
		
	}
	
	public Cliente(String cpf, String nome, Endereco endereco, String telefone) {
		this.cpf = cpf;
		this.nomeCli = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nomeCli;
	}
	
	public void setNome(String nome) {
		this.nomeCli = nome;
	}
	
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void setRua(String rua) {
		this.endereco.setRua(rua);
	}
	
	public String getRua() {
		return(this.endereco.getRua());
	}
	
	public void setBairro(String bairro) {
		this.endereco.setBairro(bairro);
	}
	
	public String getBairro() {
		return(this.endereco.getBairro());
	}
	
	public void setCidade(String cidade) {
		this.endereco.setBairro(cidade);
	}
	
	public String getCidade() {
		return(this.endereco.getCidade());
	}
}
