package jhunovis.experiments.hibernate;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
 
@Entity
@Table(name="albums")
public class Album implements Serializable{
		
		private static final long serialVersionUID = 1L;		
		private String mTitle;
		private String mPublisher;
		private int mId;

		@Id
		@GeneratedValue(generator="increment")
		@GenericGenerator(name="increment", strategy = "increment")
		public int getId() {
			return mId;
		}
		public void setId(int id) {
			mId = id;
		}
		public String getTitle() {
			return mTitle;
		}
		public void setTitle(String title) {
			mTitle = title;
		}
		public String getPublisher() {
			return mPublisher;
		}
		public void setPublisher(String publisher) {
			mPublisher = publisher;
		}
		
}
