package jhunovis.experiments.hibernate.piclib;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

/**
 * Describe a location on earth by its latitude and longitude.
 * 
 * Objects of this type will be embedded within the table of their owners. There
 * is one pitfall here: GeoLocation cannot be {@code null} within the owner. A
 * ConstraintViolationException will be thrown for primitive types, like double;
 * probably because Hibernate cannot differentiate between a null reference and
 * a uninitialized instance.
 * 
 * The obvious fix is to always store an instance of the embedded type with the
 * owner. The sub-class {@link GeoLocationWithAltitude} demonstrates this
 * approach.
 * 
 * The approach demonstrate here will simply mark all primitive attributes as
 * {@code nullable}.
 * 
 * Also note the {@link MappedSuperclass} annotation. Without it the fields of
 * this class will not be persisted when a field of a sub-class type is used! Still,
 * polymorphism is not available. (or is it?)
 * 
 * Another interesting thing is, that embeddable classes do not need to be
 * registered in your hibernate.cfg.xml.
 */
@Embeddable
@MappedSuperclass
public class GeoLocation {

	@Column(nullable = true)
	private double latitude;
	@Column(nullable = true)
	private double longitude;

	/** Initialized all fields to Double.NaN. */
	public GeoLocation() {
		latitude = Double.NaN;
		longitude = Double.NaN;
	}

	public GeoLocation(double latitude, double longitude) {
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

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof GeoLocation) {
			GeoLocation other = (GeoLocation) o;
			return Double.compare(other.latitude, latitude) == 0
					&& Double.compare(other.longitude, longitude) == 0;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return new Double(latitude).hashCode()
				^ new Double(longitude).hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(50);
		builder.append("(")
			.append(latitude)
			.append(", ")
			.append(longitude)
			.append(")");
		return builder.toString();
	}

}