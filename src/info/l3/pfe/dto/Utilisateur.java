package info.l3.pfe.dto;

public class Utilisateur {

	private int id;
	private String utl;
	private String mdp;
	private String role;
	public Utilisateur()
	{
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUtl() {
		return utl;
	}
	public void setUtl(String utl) {
		this.utl = utl;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
