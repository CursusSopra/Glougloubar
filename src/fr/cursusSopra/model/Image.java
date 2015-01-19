//Florian

package fr.cursusSopra.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.tech.Adresse;
import fr.cursusSopra.tech.PostgresConnection;

public class Image {

	private int idBar;
	private String nomImage;
	private boolean isPrincipal;
	
	// Constructeur vide
	public Image() {
	}

	// Constructeur
	public Image(int idBarExt, String nomImageExt) {
		idBar = idBarExt;
		nomImage = nomImageExt;
	}
	
	public int SaveImage() {
		Connection cnx = PostgresConnection.GetConnexion();
		String queryAddEval = "INSERT INTO images (idbar, nomimage) VALUES (?,?)";
		PreparedStatement psAddEval;
		try {
			psAddEval = cnx.prepareStatement(queryAddEval);
			psAddEval.setInt(1, idBar);
			psAddEval.setString(2, nomImage.toString());

			int result = psAddEval.executeUpdate();

			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}
	
	public static Image getMainImage(int idBarExt) {
		// TODO Auto-generated method stub
		Image image = new Image();
		Connection cnx = PostgresConnection.GetConnexion();

		
		String query = "SELECT nomimage FROM images WHERE idbar =? AND isprincipal=TRUE";

		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setInt(1, idBarExt);
			ResultSet rs = ps.executeQuery();

			// remplissage tant qu'on trouve des criteres
			if (rs.next()) {
				image.idBar=idBarExt;
				image.nomImage=rs.getString("nomimage");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return image;
	}

	public static List<Image> getAllImage(int idBarExt) {
		// TODO Auto-generated method stub
		List<Image> lstImages=new ArrayList<Image>();
		Connection cnx = PostgresConnection.GetConnexion();

		
		String query = "SELECT nomimage FROM images WHERE idbar =?";

		try {
			PreparedStatement ps = cnx.prepareStatement(query);
			ps.setInt(1, idBarExt);
			ResultSet rs = ps.executeQuery();

			// remplissage tant qu'on trouve des criteres
			if (rs.next()) {
				Image image = new Image();
				image.idBar=idBarExt;
				image.nomImage=rs.getString("nomimage");
				lstImages.add(image);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstImages;
	}
	
	
	
	
	public String getNomImage() {
		return nomImage;
	}

	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}

	public boolean isPrincipal() {
		return isPrincipal;
	}

	public void setPrincipal(boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

	public int getIdBar() {
		return idBar;
	}

	public void setIdBar(int idBar) {
		this.idBar = idBar;
	}



}
