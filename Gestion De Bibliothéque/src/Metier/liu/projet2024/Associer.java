package Metier.liu.projet2024;

import dao.liu.projet2024.LivreDao;

public class Associer {
	
	private int idBibliotheque;
	private int idAuteur;
	
	public Associer(int idBibliotheque, int idAuteur) {
		super();
		this.idBibliotheque = idBibliotheque;
		this.idAuteur = idAuteur;
	}

	public Associer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdBibliotheque() {
		return idBibliotheque;
	}

	public void setIdBibliotheque(int idBibliotheque) {
		this.idBibliotheque = idBibliotheque;
	}

	public int getIdAuteur() {
		return idAuteur;
	}

	public void setIdAuteur(int idAuteur) {
		this.idAuteur = idAuteur;
	
	}
	

	@Override
	public String toString() {
		return "Associer [idBibliotheque=" + idBibliotheque + ", idAuteur=" + idAuteur + "]";
	}}