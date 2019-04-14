package client;

import javax.naming.InitialContext;
import javax.ejb.*;

import java.util.Collection;
import java.util.List;

import ejb.*;
import entity.*;

public class MailBoxClient {
	
	public static void main(String args[]) {
	
    try {

        InitialContext ic = new InitialContext();
        IMessageManager imessagemanager = (IMessageManager) ic.lookup("ejb.IMessageManager");
        
        
        //send message1 to ying
        System.out.println("Send message1 from chenchen to ying:" + imessagemanager.sendAMessageToABox("chenchen","ying","test1","I am the first message"));

    	//read all messages in ying's mailbox
        System.out.println("All messages in ying's mailbox:" + imessagemanager.readAUserAllMessage("ying"));
        
        //send message2 to ying
        System.out.println("Send message2 from chenchen to ying:" + imessagemanager.sendAMessageToABox("chenchen","ying","test2","I am the second message"));
        
        //read the messages which haven't been read in ying's mailbox
        System.out.println("The messages which haven't been read in ying's mailbox:" + imessagemanager.readAUserNewMessage("ying"));
    	
        //read all messages in ying's mailbox
        System.out.println("All messages in ying's mailbox:" + imessagemanager.readAUserAllMessage("ying"));
        
        //send message3 to ying
        System.out.println("Send message3 from chenchen to ying:" + imessagemanager.sendAMessageToABox("chenchen","ying","test3","I am the third message"));
             
        //delete the messages which have been read in ying's mailbox
        System.out.println("Delete the messages which have been read in ying's mailbox:" + imessagemanager.deleteAUserReadMessage("ying"));
        
        //read all messages in ying's mailbox
        System.out.println("All messages in ying's mailbox now:" + imessagemanager.readAUserAllMessage("ying"));
        
        //delete all messages  in ying's mailbox
        System.out.println("Delete all messages in ying's mailbox:" + imessagemanager.deleteAUserMessage("ying"));
        
        //read all messages in ying's mailbox
        System.out.println("All messages in ying's mailbox:" + imessagemanager.readAUserAllMessage("ying"));

        
        
        
        //ying send a message to newsgroup
        System.out.println("ying send a message to newsgroup:" + imessagemanager.sendNews("ying","test newsgroup 1","First message for newsgroup"));
        
        //chenchen read messages from newsgroup
        System.out.println("chenchen read messages from newsgroup:" + imessagemanager.readNews("chenchen"));
        
        //chenchen send a message to newsgroup
        System.out.println("chenchen send a message to newsgroup:" + imessagemanager.sendNews("chenchen","test newsgroup 2","Second message for newsgroup"));
        
        //ying read messages from newsgroup
        System.out.println("ying read messages from newsgroup:" + imessagemanager.readNews("ying"));
        
        
    } catch(Exception e) {
        e.printStackTrace();
    }
	
	
}

}
