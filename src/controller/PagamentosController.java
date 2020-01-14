package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<?> tvPgmt;

    @FXML
    private TableColumn<?, ?> codCliente;

    @FXML
    private TableColumn<?, ?> nomeCli;

    @FXML
    private TableColumn<?, ?> tipoPgmtCli;

    @FXML
    private TextField codPgmt;

    @FXML
    private Label statusRemovePgmt;

    @FXML
    void btnBuscar(ActionEvent event) {

    }

    @FXML
    void btnPagar(ActionEvent event) {

    }

    @FXML
    void btnRemover(ActionEvent event) {

    }
	
	@FXML
    void btnVoltar(ActionEvent event) {
    	application.Main.trocarTela("inicial");
    }
}
