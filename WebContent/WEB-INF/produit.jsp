<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/form.css">
<title>Produit</title>
</head>
<body
	style="background-color: rgb(200, 200, 250); margin-left: 20%; margin-top: 5%;">
	<c:choose>
		<c:when test="${(sessionScope.role=='admin')}">
			<div class="tab">
				<table id="tab22">
					<tr id="tab1">
						<td id="tab1"><form action="<c:url value="/UtilisateurServlet"/>">
								<input type="HIDDEN" name="aff" value="deconnexion" /> <input
									type="submit" value="Deconnecter" id="inp" />
							</form></td>
					</tr>

					<tr id="tab22">
						<td><br />
							<table id="tab33">
								<tr>
									<td id="tab3" style="background: #edcef2;">
										<p>Id</p>
									</td>

									<td id="tab3" style="background: #edcef2;">
										<p>Code Barre</p>
									</td>

									<td id="tab3" style="background: #edcef2;">
										<p>Catégorie</p>
									</td>

									<td id="tab3" style="background: #edcef2;">
										<p>Libelle</p>
									</td>

									<td id="tab3" style="background: #edcef2;">
										<p>Prix Unitaire</p>
									</td>

									<td id="tab3" style="background: #edcef2;">
										<p>quantité</p>
									</td>

									<td id="tab3" style="background: #edcef2;">
										<p>date Peremption</p>
									</td>

									<td id="tab3" style="background: #edcef2;">
										<p>Supprimer</p>
									</td>
									<td id="tab3" style="background: #edcef2;">
										<p>Modifier</p>
									</td>
								</tr>
								<c:forEach items="${prod}" var="produit">
									<tr>
										<td id="tab3" style="background: #edcef2">
											<p>${produit.id}</p>

										</td>
										<td id="tab3">
											<p>${produit.codeBarre}</p>
										</td>

										<td id="tab3"><p>${produit.categorie}</p></td>
										<td id="tab3"><p>${produit.libele}</p></td>
										<td id="tab3"><p>${produit.prixuni}</p></td>
										<c:choose>
											<c:when test="${produit.quantite<produit.quantiteMin}">
												<td id="tab3" style="background: #ff5947"><p>${produit.quantite}</p></td>
											</c:when>
											<c:otherwise>
												<td id="tab3"><p>${produit.quantite}</p></td>
											</c:otherwise>
										</c:choose>

										<td id="tab3"><p>${produit.dateperemption}</p></td>



										<td id="tab3">
											<form action="ProduitServlet" method="get">
												<p>
													<input type="HIDDEN" name="aff" value="supprimer" /> <input
														type="radio" name="action" value="${produit.id}"
														id="radio"> <input type="submit" value="Sup"
														id="cocher">
												</p>
											</form>
										</td>
										<td id="tab3">
											<form action="ProduitServlet" method="get">
												<p>
													<input type="HIDDEN" name="aff" value="modifier" /> <input
														type="radio" name="action" value="${produit.id}"
														id="radio"> <input type="submit" value="Mod"
														id="cocher">
												</p>
											</form>

										</td>

									</tr>
								</c:forEach>
							</table> <br />
							<table style="width: 100px;" id="bout">
								<tr>
									<td>
										<form action="ProduitServlet" method="get">

											<input type="HIDDEN" name="aff" value="add" /> <input
												type="submit" value="Ajouter" id="inp">

										</form>
									</td>
									<td>
										<form action="ProduitServlet" method="get">

											<input type="HIDDEN" name="aff" value="retour" /> <input
												type="submit" value="Retour" id="inp">

										</form>
									</td>
								</tr>
							</table></td>
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