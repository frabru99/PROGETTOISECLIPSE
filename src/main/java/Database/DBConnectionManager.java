package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnectionManager {
	
	//Variabili membro - costanti necessarie per la connessione
	public static String url = "jdbc:mysql://localhost:3306/"; //Link al database
	public static String dbName= "centrosportivo"; //Nome
	public static String driver = "com.mysql.cj.jdbc.Driver"; //Driver scelto per la gestione
	public static String userName="frabru99"; //Nome e password per accesso
	public static String password="Cmcfb2002!";
	
	
	//Metodo che crea la connessione dal driver
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		//Creo oggetto connection
		Connection conn = null;
		Class.forName(driver);
		
		//Ottengo la connessione dal DriverManager passando come parametri le credenziali ed il nome del db
		conn = DriverManager.getConnection(url+dbName, userName, password); 

		return conn; 
	}
	
	
	//Metodo che chiude la connessione con il DB
	public static void closeConnection(Connection c) throws SQLException{
		
		System.out.println("Chiudo connessione...");
		c.close();
		
	}
	
	
	//ResultSet - ritorna i risultati di una query
	public static ResultSet selectQuery(String query) throws ClassNotFoundException, SQLException{
		
		//Invoco getConnection
		Connection conn = getConnection(); 
		
		//Creo uno statement - necessario per effettuare query
		java.sql.Statement statement = conn.createStatement();
		
		//Effettuo la query
		ResultSet ret = statement.executeQuery(query); 
	
		return ret;
		
	}
	
	
	//Query per inserimento ed update
	public static int updateQuery(String query) throws ClassNotFoundException, SQLException{
		
		//Invoco getConnection
		Connection conn = getConnection(); //prendo la connesione 
		
		//Creo uno statement - necessario per effettuare query
		java.sql.Statement  statement = conn.createStatement(); //creo statement 
		
		//Effettuo la query
		int ret = statement.executeUpdate(query); 
		
		//Chiudo la connessione
		closeConnection(conn); 
		
		return ret;
	}
	
	
	
	
}