package ejb;

import javax.ejb.Remote;

import entity.*;

@Remote public interface IMessageManager {

	//send message to a mailbox
	public String sendAMessageToABox(String sendername,String receivername,String subject,String body);
	
	//read the messages which haven't been read in the user's mailbox 
	public String readAUserNewMessage(String username);
	
	//read all messages in the user's mailbox
	public String readAUserAllMessage(String username );
	
	

	//delete all messages in the user's mailbox
	public String deleteAUserMessage(String username);
	
	//delete the messages which have been read in the user's mailbox
	public String deleteAUserReadMessage(String username);
	

	
	//send news to newsgroup
	public String sendNews(String sendername,String subject,String body);
	
	//read all news in the newsgroup
	public String readNews(String username);

}

