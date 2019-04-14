package entity;

import java.io.Serializable;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

import java.util.Collection;
import java.util.ArrayList;

@Entity
@Table(name="Userdb_Table")
public class Userdb implements Serializable{

	    private String name;
	    private String readright;
	    private String writeright;
	    
	   //  private Collection<Messagedb> messagedbs = new ArrayList<Messagedb>();
		
	    @Id
		public String getName() {
		     return name;
		  }
	
	    public void setName(String name) {
	        this.name = name;
	    }

	
	    public String getReadright() {
	        return readright;
	    }

	    public void setReadright(String readright) {
	        this.readright = readright;
	    }
	

	    public String getWriteright() {
	        return writeright;
	    }

	    public void setWriteright(String writeright) {
	        this.writeright = writeright;
	    }	
	    
/*	    @OneToMany(cascade=ALL, mappedBy="userdb")
	    public Collection<Messagedb> getMessagedbs() {
	        return messagedbs;
	    }

	    public void setMessagedbs(Collection<Messagedb> newValue) {
	        this.messagedbs = newValue;
	    }*/

}
