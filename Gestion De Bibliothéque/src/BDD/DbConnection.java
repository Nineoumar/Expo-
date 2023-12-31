package BDD;

	 import java.sql.Connection;
	 import java.sql.DriverManager;
	 import java.sql.SQLException;

	 public class DbConnection {
	 	private static DbConnection instance;
	 	private Connection connection;
	 	private String url = "jdbc:mysql://localhost:3306/";
	 	private String bdd= "GestionDeBibliotheque";
	 	private String username = "root";
	 	private String password = "";
	 	private DbConnection() throws SQLException {
	 		try {
	 			Class.forName("com.mysql.cj.jdbc.Driver");
	 			this.connection = DriverManager.getConnection(url+bdd,
	 					username, password);
	 			System.out.println("Connection à la base de donnee "+bdd);
	 			System.out.println();
	 		} catch (ClassNotFoundException e) {
	 			System.out.println("Erreur du Driver : " + e.getMessage());
	 		} catch (SQLException e) {
	 			System.out.println("Erreur SQL : " + e.getMessage());
	 		}
	 	}
	 	public Connection getConnection() {
	 		return connection;
	 	}
	 	public static DbConnection getInstance() throws SQLException {
	 		if (instance == null) {
	 			instance = new DbConnection();
	 		} else if (instance.getConnection().isClosed()) {
	 			instance = new DbConnection();
	 		}
	 		return instance;
	 	}
	 	
	 	public static void main(String[] args) throws SQLException {
	 		DbConnection.getInstance().getConnection();
	 	}
	 }



