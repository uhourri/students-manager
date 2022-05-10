package ma.fsdm.wisd.database.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.fsdm.wisd.database.entities.Departement;
import ma.fsdm.wisd.database.entities.Etudiant;

public class Metier implements IMetier {

	@Override
	public void ajouterDepartement(Departement departement) {
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "INSERT INTO departement(nom_dept) VALUES(?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, departement.getNomDept());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Departement recupererDepartement(int codeDept) {
		Departement departement = new Departement();
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM departement WHERE code_dept = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, codeDept);
			ResultSet result = statement.executeQuery();
			result.next();
			departement.setCodeDept(codeDept);
			departement.setNomDept(result.getString("NOM_DEPT"));
			//departement.setEtudiants(recupererEtudiants(codeDept));	stackoverflow error
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return departement;
	}

	@Override
	public void supprimerDepartement(int codeDept) {
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "DELETE FROM departement WHERE code_dept = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, codeDept);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifierDepartement(int codeDept, String nomDept) {
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "UPDATE departement SET nom_dept = ? WHERE code_dept = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nomDept);
			statement.setInt(2, codeDept);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ajouterEtudiant(Etudiant etudiant, int codeDept) {
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "INSERT INTO etudiant(nom, prenom, code_dept) VALUES(?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, etudiant.getNom());
			statement.setString(2, etudiant.getPrenom());
			statement.setInt(3, codeDept);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Etudiant recupererEtudiant(int codeEtudiant) {
		Etudiant etudiant = new Etudiant();
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM etudiant WHERE code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, codeEtudiant);
			ResultSet result = statement.executeQuery();
			result.next();
			etudiant.setCode(codeEtudiant);
			etudiant.setNom(result.getString("NOM"));
			etudiant.setPrenom(result.getString("PRENOM"));
			Departement departement = recupererDepartement(result.getInt("CODE_DEPT"));
			etudiant.setDept(departement);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return etudiant;
	}

	@Override
	public void supprimerEtudiant(int codeEtudiant) {
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "DELETE FROM etudiant WHERE code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, codeEtudiant);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifierEtudiant(int codeEtudiant, String nomEtudiant, String prenomEtudiant, int codeDept) {
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "UPDATE etudiant SET nom = ?, prenom = ?, code_dept = ? WHERE code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nomEtudiant);
			statement.setString(2, prenomEtudiant);
			statement.setInt(3, codeDept);
			statement.setInt(4, codeEtudiant);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Etudiant> recupererEtudiantsParMC(String mc) {
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
		
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM etudiant WHERE nom LIKE ? OR prenom LIKE ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%"+mc+"%");
			statement.setString(2, "%"+mc+"%");
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Etudiant etudiant = new Etudiant();
				etudiant.setCode(result.getInt("CODE"));
				etudiant.setNom(result.getString("NOM"));
				etudiant.setPrenom(result.getString("PRENOM"));
				int codeDept = result.getInt("CODE_DEPT");
				etudiant.setDept(recupererDepartement(codeDept));				
				etudiants.add(etudiant);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return etudiants;
	}

	@Override
	public List<Etudiant> recupererEtudiants(int codeDept) {
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
		
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM etudiant WHERE code_dept = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, codeDept);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Etudiant etudiant = new Etudiant();
				etudiant.setCode(result.getInt("CODE"));
				etudiant.setNom(result.getString("NOM"));
				etudiant.setPrenom(result.getString("PRENOM"));
				etudiant.setDept(recupererDepartement(codeDept));	
				etudiants.add(etudiant);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return etudiants;
	}

	@Override
	public List<Etudiant> recupererEtudiants() {
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
		
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM etudiant";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Etudiant etudiant = new Etudiant();
				etudiant.setCode(result.getInt("CODE"));
				etudiant.setNom(result.getString("NOM"));
				etudiant.setPrenom(result.getString("PRENOM"));
				int codeDept = result.getInt("CODE_DEPT");
				etudiant.setDept(recupererDepartement(codeDept));	
				etudiants.add(etudiant);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return etudiants;
	}

	@Override
	public List<Departement> recupererDepartements() {
		List<Departement> departements = new ArrayList<Departement>();
		
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM departement";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Departement departement = new Departement();
				departement.setCodeDept(result.getInt("CODE_DEPT"));
				departement.setNomDept(result.getString("NOM_DEPT"));
				departements.add(departement);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return departements;
	}

	@Override
	public Departement recupererDepartementParNom(String nomDept) {
		Departement departement = new Departement();
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM departement WHERE nom_dept = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nomDept);
			ResultSet result = statement.executeQuery();
			result.next();
			departement.setCodeDept(result.getInt("CODE_DEPT"));
			departement.setNomDept(nomDept);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return departement;
	}
	
}
