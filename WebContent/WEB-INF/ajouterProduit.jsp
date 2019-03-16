<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/form.css">
<title>Ajouter Produit</title>
</head>
<body style="background-color: rgb(200, 200, 250); margin-left: 20%">
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
							<legend id="inp">Ajouter Produit</legend>
							<c:if test="${resultat =='Produit ajout� avec succ�'}">
								<p id="succe">${resultat}</p>
							</c:if>
							<c:if test="${resultat =='Le produit n a pa et� ajout�'}">
								<p id="echec">${resultat}</p>
							</c:if>
							<label for="codebarre" id="inp">Code barre</label> <input
								type="text" id="codebarre" name="codebarre" value="" />
							<p id="echec">${erreurs.CodeBarre}</p>

							<label for="categorie" id="inp">categorie</label> <input
								type="text" id="categorie" name="categorie" value="" />
							<p id="echec">${erreurs.categorie}</p>


							<label for="libele" id="inp">Libelle</label> <input type="text"
								id="libele" name="libele" value="" />
							<p id="echec">${erreurs.libele}</p>


							<label for="prixuni" id="inp">Prix Unitaire</label> <input
								type="text" id="prixuni" name="prixuni" value="" />
							<p id="echec">${erreurs.prixuni}</p>


							<label for="quantite" id="inp">Quantit�</label> <input
								type="text" id="quantite" name="quantite" value="" />
							<p id="echec">${erreurs.quantite}</p>

							<label for="quantite" id="inp">Quantit�_Min</label> <input
								type="text" id="prodmin" name="prodmin" value="" />
							<p id="echec">${erreurs.quantite}</p>

							<label for="datpremp" id="inp">Date de Peremption </label> <input
								type="text" id="datpremp" name="datpremp" />
							<p id="echec">${erreurs.date}</p>

							<!-- <label for="prodmin" id="inp">Quantite Min</label> <input type="text"
							id="prodmin" name="prodmin" value="" />
						<p id="echec">${erreurs.quantiteMin}</p> -->

						</fieldset>
						<input type="HIDDEN" name="aff" value="ajouter" /> <input
							type="submit" value="Valider" id="inp"> <input
							type="reset" value="reset" id="inp">
					</form>
					<form action="<c:url value="/ProduitServlet"/>">
						<input type="HIDDEN" name="aff" value="afficher" /> <input
							type="submit" value="Retour" id="inp" />
					</form>

				</td>
			</tr>
		</table>
	</div>

</body>
</html>
