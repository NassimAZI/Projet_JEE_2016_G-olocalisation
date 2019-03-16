<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<label for="nomClient">Nom <span class="requis">*</span></label>
<input type="text" id="nomClient" name="nomClient"
	value="<c:out value="${client.nom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['nomClient']}</span>
<br />

<label for="prenomClient">Prénom <span class="requis">*</span></label>
<input type="text" id="prenomClient" name="prenomClient"
	value="<c:out value="${client.prenom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['prenomClient']}</span>
<br />

<label for="adresseClient">Adresse </label>
<input type="text" id="adresseClient" name="adresseClient"
	value="<c:out value="${client.adresse}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['adresseClient']}</span>
<br />

<label for="telephoneClient">Numéro de téléphone</label>
<input type="text" id="telephoneClient" name="telephoneClient"
	value="<c:out value="${client.numtelephone}"/>" size="30"
	maxlength="30" />
<span class="erreur">${form.erreurs['telephoneClient']}</span>
<br />

<label for="numCarteFidClient">Num carte De Fidelite<span
	class="requis">*</span></label>
<input type="text" id="numCarteFidClient" name="numCarteFidClient"
	value="<c:out value="${client.numcartefid}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['numCarteFid']}</span>
<br />
