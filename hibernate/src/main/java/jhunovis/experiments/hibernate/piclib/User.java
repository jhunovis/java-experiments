package jhunovis.experiments.hibernate.piclib;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

/**
 * Represents a user of the library. It is bloated on purpose.
 * 
 * @author jhunovis
 * 
 */
@Entity
public class User {

	/*
	 * The userid misses an generator annotation and must be initialized by the
	 * user.
	 */
	@Id
	private String userId;

	/*
	 * The main purpose for having this field, is to demonstrate the
	 * breaking-down of the default mapping of the String type. It maps to a
	 * varchar(255), which obviously cannot take strings longer than that. It is
	 * better tested before going into production.
	 * 
	 * Since we plan on storing rather excessively long strings inside the
	 * description, we will mark it for lazy loading. Per default basic
	 * properties, like strings, will be fetch together with the containing
	 * instance.
	 */
	@Basic(fetch = FetchType.LAZY)
	private String description;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
