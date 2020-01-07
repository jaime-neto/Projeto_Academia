package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
    private PasswordField senha;

    @FXML
    private TextField usuario;
	
	@FXML
    void btnEntrar(ActionEvent event) {
		application.Main.trocarTela("inicial");
    }

    @FXML
    void btnSair(ActionEvent event) {
    	Platform.exit();
        System.exit(0);
    }
}
