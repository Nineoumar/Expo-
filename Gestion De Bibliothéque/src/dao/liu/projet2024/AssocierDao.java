package dao.liu.projet2024;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import BDD.DbConnection;
import Metier.liu.projet2024.Associer;
import Metier.liu.projet2024.Livre;

public class AssocierDao implements Dao<Associer> {
	
	private Connection conn;
	public AssocierDao() {
		try {
			conn = DbConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void save(Associer t) {
		String requet = "INSERT INTO Associer(idBiblioteque, idAuteur) VALUES("+t.getIdBibliotheque()+", '"+t.getIdAuteur()+"')";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tAssociation enregistrée !\n");
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... enregistrement échoué");
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Associer t, String[] params) {
		
	}
	
	@Override
	public void delete(Associer t) {
		String requet = "DELETE FROM Associer WHERE idBibliotheque = " + t.getIdBibliotheque() + "AND idAuteur = " + t.getIdAuteur();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tAssociation supprimée !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... suppression échouée");
			e.printStackTrace();
		}
		
	}

	public Associer get(int idBibliotheque, int idAuteur) {
		Associer association = null;
		String requet = "SELECT * FROM Associer WHERE idbiblioteque = "+idBibliotheque+" AND idAuteur = "+idAuteur;
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next()) {
				association = new Associer(idBibliotheque, idAuteur);
				System.out.println(association.toString());
				System.out.println();
			}
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... élément introuvable");
			e.printStackTrace();
		}
		return association;
	}
	
	@Override
	public Associer get(long id) {
		return null;
	}
	
	@Override
	public List<Associer> getAll() {
		Associer association = null;
		String requet = "SELECT * FROM Associer";
		System.out.println(requet);
		ArrayList<Associer> arrayList = new ArrayList<>();
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			while (rs.next()) {
				association = new Associer(rs.getInt("idBiblioteque"), rs.getInt("idAuteur"));
				arrayList.add(association);
				System.out.println(association.toString());
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL... élément introuvable");
			e.printStackTrace();
		}
		return arrayList;
	}
	

public static void main(String [] args) {
	AssocierDao assDao =new AssocierDao();
			Associer ass11 =new Associer (11,11);
	//assDao.save(ass11);
	assDao.getAll();
}}