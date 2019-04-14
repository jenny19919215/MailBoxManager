package ejb;

import javax.ejb.Remote;

import entity.*;

@Remote public interface IUserDirectory {

	public String addUser(String username);
	
	public Userdb findUser(String username);

	public String removeUser(Userdb userDB);

	public String lookupAllUsers();
	
	public String lookupAUserRights(String username);
	
	public String updateAUserRights(String username,String userReadright,String userWriteright);
	
	
}
