package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Collection;
import java.util.List;
import java.util.Iterator;

import entity.*;

@Stateful
public class UserDirectory implements IUserDirectory{
	
	@PersistenceContext(unitName="pu2")
    private EntityManager em;
	@Override
	public String addUser(String username) {		
		
		//Data Base
		Userdb newuser= new Userdb();
		newuser.setName(username);
		newuser.setReadright("true");
		newuser.setWriteright("true");

		// Persist the new user
		em.persist(newuser);
	
		return ("Add user success!\n");
	}

	public Userdb findUser(String username){
		
		Query q1 = em.createQuery("select u from Userdb u where u.name = :name");
		//set the java value "username" to sql value "name"
		q1.setParameter("name", username);
		return (Userdb)q1.getSingleResult();
		
		
	}
	@Override
	public String removeUser(Userdb userDB) {
		// Merge the user to the new persistence context
		Userdb user1 = em.merge(userDB);

		// Delete this record.
		em.remove(user1);
	
		return ("Delete user success!\n");
	}

	@Override
	public String lookupAllUsers() {
		
		Query q2 = em.createQuery("select u.name from Userdb u");

		//Get the result of query
		List results2 = q2.getResultList();
		Iterator iterator2 = results2.iterator(); 
		
		
		/* 可循环输出
		Query q2 = em.createQuery("select u.userName from UserDB u");
		for (String u : results2) {
		      System.out.println(u.getName());
		  }
		*/
		
		String str="";
		while(iterator2.hasNext()){
			  str= str.concat(iterator2.next().toString());	
			  str= str.concat("\n");	 
		 }

		return ("\n"+ str); 
	}

	@Override
	public String lookupAUserRights(String username) {
		
		//ReadRight
		
		Query q3 = em.createQuery("select u.readright from Userdb u where u.name = :name");
	
		//set the java value "username" to sql value "name"
		q3.setParameter("name", username);
		//Get the result of query
		List results3 = q3.getResultList();
		Iterator iterator3 = results3.iterator(); 
		
		//WriteRight
		Query q4 = em.createQuery("select u.writeright from Userdb u where u.name = :name");
		//set the java value "username" to sql value "name"
		q4.setParameter("name", username);
		//Get the result of query
		List results4 = q4.getResultList();
		Iterator iterator4 = results4.iterator(); 
		
		
		return ("The Read Right is "+iterator3.next().toString()+", and the Write Right is "+iterator4.next().toString()+"."); 
	}

	@Override
	public String updateAUserRights(String username, String userReadright, String userWriteright) {
		//ReadRight
		Query q5= em.createQuery("update Userdb u set u.readright=:readright where u.name =:name");
		//set the java value to sql value 
		q5.setParameter("readright", userReadright);
		q5.setParameter("name", username);
		
		q5.executeUpdate();
		
		//WriteRight
		Query q6= em.createQuery("update Userdb u set u.writeright=:writeright where u.name =:name");
		//set the java value to sql value
		q6.setParameter("writeright", userWriteright);
		q6.setParameter("name", username);
		
		q6.executeUpdate();
		
		return ("Update user right success!\n");
	}

}
