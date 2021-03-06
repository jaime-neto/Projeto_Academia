package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Funcionario;
import model.Pagamento;

public class DBPagamento {
	
	Connection con = null;
	
	public DBPagamento() {
		con = (Connection) ConnectionFactory.getConnection();
	} 
	
	
	public boolean realizarPagamento(Pagamento pagamento) {
		PreparedStatement stmt = null;
        String sql = "insert into pagamento (data, tipo, id_func, id_cli, valor) values (to_date('"+pagamento.getData()+"','yyyy-mm-dd'), '"+pagamento.getTipo()+"', "+pagamento.getIdFunc().getIdFunc()+", "+pagamento.getIdCliente().getIdCliente()+", "+pagamento.getValor()+");";
		
		try {
            stmt =  con.prepareStatement(sql);         
            int result  = stmt.executeUpdate(); //executar o sql e insere no DB
            return result == 1 ? true: false;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
	}
	
	public boolean editPagamento(int id_pag, Pagamento pagamento) {
		
		PreparedStatement stmt = null;
        String sql = "UPDATE pagamento SET id_func = "+pagamento.getIdFunc().getIdFunc()+", "
        		+ "tipo = "+"'"+pagamento.getTipo()+"'"+", "+ "valor = "+pagamento.getValor()+", "
        				+ "data = '"+pagamento.getData()+"' WHERE id_pag = "+id_pag+";";
        try {
            stmt =  con.prepareStatement(sql);
            
            int result  = stmt.executeUpdate(); //executar o sql
            return result == 1 ? true: false;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
	}
	
	public boolean deletePagamento(int id_pag) {
		
		PreparedStatement stmt = null;
        String sql = "DELETE from pagamento WHERE id_pag = ?" ;
        
        try {
            stmt =  con.prepareStatement(sql);
            stmt.setLong(1, id_pag);
            int result = stmt.executeUpdate(); //executar o sql
            return result == 1 ? true:false;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection((Connection) con, stmt);
        }
        
	}
	
	public Pagamento buscaPagamento(int id_pgmt) {
		Cliente cli = null;
		DBCliente db_cli = new DBCliente();
		
		Funcionario func = null;
		DBFuncionario db_func = new DBFuncionario();
		
		Pagamento pgmt = null;
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
        String sql = "SELECT * from pagamento where id_pag = ?";
        
        try {
        	stmt = con.prepareStatement(sql);
        	
        	stmt.setInt(1, id_pgmt);
        	
            rs = stmt.executeQuery();
            while(rs.next()) {
            	pgmt = new Pagamento();
            	pgmt.setIdPag(rs.getInt("id_pag"));
            	pgmt.setData(rs.getString("data"));
            	pgmt.setTipo(rs.getString("tipo"));
            	pgmt.setValor(rs.getFloat("valor"));
            	
            	func = db_func.buscaFuncionario(rs.getInt("id_func"));
            	pgmt.setIdFunc(func);
            	
            	cli = db_cli.buscaCliente(rs.getInt("id_cli"));
            	pgmt.setIdCliente(cli);
            }

            return pgmt;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return null;
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
	        	pag.setData(rs.getDate("data").toString());
	        	pag.setTipo(rs.getString("tipo"));
	        	pag.setIdPag(rs.getInt("id_pag"));
	        	DBFuncionario db_func = new DBFuncionario();
	        	pag.setIdFunc(db_func.buscaFuncionario(rs.getInt("id_func")));
	        	DBCliente db_cli = new DBCliente();
	        	pag.setIdCliente(db_cli.buscaCliente(rs.getInt("id_cli")));
            }
            
            return pag;
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return null;
        }
		
	}
	
	public List<Pagamento> buscarTodosPagamentos(int id_cli) {
		
		List<Pagamento> pagamentos = new ArrayList<>();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        DBCliente db_cli =  new DBCliente();
        DBFuncionario db_func = new DBFuncionario();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM pagamento where id_cli = ?");
            stmt.setInt(1, id_cli);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pagamento pagamento = new Pagamento();

                pagamento.setIdPag(rs.getInt("id_pag"));

                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getInt("id_cli"));
                pagamento.setIdCliente(db_cli.buscaCliente(cli.getIdCliente()));
                
                Funcionario func = new Funcionario();
                func.setIdFunc(rs.getInt("id_func"));
                
                pagamento.setIdFunc(db_func.buscaFuncionario(func.getIdFunc()));
                pagamento.setData(rs.getDate("data").toString());
                pagamento.setTipo(rs.getString("tipo"));
                pagamento.setValor(rs.getFloat("valor"));
                pagamentos.add(pagamento);
            }
            return(pagamentos);
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return(null);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
	}

}
