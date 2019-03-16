package info.l3.pfe.dto;

import java.util.Date;

public class Produit {

	private int id;
	private String codeBarre;
	private String libele;
	private String categorie;
	private int quantite;
	private Date dateperemption;
	private double prixuni;
	private int quantiteMin;
	
	
	public int getQuantiteMin() {
		return quantiteMin;
	}
	public void setQuantiteMin(int quantiteMin) {
		this.quantiteMin = quantiteMin;
	}
	public Produit()
	{
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCodeBarre() {
		return codeBarre;
	}
	public void setCodeBarre(String codebarre) {
		this.codeBarre = codebarre;
	}
	public String getLibele() {
		return libele;
	}
	public void setLibele(String libele) {
		this.libele = libele;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Date getDateperemption() {
		return dateperemption;
	}
	public void setDateperemption(Date dateperemption) {
		this.dateperemption = dateperemption;
	}
	public double getPrixuni() {
		return prixuni;
	}
	public void setPrixuni(double prixuni) {
		this.prixuni = prixuni;
	}
	
	}
