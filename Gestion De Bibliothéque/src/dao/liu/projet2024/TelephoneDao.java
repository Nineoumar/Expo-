 package dao.liu.projet2024;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import BDD.DbConnection;
import Metier.liu.projet2024.Auteur;
import Metier.liu.projet2024.Telephone;


public class TelephoneDao implements Dao<Telephone> {
	private Connection conn;
	public TelephoneDao() {
		try {
			conn = DbConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Telephone get(long id) {
		Telephone telephone = null;
		String requet = "SELECT * FROM Telephone WHERE numero = " + (int) id;
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next()) {
				int numero = rs.getInt("numero");
				String type = rs.getString("type");
				int idAuteur = rs.getInt("idAuteur");
				telephone = new Telephone(numero, type, idAuteur);
				System.out.println(telephone.toString());
				System.out.println();
			}
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... élément introuvable");
			e.printStackTrace();
		}
		return telephone;
	}
	@Override
	public List<Telephone> getAll() {
		Telephone telephone = null;
		ArrayList<Telephone> arrayList = new ArrayList<>();
		String requet = "SELECT * FROM Telephone";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next())
				do {
					int numero = rs.getInt("numero");
					String type = rs.getString("type");
					int idAuteur = rs.getInt("idAuteur");
					telephone = new Telephone(numero, type, idAuteur);
					arrayList.add(telephone);
					System.out.println(telephone.toString());
				} while (rs.next());
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... éléments introuvables");
			e.printStackTrace();
		}
		return arrayList;
	}
	@Override
	public void save(Telephone t) {
		String requet = "INSERT INTO Telephone(numero, type, idAuteur) VALUES("+t.getNumero()+", '"+t.getType()+"', '"+t.getIdAuteur()+"')";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tTelephone enregistré !\n");
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... enregistrement échoué");
			e.printStackTrace();
		}
	}
	@Override
	public void update(Telephone t, String[] params) {
		String requet = "UPDATE Telephone SET type='"+params[0]+"', "
						+ "idAuteur='"+params[1]+"' "
						+ "WHERE numero = " + (int) t.getNumero();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tTelephone modifié !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... modification échouée");
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Telephone t) {
		String requet = "DELETE FROM Telephone WHERE numero = " + (int) t.getNumero();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tTelephone supprimé !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... suppression échouée");
			e.printStackTrace();
		}
	}

	
	


public static void main(String [] args) {
	TelephoneDao TelDao =new TelephoneDao();
//			Telephone Tel11 =new Telephone (123124,"Mobile",11);
//	TelDao.save(Tel11);
	TelDao.getAll();
	TelDao.get(123123);
	//TelDao.delete(TelDao.get(555555));
	String[] params = {"Fixe", "2","222222"};
	TelDao.update(new Telephone(222222, null, 0), params);
	
	
}}