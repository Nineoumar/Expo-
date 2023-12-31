package dao.liu.projet2024;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import BDD.DbConnection;
import Metier.liu.projet2024.Auteur;
import Metier.liu.projet2024.Bibliotheque;

public class AuteurDao implements Dao<Auteur> {
	private Connection conn;
	public AuteurDao() {
		try {
			conn = DbConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Auteur get(long id) {
		Auteur auteur = null;
		String requet = "SELECT * FROM Auteur WHERE idAuteur = " + (int) id;
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next()) {
				int idAuteur = rs.getInt("idAuteur");
				String nom = rs.getString("nom");
				String adresse = rs.getString("adresse");
				String region = rs.getString("region");
				auteur = new Auteur(idAuteur, nom, adresse, region);
				System.out.println(auteur.toString());
				System.out.println();
			}
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... élément introuvable");
			e.printStackTrace();
		}
		return auteur;
	}
	@Override
	public List<Auteur> getAll() {
		Auteur auteur = null;
		ArrayList<Auteur> arrayList = new ArrayList<>();
		String requet = "SELECT * FROM Auteur";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next())
				do {
					int idAuteur = rs.getInt("idAuteur");
					String nom = rs.getString("nom");
					String adresse = rs.getString("adresse");
					String region = rs.getString("region");
					auteur = new Auteur(idAuteur, nom, adresse, region);
					arrayList.add(auteur);
					System.out.println(auteur.toString());
				} while (rs.next());
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... éléments introuvables");
			e.printStackTrace();
		}
		return arrayList;
	}
	@Override
	public void save(Auteur t) {
		String requet = "INSERT INTO Auteur(idAuteur, nom, adresse, region) VALUES("+t.getIdAuteur()+", '"+t.getNom()+"', '"+t.getAdresse()+"', '"+t.getRegion()+"')";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tAuteur enregistré !\n");
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... enregistrement échoué");
			e.printStackTrace();
		}
	}
	@Override
	public void update(Auteur t, String[] params) {
		String requet = "UPDATE Auteur SET nom='"+params[0]+"', "
						+ "adresse='"+params[1]+"' "
						+ "region='"+params[1]+"' "
						+ "WHERE idAuteur = " + (int) t.getIdAuteur();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tAuteur modifié !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... modification échouée");
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Auteur t) {
		String requet = "DELETE FROM Auteur WHERE idAuteur = " + (int) t.getIdAuteur();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tAuteur supprimé !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... suppression échouée");
			e.printStackTrace();
		}
	}
	

public static void main(String [] args) {
	AuteurDao AutDao =new AuteurDao();
			Auteur Aut11 =new Auteur(11,"Hassan Ould Med","Rue des Livres , Boghe", "Trarza");
//	AutDao.save(Aut11);
	AutDao.getAll();
			
}}
