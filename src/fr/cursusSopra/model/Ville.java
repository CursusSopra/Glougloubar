package fr.cursusSopra.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.tech.PostgresConnection;

public class Ville {
	private String cp;
	private String ville;
	
	public static List<String> getLstChampVille() {
		List<String> lstChampVille = new ArrayList<String>();
		Connection cnx = PostgresConnection.GetConnexion();

		String queryVille = "SELECT ville FROM villes";
		
		Statement state;
		try {
			state = cnx.createStatement();
			ResultSet rsLstChampVille = state.executeQuery(queryVille);
			while(rsLstChampVille.next()){
				lstChampVille.add(rsLstChampVille.getString("ville"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return lstChampVille;
	}
	
	public static List<Ville> getListeVilles(){
		List<Ville> lstVille = new ArrayList<Ville>();		
		Connection cnx = PostgresConnection.GetConnexion();
		
		//requete de selection de toutes les villes
		String query = "SELECT ville, cp FROM villes";
		
		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			//remplissage tant qu'on trouve des ville
			while (rs.next())
			{
				Ville newVille = new Ville();
				newVille.cp = rs.getString("cp");
				newVille.ville = rs.getString("ville");
				lstVille.add(newVille);
			}		
			rs.close();		
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return lstVille;
	}
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}

}
