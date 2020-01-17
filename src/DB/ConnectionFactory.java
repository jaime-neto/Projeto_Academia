package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConnectionFactory {

		
		//Alterando localização do jdbc. Localização do diretorio do banco dentro da aplicação.
	    private static  final String DRIVER = "org.postgresql.JDBC";
	    private static final String URL = "jdbc:postgresql://localhost/gymapp";
	    
	    private static final String USER = "postgres";//login
	    private static final String PASS = "0000";//senha

	    public static ConnectionFactory getConnection(){
	        try {
	            Class.forName(DRIVER);
	            return  (ConnectionFactory) DriverManager.getConnection(URL);

	        } catch (ClassNotFoundException | SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
	           throw new RuntimeException(ex.getLocalizedMessage());
	        }
	    }
	    public static void closeConnection(Connection con){
	        try {
	            if(con != null){
	               con.close();
	            }
	        } catch (SQLException ex) {
	            System.err.println(ex.getLocalizedMessage());
	        }
	    }
	    public static void closeConnection(Connection con,PreparedStatement stmt){
	        closeConnection(con);   
	        try {
	          if(stmt!=null){
	              stmt.close();
	          }
	         } catch (SQLException ex) {
	             System.err.println(ex.getLocalizedMessage());
	        }
	    }
	    public static void closeConnection(Connection con,PreparedStatement stmt,ResultSet rs){
	        closeConnection(con,stmt);   
	        try {
	          if(rs!=null){
	              rs.close();
	          }
	         } catch (SQLException ex) {
	             System.err.println(ex.getLocalizedMessage());
	        }
	    }

	}
