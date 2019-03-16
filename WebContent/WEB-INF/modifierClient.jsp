<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/form.css">
<title>Modifier Client</title>
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
				<form method="post" action="<c:url value="ClientServlet"/>">
					<fieldset>
						<legend>Modifier Client</legend>
						<c:if test="${resultat =='Client modifié avec succé'}">
							<p id="succe">${resultat}</p>
						</c:if>
						<c:if test="${resultat =='Le client n a pa eté modifié'}">
							<p id="echec">${resultat}</p>
						</c:if>
						<c:if
							test="${resultat =='Vous demandez de modifier un client qui n existe pas'}">
							<p id="echec">${resultat}</p>
						</c:if>
						<label for="nom" id="inp">Id </label> <input type="text" name="id" id="id"
							value="<c:out value="${client.id}"/>" />
						<p id="echec">${erreurs.id}</p>
						<label for="nom" id="inp">Nom </label> <input type="text" name="nom"
							id="nom" value="<c:out value="${client.nom}"/>" />
						<p id="echec">${erreurs.nom}</p>

						<label for="prenom" id="inp">Prenom</label> <input type="text"
							name="prenom" id="prenom"
							value="<c:out value="${client.prenom}"/>" />
						<p id="echec">${erreurs.prenom}</p>

						<label for="adresse" id="inp">Adresse</label> <input type="text"
							name="adresse" id="adresse"
							value="<c:out value="${client.adresse}"/>" />
						<p id="echec">${erreurs.adresse}</p>

						<label for="numtelephone" id="inp">Téléphone</label> <input type="text"
							id="numtelephone" value="<c:out value="${client.numtelephone}"/>"
							name="numtelephone" />
						<p id="echec">${erreurs.numtelephone}</p>

						<label for="numcartefid" id="inp">N° carte fidelité</label> <input
							type="text" id="numcartefid"
							value="<c:out value="${client.numcartefid}"/>" name="numcartefid" />
						<p id="echec">${erreurs.numcartefid}</p>

						<label for="solde" id="inp">Solde</label> <input type="text" name="solde"
							id="solde" value="<c:out value="${client.solde}"/>" />
						<p id="echec">${erreurs.solde}</p>

						<label for="credit" id="inp">Credit</label> <input type="text" id="credit"
							name="credit" value="<c:out value="${client.credit}"/>" />
						<p id="echec">${erreurs.credit}</p>
					</fieldset>
					<input type="HIDDEN" name="aff" value="update" /> <input
						type="submit" value="Valider" id="inp"><input type="reset" value="Reset" id="inp">

				</form>
				<form action="<c:url value="ClientServlet"/>">
					<input type="HIDDEN" name="aff" value="afficher" /> <input
						type="submit" value="Annuler" id="inp" />
				</form> 
			</td>
		</tr>
	</table>
</div>
</body>
</html>