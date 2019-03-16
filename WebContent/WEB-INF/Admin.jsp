<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/form.css">
<title>Administrateur</title>
</head>
<body
	style="background-color: rgb(200, 200, 250); margin-left: 20%; margin-top: 5%">
	<c:choose>
		<c:when test="${(sessionScope.role=='admin')&&(sessionScope!=null)}">
			<div class="tab">
				<table>
					<tr id="tab1">
						<td>
						<form action="<c:url value="/UtilisateurServlet"/>">
								<input type="HIDDEN" name="aff" value="deconnexion" /> <input
									type="submit" value="Deconnecter" id="inp" />
							</form></td>
					</tr>
					<tr id="tab22">
						<td>
							<!-- <c:if test="${!empty sessionScope.util2}">
			    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.util2.utl}</p>
</c:if>--> <br />
							<form action="<c:url value="ProduitServlet"/>">
								<input type="HIDDEN" name="aff" value="afficher" /> <input
									type="submit" value="Stock" id="inp" />
							</form> <br> <br>

							<form action="<c:url value="ClientServlet"/>">
								<input type="HIDDEN" name="aff" value="afficher" /> <input
									type="submit" value="Client" id="inp" />
							</form> <br> <br>

							<form action="<c:url value="/FournisseurServlet"/>">
								<input type="HIDDEN" name="aff" value="afficher" /> <input
									type="submit" value="Fournisseur" id="inp" />
							</form> <br> <br>

							<form action="<c:url value="/UtilisateurServlet"/>">
								<input type="HIDDEN" name="aff" value="afficher" /> <input
									type="submit" value="utilisateur" id="inp" />
							</form> <br> <br>
							<form action="<c:url value="/UtilisateurServlet"/>">
								<input type="HIDDEN" name="aff" value="caisse" /> <input
									type="submit" value="Caisse" id="inp" />
							</form> <br> <br>
						</td>
					</tr>
				</table>
			</div>
		</c:when>
		<c:otherwise>
			<h1 style="color: Red;margin-left: 10%;">
				Session Invalide <br> <a href="erreur">connectez vous ici</a>
			</h1>

		</c:otherwise>
	</c:choose>
</body>
</html>