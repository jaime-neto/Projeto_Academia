package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Cliente;

public class DBCliente {
	
	private Connection con = null;
	
	public DBCliente() {
		con = (Connection) ConnectionFactory.getConnection();
	}
	
	public boolean CadCliente(Cliente cliente) {
		
		PreparedStatement stmt = null;
        String sql = "INSERT INTO cliente (nome ,cpf , endereco, telefone) VALUES (?,?,?,?)";
        try {
            stmt =  con.prepareStatement(sql);

            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());

            stmt.executeUpdate(); //executar o sql r insere no DB
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
		
	}
	
	public boolean editCliente(Cliente cliente) {
		
		PreparedStatement stmt = null;
        String sql = "UPDATE cliente SET nome = ?,cpf = ? endereco = ?, telefone = ? WHERE idCliente = ?";
        
        try {
        	stmt = con.prepareStatement(sql);

        	stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());
            
            stmt.setLong(5, cliente.getIdCliente());

            stmt.executeUpdate(); //executar o sql
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
	}
	
	public boolean deleteCliente(Cliente cliente) {
		
		PreparedStatement stmt = null;
        String sql = "DELETE cliente WHERE idCliente = ?";
        
        try {
        	stmt = con.prepareStatement(sql);
            
            stmt.setLong(1, cliente.getIdCliente());

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
