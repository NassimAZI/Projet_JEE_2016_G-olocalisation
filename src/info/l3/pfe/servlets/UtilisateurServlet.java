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

public class UtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doQuery(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doQuery(request,response);
	}
	private void doQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		GenericDAO gd=new GenericDAO();
		//partie qui gère les utilisateurs:
				if(request.getParameter("aff").equals("afficher") )
				{
					ArrayList<Object> utilisateur = new ArrayList<Object>();
					utilisateur = gd.selectAll("from Utilisateur");
					request.setAttribute("util",utilisateur);
					
					this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur.jsp").forward(request, response);
				}
				else
					
				if(request.getParameter("aff").equals("supprimer") )
				{
							System.out.println(request.getParameter("action"));
							Utilisateur cli = new Utilisateur();
							int id=0;
							 id= Integer.parseInt(request.getParameter("action"));
							 cli.setId(id);
							 Utilisateur C= (Utilisateur)gd.select(Utilisateur.class, cli.getId());
							 gd.delete(C);
							 this.getServletContext().getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
				}
				
				//Deconnexion
				if(request.getParameter("aff").equals("deconnexion") )
				{
					String resultat1;
					HttpSession session = request.getSession();
					session.removeAttribute("role");
					session.invalidate();
					resultat1="session fermée";
					System.out.println(resultat1);
					request.setAttribute("resultat1", resultat1);
					response.sendRedirect("Login.jsp");
				}
				if(request.getParameter("aff").equals("retour") )
			 	{
			 		this.getServletContext().getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
			 	}
			 	if(request.getParameter("aff").equals("add") )
			 	{
			 		this.getServletContext().getRequestDispatcher("/WEB-INF/CreerCompte.jsp").forward(request, response);
			 	}
			 	if(request.getParameter("aff").equals("caisse") )
			 	{
			 		this.getServletContext().getRequestDispatcher("/WEB-INF/Caisse.jsp").forward(request, response);
			 	}
				}
				
					
	}


