package repository;

public class Forecast {
    private final String latitude;
    private final String longitude;
    private final ForecastDetails details;

    public Forecast(String latitude, String longitude, ForecastDetails details) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.details = details;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public ForecastDetails getDetails() {
        return details;
    }
}
