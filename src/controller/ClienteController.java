package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClienteController {
	
	@FXML
    private TextField cpf;

    @FXML
    private TextField nome;

    @FXML
    private TextField end;

    @FXML
    private TextField tel;

    @FXML
    private TextField codCli;

    @FXML
    private TableView<?> tvCliente;

    @FXML
    private Label statusExcluir;

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
