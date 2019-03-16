<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/form.css">
<title>Modifier Produit</title>
</head>
<body style="background-color: rgb(200, 200, 250);margin-left: 20%">
<div class="tab">
	<table>
		<tr id="tab1">
			<td><form action="<c:url value="/UtilisateurServlet"/>">
								<input type="HIDDEN" name="aff" value="deconnexion" /> <input
									type="submit" value="Deconnecter" id="inp" />
							</form></td>
		</tr>
		<tr id="tab22">
			<td>
				<form method="get" action="<c:url value="/ProduitServlet"/>">
					<fieldset>
						<legend>Modifier Produit</legend>
						<c:if test="${resultat =='Produit modifié avec succé'}">
							<p id="succe">${resultat}</p>
						</c:if>
						<c:if test="${resultat =='Le produit n a pa eté modifié'}">
							<p id="echec">${resultat}</p>
						</c:if>
						<c:if
							test="${resultat =='Vous demandez de modifier un produit qui n existe pas'}">
							<p id="echec">${resultat}</p>
						</c:if>
						<label for="id" id="inp">Id</label> <input type="text" id="id" name="id"
							value="<c:out value="${produit.id}"/>" />
						<p id="echec">${erreurs.id}</p>
						<label for="codebarre" id="inp">Code barre</label> 
						<input type="text" id="codebarre" name="codebarre" value="${produit.codeBarre}" />
						<p id="echec">${erreurs.CodeBarre}</p>
						<label for="categorie" id="inp">categorie</label> <input type="text"
							id="categorie" name="categorie"
							value="<c:out value="${produit.categorie}"/>" />
						<p id="echec">${erreurs.categorie}</p>

						<label for="libele" id="inp">Libelle</label> <input type="text" id="libele"
							name="libele" value="<c:out value="${produit.libele}"/>" />
						<p id="echec">${erreurs.libele}</p>

						<label for="prixuni" id="inp">Prix Unitaire</label> <input type="text"
							id="prixuni" name="prixuni"
							value="<c:out value="${produit.prixuni}"/>" />
						<p id="echec">${erreurs.prixuni}</p>

						<label for="quantite" id="inp">Quantité</label> <input type="text"
							id="quantite" name="quantite"
							value="<c:out value="${produit.quantite}"/>" />
						<p id="echec">${erreurs.quantite}</p>

						<label for="datpremp" id="inp">Date de Peremption</label> <input
							type="text" id="datpremp" name="datpremp"
							value="<c:out value="${produit.dateperemption}"/>" />
						<p id="echec">${erreurs.date}</p>

						
					</fieldset>
					<input type="HIDDEN" name="aff" value="update" /> <input
						type="submit" value="Valider" id="inp">
						<input type="reset" value="Reset" id="inp">

				</form>
				<form action="<c:url value="ProduitServlet"/>">
					<input type="HIDDEN" name="aff" value="afficher" /> <input
						type="submit" value="Annuler" id="inp" />
				</form> 
			</td>
		</tr>
	</table>
</div>

</body>
</html>