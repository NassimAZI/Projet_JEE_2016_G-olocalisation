<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>Connexion</title>
<link rel="stylesheet" href="inc/form.css">
<script language="javascript" src="<c:url value="/res/MD5.js"/>"></script>	
	
<SCRIPT language="Javascript">
 
function submitForm() {
  	//alert(document.forms["connexionForm"].elements["password"].value);
	var value = document.forms["connexionForm"].elements["password"].value;
//	alert(value);
	document.forms["connexionForm"].elements["password"].value = MD5(value);
 	//alert(document.forms["connexionForm"].elements["password"].value);
	document.forms["connexionForm"].submit();
}
</SCRIPT>
</head>
<body style="background-color: rgb(200, 200, 250);margin-left: 20%;margin-top: 5%">
<div class="tab">
		<table>
			<tr id="tab1">
							
				<td>				
							<h2 style="text-align: center; color:#4d4e4d;  " id="inp" > Login </h2>
				</td>
			</tr>
			<tr id="tab22">
				<td>
	<form id="connexionForm" method="post" action="Login">
		<p id="echec">${resultat}</p>
	    <p id="succe">${resultat1}</p>
		<fieldset>
			<legend id="inp">Connexion</legend>

			<label for="utl" id="inp">Utilisateur</label>
			  <input type="text" id="utl" name="utl" size="20" maxlength="20"/>
				
			<label for="mdp" id="inp">Mot de passe</label> 
			  <input type="password" id="password" name="password" value="" size="20" maxlength="20" /><br/><br /> 
			
			<input type="button" value="Connexion" onclick="submitForm()" id="inp"/>
			<input type="reset" value="Reset" id="inp">
			<br />
		</fieldset>
		<br />
	</form>
</td>
				
				
			</tr>
		</table>
	</div>
</body>
</html>