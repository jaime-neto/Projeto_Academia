package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class PagamentosController {

	@FXML
    private TextField codFunc;

    @FXML
    private TextField codCli;

    @FXML
    private DatePicker data;

    @FXML
    private MenuButton tipo;

    @FXML
    void btnPagar(ActionEvent event) {

    }
	
	@FXML
    void btnVoltar(ActionEvent event) {
    	application.Main.trocarTela("inicial");
    }
}
