package main;

import org.json.JSONObject;
import repository.Forecast;
import repository.ForecastDetails;
import repository.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import static api.Api.doGetRequest;

public class App extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String latitude;
        String longitude;

        latitude = request.getParameter("latitude").replace(",", ".");
        longitude = request.getParameter("longitude").replace(",", ".");

        if(!(isNumeric(latitude) && isNumeric(longitude))){
            RequestDispatcher ret = request.getRequestDispatcher("index.jsp");
            ret.forward(request, response);
        }

        JSONObject apiResponse = doGetRequest(String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current_weather=true",
                latitude, longitude));
        JSONObject current_weather = new JSONObject();
        if(apiResponse != null && (apiResponse.get("current_weather") != null)){
            current_weather = (JSONObject) apiResponse.get("current_weather");
        }

        String temperature = current_weather.get("temperature").toString();
        String windspeed = current_weather.get("windspeed").toString();
        String winddirection = current_weather.get("winddirection").toString();

        ForecastDetails forecastDetails = new ForecastDetails(temperature, winddirection, windspeed);
        Forecast forecast = new Forecast(latitude, longitude, forecastDetails);


        Repository repo = new Repository();
        repo.insert(forecast);
        repo.export();

        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("latitude: "+latitude+"<br/>");
        out.println("longitude: "+longitude+"<br/>");
        out.println("Temperature: " +temperature+"<br/>");
        out.println("Wind Speed: " +windspeed+"<br/>");
        out.println("Wind Direction: " +winddirection+"<br/>");
        out.println("</body>");
        out.println("</html>");
    }

    private boolean isNumeric(String userInput){
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (userInput == null) {
            return false;
        }
        return pattern.matcher(userInput).matches();
    }
}