package trabalho.grafos;

public class Point {
    private final int earthRadius = 6371;
    private final double latitude;
    private final double longitude;

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private double distanceBetween(double latitude, double longitude) {
        var latDistance = Math.toRadians(latitude - this.latitude);
        var lonDistance = Math.toRadians(longitude - this.longitude);

        var sinLatitude = Math.sin(latDistance / 2) * Math.sin(latDistance / 2);
        var cosLatitude = Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(latitude));
        var sinLongitude = Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        var angle = sinLatitude + cosLatitude * sinLongitude;
        var distance = 2 * Math.atan2(Math.sqrt(angle), Math.sqrt(1 - angle));
        var distanceMeters = this.earthRadius * distance * 1000;
        distanceMeters = Math.pow(distanceMeters, 2);
        return Math.sqrt(distanceMeters);
    }

    public double distanceBetween(Point point) {
        return distanceBetween(point.latitude, point.longitude);
    }
}
