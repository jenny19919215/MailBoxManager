package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Date;
import java.util.Map;

import entity.*;

@Stateful
public class MessageManager implements IMessageManager{
	
	@PersistenceContext(unitName="pu2")
    private EntityManager em;
	
	@Override
	public String sendAMessageToABox(String sendername, String receivername,
			String subject, String body) {
		
		//Data Base
		Messagedb newmessage= new Messagedb();
		
		newmessage.setSendername(sendername);
		newmessage.setReceivername(receivername);
		newmessage.setSubject(subject);
		newmessage.setBody(body);
		newmessage.setAlreadyread("false");
		
		Date dt = new Date();	
		newmessage.setSendingdate(dt.toString());
		
		// Persist the new message
		em.persist(newmessage);
			
		return ("Send message success!\n");
		
	}

	@Override
	public String readAUserNewMessage(String username) {
		String str="";
		Query q1 = em.createQuery("select m.subject, m.body, m.sendingdate from Messagedb m where m.receivername = :receivername and m.alreadyread= :alreadyread");

		String alreadyread="false";
		
		q1.setParameter("receivername", username);
		q1.setParameter("alreadyread", alreadyread);
		
		List<Object[]> results1 = q1.getResultList();
		for (Object[] result : results1)
		{
		  str= str.concat((String)result[0]);	
		  str= str.concat("     ");
		  str= str.concat((String)result[1]);	
		  str= str.concat("     ");
		  str= str.concat((String)result[2]);	
		  str= str.concat("\n");	
		 
		}

	
		
		Query q2= em.createQuery("update Messagedb m set m.alreadyread= :alreadyread where m.receivername = :receivername");
		
		String alreadyread2="true";
		
		q2.setParameter("receivername", username);
		q2.setParameter("alreadyread", alreadyread2);
		
		q2.executeUpdate();
		
		return ("New messages are : \n"+ str); 
		
	}

	@Override
	public String readAUserAllMessage(String username) {
		
		String str1="";
		Query q3 = em.createQuery("select m.subject, m.body, m.sendingdate from Messagedb m where m.receivername = :receivername");

		q3.setParameter("receivername", username);
	
		List<Object[]> results3 = q3.getResultList();
		for (Object[] result : results3)
		{
		  str1= str1.concat((String)result[0]);	
		  str1= str1.concat("     ");
		  str1= str1.concat((String)result[1]);	
		  str1= str1.concat("     ");
		  str1= str1.concat((String)result[2]);
		  str1= str1.concat("\n");	
		 
		}
		
		Query q4= em.createQuery("update Messagedb m set m.alreadyread= :alreadyread where m.receivername = :receivername");
		
		String alreadyread4="true";
		
		q4.setParameter("receivername", username);
		q4.setParameter("alreadyread", alreadyread4);
		
		q4.executeUpdate();
	
		return ("\n"+str1);
		
		
		

	}

	@Override
	public String deleteAUserMessage(String username) {
		
		Query q5= em.createQuery("delete from Messagedb m where m.receivername = :receivername");	
		
		q5.setParameter("receivername", username);
		
		q5.executeUpdate();
		
		return ("Delete all messages success!\n");
	}

	@Override
	public String deleteAUserReadMessage(String username) {
		
		Query q6= em.createQuery("delete from Messagedb m where m.receivername = :receivername and m.alreadyread= :alreadyread");

		String alreadyread3="true";
				
		q6.setParameter("receivername", username);
		q6.setParameter("alreadyread", alreadyread3);
		
		q6.executeUpdate();
		
		return ("Delete the messages which have been read success!\n");
	}

	@Override
	public String sendNews(String sendername, String subject, String body) {
		
		//check the user's write right
		Query q7 = em.createQuery("select u.writeright from Userdb u where u.name = :name");

		q7.setParameter("name", sendername);
		
		List results7 = q7.getResultList();
		Iterator iterator7 = results7.iterator();
		
		String checker=iterator7.next().toString();
		
		if(checker.equals("true"))
		{
			//Data Base
			Messagedb newnews= new Messagedb();
			
			newnews.setSendername(sendername);
			newnews.setReceivername("newsgroup");
			newnews.setSubject(subject);
			newnews.setBody(body);
			newnews.setAlreadyread("news");
			
			Date dt = new Date();	
			newnews.setSendingdate(dt.toString());
			
			// Persist the new message
			em.persist(newnews);
				
			return ("Send message to newsgroup success!\n");
		
		}
		else
			
			return ("You don't have the right to send message to newsgroup!\n");
	}

	@Override
	public String readNews(String username) {
		
		
		//check the user's read right
		Query q8 = em.createQuery("select u.readright from Userdb u where u.name = :name");

		q8.setParameter("name", username);
		
		List results8 = q8.getResultList();
		Iterator iterator8 = results8.iterator();
		
		String checker=iterator8.next().toString();
		
		if(checker.equals("true"))
		{	String str2="";
			Query q9 = em.createQuery("select m.subject, m.body, m.sendingdate from Messagedb m where m.receivername = :receivername");
			String receiver="newsgroup";
			q9.setParameter("receivername", receiver);
			
			
			List<Object[]> results9 = q9.getResultList();
			for (Object[] result : results9)
			{
			  str2= str2.concat((String)result[0]);	
			  str2= str2.concat("     ");
			  str2= str2.concat((String)result[1]);	
			  str2= str2.concat("     ");
			  str2= str2.concat((String)result[2]);
			  str2= str2.concat("\n");	
			 
			}	

			return ("All messages in newsgroup are : \n"+ str2); 
		
		}
		else
			
			return ("You don't have the right to read message in newsgroup!\n");
		
	}

}
