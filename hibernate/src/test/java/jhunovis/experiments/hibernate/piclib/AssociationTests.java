package jhunovis.experiments.hibernate.piclib;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

public class AssociationTests extends TestBase {

	/** Demonstrates how to correctly store a bidirectional one-to-many link.
	 */
	@Test
	public void bidirectionalOneToMany() {

		Session session = mSessionFactory.openSession();
		session.beginTransaction();

		int picId;
		Picture picture = new Picture();
		picture.setGeoLocation(new GeoLocation());

		Comment comment = new Comment();
		comment.setComment("Awesome!");

		// establish link
		comment.setPicture(picture);
		picture.addComment(comment);

		// Saving one end of an association will not automatically save the
		// other end as well. For this, cascading must be configured.
		session.save(picture);
		session.save(comment);

		// As usual the id is only available after the instance has been
		// attached to a persistence context.
		picId = picture.getId();

		session.getTransaction().commit();
		session.close();

		// restore by id
		session = mSessionFactory.openSession();
		session.beginTransaction();

		picture = (Picture) session.load(Picture.class, picId);
		assertEquals("Awesome!", picture.getComments().get(0).getComment());

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
		int picId, commentId;
		Picture picture = new Picture();
		picture.setDescription("Commented Picture");

		Comment comment = new Comment();
		comment.setComment("Nice one!");
		comment.setPicture(picture);
		picture.addComment(comment);

		session.save(picture);
		picId = picture.getId();

		commentId = comment.getId();
		
		session.getTransaction().commit();
		session.close();

		// restore by id
		session = mSessionFactory.openSession();
		session.beginTransaction();

		picture = (Picture) session.load(Picture.class, picId);
		assertEquals(0, picture.getComments().size());

		// comment has never been stored
		comment = (Comment) session.get(Comment.class, commentId);
		assertNull(comment);

		session.close();

	}

}
