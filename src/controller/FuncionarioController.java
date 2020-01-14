package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FuncionarioController {
	 
	@FXML
	private TextField nome;

	@FXML
	private TextField salario;

	@FXML
    private TextField cpf;

	@FXML
	private TextField codFunc;

	@FXML
	private TableView<?> tvFunc;

	@FXML
	void btnBuscar(ActionEvent event) {

	}

	@FXML
	void btnExcluir(ActionEvent event) {

	}

	@FXML
	void btnSalvar(ActionEvent event) {

	}
	
	@FXML
    void btnVoltar(ActionEvent event) {
		application.Main.trocarTela("inicial");
    }
}
