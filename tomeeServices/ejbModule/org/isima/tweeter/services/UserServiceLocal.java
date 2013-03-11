package org.isima.tweeter.services;

import java.util.List;

import javax.ejb.Local;

import org.isima.tweeter.model.Tweet;
import org.isima.tweeter.model.User;

@Local
public interface UserServiceLocal {
	
	User login(String id, String password);
	
	User create(String name, String email,String username, String password);

	List<Tweet> getListTweet(User user);
	
	List<User> getAllUsers(User u);
	
	void connectUser(User u1, User u2);

}
