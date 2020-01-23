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
    private TextField nomeCad;
    
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
    
    @FXML
    private TextField codCliEdit;
    
    @FXML
    private TextField nomeEdit;
    
    @FXML
    private TextField cpfEdit;
    
    @FXML
    private TextField telEdit;
    
    @FXML
    private TextField ruaEdit;
    
    @FXML
    private TextField bairroEdit;
    
    @FXML
    private TextField cidadeEdit;
    
    private Endereco endTemp = null;
    
    private void limparCampos() {
		cpf.clear();
		bairro.clear();
		rua.clear();
		cidade.clear();
		tel.clear();
		codCli.clear();
		nomeCad.clear();
		codCliEdit.clear();
		nomeEdit.clear();
		cpfEdit.clear();
		telEdit.clear();
		ruaEdit.clear();
		bairroEdit.clear();
		cidadeEdit.clear();
	}
    
    @FXML
    void btnBuscarTab(ActionEvent event) {
    	
    }
    
    @FXML
    void btnBuscarEditar(ActionEvent event) {
    	Cliente cli = new Cliente();
    	cli.setIdCliente(Integer.parseInt(codCliEdit.getText()));
    	DBCliente db_cli =  new DBCliente();
    	cli = db_cli.buscaCliente(cli);
    	
    	if(cli != null) {
    		JOptionPane.showMessageDialog(null, "Cliente " + cli.getNome()
			+ " encontrado, altere"
			+ " somente os dados que deseja.");
    		
    		
    		nomeEdit.setText(cli.getNome());
    		cpfEdit.setText(cli.getCpf());
    		telEdit.setText(cli.getTelefone());
    		ruaEdit.setText(cli.getEndereco().getRua());
    		bairroEdit.setText(cli.getEndereco().getBairro());
    		cidadeEdit.setText(cli.getEndereco().getCidade());
    		
    		endTemp = cli.getEndereco();
    	}	
    }
    
    @FXML
    void btnEditarCli(ActionEvent event) {
    	try {
    		DBEndereco db_end = new DBEndereco();
    		Endereco end = new Endereco(ruaEdit.getText(),bairroEdit.getText(), cidadeEdit.getText());
    		end.setIdEndereco(endTemp.getIdEndereco());
    		db_end.editEndereco(end);
    		
    		Cliente cli = new Cliente(cpfEdit.getText(), nomeEdit.getText(),end, telEdit.getText());
			cli.setIdCliente(Integer.parseInt(codCliEdit.getText()));
			
			DBCliente db_cli = new DBCliente();
			
			if(db_cli.editCliente(cli)) {
				JOptionPane.showMessageDialog(null, "Cliente editado com sucesso.");
				limparCampos();
			} else {
				JOptionPane.showMessageDialog(null, "Problema ao editar Cliente. Tente novamente.");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
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
			
    		Cliente cli = new Cliente(cpf.getText(), nomeCad.getText(), end, tel.getText());
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
