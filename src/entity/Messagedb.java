package entity;

import java.io.Serializable;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name="Messagedb_Table")
public class Messagedb implements Serializable{
	
	
    private String id;
    private String sendername;
    private String receivername;
    private String sendingdate;
    private String subject;
    private String body;
    private String alreadyread;
    
   // private Userdb userdb;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
	     return id;
	  }
	
	public void setId(String id) {
	     this.id=id;
	  }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }
    
    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }   
    
   public String getSendingdate() {
        return sendingdate;
    }

    public void setSendingdate(String sendingdate) {
        this.sendingdate = sendingdate;
    }    
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public String getAlreadyread() {
        return alreadyread;
    }

    public void setAlreadyread(String alreadyread) {
        this.alreadyread = alreadyread;
    }
    
    
   /* @ManyToOne()
    public Userdb getUserdb() {
        return userdb;
    }

    public void setUserdb(Userdb userdb) {
        this.userdb = userdb;
    }*/
}
