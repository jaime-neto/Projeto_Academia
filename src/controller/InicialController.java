package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InicialController {
	@FXML
    void btnTelaCliente(ActionEvent event) {
		application.Main.trocarTela("cliente");
    }

    @FXML
    void btnTelaFunc(ActionEvent event) {
    	application.Main.trocarTela("funcionario");
    }

    @FXML
    void btnTelaPagmt(ActionEvent event) {
    	application.Main.trocarTela("pgmt");
    }
    
    @FXML
    void btnSair(ActionEvent event) {
    	application.Main.trocarTela("login");
    }
}
