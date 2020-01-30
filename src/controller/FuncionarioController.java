package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DB.DBFuncionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Funcionario;
import model.ValidaCpf;

public class FuncionarioController {
	
	@FXML
	private Label funcAtual;
	
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
    private TextField cadNomeEditar;

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
	
	private String cpfTemp = null;
	
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
		/*Tela inserir*/
		cadNome.clear();
		user.clear();
		senha.clear();
		cpf.clear();
		salario.clear();
		
		/*Tela editar*/
		codFuncEditar.clear();
		cadNomeEditar.clear();
		cpfEditar.clear();
		salarioEditar.clear();
		senhaEditar.clear();
		userEditar.clear();
		
		/*Tela excluir*/
		codFuncExcluir.clear();
	}
	
	@FXML
	void btnBuscar(ActionEvent event) {
		limparCampos();
		tvFunc.getItems().clear();
		Funcionario func = new Funcionario();
		DBFuncionario DBfunc = new DBFuncionario();
		
		try {
			func.setIdFunc(Integer.parseInt(buscCod.getText()));
			func = DBfunc.buscaFuncionario(func.getIdFunc());
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
			
			if(cpfEditar.getText().isEmpty() || cadNomeEditar.getText().isEmpty() || salarioEditar.getText().isEmpty()
					|| userEditar.getText().isEmpty() || senhaEditar.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
			} else {
				
				func.setCpf(cpfEditar.getText());
				func.setNome(cadNomeEditar.getText());
				func.setSalario(Float.parseFloat(salarioEditar.getText()));
				func.setUsuario(userEditar.getText());
				func.setSenha(senhaEditar.getText());
				func.setIdFunc(idFuncTemporario);

				Funcionario existeFunc = DBfunc.buscaFuncionarioCpf(cpfEditar.getText());
				
				ValidaCpf cpfValido = new ValidaCpf();
				
				if(cpfValido.isCPF(cpfEditar.getText())) {
					if(cpfTemp.equals(cpfEditar.getText())) {					
						if(DBfunc.editFuncionario(func.getIdFunc(),func)) {
							JOptionPane.showMessageDialog(null, "Funcionario editado com sucesso.");
							limparCampos();
						}else {
							JOptionPane.showMessageDialog(null, "O Funcionario nao foi editado,"
									+ " voce precisa primeiro buscar um ID valido.");
						}
					} else if(existeFunc != null) {
						JOptionPane.showMessageDialog(null, "Ja existe um funcionario com esse cpf.");
					} else {
						if(DBfunc.editFuncionario(func.getIdFunc(),func)) {
							JOptionPane.showMessageDialog(null, "Funcionario editado com sucesso.");
							limparCampos();
						}else {
							JOptionPane.showMessageDialog(null, "O Funcionario nao foi editado,"
									+ " voce precisa primeiro buscar um ID valido.");
						}	
					}
				}else {
					JOptionPane.showMessageDialog(null, "Você precisa inserir um CPF válido.");
				}
			}
			
		}catch(Exception ex){
			System.err.println(ex.getMessage());	
		}
    }

	@FXML
    void btnBuscarEditar(ActionEvent event) {
		try {
			Funcionario func = new Funcionario();
			DBFuncionario DBfunc = new DBFuncionario();
			
			if (codFuncEditar.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor digite o codigo do funcionario.");
			} else {				
				func.setIdFunc(Integer.parseInt(codFuncEditar.getText()));
				
				func = DBfunc.buscaFuncionario(func.getIdFunc());
				
				if(func != null) {
					JOptionPane.showMessageDialog(null, "Funcionario " + func.getNome()
					+ " encontrado, altere somente os dados que deseja.");
					
					cpfEditar.setText(func.getCpf());
					cadNomeEditar.setText(func.getNome());
					cpfTemp = func.getCpf();
					salarioEditar.setText(String.valueOf(func.getSalario()));
					userEditar.setText(func.getUsuario());;
					senhaEditar.setText(func.getSenha());;	
					idFuncTemporario = func.getIdFunc();
				}else {
					JOptionPane.showMessageDialog(null, "Funcionario nao encontrado.");
				}
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
			if(!codFuncExcluir.getText().isEmpty() ) {
				func.setIdFunc(Integer.parseInt(codFuncExcluir.getText()));
				DBFuncionario DBfunc = new DBFuncionario();
				if(DBfunc.deleteFuncionario(func.getIdFunc())) {
					JOptionPane.showMessageDialog(null, "Funcionario deletado com sucesso");
					limparCampos();
				}else {
					JOptionPane.showMessageDialog(null, "Ops. Ocorreu um problema durante a remocao, certifique-se que digitou um ID valido e que o Funcionario nao esta associado a nenhum pagamento.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Digite um ID valido.");
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um problema durante a remocao. Certifique-se que digitou um ID valido.");
			System.err.println(ex.getMessage());
		}
	}

	@FXML
	void btnSalvar(ActionEvent event) {
		ValidaCpf cpfValido = new ValidaCpf();
		
		try {
			if(cadNome.getText().isEmpty() || user.getText().isEmpty() || senha.getText().isEmpty() || cpf.getText().isEmpty() || salario.getText().isEmpty()) {				
				JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
			} else { 		
				
				if(cpfValido.isCPF(cpf.getText())) {
					Funcionario func = new Funcionario(cadNome.getText(), user.getText(),
							senha.getText(), cpf.getText(), Float.parseFloat(salario.getText()));
					
					DBFuncionario DBfunc = new DBFuncionario();
					
					if(DBfunc.buscaFuncionarioCpf(cpf.getText()) == null) {
						if(DBfunc.cadFuncionario(func.getNome(), func.getCpf(),func.getSalario(), func.getUsuario(), func.getSenha())) {
							JOptionPane.showMessageDialog(null, "Funcionario inserido com sucesso");
						}else {
							JOptionPane.showMessageDialog(null, "Funcionario nao foi inserido.");
						}
						limparCampos();
					}else {
						JOptionPane.showMessageDialog(null, "Um funcionario com esse CPF ja existe");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Você precisa inserir um CPF válido.");
				}
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
