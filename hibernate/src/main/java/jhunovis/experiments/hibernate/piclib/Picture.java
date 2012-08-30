package jhunovis.experiments.hibernate.piclib;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pictures")
public class Picture {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int id;

	/**
	 * This is a bidirectional many-to-one relationship: many pictures may be
	 * derived of one original picture, e.g. by apply different filters to it.
	 * 
	 * The "many" end of the association has to be the owner of the association.
	 * The owner can be designated via either "inverse" or "mappedBy". In this
	 * case I opt for mappedBy.
	 * 
	 * This association shall be stored in a dedicated table. The name attribute
	 * is mandatory if used on the to-one end. But since Hibernate will construct
	 * the name from the joined types instead of the field names, explicitly naming
	 * the table seems to be sensible anyway.
	 * 
	 * Leaving away the "mappedBy" will cause another link table to be created,
	 * instead of the "derivatives" table to be used.
	 * Todo: play with all possibilities show the resulting tables in blog posting.
	 */
	@OneToMany(mappedBy="derivedOf")
	private List<Picture> derivatives = null;
	
	@ManyToOne
	@JoinTable(name="derivatives",
		joinColumns= {@JoinColumn(name="original_fkey")},
		inverseJoinColumns={@JoinColumn(name="derived_fkey")})
	private Picture derivedOf = null;

	public Picture getDerivedOf() {
		return derivedOf;
	}

	public void setDerivedOf(Picture derivedOf) {
		this.derivedOf = derivedOf;
	}

	public List<Picture> getDerivatives() {
		return derivatives;
	}

	public void addDerived(Picture derived) {
		if (derivatives == null) derivatives = new ArrayList<Picture>();
		derivatives.add(derived);
	}
	
	@Transient
	private Map<String, Date> timeStamps = null;

	// basic attributes; no trouble here
	private String description = null;

	private URL file;

	// Complex attribute; will still be embedded; see its class!
	@Embedded
	private GeoLocation geoLocation = null;

	/**
	 * The (first-level) comments on this picture.
	 * 
	 * This is the one-end of a bidirectional association and must name the
	 * owner of the association. It suffices to name the property in the other
	 * end that owns this association with the {@code mappedBy} annotation.
	 */
	@OneToMany(mappedBy = "picture")
	private List<Comment> comments = null;

	public List<Comment> getComments() {
		return comments;
	}

	public void addComment(Comment comment) {
		if (comments == null) {
			comments = new ArrayList<Comment>();
		}
		comments.add(comment);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public URL getFile() {
		return file;
	}

	public void setFile(URL file) {
		this.file = file;
	}

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

}
