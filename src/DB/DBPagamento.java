package DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Funcionario;
import model.Pagamento;

public class DBPagamento {
	
	Connection con = null;
	
	public DBPagamento() {
		con = (Connection) ConnectionFactory.getConnection();
	} 
	
	
	public boolean realizarPagamento(Pagamento pagamento) {
		PreparedStatement stmt = null;
        String sql = "INSERT INTO pagamento (data ,tipo, id_func , id_cli) VALUES (?,?,?,?)";
        
        try {
            stmt =  con.prepareStatement(sql);

            stmt.setDate(1, (java.sql.Date)  new Date(Calendar.getInstance().getTime().getTime()));
            stmt.setString(2, pagamento.getTipo());
            stmt.setInt(3, pagamento.getIdFunc().getIdFunc());
            stmt.setInt(4, pagamento.getIdCliente().getIdCliente());

            int result  = stmt.executeUpdate(); //executar o sql r insere no DB
            return result == 1 ? true: false;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
	}
	
	public boolean editPagamento(Pagamento pagamento) {
		
		PreparedStatement stmt = null;
        String sql = "UPDATE pagamento SET id_cli = ?, tipo = ? WHERE id_pag = ?" ;
        
        try {
            stmt =  con.prepareStatement(sql);

            stmt.setLong(1, pagamento.getIdCliente().getIdCliente());
            stmt.setString(2, pagamento.getTipo());
            stmt.setLong(3, pagamento.getIdPag());

            int result = stmt.executeUpdate(); //executar o sql
            return result == 1 ? true: false;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
	}
	
	public boolean deletePagamento(Pagamento pagamento) {
		
		PreparedStatement stmt = null;
        String sql = "DELETE from pagamento WHERE id_cli = ?" ;
        
        try {
            stmt =  con.prepareStatement(sql);
            
            stmt.setLong(1, pagamento.getIdCliente().getIdCliente());

            stmt.executeUpdate(); //executar o sql
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
        
	}
	
	public Pagamento buscaUltimoPagamento() {
		Pagamento pag = null; 
		ResultSet rs = null;
		PreparedStatement stmt = null;
        String sql = "SELECT * from pagamento where id_pag = (SELECT max(id_pag) from pagamento)";
        
        try {
        	stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
            	pag = new Pagamento();
            	Calendar cal = Calendar.getInstance();
            	cal.setTime(rs.getDate("data"));
            	pag.setData(cal);
            	pag.setTipo(rs.getString("tipo"));
            	pag.setIdPag(rs.getInt("id_pag"));
            	//buscar Funcionario no BD
            	Funcionario func =  new Funcionario();
            	func.setIdFunc(rs.getInt("id_func"));
            	DBFuncionario db_func = new DBFuncionario();
            	pag.setIdFunc(db_func.buscaFuncionario(func));
            	//Buscar Cliente no BD
            	//	pag.setCidade(rs.getString("cidade"));
            }
            
            return pag;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return null;
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
             //   pagamento.setIdCli(rs.getInt("idCliente"));
                //pagamento.setData(rs.getString("data"));
               // pagamento.setIdFunc(rs.getInt("idFunc"));
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
