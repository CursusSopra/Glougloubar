package fr.cursusSopra.tech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.model.Bar;
import fr.cursusSopra.model.Critere;
import fr.cursusSopra.model.Ville;

public class Adresse {
	private int idAdresse;
	private Ville ville;
	private String voie;
	private double latitude;
	private double longitude;
	
	public Adresse(Ville ville, String voie, double latitude, double longitude){
		setVille(ville);
		setVoie(voie);
		setLatitude(latitude);
		setLongitude(longitude);
	}
	
	public Adresse() {
		
	}

	/**
	 * Ajoute une adresse � un bar. Appel�e dans la m�thode Create()
	 * 
	 * @return idadresse
	 */
	public void SaveAdresse() {
		Connection cnx = PostgresConnection.GetConnexion();
		String queryAdresse = "INSERT INTO adresses (voie, cp, latitude, longitude) VALUES (?, ?, ?, ?) RETURNING idadresse";
		try {
			// Insertion de l'adresse dans la base de donn�es
			PreparedStatement psAdresse = cnx.prepareStatement(queryAdresse);
			psAdresse.setString(1, getVoie());
			psAdresse.setString(2, getVille().getCp());
			psAdresse.setDouble(3, getLatitude());
			psAdresse.setDouble(4, getLongitude());
			// R�cup�ration de l'idadresse
			ResultSet rsAdresse = psAdresse.executeQuery();
			rsAdresse.next();
			setIdAdresse(rsAdresse.getInt(1));
			rsAdresse.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public static Adresse getAdresse(int idBar) {
		// TODO Auto-generated method stub
		Adresse adresse = new Adresse();
		Connection cnx = PostgresConnection.GetConnexion();

		// requete de selection de tous les bars
		String query = "SELECT * FROM v_adressebar WHERE idbar =?   ORDER BY idbar";

		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setInt(1, idBar);
			ResultSet rs = ps.executeQuery();

			// remplissage tant qu'on trouve des criteres
			if (rs.next()) {
				Ville ville = new Ville(rs.getString("cp"), rs.getString("ville"));	
				adresse.setVille(ville);
				adresse.setVoie(rs.getString("voie"));
				adresse.setLatitude(rs.getDouble("latitude"));
				adresse.setLongitude(rs.getDouble("longitude"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresse;
	}

	public int getIdAdresse() {
		return idAdresse;
	}
}
