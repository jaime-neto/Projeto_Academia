package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DB.DBFuncionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Funcionario;

public class FuncionarioController {
	 
	@FXML
	private TextField cadNome;
	
	@FXML
	private TextField buscCod;
	
	@FXML
	private TextField salario;

	@FXML
    private TextField cpf;

	@FXML
	private TextField codFunc;
	
	@FXML
    private TextField user;

    @FXML
    private TextField senha;

	@FXML
	private TableView<Funcionario> tvFunc;
	
	@FXML
	private TableColumn<Funcionario, String> tcCod;

	@FXML
	private TableColumn<Funcionario, String> tcNome;

	@FXML
	private TableColumn<Funcionario, String> tcSalario;

	@FXML
	private TableColumn<Funcionario, String> tcCpf;

	private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();
	
	/*Preenche as tabelas*/
	private void initTable(){
		tcCod.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("idFunc"));
		tcNome.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cpf"));
		tcSalario.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("salario"));
		tvFunc.setItems(FXCollections.observableArrayList(funcionarios));
	}
	
	/*Limpar todos os campos*/
	private void limparCampos() {
		cadNome.clear();
		user.clear();
		senha.clear();
		cpf.clear();
		salario.clear();
	}
	
	@FXML
	void btnBuscar(ActionEvent event) {
		
		Funcionario func = new Funcionario();
		DBFuncionario DBfunc = new DBFuncionario();
		
		try {
			func.setIdFunc(Integer.parseInt(buscCod.getText()));
			func = DBfunc.buscaFuncionario(func);
			if(func != null) {
				funcionarios.add(func);
				initTable();
			}else {
				JOptionPane.showMessageDialog(null, "Funcionario nao encontrado.");
			}
			
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	@FXML
	void btnBuscarTodos(ActionEvent event) {
		ArrayList<?> todosFunc;
		DBFuncionario DBfunc = new DBFuncionario();
		
		try {
			
			todosFunc = DBfunc.buscarTodosFuncionarios();
			if(todosFunc != null) {
				for (int i = 0; i < todosFunc.size(); i++) {
					funcionarios.add((Funcionario)todosFunc.get(i));
			    }
				initTable();
			}else {
				JOptionPane.showMessageDialog(null, "Nenhum funcionario foi encontrado.");
			}
			
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	@FXML
	void btnExcluir(ActionEvent event) {
		try {
			
			Funcionario func = new Funcionario();
			func.setIdFunc(Integer.parseInt(codFunc.getText()));
			DBFuncionario DBfunc = new DBFuncionario();
			if(DBfunc.deleteFuncionario(func)) {
				JOptionPane.showMessageDialog(null, "Funcionario deletado com sucesso");
			}else {
				JOptionPane.showMessageDialog(null, "Funcionario nao encontrado.");
			}
			
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
		
	}

	@FXML
	void btnSalvar(ActionEvent event) {
		
		try {
			
			Funcionario func = new Funcionario(cadNome.getText(), user.getText(),
					senha.getText(), cpf.getText(), Float.parseFloat(salario.getText()));
			
			DBFuncionario DBfunc = new DBFuncionario();
			if(DBfunc.cadFuncionario(func)) {
				JOptionPane.showMessageDialog(null, "Funcionario inserido com sucesso");
			}else {
				JOptionPane.showMessageDialog(null, "Funcionario nao foi inserido.");
			}
			limparCampos();
			
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
		
	}
	
	@FXML
    void btnVoltar(ActionEvent event) {
		application.Main.trocarTela("inicial");
    }
}
