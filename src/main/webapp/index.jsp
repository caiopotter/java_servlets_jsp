<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="forecast-api.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<form id="latlong" onsubmit="return submitFunction(event)">
    Latitude: <input type = "text" name = "latitude">
    <br />
    Longitude: <input type = "text" name = "longitude" />
    <input type = "submit" value = "Submit" />
</form>

<%
    for(int j=1;j<=10;j++)
    {%>
<%=j%><br/>
<%
    }
%>

<%
    request.setAttribute("file",fileObject);
%>

<div id="resultado"></div>



<c:forEach var="status" items="${status}">
    <tr>
        <td><c:out value="${status}" /></td>
    </tr>
</c:forEach>


</body>
</html>
