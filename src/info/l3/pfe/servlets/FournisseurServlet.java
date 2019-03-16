package info.l3.pfe.servlets;

import info.l3.pfe.dao.GenericDAO;
import info.l3.pfe.dto.Fournisseur;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FournisseurServlet extends HttpServlet {
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
		try{
			if (request.getSession().getAttribute("role").equals("utl")
					|| request.getSession().getAttribute("role").equals("admin")) {
				
		Map<String, String> erreurs = new HashMap<String, String>();
		GenericDAO gd = new GenericDAO();

		// AFFICHAGE
		if (request.getParameter("aff").equals("afficher")) {
			ArrayList<Object> fournisseur = new ArrayList<Object>();
			fournisseur = gd.selectAll("from Fournisseur");
			request.setAttribute("fourn", fournisseur);
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/fournisseur.jsp")
					.forward(request, response);
		} else {
			String Id = request.getParameter("id");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String adresse = request.getParameter("adresse");
			String specialite = request.getParameter("specialite");
			String telephone = request.getParameter("teleph");
			String resultat = null;
			// boolean rep;

			// AJOUT
			if (request.getParameter("aff").equals("ajouter")) {
				Fournisseur fournisseur = new Fournisseur();
				if (nom.length() >= 3)
					fournisseur.setNom(nom);
				else
					erreurs.put("nom",
							"Veuillez entrer une chaine de longueur superieure ou egale a 3");
				if (prenom.length() >= 3)
					fournisseur.setPrenom(prenom);
				else
					erreurs.put("prenom",
							"Veuillez entrer une chaine de longueur superieure ou egale a 3");
				if (adresse.length() >= 3)
					fournisseur.setAdresse(adresse);
				else
					erreurs.put("adresse",
							"Veuillez entrer une chaine de longueur superieure ou egale a 3");
				if (adresse.length() >= 3)
					fournisseur.setSpecialite(specialite);
				else
					erreurs.put("specialite",
							"Veuillez entrer une chaine de longueur superieure ou egale a 3");
				if (telephone != null) {
					if (!telephone.matches("^\\d+$"))
						erreurs.put("telephone",
								"Le numéro de téléphone doit uniquement contenir des chiffres.");
					else {
						if (telephone.length() >= 10)
							fournisseur.setNumtelephone(telephone);
						else
							erreurs.put("telephone",
									"le numero de telephone doit contenir au moins 10 chiffres");
					}
				}

				if (existF(telephone))
					erreurs.put("tele", "Le fournisseur existe déjà");
				if (erreurs.isEmpty()) {
					gd.insert(fournisseur);
					resultat = "Fournisseur ajouté avec succé";
				} else
					resultat = "Le fournisseur n a pa eté ajouté";
				request.setAttribute("resultat", resultat);
				request.setAttribute("erreurs", erreurs);
				this.getServletContext()
						.getRequestDispatcher("/WEB-INF/ajouterFournisseur.jsp")
						.forward(request, response);
			}
			// MODIFICATION
			if (request.getParameter("aff").equals("update")) {
				Fournisseur fournisseur = new Fournisseur();

				int com = 0;
				com = Integer.parseInt(Id);
				if (com != 0) {
					fournisseur.setId(com);
				} else
					erreurs.put("id",
							"Veuillez entrer une entier superieure a zero");

				if (nom.length() >= 3) {
					fournisseur.setNom(nom);
				} else {
					erreurs.put("nom",
							"Veuillez entrer une chaine de longueur superieure ou egale a 3");
				}

				if (prenom.length() >= 3) {
					fournisseur.setPrenom(prenom);
				} else {
					erreurs.put("prenom",
							"Veuillez entrer une chaine de longueur superieure ou egale a 3");
				}

				if (adresse.length() >= 3) {
					fournisseur.setAdresse(adresse);
				} else {
					erreurs.put("adresse",
							"Veuillez entrer une chaine de longueur superieure ou egale a 3");
				}

				if (adresse.length() >= 3) {
					fournisseur.setSpecialite(specialite);
				} else {
					erreurs.put("specialite",
							"Veuillez entrer une chaine de longueur superieure ou egale a 3");
				}

				if (telephone.length() >= 10) {
					fournisseur.setNumtelephone(telephone);
				} else {
					erreurs.put("telephone",
							"Le numero de telephone doit contenir au moins 10 chiffres");
				}

				if (erreurs.isEmpty()) {

					if (exist(fournisseur)) {
						gd.update(fournisseur);
						resultat = "Fournisseur modifié avec succé";
					} else if (!exist(fournisseur))
						resultat = "Vous demandez de modifier un fournisseur qui n existe pas";
				} else
					resultat = "Le fournisseur n a pa eté modifié";

				request.setAttribute("resultat", resultat);
				request.setAttribute("erreurs", erreurs);
				this.getServletContext()
						.getRequestDispatcher(
								"/WEB-INF/modifierFournisseur.jsp")
						.forward(request, response);
			}

			// SUPPRESSION
			if (request.getParameter("aff").equals("supprimer")) {
				System.out.println(request.getParameter("action"));
				Fournisseur cli = new Fournisseur();
				int id = 0;
				id = Integer.parseInt(request.getParameter("action"));
				cli.setId(id);
				Fournisseur C = (Fournisseur) gd.select(Fournisseur.class,
						cli.getId());
				gd.delete(C);
				this.getServletContext()
						.getRequestDispatcher("/WEB-INF/Admin.jsp")
						.forward(request, response);
			}
			if (request.getParameter("aff").equals("modifier")) {
				Fournisseur cli = new Fournisseur();
				int id = 0;
				id = Integer.parseInt(request.getParameter("action"));
				cli.setId(id);
				Fournisseur C = (Fournisseur) gd.select(Fournisseur.class,
						cli.getId());
				request.setAttribute("fournisseur", C);
				this.getServletContext()
						.getRequestDispatcher(
								"/WEB-INF/modifierFournisseur.jsp")
						.forward(request, response);
			}

		}
		if (request.getParameter("aff").equals("retour")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/Admin.jsp")
					.forward(request, response);
		}
		if (request.getParameter("aff").equals("add")) {
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/ajouterFournisseur.jsp")
					.forward(request, response);
		}
		}else{
			request.getRequestDispatcher("WEB-INF/erreur.jsp").forward(request, response);
		}
	}catch(NullPointerException e){
		request.getRequestDispatcher("WEB-INF/erreur.jsp").forward(request, response);
		
	}
     

	}

	public boolean exist(Fournisseur four) {
		GenericDAO dao = new GenericDAO();
		Fournisseur cl = new Fournisseur();
		cl = (Fournisseur) dao.select(Fournisseur.class, four.getId());
		if (cl != null)
			return true;
		else
			return false;
	}

	public boolean existF(String telephone) {
		GenericDAO dao = new GenericDAO();
		ArrayList<Object> cli = new ArrayList<Object>();
		Fournisseur four = new Fournisseur();
		boolean bool = false;
		int i = 0;
		cli = dao.selectAll("from Fournisseur");
		// for (int i = 0; i < cli.size() ; i++)
		while (i < cli.size() && bool == false) {
			four = (Fournisseur) cli.get(i);
			if (four.getNumtelephone().equals(telephone)) {
				bool = true;
			}
			i++;
		}

		return bool;
	}
}
