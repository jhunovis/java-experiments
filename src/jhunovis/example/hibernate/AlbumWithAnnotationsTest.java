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

	// @BeforeClass
	@SuppressWarnings("deprecation")
	public static void setupHibernateMySQLConfig(){
		mConfig = new Configuration();
		mConfig.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		mConfig.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/simple");
		mConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");		
		mConfig.setProperty("hibernate.connection.username", "hibernate");
		mConfig.setProperty("hibernate.connection.password", "");
		mConfig.setProperty("hibernate.connection.pool_size", "1");	
		mConfig.addAnnotatedClass(AlbumWithAnnotations.class);
		mSessionFactory = mConfig.buildSessionFactory();
	}
	
	@BeforeClass
	@SuppressWarnings("deprecation")
	public static void setupHibernateH2Config(){
		mConfig = new Configuration();
		mConfig.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		mConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		mConfig.setProperty("hibernate.connection.url", "jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1;MVCC=TRUE");		
		mConfig.setProperty("hibernate.connection.username", "sa");
		mConfig.setProperty("hibernate.connection.password", "");
		mConfig.setProperty("hibernate.connection.pool_size", "1");
		// Disable the second-level cache
		mConfig.setProperty("cache.provider_class","org.hibernate.cache.internal.NoCacheProvider");
		// Echo all executed SQL to stdout
		mConfig.setProperty("show_sql","true"); 
		// Drop and re-create the database schema on startup
		mConfig.setProperty("hibernate.hbm2ddl.auto","create-drop");
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
