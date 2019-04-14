package client;

import javax.naming.InitialContext;
import javax.ejb.*;

import java.util.Collection;
import java.util.List;

import ejb.IUserDirectory;
import entity.*;

public class UserDirectoryClient {

	public static void main(String args[]) {
		
        try {

            InitialContext ic = new InitialContext();
            IUserDirectory iuserdirectory = (IUserDirectory) ic.lookup("ejb.IUserDirectory");

            //Add User1
            System.out.println("Add user1:" +iuserdirectory.addUser("chenchen"));
            
            //Add User2
            System.out.println("Add user2:" +iuserdirectory.addUser("ying"));
            
            //Add User3
            System.out.println("Add user3:" +iuserdirectory.addUser("C"));
            
            //Look up all users
            System.out.println("The list of all users: " +iuserdirectory.lookupAllUsers());
            
            //Look up the user rights of User3
            System.out.println("The rights of user3: " +iuserdirectory.lookupAUserRights("C"));
            
            //Update the user rights of User3
            System.out.println("Now we update the rights of user3: " +iuserdirectory.updateAUserRights("C","false","false"));
            
            //Re-look up the user rights of User1
            System.out.println("The rights of user3 now is: " +iuserdirectory.lookupAUserRights("C"));
            
            //Remove User3 (Find it first)
            Userdb u=iuserdirectory.findUser("C");
            System.out.println("Then we remove the user3 :" +iuserdirectory.removeUser(u));
         
            //Look up all users
            System.out.println("The list of all users now is : " +iuserdirectory.lookupAllUsers());    
            
            //Update the user rights of User1 and User2
            System.out.println("Now we update the rights for newsgroup of user1 and user2 as: \n The User1 chenchen can read but cannot write, \n The User2 ying can write but cannot read.");
            iuserdirectory.updateAUserRights("chenchen","true","false");
            iuserdirectory.updateAUserRights("ying","false","true");
            
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
		
		
	}
	
	
}
