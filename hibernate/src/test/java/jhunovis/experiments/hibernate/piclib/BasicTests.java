package jhunovis.experiments.hibernate.piclib;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.hibernate.Session;
import org.hibernate.exception.DataException;
import org.hibernate.id.IdentifierGenerationException;
import org.junit.Test;


public class BasicTests extends BaseTests {

	/**
	 * Test Hibernate's basic functions for saving entities, and for restoring
	 * them by id.
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	public void saveAndLoad() throws MalformedURLException {

		Session session = mSessionFactory.openSession();
		session.beginTransaction();

		int picId;
		String descr = "First Picture";
		GeoLocationWithAltitude geoLoc = new GeoLocationWithAltitude(1d, 2d, 3d);
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

	/**
	 * Stores a long string in a string attribute.
	 * 
	 * Attributes that might receive long string, i.e. such that would not fit
	 * in a varchar(255) must be mapped appropriately.
	 * 
	 * @see {@link #longString2()}
	 */
	@Test
	public void longString() {
		StringBuilder aLongString = new StringBuilder(300);
		for (int i = 0; i < 20; i++) {
			aLongString.append("A long string!");
		}

		Session session = mSessionFactory.openSession();
		session.beginTransaction();

		User user = new User();
		user.setUserId("jhunovis");
		user.setDescription(aLongString.toString());
		session.save(user);
		String userId = user.getUserId();

		session.getTransaction().commit();
		session.close();

		// restore by id
		session = mSessionFactory.openSession();
		session.beginTransaction();

		user = (User) session.load(User.class, userId);
		assertEquals(aLongString.toString(), user.getDescription());

		session.close();
	}

	/**
	 * Stores a long string in an attribute that must not receive one.
	 * 
	 * This will cause Hibernate to throw an DataException, which should be
	 * either avoided by checking the length of the string, or by catching the
	 * Hibernate exception.
	 */
	@Test(expected = DataException.class)
	public void longString2() {
		StringBuilder aLongString = new StringBuilder(300);
		for (int i = 0; i < 20; i++) {
			aLongString.append("A long string!");
		}

		Session session = mSessionFactory.openSession();
		session.beginTransaction();

		User user = new User();
		user.setUserId(aLongString.toString());
		session.save(user);

		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Id-fields allow the attachment of a generator strategy that actually
	 * provides a suitable primary key value. This test demonstrates, that
	 * without such an generator attached, the id must be assigned by the
	 * developer, or else an exception will be thrown.
	 */
	@Test(expected = IdentifierGenerationException.class)
	public void undefinedNonGeneratedIds() {
		Session session = mSessionFactory.openSession();
		session.beginTransaction();

		User user1 = new User();
		user1.setDescription("User 1");
		session.save(user1);
		logger.debug("userId1 = {}", user1.getUserId());

		session.getTransaction().commit();
		session.close();

	}

	/**
	 * Associating objects with each other will not automatically persist the
	 * whole network one one end is saved. You must enabled this by use of the
	 * {@code cascade} property.
	 */
	@Test
	public void cascading() {
		Session session = mSessionFactory.openSession();
		session.beginTransaction();

		// create a picture and an associated comment
		// but only persist the picture explicitly
		int picId;
		Picture picture = new Picture();
		picture.setDescription("Commented Picture");

		Comment comment = new Comment();
		comment.setComment("Nice one!");
		comment.setPicture(picture);
		picture.addComment(comment);
		
		session.save(picture);
		picId = picture.getId();

		session.getTransaction().commit();
		session.close();

		// restore by id
		session = mSessionFactory.openSession();
		session.beginTransaction();

		picture = (Picture) session.load(Picture.class, picId);
		assertEquals("Nice One!", picture.getComments().get(0));

		session.close();

	}
	

}
