package info.l3.pfe.dto;

public class Fournisseur {
	
	private int id;
	private String nom;
	private String prenom;
	private String specialite;
	private String numtelephone;
	private String adresse;
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getNumtelephone() {
		return numtelephone;
	}
	public void setNumtelephone(String numtelephone) {
		this.numtelephone = numtelephone;
	}
	
	
	
}
