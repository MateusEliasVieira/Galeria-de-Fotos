package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionFactory {
	
	private static String database = "jdbc:mysql://localhost:3306/crud_jsp";
	private static String user = "root";
	private static String password = "";
	private static Connection connection = null;

	public static Connection getConnection() 
	{
		try {

			if (connection == null) {
				Class.forName("com.mysql.cj.jdbc.Driver"); /* Carrega o driver de conexão do banco */
				connection = DriverManager.getConnection(database, user, password);
				connection.setAutoCommit(false); /* para nao efetuar alteraçõess no banco sem nosso comando */
			}
			
		} catch (Exception e) {
			e.printStackTrace();/* Mostrar qualquer erro no momento de conectar */
		}
		
		return connection;
	}
	
	
	

}
