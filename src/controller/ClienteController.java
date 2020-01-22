package controller;

import javax.swing.JOptionPane;

import DB.DBCliente;
import DB.DBEndereco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Cliente;
import model.Endereco;

public class ClienteController {
	
	@FXML
    private TextField cpf;

    @FXML
    private TextField nome;

    @FXML
    private TextField bairro;
    
    @FXML
    private TextField rua;
    
    @FXML
    private TextField cidade;

    @FXML
    private TextField tel;

    @FXML
    private TextField codCli;

    @FXML
    private TableView<?> tvCliente;

    @FXML
    private Label statusExcluir;
    
    private void limparCampos() {
		cpf.clear();
		nome.clear();
		bairro.clear();
		rua.clear();
		cidade.clear();
		tel.clear();
		codCli.clear();
	}
    
    @FXML
    void btnBuscar(ActionEvent event) {

    }

    @FXML
    void btnExcluir(ActionEvent event) {

    }

    @FXML
    void btnSalvar(ActionEvent event) {
    	try {
    		Endereco end = new Endereco(rua.getText(), bairro.getText(), cidade.getText());
    		DBEndereco db_end = new DBEndereco();
    		db_end.cadEndereco(end);
    		end.setIdEndereco(db_end.buscaUltimoEndereco().getIdEndereco());
			
    		Cliente cli = new Cliente(cpf.getText(), nome.getText(), end, tel.getText());
			DBCliente db_cli = new DBCliente();
			
			if (db_cli.cadCliente(cli)) {
				JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso");
				limparCampos();
			} else {
				JOptionPane.showMessageDialog(null, "Cliente nao foi inserido.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
	
	@FXML
    void btnVoltar(ActionEvent event) {
    	application.Main.trocarTela("inicial");
    }
}
