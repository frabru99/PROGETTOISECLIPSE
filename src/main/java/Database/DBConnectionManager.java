package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;

public class DBConnectionManager {
	
	public static String url = "jdbc:mysql://localhost:3306/"; //linbk al database
	public static String dbName= "centrosportivo"; //nome
	public static String driver = "com.mysql.cj.jdbc.Driver"; //driver scelto per la gestione
	public static String userName="root"; //nome e password per accesso
	public static String password="Cmcfb2002!";
	//fornisco le costanti che servono per la connessione
	

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName(driver);	//gestisco connessione e driver
		
		conn = DriverManager.getConnection(url+dbName, userName, password); //dal driver manager prendo la connessione
		//dando url e nome del db con username e password!
		
		return conn; //ritorno la connessione!
	}
	
	
	public static void closeConnection(Connection c) throws SQLException{
		
		System.out.println("Chiudo connessione...");
		c.close();
	}
	
	
	
	
	public static ResultSet selectQuery(String query) throws ClassNotFoundException, SQLException{
		
		
		Connection conn = getConnection(); //prendoi la connessione 
		
		java.sql.Statement statement = conn.createStatement();	//creo statement per effettuare query!!!
		
		ResultSet ret = statement.executeQuery(query); //faccio la query e torno ret!
	
		//System.out.println("Eseguito la query!");
		
		
		return ret;
		
	}
	
	public static int updateQuery(String query) throws ClassNotFoundException, SQLException{
		
		Connection conn = getConnection(); //prendo la connesione 
		
		java.sql.Statement  statement = conn.createStatement(); //creo statement 
		
		int ret = statement.executeUpdate(query); //faccio query
		
		closeConnection(conn); //chiudo
		
		return ret;
	}
	
	
	
	
}