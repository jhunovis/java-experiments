package jhunovis.experiments.hibernate.piclib;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import jhunovis.experiments.hibernate.piclib.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class PictureLibraryTest {

	private static SessionFactory mSessionFactory;

	@BeforeClass
	@SuppressWarnings("deprecation")
	public static void setupSessioFactory(){
		mSessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	@AfterClass
	public static void tearDown(){
		if ( mSessionFactory != null ) {
			mSessionFactory.close();
		}
	}

	
	@Test
	public void picture() throws MalformedURLException {
		
		
		Session session = mSessionFactory.openSession();
		session.beginTransaction();
		
		int picId;
		String descr = "First Picture";
		GeoLocation geoLoc = new GeoLocation(0d,0d,0d);
		URL url = new URL("file:///tmp/none");
				
		Picture picture = new Picture();
		picture.setDescription(descr);
		picture.setFile(url);
		picture.setGeoLocation(geoLoc);
		
		session.save(picture);
		picId = picture.getId();
		
		session.getTransaction().commit();		
		session.close();				

		// restore by id
		session = mSessionFactory.openSession();
		session.beginTransaction();
		picture = (Picture) session.load(Picture.class, picId);
		assertEquals(descr, picture.getDescription());
		assertEquals(url, picture.getFile());
		assertEquals(geoLoc, picture.getGeoLocation());
		session.close();				
		
	}
}
