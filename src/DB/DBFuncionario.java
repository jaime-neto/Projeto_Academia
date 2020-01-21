package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Funcionario;

public class DBFuncionario {
	
private Connection con = null;
	
	public DBFuncionario() {
		con = (Connection) ConnectionFactory.getConnection();
	}
	
public boolean cadFuncionario(Funcionario func) {
			
		PreparedStatement stmt = null;
        String sql = "INSERT INTO funcionario (nome ,usuario ,senha , cpf, salario) VALUES (?,?,?,?,?)";
        try {
        	stmt =  con.prepareStatement(sql);

            stmt.setString(1, func.getNome());
            stmt.setString(2, func.getUsuario());
            stmt.setString(3, func.getSenha());
            stmt.setString(4, func.getCpf());
            stmt.setFloat(5, func.getSalario());

            int result = stmt.executeUpdate(); //executar o sql r insere no DB
            return result == 1 ? true:false;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
		
	}
	
	public boolean editFuncionario(Funcionario func) {
		
		PreparedStatement stmt = null;
        String sql = "UPDATE funcionario SET nome = ?, usuario = ?,senha = ?, cpf = ?, salario = ? WHERE id_func = ?";
        
        try {
        	stmt = con.prepareStatement(sql);
        	 
        	stmt.setString(1, func.getNome());
        	 stmt.setString(2, func.getUsuario());
             stmt.setString(3, func.getSenha());
             stmt.setString(4, func.getCpf());
             stmt.setFloat(5, func.getSalario());
            
            stmt.setLong(6, func.getIdFunc());

            int result = stmt.executeUpdate(); //executar o sql
            return result == 1 ? true:false;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
	}
	
	public boolean deleteFuncionario(Funcionario func) {
		
		PreparedStatement stmt = null;
        String sql = "DELETE from funcionario WHERE id_func = ?";
        
        try {
        	stmt = con.prepareStatement(sql);
            
        	stmt.setLong(1, func.getIdFunc());

            int result = stmt.executeUpdate(); //executar o sql
            return result == 1 ? true:false;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
		
	}
	
	public Funcionario buscaUltimoFuncionario() {
		Funcionario func = null; 
		ResultSet rs = null;
		PreparedStatement stmt = null;
        String sql = "SELECT * from funcionario where id_func = (SELECT max(id_func) from funcionario)";
        
        try {
        	stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
            	func = new Funcionario();
            	func.setIdFunc(rs.getInt("id_func"));
            	func.setNome(rs.getString("nome"));
            	func.setUsuario(rs.getString("usuario"));
            	func.setSenha(rs.getString("senha"));
            	func.setCpf(rs.getString("cpf"));
            	func.setSalario(rs.getFloat("salario"));
            }

            return func;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return null;
        } 
		
	}
	
	public Funcionario buscaFuncionario(Funcionario idFunc) {
		Funcionario func = null; 
		ResultSet rs = null;
		PreparedStatement stmt = null;
        String sql = "SELECT * from funcionario where id_func = ?";
        
        try {
        	stmt = con.prepareStatement(sql);
        		
        	stmt.setLong(1, idFunc.getIdFunc());
        	
            rs = stmt.executeQuery();
            while(rs.next()) {
            	func = new Funcionario();
            	func.setIdFunc(rs.getInt("id_func"));
            	func.setNome(rs.getString("nome"));
            	func.setUsuario(rs.getString("usuario"));
            	func.setSenha(rs.getString("senha"));
            	func.setCpf(rs.getString("cpf"));
            	func.setSalario(rs.getFloat("salario"));
            }

            return func;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return null;
        } 
		
	}
	
	public ArrayList<Funcionario> buscarTodosFuncionarios() {
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>(); 
		ResultSet rs = null;
		PreparedStatement stmt = null;
        String sql = "SELECT * from funcionario";
        
        try {
        	stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
            	Funcionario func = new Funcionario();
            	func.setIdFunc(rs.getInt("id_func"));
            	func.setNome(rs.getString("nome"));
            	func.setCpf(rs.getString("cpf"));
            	func.setNome(rs.getString("nome"));
            	func.setSenha(rs.getString("senha"));
            	func.setSalario(rs.getFloat("salario"));
            	funcionarios.add(func);
            }
            
            return funcionarios;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return null;
        } 
		
	}
}
