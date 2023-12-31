package dao.liu.projet2024;


	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	import BDD.DbConnection;
import Metier.liu.projet2024.Bibliotheque;
import Metier.liu.projet2024.Livre;
import Metier.liu.projet2024.Telephone;

	
	public class LivreDao implements Dao<Livre> {
		//variable de connection
		private Connection conn;
		
		//initialisation des Variables
		public LivreDao() {
			try {
				conn = DbConnection.getInstance().getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public Livre get(long id) {
			Livre livre = null;
			//requet
			String requet = "SELECT * FROM Livre WHERE ISBN = " + (int) id;
			System.out.println(requet);
			try {
				Statement pstm = conn.createStatement();
				ResultSet rs = pstm.executeQuery(requet);
				if (rs.next()) {
					int ISBN = rs.getInt("ISBN");
					String titre = rs.getString("titre");
					int idAuteur = rs.getInt("idAuteur");
					livre = new Livre(ISBN, titre, idAuteur);
					System.out.println(livre.toString());
					System.out.println();
				}
				else throw new SQLException();
			} catch (SQLException e) {
				System.out.println("Erreur SQL... élément introuvable");
				e.printStackTrace();
			}
			return livre;
		}
		@Override
		public List<Livre> getAll() {
			Livre livre = null;
			ArrayList<Livre> arrayList = new ArrayList<>();
			String requet = "SELECT * FROM Livre";
			System.out.println(requet);
			try {
				Statement pstm = conn.createStatement();
				ResultSet rs = pstm.executeQuery(requet);
				if (rs.next())
					do {
						int ISBN = rs.getInt("ISBN");
						String titre = rs.getString("titre");
						int idAuteur = rs.getInt("idAuteur");
						livre = new Livre(ISBN, titre, idAuteur);
						arrayList.add(livre);
						System.out.println(livre.toString());
					} while (rs.next());
				else throw new SQLException();
			} catch (SQLException e) {
				System.out.println("Erreur SQL... éléments introuvables");
				e.printStackTrace();
			}
			return arrayList;
		}
		@Override
		public void save(Livre t) {
			String requet = "INSERT INTO Livre(ISBN, titre, idAuteur) VALUES("+t.getISBN()+", '"+t.getTitre()+"', '"+t.getIdAuteur()+"')";
			System.out.println(requet);
			try {
				Statement pstm = conn.createStatement();
				int rs = pstm.executeUpdate(requet);
				if (rs>0)
					System.out.println("\tLivre enregistré !\n");
				else
					throw new SQLException();
			} catch (SQLException e) {
				System.out.println("Erreur SQL... enregistrement échoué");
				e.printStackTrace();
			}
		}
		@Override
		public void update(Livre t, String[] params) {
			String requet = "UPDATE Livre SET titre='"+params[0]+"', "
							+ "idAuteur='"+params[1]+"' "
							+ "WHERE ISBN = " + (int) t.getISBN();
			System.out.println(requet);
			try {
				Statement pstm = conn.createStatement();
				int rs = pstm.executeUpdate(requet);
				if (rs>0)
					System.out.println("\tLivre modifié !\n");
				else throw new SQLException();
			} catch (SQLException e) {
				System.out.println("Erreur SQL... modification échouée");
				e.printStackTrace();
			}
		}
		@Override
		public void delete(Livre t) {
			String requet = "DELETE FROM Livre WHERE ISBN = " + (int) t.getISBN();
			System.out.println(requet);
			try {
				Statement pstm = conn.createStatement();
				int rs = pstm.executeUpdate(requet);
				if (rs>0)
					System.out.println("\tLivre supprimé !\n");
				else throw new SQLException();
			} catch (SQLException e) {
				System.out.println("Erreur SQL... suppression échouée");
				e.printStackTrace();
			}
		}
		
		
		
		
	


	public static void main(String [] args) {
		LivreDao livDao =new LivreDao();
				Livre liv11 =new Livre (111222,"Voyages à travers le Savoi",11);
		// livDao.save(liv11);
	//livDao.getAll();
		
	}}