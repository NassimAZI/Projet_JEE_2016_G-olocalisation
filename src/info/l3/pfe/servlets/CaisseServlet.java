package info.l3.pfe.servlets;

import info.l3.pfe.dao.GenericDAO;
import info.l3.pfe.dto.Produit;
import info.l3.pfe.utils.DBConnectionFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

public class CaisseServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	/**
* 
*/
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CaisseServlet() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
     try{
		if (request.getSession().getAttribute("role").equals("utl")
				|| request.getSession().getAttribute("role").equals("admin")) {
			//
			if (request.getServletPath().equals("/validerVente")) {
				boolean vrai = false;
				String valeur = request.getParameter("valeur1");
				String reponse = "avant";
				String[] valeurs = valeur.split("&");
				Session session = DBConnectionFactory.openSession();
				Produit toUpdate = null;
				//
				GenericDAO dao = new GenericDAO();
				ArrayList<Object> goods = dao.selectAll("From Produit");
				for (int i = 0; i < goods.size(); i++) {
					Produit article = (Produit) goods.get(i);
					if (article.getCodeBarre().equals(valeurs[0])) {
						vrai = article.getQuantite() >= Integer
								.parseInt(valeurs[1]);
						if (vrai) {
							toUpdate = article;
							toUpdate.setQuantite(article.getQuantite()
									- Integer.parseInt(valeurs[1]));

						} else
							reponse = "quantite invalide";

					}
				}
				//
				if (vrai) {
					session = DBConnectionFactory.openSession();
					try {
						session.beginTransaction();
						session.update(toUpdate);
						session.getTransaction().commit();
						reponse = "Transaction validée";
					} catch (Exception e) {
						reponse = "Transaction non validée";
					} finally {
						session.close();
					}
				}
				//
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter out = response.getWriter();
				out.write(reponse);
			} else {
				//

				String libelle = "";
				String prix = "";
				String valeur = request.getParameter("valeur");
				String produitId = valeur;
				//
				GenericDAO dao = new GenericDAO();
				ArrayList<Object> goods = dao.selectAll("From Produit");
				for (int i = 0; i < goods.size(); i++) {
					Produit article = (Produit) goods.get(i);
					if (article.getCodeBarre().equals(produitId)) {
						libelle = article.getLibele();
						prix = String.valueOf(article.getPrixuni());
						break;
					} else {
						prix = "0.0";
						libelle = "erreur";
					}
				}
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter out = response.getWriter();
				out.write(libelle + " et " + prix);

			}
		}else{
			request.getRequestDispatcher("WEB-INF/erreur.jsp").forward(request, response);
		}
	}catch(NullPointerException e){
		request.getRequestDispatcher("WEB-INF/erreur.jsp").forward(request, response);
		
	}
     }
}