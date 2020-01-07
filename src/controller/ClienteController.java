package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ClienteController {
	@FXML
    void btnVoltar(ActionEvent event) {
    	application.Main.trocarTela("inicial");
    }
}
