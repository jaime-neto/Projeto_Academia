package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DB.DBFuncionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Funcionario;

public class FuncionarioController {
	
	@FXML
    private Button editar;
	
	@FXML
	private TextField cadNome;
	
	@FXML
	private TextField buscCod;
	
	@FXML
	private TextField salario;

	@FXML
    private TextField cpf;

	@FXML
    private TextField codFuncExcluir;
	
	@FXML
    private TextField codFuncEditar;
	
	@FXML
    private TextField user;

    @FXML
    private TextField senha;
    
    @FXML
    private TextField cpfEditar;

    @FXML
    private TextField cadNomeEdita;

    @FXML
    private TextField salarioEditar;

    @FXML
    private TextField userEditar;

    @FXML
    private TextField senhaEditar;

    private int idFuncTemporario; /*Guarda o id do funcionario temporariamente
     								para que seja feita a edição*/
    
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
		limparCampos();
		tvFunc.getItems().clear();
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
    void btnEditar(ActionEvent event) {
		try {
			Funcionario func = new Funcionario();
			DBFuncionario DBfunc = new DBFuncionario();
			
			func.setCpf(cpfEditar.getText());
			func.setNome(cadNomeEdita.getText());
			func.setSalario(Float.parseFloat(salarioEditar.getText()));
			func.setUsuario(userEditar.getText());
			func.setSenha(senhaEditar.getText());
			func.setIdFunc(idFuncTemporario);
			
			if(DBfunc.editFuncionario(func)) {
				JOptionPane.showMessageDialog(null, "Funcionario editado com sucesso.");
			}else {
				JOptionPane.showMessageDialog(null, "O Funcionario não foi editado,"
						+ " você precisa selecisa primeiro buscar um ID válido.");
			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());	
		}
    }

	@FXML
    void btnBuscarEditar(ActionEvent event) {
		try {
			Funcionario func = new Funcionario();
			func.setIdFunc(Integer.parseInt(codFuncEditar.getText()));
			DBFuncionario DBfunc = new DBFuncionario();
			
			func = DBfunc.buscaFuncionario(func);
			
			if(func != null) {
				JOptionPane.showMessageDialog(null, "Funcionario " + func.getNome()
						+ " encontrado, altere"
						+ " somente os dados que deseja.");
				
				cpfEditar.setText(func.getCpf());
				cadNomeEdita.setText(func.getNome());
				salarioEditar.setText(String.valueOf(func.getSalario()));
				userEditar.setText(func.getUsuario());;
				senhaEditar.setText(func.getSenha());;	
				idFuncTemporario = func.getIdFunc();
			}else {
				JOptionPane.showMessageDialog(null, "Funcionario nao encontrado.");
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
    }
	
	@FXML
	void btnBuscarTodos(ActionEvent event) {
		limparCampos();
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
			func.setIdFunc(Integer.parseInt(codFuncExcluir.getText()));
			DBFuncionario DBfunc = new DBFuncionario();
			if(DBfunc.deleteFuncionario(func)) {
				JOptionPane.showMessageDialog(null, "Funcionario deletado com sucesso");
				limparCampos();
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
			
			if(!DBfunc.buscaCpf(cpf.getText())) {
				if(DBfunc.cadFuncionario(func)) {
					JOptionPane.showMessageDialog(null, "Funcionario inserido com sucesso");
				}else {
					JOptionPane.showMessageDialog(null, "Funcionario nao foi inserido.");
				}
				limparCampos();
			}else {
				JOptionPane.showMessageDialog(null, "Já existe um funcionario com esse CPF.");
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	@FXML
    void btnVoltar(ActionEvent event) {
		application.Main.trocarTela("inicial");
    }
}
