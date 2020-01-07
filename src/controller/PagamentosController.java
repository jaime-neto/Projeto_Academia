package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PagamentosController {
	@FXML
    void btnVoltar(ActionEvent event) {
    	application.Main.trocarTela("inicial");
    }
}
