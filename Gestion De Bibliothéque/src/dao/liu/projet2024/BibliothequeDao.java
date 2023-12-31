package dao.liu.projet2024;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import BDD.DbConnection;
import Metier.liu.projet2024.Bibliotheque;

public class BibliothequeDao implements Dao<Bibliotheque> {
	// variable de connection
	private Connection conn;

	// initialisation des Variables
	public BibliothequeDao() {

		// connection a la base de donnees
		try {

			conn = DbConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Bibliotheque get(int id) {
		Bibliotheque bibliotheque = null;
		// requet
		String requet = "SELECT * FROM Bibliotheque WHERE idBiblioteque = " + id;
		System.out.println(requet);
		try {
			// conteneur de requete
			Statement pstm = conn.createStatement();
			// rucuperer les resultas
			ResultSet rs = pstm.executeQuery(requet);
			// stocker les resultas dans l'objet biblio
			if (rs.next()) {
				int idBiblioteque = rs.getInt(1);
				String emplacement = rs.getString(2);
				bibliotheque = new Bibliotheque(idBiblioteque, emplacement);
				// Affichage
				System.out.println(bibliotheque.toString());
				System.out.println();
			} else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... élément introuvable");
			e.printStackTrace();
		}
		return bibliotheque;
	}

	@Override
	public List<Bibliotheque> getAll() {
		Bibliotheque bibliotheque = null;
		ArrayList<Bibliotheque> arrayList = new ArrayList<>();
		String requet = "SELECT * FROM Bibliotheque";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next())
				do {
					int idBiblioteque = rs.getInt("idBiblioteque");
					String emplacement = rs.getString("emplacement");
					bibliotheque = new Bibliotheque(idBiblioteque, emplacement);
					arrayList.add(bibliotheque);
					System.out.println(bibliotheque.toString());
				} while (rs.next());
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... éléments introuvables");
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public void save(Bibliotheque t) {
		String requet = "INSERT INTO Bibliotheque(idBiblioteque, emplacement) VALUES('" + t.getIdBibliotheque() + "', '"
				+ t.getEmplacement() + "')";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs > 0)
				System.out.println("Bibliotheque enregistrée !");
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... enregistrement échoué");
			e.printStackTrace();
		}
	}

	@Override
	public void update(Bibliotheque t, String[] params) {
		String requet = "UPDATE Bibliotheque SET " + "emplacement='" + params[1] + "' " + "WHERE idBiblioteque = "
				+ (int) t.getIdBibliotheque();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs > 0)
				System.out.println("\tBibliotheque modifiée !\n");
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... modification échouée");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Bibliotheque t) {
		String requet = "DELETE FROM bibliotheque WHERE idBiblioteque  = " + (int) t.getIdBibliotheque();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs > 0)
				System.out.println("\tBibliotheque supprimée !\n");
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... suppression échouée");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BibliothequeDao biblioDao = new BibliothequeDao();

//			Bibliotheque bibLio11=new Bibliotheque(11,"Bibliotheque de Village, Boghe");
//			//	biblioDao.save(bib11);
		biblioDao.get(6);
		// biblioDao.getAll();
		// String[] param={"12","akjoujt"};
		// biblioDao.update(biblioDao.get(5), param);
		biblioDao.getAll();
		biblioDao.delete(biblioDao.get(5));
		biblioDao.getAll();

	}

	@Override
	public Bibliotheque get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}