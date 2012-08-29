package jhunovis.experiments.hibernate.piclib;

import javax.persistence.Embeddable;

@Embeddable
public class GeoLocation {
	private double latitude;
	private double longitude;
	private double altitude;

	public GeoLocation() {
	}

	public GeoLocation(double latitude, double longitude, double altitude) {
		this.altitude = altitude;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof GeoLocation) {
			GeoLocation other = (GeoLocation) o;
			return Double.compare(other.altitude, altitude) == 0
					&& Double.compare(other.latitude, latitude) == 0
					&& Double.compare(other.longitude, longitude) == 0;
		} else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		return new Double(altitude).hashCode()
				^ new Double(latitude).hashCode()
				^ new Double(longitude).hashCode();
	}
}
