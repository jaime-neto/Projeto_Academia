package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConnectionFactory {

		
		//Alterando localiza��o do jdbc. Localiza��o do diretorio do banco dentro da aplica��o.
	    private static  final String DRIVER = "org.postgresql.jdbc";
	    //private static final String URL = "jdbc:postgresql://localhost/gymapp";
	    private static final String URL = "jdbc:postgresql://localhost/gymapp?user=postgres&password=postgres"; //pc-jaime
	    //private static final String URL = "jdbc:postgresql://192.168.99.100:5432/gymapp?user=admin&password=admin"; //pc-jadson
	    //private static final String USER = "admin";//login
	    //private static final String PASS = "admin";//senha

	    public static Connection getConnection(){
	        try {
	            //Class.forName(DRIVER); //talvez precise
	            return  DriverManager.getConnection(URL);

	        } catch (SQLException ex) {
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
	        	  System.out.println("Conexão com o Postgres finalizada.");
	              rs.close();
	          }
	         } catch (SQLException ex) {
	             System.err.println(ex.getLocalizedMessage());
	        }
	    }

	}
