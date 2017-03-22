package pkg.geo.controller;

/**
 * Created by ewert on 3/22/2017.
 */
public class SpatialResult {
    private double latitude, longitude;

    public SpatialResult(double lat, double lon)
    {
        latitude = lat;
        longitude = lon;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }
}
