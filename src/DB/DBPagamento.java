package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pagamento;

public class DBPagamento {
	
	Connection con = null;
	
	public DBPagamento() {
		con = (Connection) ConnectionFactory.getConnection();
	} 
	
	
	public boolean realizarPagamento(Pagamento pagamento) {
		PreparedStatement stmt = null;
        String sql = "INSERT INTO pagamento (data ,idCliente , idFunc, tipo) VALUES (?,?,?,?)";
        
        try {
            stmt =  con.prepareStatement(sql);

            stmt.setString(1, pagamento.getData());
            stmt.setLong(2, pagamento.getIdCli());
            stmt.setLong(3, pagamento.getIdFunc());
            stmt.setString(4, pagamento.getTipo());

            stmt.executeUpdate(); //executar o sql r insere no DB
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
	}
	
	public boolean editPagamento(Pagamento pagamento) {
		
		PreparedStatement stmt = null;
        String sql = "UPDATE pagamento SET idCliente = ?, tipo = ? WHERE idPag = ?" ;
        
        try {
            stmt =  con.prepareStatement(sql);

            stmt.setLong(1, pagamento.getIdCli());
            stmt.setString(2, pagamento.getTipo());
            
            stmt.setLong(3, pagamento.getIdPag());

            stmt.executeUpdate(); //executar o sql
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
	}
	
	public boolean deletePagamento(Pagamento pagamento) {
		
		PreparedStatement stmt = null;
        String sql = "DELETE pagamento WHERE idPag = ?" ;
        
        try {
            stmt =  con.prepareStatement(sql);
            
            stmt.setLong(1, pagamento.getIdPag());

            stmt.executeUpdate(); //executar o sql
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
        
	}
	
	public List<Pagamento> listaPagamentos() {
		
		List<Pagamento> pagamentos = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM pagamento");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Pagamento pagamento = new Pagamento();

                pagamento.setIdPag(rs.getInt("idPag"));
                pagamento.setIdCli(rs.getInt("idCliente"));
                pagamento.setData(rs.getString("data"));
                pagamento.setIdFunc(rs.getInt("idFunc"));
                pagamento.setTipo(rs.getString("tipo"));

                pagamentos.add(pagamento);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
		
		return pagamentos;
	}

}
