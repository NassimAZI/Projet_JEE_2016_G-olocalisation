package info.l3.pfe.servlets;

import info.l3.pfe.dao.GenericDAO;
import info.l3.pfe.dto.Produit;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProduitServlet extends HttpServlet {
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
				Map<String, String> erreurs = new HashMap<String, String>();
				GenericDAO gd = new GenericDAO();

				// AFFICHAGE
				if (request.getParameter("aff").equals("afficher")) {
					ArrayList<Object> produit = new ArrayList<Object>();
					produit = gd.selectAll("from Produit");
					request.setAttribute("prod", produit);
					this.getServletContext()
							.getRequestDispatcher("/WEB-INF/produit.jsp")
							.forward(request, response);
				} else {
					String Id = request.getParameter("id");
					String codebar = request.getParameter("codebarre");
					String categorie = request.getParameter("categorie");
					String libele = request.getParameter("libele");
					String prix = request.getParameter("prixuni");
					String quant = request.getParameter("quantite");
					String quantMin = request.getParameter("prodmin");
					String resultat = null;
					// boolean rep;

					// AJOUT
					if (request.getParameter("aff").equals("ajouter")) {

						Produit produit = new Produit();
						if (categorie.length() >= 3)
							produit.setCategorie(categorie);
						else
							erreurs.put("categorie",
									"Veuillez entrer une chaine de longueur superieure a 3");

						if (codebar.length() == 13)
							produit.setCodeBarre(codebar);

						else
							erreurs.put("CodeBarre",
									"Le code barre doit contenir 13 chiffre");

						if (libele.length() >= 3)
							produit.setLibele(libele);
						else
							erreurs.put("libele",
									"Veuillez entrer une chaine de longueur superieure ou egale a 3");

						double pri = 0;
						pri = Double.parseDouble(prix);
						if (pri != 0)
							produit.setPrixuni(pri);
						else
							erreurs.put("prixuni",
									"Veuillez entrer une valeur superieure a 0");

						int qn = 0;
						qn = Integer.parseInt(quant);
						if (qn != 0)
							produit.setQuantite(qn);
						else
							erreurs.put("quantite",
									"Veuillez entrer une valeur superieure a 0");

						//

						int qn_min = 0;
						if (quantMin != null) {
							qn_min = Integer.parseInt(quantMin);
							if (qn_min != 0)
								produit.setQuantiteMin(qn_min);
						} else
							erreurs.put("quantite",
									"Veuillez entrer une valeur superieure a 0");
						//

						DateFormat formatter = null;
						Date convertedDate = null;
						String dd_MM_YY = request.getParameter("datpremp");
						if (!dd_MM_YY.equals("")) {

							formatter = new SimpleDateFormat("dd-MM-yyyy");
							convertedDate = (Date) formatter.parse(dd_MM_YY);
							produit.setDateperemption(convertedDate);
						}

						if (existC(codebar))
							erreurs.put("CodeBarre", "Le produit existe deja");
						if (erreurs.isEmpty()) {
							gd.insert(produit);
							resultat = "Produit ajouté avec succé";
						} else
							resultat = "Le produit n a pa eté ajouté";

						request.setAttribute("resultat", resultat);
						request.setAttribute("erreurs", erreurs);
						this.getServletContext()
								.getRequestDispatcher(
										"/WEB-INF/ajouterProduit.jsp")
								.forward(request, response);
					}

					// RECUPERER L'OBJET A MODIFIER ET L'AFFICHER DANS LA JSP
					// MODIFIER
					if (request.getParameter("aff").equals("modifier")) {
						Produit cli = new Produit();
						int id = 0;
						id = Integer.parseInt(request.getParameter("action"));
						cli.setId(id);
						Produit C = (Produit) gd.select(Produit.class,
								cli.getId());
						request.setAttribute("produit", C);
						this.getServletContext()
								.getRequestDispatcher(
										"/WEB-INF/modifierProduit.jsp")
								.forward(request, response);
					}

					// MODIFICATION
					if (request.getParameter("aff").equals("update")) {
						Produit produit = new Produit();
						int com = 0;
						com = Integer.parseInt(Id);
						if (com != 0)
							produit.setId(com);
						else
							erreurs.put("id",
									"Veuillez entrer une entier superieure a zero");

						if (codebar != null) {
							if (!codebar.matches("^\\d+$"))
								erreurs.put("CodeBarre",
										"Le code barre doit uniquement contenir des chiffres.");
							else if (codebar.length() == 13)
								produit.setCodeBarre(codebar);
							else
								erreurs.put("codebarre",
										"Le code barra doit contenir 13 chiffre");

							if (categorie.length() >= 3)
								produit.setCategorie(categorie);
							else
								erreurs.put("categorie",
										"Veuillez entrer une chaine de longueur superieure a 3");

							if (libele.length() >= 3)
								produit.setLibele(libele);
							else
								erreurs.put("libele",
										"Veuillez entrer une chaine de longueur superieure a 3");

							double pri = 0;
							pri = Double.parseDouble(prix);
							produit.setPrixuni(pri);
							if (pri == 0)
								erreurs.put("prixuni",
										"Veuillez entrer une valeur superieure a 0");

							int qn = 0;
							qn = Integer.parseInt(quant);
							if (qn != 0)
								produit.setQuantite(qn);
							else
								erreurs.put("quantite",
										"Veuillez entrer une valeur superieure a 0");

							/*
							 * int min =0; min=Integer.parseInt(quantMin);
							 * if(min!=0) produit.setMinProduit(min); else
							 * erreurs.put("quantiteMin",
							 * "Veuillez entrer une valeur superieure a 0");
							 */
							DateFormat formatter = null;
							Date convertedDate = null;

							String dd_MM_YY = request.getParameter("datpremp");
							if (dd_MM_YY != null) {
								formatter = new SimpleDateFormat("dd-MM-yyyy");
								convertedDate = (Date) formatter
										.parse(dd_MM_YY);

								if (convertedDate != null)
									produit.setDateperemption(convertedDate);
								else
									erreurs.put("date",
											"Veuillez entrer une date valide dd-MM-yyyy");

							}

							if (erreurs.isEmpty()) {
								System.out.println(exist(produit));
								if (exist(produit)) {
									gd.update(produit);
									resultat = "Produit modifié avec succé";
								} else if (!exist(produit))
									resultat = "Vous demandez de modifier un produit qui n existe pas";
							} else
								resultat = "Le produit n a pa eté modifié";

							request.setAttribute("resultat", resultat);
							request.setAttribute("erreurs", erreurs);
							this.getServletContext()
									.getRequestDispatcher(
											"/WEB-INF/modifierProduit.jsp")
									.forward(request, response);
						}
					}

					// SUPPRESSION
					if (request.getParameter("aff").equals("supprimer")) {
						System.out.println(request.getParameter("action"));
						Produit cli = new Produit();
						int id = 0;
						id = Integer.parseInt(request.getParameter("action"));
						cli.setId(id);
						Produit C = (Produit) gd.select(Produit.class,
								cli.getId());
						gd.delete(C);
						this.getServletContext()
								.getRequestDispatcher("/WEB-INF/Admin.jsp")
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
							.getRequestDispatcher("/WEB-INF/ajouterProduit.jsp")
							.forward(request, response);
				}
				if (request.getParameter("aff").equals("log")) {
					String resultat1;
					HttpSession session = request.getSession();
					session.removeAttribute("role");
					session.invalidate();
					resultat1 = "session fermée";
					System.out.println(resultat1);
					request.setAttribute("resultat1", resultat1);
					response.sendRedirect("Login.jsp");
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

	// ELLE VERIFIE SI L'ID DU PRODUIT A MODIFIER EXISTE
	public boolean exist(Produit prod) {
		GenericDAO dao = new GenericDAO();
		Produit p = new Produit();
		p = (Produit) dao.select(Produit.class, prod.getId());
		if (p != null)
			return true;
		else
			return false;
	}

	/*
	 * Verifie si le produit a ajouter n'existe pas deja dans la base de données
	 */
	public boolean existC(String codebarre) {
		GenericDAO dao = new GenericDAO();
		ArrayList<Object> cli = new ArrayList<Object>();
		Produit four = new Produit();
		boolean bool = false;
		int i = 0;
		cli = dao.selectAll("from Produit");
		// for (int i = 0; i < cli.size() ; i++)
		while (i < cli.size() && bool == false) {
			four = (Produit) cli.get(i);
			if (four.getCodeBarre().equals(codebarre)) {
				bool = true;
			}
			i++;
		}

		return bool;
	}
}
