package repository;

public class ForecastDetails {
    private final String temperature;
    private final String windDirection;
    private final String windSpeed;

    public ForecastDetails(String temperature, String windDirection, String windSpeed) {
        this.temperature = temperature;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }
}
