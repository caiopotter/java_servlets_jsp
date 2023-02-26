document.getElementById("latlong").addEventListener("submit", function(event) {
    event.preventDefault();

    // obtém os valores dos campos do formulário
    var campo1 = document.getElementsByName("latitude")[0].value;
    var campo2 = document.getElementsByName("longitude")[0].value;

    // cria uma solicitação AJAX para a API externa
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            // atualiza a página com a resposta recebida
            document.getElementById("resultado").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current_weather=true", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("latitude=" + campo1 + "&longitude=" + campo2 + "&current_weather=true");
});
