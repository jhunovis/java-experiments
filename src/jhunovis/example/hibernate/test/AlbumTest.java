package jhunovis.example.hibernate.test;

import static org.junit.Assert.fail;
import jhunovis.example.hibernate.Album;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AlbumTest {

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
	@Ignore("Not yet ready!")
	public void loadColumns() {
		fail("Not yet implemented");
	}

	@Test
	public void readWriteTest() {
		Session session = mSessionFactory.openSession();
		session.beginTransaction();
		Album album = new Album();
		album.setPublisher("Publisher 1");
		album.setTitle("Album Title 1");
		session.save( album );
		session.getTransaction().commit();		
		session.close();		
	}
}
