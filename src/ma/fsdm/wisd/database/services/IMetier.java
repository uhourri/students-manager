package ma.fsdm.wisd.database.services;

import java.util.List;

import ma.fsdm.wisd.database.entities.Departement;
import ma.fsdm.wisd.database.entities.Etudiant;

public interface IMetier {
	
	public void ajouterDepartement(Departement departement);
	public Departement recupererDepartement(int codeDept);
	public void supprimerDepartement(int codeDept);
	public void modifierDepartement(int codeDept, String nomDept);
	
	public void ajouterEtudiant(Etudiant etudiant, int codeDept);
	public Etudiant recupererEtudiant(int codeEtudiant);
	public void supprimerEtudiant(int codeEtudiant);
	public void modifierEtudiant(int codeEtudiant, String nomEtudiant, String prenomEtudiant, int codeDept);
	
	public List<Etudiant> recupererEtudiantsParMC(String mc);
	public List<Etudiant> recupererEtudiants(int codeDept);
	public List<Etudiant> recupererEtudiants();
	
	public List<Departement> recupererDepartements();
	public Departement recupererDepartementParNom(String nomDept);
	
}

