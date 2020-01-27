package controller;

import javax.swing.JOptionPane;

import DB.DBFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Funcionario;

public class CadastroFuncionarioController {
	@FXML
	private TextField cadNome;
	
	@FXML
	private TextField salario;

	@FXML
    private TextField cpf;
	
	@FXML
    private TextField user;

    @FXML
    private TextField senha;

    private void limparCampos() {
		cadNome.clear();
		user.clear();
		senha.clear();
		cpf.clear();
		salario.clear();
	}
    
    
    @FXML
	void btnSalvar(ActionEvent event) {
		
		try {
			Funcionario func = new Funcionario(cadNome.getText(), user.getText(),
					senha.getText(), cpf.getText(), Float.parseFloat(salario.getText()));
			
			DBFuncionario DBfunc = new DBFuncionario();
			
			if(!DBfunc.buscaFuncionarioCpf(cpf.getText())) {
				if(DBfunc.cadFuncionario(func.getNome(), func.getCpf(),func.getSalario(), func.getUsuario(), func.getSenha())) {
					JOptionPane.showMessageDialog(null, "Funcionario inserido com sucesso");
				}else {
					JOptionPane.showMessageDialog(null, "Funcionario nao foi inserido.");
				}
				limparCampos();
			}else {
				JOptionPane.showMessageDialog(null, "Um funcionario com esse CPF ja existe");
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
    
    @FXML
    void btnVoltar(ActionEvent event) {
		application.Main.trocarTela("login");
    }
}