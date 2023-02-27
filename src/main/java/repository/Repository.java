package repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.time.Instant;

public class Repository {
    public void insert(Forecast forecast) {
        try {
            String URL = "jdbc:mysql://localhost:3306/forecast";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, "root", "root");

            String coordinatesSql = "INSERT INTO coordinates (latitude,longitude) values (?,?)";
            PreparedStatement coordinatesStatement = con.prepareStatement(coordinatesSql, Statement.RETURN_GENERATED_KEYS);
            coordinatesStatement.setString(1, forecast.getLatitude());
            coordinatesStatement.setString(2, forecast.getLongitude());

            coordinatesStatement.executeUpdate();

            int coordinatesIdValue = 0;
            try (ResultSet generatedKeys = coordinatesStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    coordinatesIdValue = (generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating coordinates failed, no ID obtained.");
                }
            }

            coordinatesStatement.close();

            String detailsSql = "INSERT INTO details (coordinatesId,temperature,windspeed," +
                    "winddirection,timestamp_local) values (?,?,?,?,?)";
            PreparedStatement detailsStatement = con.prepareStatement(detailsSql);
            detailsStatement.setInt(1, coordinatesIdValue);
            detailsStatement.setString(2, forecast.getDetails().getTemperature());
            detailsStatement.setString(3, forecast.getDetails().getWindSpeed());
            detailsStatement.setString(4, forecast.getDetails().getWindDirection());
            detailsStatement.setTimestamp(5, Timestamp.from(Instant.now()));

            detailsStatement.executeUpdate();
            detailsStatement.close();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void export(){
        try{
            String URL = "jdbc:mysql://localhost:3306/forecast";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, "root", "root");

            String csvFilePath = "./forecast-details.csv";
            String detailsSql = "Select * from details";

            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(detailsSql);

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            fileWriter.write("Id, coordinatesId, Temperature, Wind Speed, Wind Direction, timestamp_local");

            while (result.next()) {
                int id = result.getInt("id");
                int coordinatesId = result.getInt("coordinatesId");
                String temperature = result.getString("temperature");
                String windSpeed = result.getString("windspeed");
                String windDirection = result.getString("winddirection");
                String timestamp_local = result.getString("timestamp_local");


                String line = String.format("\"%s\",%s,%s,%s,%s,%s",
                        id, coordinatesId, temperature, windSpeed, windDirection, timestamp_local);

                fileWriter.newLine();
                fileWriter.write(line);
            }

            statement.close();
            fileWriter.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
