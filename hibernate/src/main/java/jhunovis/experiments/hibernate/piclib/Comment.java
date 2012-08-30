package jhunovis.experiments.hibernate.piclib;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * A comment on a picture.
 * 
 * @author jhunovis
 * 
 */
@Entity
public class Comment {

	@Id
	@GeneratedValue
	private int commentId;

	/** The actual comment text. */
	private String comment;

	/**
	 * The picture we are commenting on. This is a bi-directional relationship.
	 * As such it is always owned by the many-side of the relationship, i.e.
	 * this class. The picture must specify this property via {@code mappedBy}.
	 * 
	 * Some sources recommend adding a JoinColumn annotation to give the foreign
	 * key column that reference the picture a meaningful name. In this case
	 * this is not necessary as Hibernate (at version 4, which used)
	 * concatenates the name of association ends to create the name. It will
	 * result in "picture_id" here, which is just fine.
	 */
	@ManyToOne
	private Picture picture;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/** When this comment was given. */
	@Transient
	private Date timestamp;

}
