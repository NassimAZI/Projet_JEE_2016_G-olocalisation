package info.l3.pfe.servlets;

import info.l3.pfe.dao.GenericDAO;
import info.l3.pfe.dto.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doQuery(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doQuery(request, response);
	}

	private void doQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		GenericDAO gd = new GenericDAO();
		String resultat = "";

		// Partie qui gère la création de comptes:
		
				
				if ("/CreerCompte".equals(path)) {
					Utilisateur util = new Utilisateur();
					util.setUtl(request.getParameter("utl"));
					util.setMdp(request.getParameter("password"));
					util.setRole(request.getParameter("role"));

					ArrayList<Object> cli = new ArrayList<Object>();
					Utilisateur four = new Utilisateur();
					cli = gd.selectAll("from Utilisateur");
					int i = 0;
					boolean bool = false;
					while (i < cli.size() && bool == false) {
						four = (Utilisateur) cli.get(i);
						if ((four.getUtl().equals(util.getUtl()) && (four
								.getMdp().equals(util.getMdp())))) {
							resultat = "l tilisateur existe deja";
							request.setAttribute("resultat", resultat);
							bool = true;
							this.getServletContext()
									.getRequestDispatcher(
											"/WEB-INF/CreerCompte.jsp")
									.forward(request, response);
						}
						i++;
					}
					if (bool == false) {
						if (util.getRole().equals("utl")
								|| util.getRole().equals("admin")) {
							resultat = "l tilisateur crée avec succé";
							gd.insert(util);
						} else
							resultat = "role doit etre utl ou admin";
						request.setAttribute("resultat", resultat);
						this.getServletContext()
								.getRequestDispatcher(
										"/WEB-INF/CreerCompte.jsp")
								.forward(request, response);

					}

				}
			

		// Partie qui gère le Login:
		if ("/login".equals(path)) {
			Utilisateur util2 = new Utilisateur();
			util2.setUtl(request.getParameter("utl"));
			util2.setMdp(request.getParameter("password"));

			HttpSession session = request.getSession();

			ArrayList<Object> cli = new ArrayList<Object>();
			Utilisateur four = new Utilisateur();
			cli = gd.selectAll("from Utilisateur");
			int i = 0;
			boolean bool = false;
			while (i < cli.size() && bool == false) {
				four = (Utilisateur) cli.get(i);
				if ((four.getUtl().equals(util2.getUtl()) && (four.getMdp()
						.equals(util2.getMdp())))) {
					if (four.getRole().equals("utl")) {
						bool = true;
						session.setAttribute("role", "utl");
						session.setAttribute("util2", util2);
						if (session.getAttribute("util2") != null) {
							this.getServletContext()
									.getRequestDispatcher("/WEB-INF/Caisse.jsp")
									.forward(request, response);
						}
					} else if (four.getRole().equals("admin")) {
						bool = true;
						session.setAttribute("role", "admin");
						session.setAttribute("util2", util2);
						if (session.getAttribute("util2") != null) {

							this.getServletContext()
									.getRequestDispatcher("/WEB-INF/Admin.jsp")
									.forward(request, response);
						}
					}

				}
				i++;
			}
			if (bool == false) {
				resultat = "mot de passe ou nom de l utilisateur incorrecte";
				request.setAttribute("resultat", resultat);
				this.getServletContext().getRequestDispatcher("/Login.jsp")
						.forward(request, response);

			}

		}

	}
}