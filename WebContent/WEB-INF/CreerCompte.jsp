<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>CreerCompte</title>
<link rel="stylesheet" href="inc/form.css">

<script language="javascript" src="<c:url value="/res/MD5.js"/>"></script>

<SCRIPT language="Javascript">
	function submitForm() {
		alert(document.forms["connexionForm"].elements["password"].value);
		var value = document.forms["connexionForm"].elements["password"].value;
		//	alert(value);
		document.forms["connexionForm"].elements["password"].value = MD5(value);
		alert(document.forms["connexionForm"].elements["password"].value);
		document.forms["connexionForm"].submit();
	}
</SCRIPT>
</head>

<body style="background-color: rgb(200, 200, 250);margin-left: 20% ;margin-top: 5%">
	<div class="tab">
		<table>
			<tr id="tab1">

				<td><form action="<c:url value="/UtilisateurServlet"/>">
								<input type="HIDDEN" name="aff" value="deconnexion" /> <input
									type="submit" value="Deconnecter" id="inp" />
							</form>
				</td>
			</tr>
			<tr id="tab22">
				<td>
					<p>
						<font color="red">${Message}</font>
					</p>

				
						<form id="connexionForm" method="post" action="CreerCompte">
							<fieldset>
								<legend id="inp">Créer un compte</legend>
								<c:if test="${resultat =='l tilisateur crée avec succé'}">
									<p id="succe">${resultat}</p>
								</c:if>
								<c:if test="${resultat =='l tilisateur existe deja'}">
									<p id="echec">${resultat}</p>
								</c:if>
								<c:if test="${resultat =='role doit etre utl ou admin'}">
									<p id="echec">${resultat}</p>
								</c:if>

								<label for="utl" id="inp">Utilisateur</label> <input type="text"
									id="utl" name="utl" value="" size="20" maxlength="20" /><br />
								<label for="password" id="inp">Mot de passe</label> <input
									type="password" id="password" name="password" value=""
									size="20" maxlength="20" /><br /> <label for="role" id="inp">Role</label>
								<input type="text" id="role" name="role" value="" size="20"
									maxlength="20" /><br />
							</fieldset>
							<input type="button" value="Valider" onclick="submitForm()"
								id="inp" /> <input type="reset" value="Reset" id="inp">
						</form>
						<form action="UtilisateurServlet">
							<input type="HIDDEN" name="aff" value="afficher" /> <input
								type="submit" value="Retour" id="inp" />
						</form>
				</td>
			</tr>
		</table>

	</div>
</body>
</html>