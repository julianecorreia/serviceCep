<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consulta de Usuário</title>
</head>
<body>

<h1>Consulta de Usuário</h1>

<form id="usuarioForm">
    <label for="idUsuarioInput">ID do Usuário:</label>
    <input type="text" id="idUsuarioInput" name="idUsuario" required>
    <button type="submit">Consultar</button>
</form>

<div id="resultado"></div>

<script>
    document.getElementById('usuarioForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var idUsuario = document.getElementById('idUsuarioInput').value;

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var xmlDoc = xmlhttp.responseXML;
                var nome = xmlDoc.getElementsByTagName("nome")[0].childNodes[0].nodeValue;
                var email = xmlDoc.getElementsByTagName("email")[0].childNodes[0].nodeValue;
                var idade = xmlDoc.getElementsByTagName("idade")[0].childNodes[0].nodeValue;

                var resultadoHTML = "<p>Nome: " + nome + "</p>";
                resultadoHTML += "<p>Email: " + email + "</p>";
                resultadoHTML += "<p>Idade: " + idade + "</p>";

                document.getElementById("resultado").innerHTML = resultadoHTML;
            }
        };
        xmlhttp.open("POST", "http://localhost:8080/enderecoSIB", true);
        xmlhttp.setRequestHeader("Content-Type", "text/xml");
        var soapEnvelope = `<?xml version="1.0" encoding="UTF-8"?>
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://servicecep.programacaointernet.unipar.br/">
       <soapenv:Header/>
       <soapenv:Body>
          <ser:consultaUsuario>
             <idUsuario>${idUsuario}</idUsuario>
          </ser:consultaUsuario>
       </soapenv:Body>
    </soapenv:Envelope>`;
        xmlhttp.send(soapEnvelope);
    });
</script>

</body>
</html>
