package controller;

import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.JOptionPane;

import DB.DBCliente;
import DB.DBFuncionario;
import DB.DBPagamento;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import model.Cliente;
import model.Funcionario;
import model.Pagamento;

public class PagamentosController {
	
	@FXML
	private TextField buscaCpf;

	@FXML
	private TextField codFuncPagar;

	@FXML
	private TextField codCliPagar;

	@FXML
	private DatePicker data;

	@FXML
	private MenuButton tipo;
	    
	@FXML
	private MenuItem menuItem1;
    	
	@FXML
	private MenuItem menuItem2;
    	
	@FXML
	private MenuItem menuItem3;

	@FXML
	private TableView<?> tvPgmt;

	@FXML
	private TableColumn<?, ?> codCliente;

	@FXML
	private TableColumn<?, ?> nomeCli;

	@FXML
	private TableColumn<?, ?> tipoPgmtCli;

	 @FXML
	 private TableColumn<?, ?> tcData;

	 @FXML
	 private TextField codPag;

	 @FXML
	 private Label statusRemovePgmt;

	 @FXML
	 private TableColumn<?, ?> tcCodepag;

	 @FXML
	 private TableColumn<?, ?> tcDataPag;

	 @FXML
	 private TableColumn<?, ?> tcTipoPag;

	 @FXML
	 private TableColumn<?, ?> tcFuncionarioPag;

	 @FXML
	 private TableColumn<?, ?> tcCliPag;
	 
	 
	private String tipoPgmt = null;
	 
    @FXML
    void btnBuscarPag(ActionEvent event) {
    	
    }
    
    @FXML
    void btnBuscar(ActionEvent event) {

    }
    
    @FXML
    void btnBuscarTodosPag(ActionEvent event) {

    }
    		
    @FXML
    void miMensal(ActionEvent event) {
    	tipoPgmt = "Mensal";
    }
    
    @FXML
    void miSemestral(ActionEvent event) {
    	tipoPgmt = "Semestral";
    }
    
    @FXML
    void miPromocional(ActionEvent event) {
    	tipoPgmt = "Promocional";
    }
    
    @FXML
    void btnPagar(ActionEvent event) {    	
    	try {
    		Cliente cli = new Cliente();
    		Funcionario func = new Funcionario();
    		
    		DBCliente DBcli = new DBCliente();
    		DBFuncionario DBfunc = new DBFuncionario();
    		DBPagamento DBpgmt = new DBPagamento();
    		
    		cli.setIdCliente(Integer.parseInt(codCliPagar.getText()));
    		func.setIdFunc(Integer.parseInt(codFuncPagar.getText()));
    		
    		cli = DBcli.buscaCliente(cli);
    		func = DBfunc.buscaFuncionario(func);
    		
    		if(cli != null && func != null & tipoPgmt != null) {
    			Pagamento pgmt = new Pagamento(data.getValue().toString(), cli,
    					func, tipoPgmt);		
    			if(DBpgmt.realizarPagamento(pgmt)) {
    				JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso");
    			}else {
    				JOptionPane.showMessageDialog(null, "O pagamento não foi realizado.");
   				}
    		}else {
    			JOptionPane.showMessageDialog(null, "Verifique o codigo do cliente/funcionario e o tipo de pagamento.");
    		}
			
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
    }

    @FXML
    void btnRemoverPag(ActionEvent event) {

    }
	
	@FXML
    void btnVoltar(ActionEvent event) {
    	application.Main.trocarTela("inicial");
    }
}
