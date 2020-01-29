package controller;

import javax.swing.JOptionPane;

import DB.DBFuncionario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Funcionario;

public class LoginController {
	@FXML
    private PasswordField senha;

    @FXML
    private TextField usuario;
 
    private void limparCampos() {
		senha.clear();
		usuario.clear();
	}
    
	@FXML
    void btnEntrar(ActionEvent event) {
		DBFuncionario db_func = new DBFuncionario();
		if(!usuario.getText().isEmpty() && !senha.getText().isEmpty()) {			
			Funcionario func = db_func.loginFunc(usuario.getText(), senha.getText());
			if(func != null) {			
				JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
				application.Main.trocarTela("inicial");
			} else {
				JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos. Tente novamente.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Ops. Voce esqueceu algum campo em branco.");
		}
		limparCampos();
    }
	
	@FXML
	void btnCadastrar(ActionEvent event) {
		application.Main.trocarTela("cadFunc");
	}
	
    @FXML
    void btnSair(ActionEvent event) {
    	Platform.exit();
        System.exit(0);
    }
}
