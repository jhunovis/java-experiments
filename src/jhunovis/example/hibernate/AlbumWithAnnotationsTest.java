package jhunovis.example.hibernate;

import static org.junit.Assert.fail;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlbumWithAnnotationsTest {

	private static Configuration mConfig;
	private SessionFactory mSessionFactory;

	@BeforeClass
	public static void setupHibernateConfig(){
		mConfig = new Configuration();
		mConfig.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		mConfig.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/simple");
		mConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");		
		mConfig.setProperty("hibernate.connection.username", "eclipse");
		mConfig.setProperty("hibernate.connection.password", "");
		mConfig.setProperty("hibernate.connection.pool_size", "10");	
		mConfig.addAnnotatedClass(AlbumWithAnnotations.class);		
	}
	
	@Before
	public void setUp(){
		ServiceRegistryBuilder builder = new ServiceRegistryBuilder();
		ServiceRegistry registry = builder.buildServiceRegistry();
		// This throws an exception. Something in wrong with the registry.
		// mSessionFactory = mConfig.buildSessionFactory(registry);
		mSessionFactory = mConfig.buildSessionFactory();
	}

	@After
	public void tearDown(){
		if ( mSessionFactory != null ) {
			mSessionFactory.close();
		}
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
