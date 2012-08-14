package jhunovis.example.hibernate;

import static org.junit.Assert.fail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AlbumWithAnnotationsTest {

	private static Configuration mConfig;
	private static SessionFactory mSessionFactory;

	@BeforeClass
	@SuppressWarnings("deprecation")
	public static void setupHibernateConfig(){
		mConfig = new Configuration();
		mConfig.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		mConfig.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/simple");
		mConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");		
		mConfig.setProperty("hibernate.connection.username", "hibernate");
		mConfig.setProperty("hibernate.connection.password", "");
		mConfig.setProperty("hibernate.connection.pool_size", "10");	
		mConfig.addAnnotatedClass(AlbumWithAnnotations.class);
		mSessionFactory = mConfig.buildSessionFactory();
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
		AlbumWithAnnotations album = new AlbumWithAnnotations();
		album.setPublisher("Publisher 1");
		album.setTitle("Album Title 1");
		session.save( album );
		session.getTransaction().commit();		
		session.close();		
	}
}
