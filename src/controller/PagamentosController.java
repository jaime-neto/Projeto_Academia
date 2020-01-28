package controller;

import java.util.List;

import javax.swing.JOptionPane;
import DB.DBCliente;
import DB.DBFuncionario;
import DB.DBPagamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cliente;
import model.Funcionario;
import model.Pagamento;

public class PagamentosController {
	
	@FXML
    private TextField valorPagar;
	
	@FXML
    private TextField valorEditar;
	
	@FXML
	private TextField buscaCpf;

	@FXML
	private TextField codFuncPagar;

	@FXML
	private TextField codCliPagar;

	@FXML
	private TextField codCliEditar;
	
	@FXML
    private TextField codFuncEdita;
	
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
    private TableView<Pagamento> tvPgmtMostra;

	 @FXML
	 private TextField codPag;

	 @FXML
	 private Label statusRemovePgmt;

	 @FXML
	 private TableColumn<Pagamento, String> tcCodepag;

	 @FXML
	 private TableColumn<Pagamento, String> tcDataPag;

	 @FXML
	 private TableColumn<Pagamento, String> tcTipoPag;

	 @FXML
	 private TableColumn<Funcionario, String> tcFuncionarioPag;

	 @FXML
	 private TableColumn<Cliente, String> tcCliPag;
	 
	 @FXML
	 private TableColumn<Pagamento, Float> tcValorPag;

	 private ObservableList<Pagamento> pagamentos = FXCollections.observableArrayList();
	 
	private String tipoPgmt = null;
	 
	private void initTableMostrar() {
		tcCodepag.setCellValueFactory(new PropertyValueFactory<Pagamento, String>("idPag"));
		tcDataPag.setCellValueFactory(new PropertyValueFactory<Pagamento, String>("data"));
		tcTipoPag.setCellValueFactory(new PropertyValueFactory<Pagamento, String>("tipo"));
		tcFuncionarioPag.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nomeFunc"));
		tcCliPag.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nomeCli"));
		tcValorPag.setCellValueFactory(new PropertyValueFactory<Pagamento, Float>("valor"));
		tvPgmtMostra.setItems(FXCollections.observableArrayList(pagamentos));
	}
    
    @FXML
    void btnBuscarPag(ActionEvent event) {
    	Pagamento pgmt = new Pagamento();
		DBPagamento DBpgmt = new DBPagamento();
		Cliente cli = new Cliente();
		DBCliente DBcli = new DBCliente();
		List<Pagamento> listaPgmt;
		
		try {
			cli = DBcli.buscaClienteCpf(buscaCpf.getText());
			
			if(cli != null) {
				pgmt.setIdCliente(cli);
				
				listaPgmt = DBpgmt.buscarTodosPagamentos(cli.getIdCliente());
				if(listaPgmt != null) {	
					for(int i=0; i<listaPgmt.size(); i++) {
						pagamentos.add(listaPgmt.get(i));
						initTableMostrar();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Nenhum pagamento foi encontado.");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Nenhum cliente foi encontado.");
			}
			
			
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
    }
    
    @FXML
    void btnTipoEditarPgmt(ActionEvent event) {

    }

    @FXML
    void btnTovoTipoEditarPgmt(ActionEvent event) {

    }
    
    @FXML
    void btnEditar(ActionEvent event) {

    }
    
    @FXML
    void btnBuscarEditar(ActionEvent event) {
    	
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
    	
    	Cliente cli = new Cliente();
		Funcionario func = new Funcionario();
		DBCliente DBcli = new DBCliente();
		DBFuncionario DBfunc = new DBFuncionario();
		DBPagamento DBpgmt = new DBPagamento();
		DBPagamento DBVerifica = new DBPagamento();
		List<Pagamento> verificaPagamento;
		
    	try {
    		
    		cli.setIdCliente(Integer.parseInt(codCliPagar.getText()));
    		func.setIdFunc(Integer.parseInt(codFuncPagar.getText()));
    
    		verificaPagamento = DBVerifica.buscarTodosPagamentos(cli.getIdCliente());
    		
    		boolean existeData = false;/*Verifica se o pagamento ja foi 
    									feito de acordo com a data fornecida*/
    		if(verificaPagamento != null) {
    			for(int i=0; i<verificaPagamento.size(); i++) {
					if(verificaPagamento.get(i).getData().equals(data.getValue().toString())){
						existeData = true;
					}
				}
    		}
    		
    		cli = DBcli.buscaCliente(cli.getIdCliente());
    		func = DBfunc.buscaFuncionario(func.getIdFunc());
    		
    		if(cli != null && func != null & tipoPgmt != null) {
    			if(!existeData) {
    				System.out.println(data.getValue().toString());
        			Pagamento pgmt = new Pagamento(data.getValue().toString(), cli,
        					func, tipoPgmt, Float.parseFloat(valorPagar.getText()));		
        			if(DBpgmt.realizarPagamento(pgmt)) {
        				JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso");
        			}else {
        				JOptionPane.showMessageDialog(null, "O pagamento não foi realizado.");
       				}
    			}else {
    				JOptionPane.showMessageDialog(null, "O pagamento referente a "
    						+ "data: "+data.getValue().toString()+" ja foi realizado.");
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
    
    	DBPagamento db_pgmt = new DBPagamento();
    	Pagamento pgmt = new Pagamento();
    	
    	try {	
        	if(!codPag.getText().isEmpty()) {    		
        		pgmt.setIdPag(Integer.parseInt(codPag.getText()));
        		Alert alert = new Alert(AlertType.CONFIRMATION, "Tem certeza que deseja "
        				+ "exluir?" , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        		
        		alert.showAndWait();        		
        		if(alert.getResult() == ButtonType.YES) {
        			if(db_pgmt.deletePagamento(pgmt.getIdPag())) {        				
        				JOptionPane.showMessageDialog(null, "Pagamento excluido com sucesso.");
        				codPag.clear();
        			} else {
        				JOptionPane.showMessageDialog(null, "Ocorreu um problema durante a remocao do Pagamento. Tente novamente. Certifique-se que esta inserindo uma ID valido.");
        			}
        		}
        	} else {
        		JOptionPane.showMessageDialog(null, "Digite um ID valido.");
        	}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			codPag.clear();
		}
    }
	
	@FXML
    void btnVoltar(ActionEvent event) {
    	application.Main.trocarTela("inicial");
    }
}
