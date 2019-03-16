function chargeAll() {
	var donnees = document.getElementById("codeBar_id");
	var url = "valider?valeur=" + escape(donnees.value);

	if (window.XMLHttpRequest) {
		requete = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		requete = new ActiveXObject("Microsoft.XMLHTTP");
	}
	requete.open("GET", url, true);
	requete.onreadystatechange = majIHM;
	requete.send(null);

}
function majIHM() {
	if (requete.readyState == 4) {
		if (requete.status == 200) {

			// exploitation des données de la réponse
			var messageTag = requete.responseText;
			var array = messageTag.split('et', 3);
			document.getElementById("libelle_id").setAttribute('value',
					array[0]);
			document.getElementById("prix_id").setAttribute('value', array[1]);

		}
	}
}
// -->
function addRow() {
	var i = document.getElementById('table_id').getElementsByTagName('tr').length;

	var newLine = document.createElement("tr");
	newLine.id = 'new_row' + i;
	document.getElementById('table_id').appendChild(newLine);
	var k;

	for (k = 1; k < 6; k++) {

		newCol = document.createElement('td');
		newCol.id = 'new_col' + i + '' + k;
		document.getElementById('new_row' + i).appendChild(newCol);

		newInput = document.createElement('input');
		newInput.id = 'new_input' + k + '' + i;
		newInput.type = 'text';
		if ((k == 2) || (k == 3) || (k == 5)) {
			newInput.disabled = 'disabled';
		}
		document.getElementById('new_col' + i + '' + k).appendChild(newInput);
	}
	newCol = document.createElement('td');
	newCol.id = 'new_col' + i + '' + 6;
	document.getElementById('new_row' + i).appendChild(newCol);

	var newButton = document.createElement("button");
	newButton.id = 'new_button' + 6 + '' + i;
	var text = document.createTextNode("OK");
	newButton.appendChild(text);
	document.getElementById('new_col' + i + '' + 6).appendChild(newButton);

	addEvents('new_input' + 1 + '' + i, 'new_input' + 2 + '' + i, 'new_input'
			+ 3 + '' + i, 'new_input' + 4 + '' + i, 'new_input' + 5 + '' + i,
			'new_button' + 6 + '' + i);

}

function addEvents(codeB_id, libelle_id, prix_id, quantite_id, somme_id,
		valid_id) {

	var element = document.getElementById(codeB_id);
	element.addEventListener('dblclick', function() {
		var url = "valider?valeur=" + escape(element.value);
		if (window.XMLHttpRequest) {
			requete = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requete = new ActiveXObject("Microsoft.XMLHTTP");
		}
		requete.open("GET", url, true);
		requete.onreadystatechange = function() {
			if (requete.readyState == 4) {
				if (requete.status == 200) {

					var messageTag = requete.responseText;

					var array = messageTag.split('et', 3);

					document.getElementById(libelle_id).setAttribute('value',
							array[0]);
					document.getElementById(prix_id).setAttribute('value',
							array[1]);

				}
			}
		};
		requete.send(null);

	}, false);
	var element2 = document.getElementById(quantite_id);
	element2.addEventListener('dblclick', function() {
		var quantite = document.getElementById(quantite_id).value;
		var prix = document.getElementById(prix_id).value;
		document.getElementById(somme_id)
				.setAttribute('value', quantite * prix);

	}, false);

	var element3 = document.getElementById(valid_id);
	element3.addEventListener('click', function() {
		if (confirm("voulez vous valider la transaction ?")) {
			element3.style.color = 'red';
			var donnees = document.getElementById(codeB_id);
			var quantite = document.getElementById(quantite_id);
			var url = 'validerVente?valeur1='
					+ escape(donnees.value + '&' + quantite.value);
			if (window.XMLHttpRequest) {
				requete = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				requete = new ActiveXObject("Microsoft.XMLHTTP");
			}
			requete.open("GET", url, true);
			requete.onreadystatechange = validerTrans;
			requete.send(null);
			
		} else {
			alert("transaction annulée");
		}
	}, false);

}

function deleteRow() {

	var i = document.getElementById('table_id').getElementsByTagName('tr').length;
	i--;
	var ligne = document.getElementById('new_row' + i);
	ligne.parentNode.removeChild(ligne);

}

function calcul() {
	var quantite = document.getElementById("quantite_id").value;
	var prix = document.getElementById("prix_id").value;
	document.getElementById("somme_id").setAttribute('value', prix * quantite);

}
function Somme() {

	var list = document.getElementById('table_id').getElementsByTagName('tr');
	var i = list.length;
	var tr1 = document.getElementById('somme_id').value;
	if (tr1 == '')
		tr1 = '0';
	var somme = parseInt(tr1);
	for ( var k = 3; k <= i; k++) {
		var temp = k - 1;
		var tr = document.getElementById('new_input' + 5 + '' + temp).value;
		if (tr == '')
			tr = '0';
		somme += parseInt(tr);
	}
	document.getElementById("affich_somme_id").value = somme;
}
function validerV() {
	if (confirm("voulez vous valider la transaction ?")) {
		validerVente();
	} else {
		alert("transaction annulée");
	}

}
function validerVente() {
    var bout= document.getElementById("bouton_valider");
    bout.style.color='red';
	var donnees = document.getElementById("codeBar_id");
	var quantite = document.getElementById("quantite_id");
	var url = 'validerVente?valeur1='
			+ escape(donnees.value + '&' + quantite.value);
	if (window.XMLHttpRequest) {
		requete = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		requete = new ActiveXObject("Microsoft.XMLHTTP");
	}
	requete.open("GET", url, true);
	requete.onreadystatechange = validerTrans;
	requete.send(null);

}
var temp = 0;
function validerTrans() {
	if (requete.readyState == 4) {
		if (requete.status == 200) {
			alert(requete.responseText);
		}
	}
}