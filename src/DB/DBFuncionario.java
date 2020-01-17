package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Funcionario;

public class DBFuncionario {
	
private Connection con = null;
	
	public DBFuncionario() {
		con = (Connection) ConnectionFactory.getConnection();
	}
	
public boolean CadFuncionario(Funcionario func) {
		
		PreparedStatement stmt = null;
        String sql = "INSERT INTO funcionario (usuario ,senha , cpf, salario) VALUES (?,?,?,?)";
        try {
        	stmt =  con.prepareStatement(sql);

            stmt.setString(1, func.getUsuario());
            stmt.setString(2, func.getSenha());
            stmt.setString(3, func.getCpf());
            stmt.setFloat(4, func.getSalario());

            stmt.executeUpdate(); //executar o sql r insere no DB
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
		
	}
	
	public boolean editFuncionario(Funcionario func) {
		
		PreparedStatement stmt = null;
        String sql = "UPDATE funcionario SET usuario = ?,senha = ? cpf = ?, salario = ? WHERE idFunc = ?";
        
        try {
        	stmt = con.prepareStatement(sql);

        	 stmt.setString(1, func.getUsuario());
             stmt.setString(2, func.getSenha());
             stmt.setString(3, func.getCpf());
             stmt.setFloat(4, func.getSalario());
            
            stmt.setLong(5, func.getIdFunc());

            stmt.executeUpdate(); //executar o sql
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
	}
	
	public boolean deleteFuncionario(Funcionario func) {
		
		PreparedStatement stmt = null;
        String sql = "DELETE funcionario WHERE idFunc = ?";
        
        try {
        	stmt = con.prepareStatement(sql);
            
        	stmt.setLong(1, func.getIdFunc());

            stmt.executeUpdate(); //executar o sql
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
		
	}

}
