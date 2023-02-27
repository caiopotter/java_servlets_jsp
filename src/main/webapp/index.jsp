<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
    <body>
        <h2>Forecast by coordinates</h2>
        <form id="latlong" action="App">
            Latitude: <input type = "text" name = "latitude">
            <br />
            Longitude: <input type = "text" name = "longitude" />
            <input type = "submit" value = "Submit" />
        </form>

    </body>
</html>
