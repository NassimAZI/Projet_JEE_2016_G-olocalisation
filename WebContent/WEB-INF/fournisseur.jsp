<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/form.css">
<title>Fournisseur</title>
</head>
<body style="background-color: rgb(200, 200, 250);margin-left: 20%">
<c:choose>
		<c:when test="${(sessionScope.role=='admin')&&(sessionScope!=null)}">
<div class="tab">
	<table id="tab22">
		<tr id="tab1">
			<td><form action="<c:url value="/UtilisateurServlet"/>">
								<input type="HIDDEN" name="aff" value="deconnexion" /> <input
									type="submit" value="Deconnecter" id="inp" />
							</form></td>
		</tr>
		<tr id="tab22">
			<td><br />
				<table id="tab33">
					<tr>
						<td id="tab3" style="background:#edcef2 ; ">
							<p>id</p>
						</td>

						<td id="tab3" style="background:#edcef2 ; ">
							<p>Nom</p>
						</td>

						<td id="tab3" style="background:#edcef2 ; ">
							<p>prenom</p>
						</td>

						<td id="tab3" style="background:#edcef2 ; ">
							<p>Adresse</p>
						</td>
						<td id="tab3" style="background:#edcef2 ; ">
							<p>Spécialité</p>
						</td>

						<td id="tab3" style="background:#edcef2 ; ">
							<p>téléphone</p>
						</td>

						<td id="tab3" style="background:#edcef2 ; ">
							<p>Supprimer</p>
						</td>
						<td id="tab3" style="background:#edcef2 ; ">
							<p>Modifier</p>
						</td>
					</tr>
					<c:forEach items="${fourn}" var="fournisseur">
						<tr>
						<tr>
							<td id="tab3" style="background:#edcef2 ; ">
								<p>${fournisseur.id }</p>
							</td>
							<td id="tab3">
								<p>${fournisseur.nom }</p>
							</td>
							<td id="tab3">
								<p>${fournisseur.prenom }</p>
							</td>
							<td id="tab3">
								<p>${fournisseur.adresse}</p>
							</td>
							<td id="tab3">
								<p>${fournisseur.specialite}</p>
							</td>
							<td id="tab3">
								<p>${fournisseur.numtelephone}</p>
							</td>
							<td id="tab3">
								<form action="FournisseurServlet" method="get">
									<input type="HIDDEN" name="aff" value="supprimer" /> <input
										type="radio" name="action" value="${fournisseur.id}"
										id="radio"> <input type="submit" value="Envoie"
										id="cocher">
								</form>
							</td>
							<td id="tab3">
								<form action="FournisseurServlet" method="get">
									<input type="HIDDEN" name="aff" value="modifier" /> <input
										type="radio" name="action" value="${fournisseur.id}"
										id="radio"> <input type="submit" value="Mod"
										id="cocher">
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
				<table style="width: 100px; " id="bout">
					<tr>
					<td>
						<form action="FournisseurServlet" method="get">
									<p>
										<input type="HIDDEN" name="aff" value="add" />
										<input type="submit" value="Ajouter" id="inp">
									</p>
								</form>
				</td>
					<td>
				 				<form action="FournisseurServlet" method="get">
									<p>
										<input type="HIDDEN" name="aff" value="retour" />
										<input type="submit" value="Retour" id="inp" >
									</p>
								</form>
				</td>
						 </tr>
						</table>
			</td>
		</tr>
	</table>
</div>
</c:when>
<c:otherwise>
           <h1 style="color: Red;margin-left: 10%;">Session Invalide <br>
               <a href="erreur">connectez vous ici</a>
           </h1>
           
		</c:otherwise>
</c:choose>
</body>
</html>