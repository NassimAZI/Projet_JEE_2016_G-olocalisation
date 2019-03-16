<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/form.css">
<title>Modifier Fournisseur</title>
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
				<form method="post" action="<c:url value="/FournisseurServlet"/>">
					<fieldset>
						<legend>Modifier Fournisseur</legend>
						<c:if test="${resultat =='Fournisseur modifié avec succé'}">
							<p id="succe">${resultat}</p>
						</c:if>
						<c:if test="${resultat =='Le fournisseur n a pa eté modifié'}">
							<p id="echec">${resultat}</p>
						</c:if>
						<c:if
							test="${resultat =='Vous demandez de modifier un fournisseur qui n existe pas'}">
							<p id="echec">${resultat}</p>
						</c:if>
						<label for="nom" id="inp">Id </label> <input type="text" name="id" id="id"
							value="<c:out value="${fournisseur.id}"/>" />
						<p id="echec">${erreurs.id}</p>
						<label for="nom" id="inp">Nom </label> <input type="text" id="nom"
							name="nom" value="<c:out value="${fournisseur.nom}"/>" />
						<p id="echec">${erreurs.nom}</p>

						<label for="prenom" id="inp">prenom</label> <input type="text" id="prenom"
							name="prenom" value="<c:out value="${fournisseur.prenom}"/>" />
						<p id="echec">${erreurs.prenom}</p>

						<label for="adresse" id="inp">Adresse</label> <input type="text"
							id="adresse" name="adresse"
							value="<c:out value="${fournisseur.adresse}"/>" />
						<p id="echec">${erreurs.adresse}</p>

						<label for="specialite" id="inp">Spécialité</label> <input type="text"
							id="specialite" name="specialite"
							value="<c:out value="${fournisseur.specialite}"/>" />
						<p id="echec">${erreurs.specialite}</p>

						<label for="teleph" id="inp">Téléphone</label> <input type="text"
							name="teleph" id="teleph"
							value="<c:out value="${fournisseur.numtelephone}"/>" />
						<p id="echec">${erreurs.telephone}</p>
					</fieldset>
					<input type="HIDDEN" name="aff" value="update" /> <input
						type="submit" value="Valider" id="inp">
						<input type="reset" value="Reset" id="inp">

				</form>
				<form action="<c:url value="FournisseurServlet"/>">
					<input type="HIDDEN" name="aff" value="afficher" /> <input
						type="submit" value="Annuler" id="inp"/>
				</form> 
			</td>
		</tr>
	</table>
</div>
</body>
</html>