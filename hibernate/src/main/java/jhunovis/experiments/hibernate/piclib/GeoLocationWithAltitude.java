package jhunovis.experiments.hibernate.piclib;

import javax.persistence.Embeddable;

/**
 * This class adds an optional altitude to the base-class.
 * 
 * The sole purpose for this madness is to demonstrate the natural way to deal
 * with the problem that the owner of the embedded object cannot store a null
 * reference for am embedded property (with primitive types).
 * 
 * The solution is to always store an instance of the embedded type. Simple, ey?
 * 
 * Precaution must be taken to distinguish this uninitialized instance from
 * regular instances. In case of this class, {@code altitude} will be
 * initialized with {@code Double.NaN}.
 * 
 * Another approach would be the use of a designated null object. The null
 * object (see null object pattern) is a special sub-class of the base type
 * whose sole purpose it is to mark uninitialized objects. User Bozho
 * recommended it on Stack Overflow
 * (http://stackoverflow.com/questions/2838528/hibernate-does-not-
 * allow-an-embedded-object-with-an-int-field-to-be-null).
 * 
 * 
 * @author jhunovis
 * 
 */
@Embeddable
public class GeoLocationWithAltitude extends GeoLocation {

	/** Unlike the properties of the base-class, altitude is not nullable. 
	 *  You *must* store an instance of this class with the embedding instance!
	 *  */
	private double altitude;

	/** Initialized all fields to Double.NaN. */
	public GeoLocationWithAltitude() {
		super();
		altitude = Double.NaN;
	}

	public GeoLocationWithAltitude(double latitude, double longitude,
			double altitude) {
		super(latitude, longitude);
		this.altitude = altitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o)
				&& Double.compare(((GeoLocationWithAltitude) o).altitude,
						altitude) == 0;
	}

	@Override
	public int hashCode() {
		return new Double(altitude).hashCode() ^ super.hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(50);
		builder.append("(")
			.append(getLatitude())
			.append(", ")
			.append(getLongitude())
			.append(", ")
			.append(altitude)
			.append(")");
		return builder.toString();
	}
	

}
