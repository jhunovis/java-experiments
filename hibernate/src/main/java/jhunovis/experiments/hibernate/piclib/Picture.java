package jhunovis.experiments.hibernate.piclib;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="pictures")
public class Picture {	
    @Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
    private int id;
    
	@Transient private List<Picture> derivatives = null;
    @Transient private Picture derivedOf = null;
    
    private String description = null;
    private URL file;
    private GeoLocation geoLocation = null;
    
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

	@Transient public Map<String,Date> timeStamps = null;

}
