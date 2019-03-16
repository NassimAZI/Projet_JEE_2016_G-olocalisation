<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>La Caisse</title>
</head>
<body style="background-color: rgb(200, 200, 250);">
	<c:choose>
		<c:when
			test="${(sessionScope.role=='utl')||(sessionScope.role=='admin')}">

			<h1 style="text-align: center;">La Caisse</h1>
			<br>
			<form action="<c:url value="/UtilisateurServlet"/>">
				<input type="HIDDEN" name="aff" value="deconnexion" /> <input
					type="submit" value="Deconnecter" id="inp"
					style="text-align: center; margin: auto; font-size: large; margin-left: 80%;" />
			</form>
			<div style="margin-left: 50%;">
				<button onclick="Somme();" id="Somme_botton"
					style="text-align: center; margin: auto; font-size: xx-large;">Somme</button>
				<input type="text" id="affich_somme_id" name="affich_somme_id"
					value="0" style="font-size: xx-large;">
			</div>
			<br>
			<table id="table_id"
				style="width: 70%; text-align: center; margin: auto; background-color: darkgray">
				<tr>
					<th>Code Bar</th>
					<th>Libelle</th>
					<th>Prix</th>
					<th>Quantite</th>
					<th>Somme</th>
					<th>Valider</th>
				</tr>
				<tr id="ligne_id">
					<td><input type="text" ondblclick="chargeAll();"
						name="codeBar_id" id="codeBar_id" alt="double clique pour charger"></td>
					<td><input type="text" disabled="disabled" id="libelle_id"></td>
					<td><input type="text" disabled="disabled" id="prix_id"></td>
					<td><input type="text" id="quantite_id" ondblclick="calcul();"
						alt="double clique pour afficher la somme"></td>
					<td><input type="text" disabled="disabled" id="somme_id"></td>
					<td><button onclick="validerV();" id="bouton_valider">
							OK</button></td>
				</tr>
			</table>
			<div style="margin-left: 60%;">
				<button onclick="addRow();" id="ajout_botton"
					style="text-align: center; background-color: gray; font-style: italic;">Ajouter
					une Ligne</button>
				<button onclick="deleteRow(ligne_id);" id="sup_botton"
					style="text-align: center; background-color: gray; font-style: italic;">Supprimer
					la derniere Ligne</button>
			</div>

			<script type="text/javascript" src="inc/Caisse.js">
				
			</script>
		</c:when>
		<c:otherwise>
			<h1 style="color: Red; margin-left: 10%;">
				Session Invalide <br> <a href="erreur">connectez vous ici</a>
			</h1>

		</c:otherwise>
	</c:choose>
</body>
</html>