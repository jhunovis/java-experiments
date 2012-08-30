package jhunovis.experiments.hibernate.piclib;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

public class EmbeddedTests extends TestBase {
	
	@Test
	public void nullableFields() {

		Session session = mSessionFactory.openSession();
		session.beginTransaction();

		int picId;
		Picture picture = new Picture();
		// store a null value for the embedded geo-location.
		picture.setGeoLocation(null);
		session.save(picture);
		picId = picture.getId();
		session.getTransaction().commit();
		session.close();
		
		// restore picture
		session = mSessionFactory.openSession();
		session.beginTransaction();
		picture = (Picture)session.load(Picture.class, picId);

		GeoLocation loc = picture.getGeoLocation();
		
		assertNotNull(loc);
		assertEquals(Double.NaN, loc.getLatitude(), Double.NaN);
		assertEquals(Double.NaN, loc.getLatitude(), Double.NaN);
		
		session.getTransaction().commit();
		session.close();

	}
	
	@Test(expected=ClassCastException.class)
	public void polymorphy() {
		
		Session session = mSessionFactory.openSession();
		session.beginTransaction();
		int picId;
		Picture picture = new Picture();
		GeoLocationWithAltitude loc = new GeoLocationWithAltitude(0d, 1d, 2d); 
		// store an location with an altitude
		picture.setGeoLocation(loc);
		session.save(picture);
		picId = picture.getId();
		session.getTransaction().commit();
		session.close();		

		// restore picture
		session = mSessionFactory.openSession();
		session.beginTransaction();
		picture = (Picture)session.load(Picture.class, picId);

		loc = (GeoLocationWithAltitude)picture.getGeoLocation();
			
		session.getTransaction().commit();
		session.close();
	
	}
	
	

}
