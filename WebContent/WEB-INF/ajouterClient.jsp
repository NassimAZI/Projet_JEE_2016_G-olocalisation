<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/form.css">
<title>Ajouter un Client</title>
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
					<form method="post" action="<c:url value="/ClientServlet"/>">
						<fieldset>
							<legend id="inp">Ajouter Client</legend>

							<c:if test="${resultat =='Client ajouté avec succé'}">
								<p id="succe">${resultat}</p>
							</c:if>
							<c:if test="${resultat =='Le client n a pa eté ajouté'}">
								<p id="echec">${resultat}</p>
								<p id="echec">${erreurs.client}</p>
							</c:if>

							<label for="nom" id="inp">Nom </label> <input type="text"
								id="nom" value="" name="nom" id="inp" />
							<p id="echec">${erreurs.nom}</p>

							<label for="prenom" id="inp">Prenom</label> <input type="text"
								value="" id="prenom" name="prenom" id="inp" />
							<p id="echec">${erreurs.prenom}</p>

							<label for="numcartefid" id="inp">N° carte fidelité</label> <input
								type="text" value="" id="numcartefid" name="numcartefid" />
							<p id="echec">${erreurs.numcartefid}</p>

							<label for="adresse" id="inp">Adresse</label> <input type="text"
								value="" id="adresse" name="adresse" />
							<p id="echec">${erreurs.adresse}</p>

							<label for="numtelephone" id="inp">Téléphone</label> <input
								type="text" id="numtelephone" value="" name="numtelephone" />
							<p id="echec">${erreurs.numtelephone}</p>

							<label for="credit" id="inp">Credit</label> <input type="text"
								id="credit" value="" name="credit" />
							<p id="echec">${erreurs.credit}</p>


							<label for="solde" id="inp">Solde</label> <input type="text"
								id="solde" value="" name="solde" />
							<p id="echec">${erreurs.solde}</p>

						</fieldset>
						<input type="HIDDEN" name="aff" value="ajouter" /> <input
							type="submit" value="Valider" id="inp" /> <input type="reset"
							value="Reset" id="inp" />

					</form>

					<form action="<c:url value="/ClientServlet"/>">
						<input type="HIDDEN" name="aff" value="afficher" /> <input
							type="submit" value="Retour" id="inp" />
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>