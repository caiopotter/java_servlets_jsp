function submitFunction(event){
    event.preventDefault();

    let form = document.querySelector('#latlong');
    if (form !== null) {
        let latitudeField = document.getElementsByName("latitude")[0].value;
        let longitudeField = document.getElementsByName("longitude")[0].value;

        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("resultado").innerHTML = this.responseText;
            }
        };
        let forecastUrl = `https://api.open-meteo.com/v1/forecast?latitude=${latitudeField}&longitude=${longitudeField}&current_weather=true`;
        xhttp.open("GET", forecastUrl, true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send();
    } else {
        console.log('Formulário não encontrado na página');
    }
}