package main;

import org.json.JSONObject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static api.Api.doGetRequest;

public class App extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String latitude;
        String longitude;

        JSONObject apiResponse = doGetRequest("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true");
        JSONObject current_weather = new JSONObject();
        if(apiResponse != null && (apiResponse.get("current_weather") != null)){
            current_weather = (JSONObject) apiResponse.get("current_weather");
        }

        latitude = request.getParameter("latitude");
        longitude = request.getParameter("longitude");


        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("latitude: "+latitude+"<br/>");
        out.println("longitude: "+longitude+"<br/>");
        out.println("Temperature: " +current_weather.get("temperature")+"<br/>");
        out.println("Wind Speed: " +current_weather.get("windspeed")+"<br/>");
        out.println("Wind Direction: " +current_weather.get("winddirection")+"<br/>");
        out.println("</body>");
        out.println("</html>");
    }
}