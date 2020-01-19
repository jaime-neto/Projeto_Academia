package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;
import model.Endereco;
import model.Funcionario;

public class DBCliente {
	
	private Connection con = null;
	
	public DBCliente() {
		con = (Connection) ConnectionFactory.getConnection();
	}
	
	public boolean cadCliente(Cliente cliente) {
		
		PreparedStatement stmt = null;
        String sql = "INSERT INTO cliente (nome ,cpf,telefone, id_end) VALUES (?,?,?,?)";
        try {
            stmt =  con.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getEndereco().getIdEndereco());
            
            int result = stmt.executeUpdate(); //executar o sql r insere no DB
            if(result == 1) {
            	return true;
            } else {
            	return false;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
		
	}
	
	public boolean editCliente(Cliente cliente) {
		
		PreparedStatement stmt = null;
        String sql = "UPDATE cliente SET nome = ?,cpf = ?, id_end = ?, telefone = ? WHERE id_cli = ?";
        
        try {
        	stmt = con.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setInt(3, cliente.getEndereco().getIdEndereco());
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
        String sql = "DELETE from cliente WHERE id_cli = ?";
        
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
	
	public Cliente buscaUltimoCliente() {
		Cliente cli = null; 
		ResultSet rs = null;
		PreparedStatement stmt = null;
        String sql = "SELECT * from cliente where id_cli = (SELECT max(id_cli) from cliente)";
        
        try {
        	stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
            	cli = new Cliente();
            	cli.setIdCliente(rs.getInt("id_cli"));
            	cli.setNome(rs.getString("nome"));
            	cli.setCpf(rs.getString("cpf"));
            	cli.setTelefone(rs.getString("telefone"));
            	Endereco end = new Endereco();
            	end.setIdEndereco(rs.getInt("id_end"));
            	cli.setEndereco(end);
            }

            return cli;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return null;
        } 
		
	}

}
