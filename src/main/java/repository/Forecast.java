package repository;

public class Forecast {
    private int id;
    private String latitude;
    private String longitude;
    private ForecastDetails details;

    public Forecast(String latitude, String longitude, ForecastDetails details) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.details = details;
    }

    public Forecast(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public ForecastDetails getDetails() {
        return details;
    }

    public void setDetails(ForecastDetails details) {
        this.details = details;
    }
}
