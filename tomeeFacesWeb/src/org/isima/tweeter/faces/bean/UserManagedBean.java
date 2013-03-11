package org.isima.tweeter.faces.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.isima.tweeter.model.User;
import org.isima.tweeter.services.UserServiceLocal;

@ManagedBean
@SessionScoped
public class UserManagedBean {
	
	// injected attributes
	
	@EJB
	private UserServiceLocal userService;
	
	// attributes
	private User user1;
	private User user2;
	
  
	public String connectUser()
	{
		userService.connectUser(user1,user2);
		return "success";
	}
	
	public User getUser1() {
		return user1;
	}


	public void setUser1(User user1) {
		this.user1 = user1;
	}


	public User getUser2() {
		return user2;
	}


	public void setUser2(User user2) {
		this.user2 = user2;
	}

	

}
