package jhunovis.experiments.hibernate.piclib;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TestBase {

	protected static SessionFactory mSessionFactory;
	protected final Logger logger = LoggerFactory
				.getLogger(BasicTests.class);

	@BeforeClass
	@SuppressWarnings("deprecation")
	public static void setupSessioFactory() {
		mSessionFactory = new Configuration().configure().buildSessionFactory();
	}

	@AfterClass
	public static void tearDown() {
		if (mSessionFactory != null) {
			mSessionFactory.close();
		}
	}

	public TestBase() {
		super();
	}

}