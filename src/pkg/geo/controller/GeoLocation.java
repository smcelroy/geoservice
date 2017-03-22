package pkg.geo.controller;

public class GeoLocation {
	
    public String address;
    public double distance;
    public double latitude;
    public double longitude;
    public int count;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
