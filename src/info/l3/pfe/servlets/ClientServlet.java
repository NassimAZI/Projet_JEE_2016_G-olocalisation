package info.l3.pfe.servlets;

import info.l3.pfe.dao.GenericDAO;
import info.l3.pfe.dto.Client;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doQuery(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doQuery(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void doQuery(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			ServletException, IOException {

		try {
			if (request.getSession().getAttribute("role").equals("utl")
					|| request.getSession().getAttribute("role")
							.equals("admin")) {
				//

				Map<String, String> erreurs = new HashMap<String, String>();
				GenericDAO gd = new GenericDAO();

				// AFFICHAGE
				if (request.getParameter("aff").equals("afficher")) {
					ArrayList<Object> client = new ArrayList<Object>();
					client = gd.selectAll("from Client");
					request.setAttribute("clients", client);
					this.getServletContext()
							.getRequestDispatcher("/WEB-INF/client.jsp")
							.forward(request, response);
				} else {
					String Id = request.getParameter("id");
					String nom = request.getParameter("nom");
					String prenom = request.getParameter("prenom");
					String numcartefid = request.getParameter("numcartefid");
					String numtelephone = request.getParameter("numtelephone");
					String solde = request.getParameter("solde");
					String credit = request.getParameter("credit");
					String adresse = request.getParameter("adresse");
					String resultat = null;
					boolean rep;

					// AJOUT
					if (request.getParameter("aff").equals("ajouter")) {
						Client client = new Client();
						if (nom.length() >= 3)
							client.setNom(nom);
						else
							erreurs.put("nom",
									"Veuillez entrer une chaine de longueur superieure ou egale a 3");
						if (prenom.length() >= 3)
							client.setPrenom(prenom);
						else
							erreurs.put("prenom",
									"Veuillez entrer une chaine de longueur superieure ou egale a 3");
						if (numcartefid.length() >= 2)
							client.setNumcartefid(numcartefid);
						else
							erreurs.put("numcartefid",
									"Le numero doit contenir au moins seux chiffres");
						if (numtelephone != null) {
							if (!numtelephone.matches("^\\d+$"))
								erreurs.put("numtelephone",
										"Le numéro de téléphone doit uniquement contenir des chiffres.");
							else {
								if (numtelephone.length() >= 10)
									client.setNumtelephone(numtelephone);
								else
									erreurs.put("numtelephone",
											"le numero de telephone doit contenir au moins 10 chiffres");
							}
						}
						if (adresse.length() >= 3)
							client.setAdresse(nom);
						else
							erreurs.put("adresse",
									"Veuillez entrer une chaine de longueur superieure ou egale a 3");
						client.setSolde(solde);
						client.setCredit(credit);

						if (existF(client.getNumcartefid()))
							erreurs.put("client", "le client existe dejai");
						if (erreurs.isEmpty()) {
							gd.insert(client);
							resultat = "Client ajouté avec succé";
						} else
							resultat = "Le client n a pa eté ajouté";

						request.setAttribute("resultat", resultat);
						request.setAttribute("erreurs", erreurs);
						this.getServletContext()
								.getRequestDispatcher(
										"/WEB-INF/ajouterClient.jsp")
								.forward(request, response);
					}

					// SUPPRESSION
					if (request.getParameter("aff").equals("supprimer")) {
						Client cli = new Client();
						int id = 0;
						id = Integer.parseInt(request.getParameter("action"));
						cli.setId(id);
						Client C = (Client) gd
								.select(Client.class, cli.getId());
						gd.delete(C);
						this.getServletContext()
								.getRequestDispatcher("/WEB-INF/Admin.jsp")
								.forward(request, response);
					}

					// RECUPERER LE CLIENT A MODIFIER
					if (request.getParameter("aff").equals("modifier")) {
						Client cli = new Client();
						int id = 0;
						id = Integer.parseInt(request.getParameter("action"));
						cli.setId(id);
						Client C = (Client) gd
								.select(Client.class, cli.getId());
						request.setAttribute("client", C);
						this.getServletContext()
								.getRequestDispatcher(
										"/WEB-INF/modifierClient.jsp")
								.forward(request, response);
					}

					// MODIFICATION
					if (request.getParameter("aff").equals("update")) {
						Client client = new Client();
						int com = 0;
						com = Integer.parseInt(Id);
						if (com != 0)
							client.setId(com);
						else
							erreurs.put("id",
									"Veuillez entrer une entier superieure a zero");
						if (nom.length() >= 3)
							client.setNom(nom);
						else
							erreurs.put("nom",
									"Veuillez entrer une chaine de longueur superieure ou egale a 3");
						if (prenom.length() >= 3)
							client.setPrenom(prenom);
						else
							erreurs.put("prenom",
									"Veuillez entrer une chaine de longueur superieure ou egale a 3");

						if (numcartefid.length() >= 2)
							client.setNumcartefid(numcartefid);
						else
							erreurs.put("numcartefid",
									"Le numero doit contenir au moins seux chiffres");
						if (numtelephone.length() >= 10)
							client.setNumtelephone(numtelephone);
						else
							erreurs.put("numtelephone",
									"le numero de telephone doit contenir au moins 10 chiffres");
						client.setSolde(solde);
						client.setCredit(credit);
						if (adresse.length() >= 3)
							client.setAdresse(nom);
						else
							erreurs.put("adresse",
									"Veuillez entrer une chaine de longueur superieure ou egale a 3");
						if (erreurs.isEmpty()) {
							rep = exist(client);
							if (rep == true) {
								gd.update(client);
								resultat = "Client modifié avec succé";
							} else if (rep == false)
								resultat = "Vous demandez de modifier un client qui n existe pas";
						} else
							resultat = "Le client n a pa eté modifié";

						request.setAttribute("resultat", resultat);
						request.setAttribute("erreurs", erreurs);
						this.getServletContext()
								.getRequestDispatcher(
										"/WEB-INF/modifierClient.jsp")
								.forward(request, response);
					}

				}
				if (request.getParameter("aff").equals("retour")) {
					this.getServletContext()
							.getRequestDispatcher("/WEB-INF/Admin.jsp")
							.forward(request, response);
				}
				if (request.getParameter("aff").equals("add")) {
					this.getServletContext()
							.getRequestDispatcher("/WEB-INF/ajouterClient.jsp")
							.forward(request, response);
				}
			} else {
				request.getRequestDispatcher("WEB-INF/erreur.jsp").forward(
						request, response);
			}
		} catch (NullPointerException e) {
			request.getRequestDispatcher("WEB-INF/erreur.jsp").forward(request,
					response);

		}
	}

	public boolean exist(Client cli) {
		GenericDAO dao = new GenericDAO();
		Client cl = new Client();
		cl = (Client) dao.select(Client.class, cli.getId());
		if (cl != null)
			return true;
		else
			return false;
	}

	public boolean existF(String numcartefid) {
		GenericDAO dao = new GenericDAO();
		ArrayList<Object> cli = new ArrayList<Object>();
		Client cle = new Client();
		boolean bool = false;
		int i = 0;
		cli = dao.selectAll("from Client");
		// for (int i = 0; i < cli.size() ; i++)
		while (i < cli.size() && bool == false) {
			cle = (Client) cli.get(i);
			if (cle.getNumcartefid().equals(numcartefid)) {
				bool = true;
			}
			i++;
		}
		return bool;
	}
}
