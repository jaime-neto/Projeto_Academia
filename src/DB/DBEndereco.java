package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Endereco;


public class DBEndereco {
private Connection con = null;
	
	public DBEndereco() {
		con = (Connection) ConnectionFactory.getConnection();
	}
	
	public boolean cadEndereco(Endereco end) {
		
		PreparedStatement stmt = null;
        String sql = "INSERT INTO endereco (rua,bairro,cidade) VALUES (?,?,?)";
        try {
            stmt =  con.prepareStatement(sql);

            stmt.setString(1, end.getRua());
            stmt.setString(2, end.getBairro());
            stmt.setString(3, end.getCidade());

            int result = stmt.executeUpdate(); //executar o sql e insere no DB
            if(result == 1) {
            	return true;
            }else {
            	return false;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
		
	}
	
	public boolean editEndereco(Endereco end) {
		
		PreparedStatement stmt = null;
        String sql = "UPDATE endereco SET rua = ?,bairro = ?, cidade = ? WHERE id_end = ?";
        
        try {
        	stmt = con.prepareStatement(sql);

        	stmt.setString(1, end.getRua());
            stmt.setString(2, end.getBairro());
            stmt.setString(3, end.getCidade());
            stmt.setInt(4, end.getIdEndereco());

            stmt.executeUpdate(); //executar o sql
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
	}
	
	public boolean deleteEndereco(Endereco end) {
		
		PreparedStatement stmt = null;
        String sql = "DELETE from endereco WHERE id_end = ?";
        
        try {
        	stmt = con.prepareStatement(sql);
            
            stmt.setLong(1, end.getIdEndereco());

            stmt.executeUpdate(); //executar o sql
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
		
	}
	
	public Endereco buscaUltimoEndereco() {
			Endereco end = null; 
			ResultSet rs = null;
			PreparedStatement stmt = null;
	        String sql = "SELECT * from endereco where id_end = (SELECT max(id_end) from endereco)";
	        
	        try {
	        	stmt = con.prepareStatement(sql);
	            rs = stmt.executeQuery();
	            while(rs.next()) {
	            	end = new Endereco();
	            	end.setIdEndereco(rs.getInt("id_end"));
	            	end.setRua(rs.getString("rua"));
	            	end.setBairro(rs.getString("bairro"));
	            	end.setCidade(rs.getString("cidade"));
	            }
	            
	            return end;
	        } catch (SQLException ex) {
	            System.err.println(ex.getLocalizedMessage());
	            return null;
	        }
			
		}

}
