package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	protected static Scene login;
	protected static Scene cliente;
	protected static Scene funcionario;
	protected static Scene inicial;
	protected static Scene pgmt;
	protected static Scene cadFunc;
	
	protected static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Main.stage = primaryStage;
			
			Parent telaLogin = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			Parent telaCliente = FXMLLoader.load(getClass().getResource("/view/Cliente.fxml"));
			Parent telaFunc = FXMLLoader.load(getClass().getResource("/view/Funcionario.fxml"));
			Parent telaInicial = FXMLLoader.load(getClass().getResource("/view/Inicial.fxml"));
			Parent telaPgmt = FXMLLoader.load(getClass().getResource("/view/Pagamentos.fxml"));
			Parent telaCadastroFunc = FXMLLoader.load(getClass().getResource("/view/CadastroFuncionario.fxml")); 
			
			login = new Scene(telaLogin, 520, 300);
			cliente = new Scene(telaCliente, 720, 400);
			funcionario = new Scene(telaFunc, 720, 400);
			inicial = new Scene(telaInicial, 520, 300);
			pgmt = new Scene(telaPgmt, 720, 400);
			cadFunc = new Scene(telaCadastroFunc, 720,400);
			
			primaryStage.setScene(login);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void trocarTela(String tela) {
		switch(tela) {
		case "login":
			stage.setScene(login);
			stage.show();
			break;
		case "cliente":
			stage.setScene(cliente);
			stage.show();
			break;
		case "funcionario":
			stage.setScene(funcionario);
			stage.show();
			break;
		case "inicial":
			stage.setScene(inicial);
			stage.show();
			break;
		case "pgmt":
			stage.setScene(pgmt);
			stage.show();
		case "cadFunc":
			stage.setScene(cadFunc);
			stage.show();
		}
	}
	
}
