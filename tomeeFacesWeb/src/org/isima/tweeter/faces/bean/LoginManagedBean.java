package org.isima.tweeter.faces.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.isima.tweeter.model.Tweet;
import org.isima.tweeter.model.User;
import org.isima.tweeter.services.UserServiceLocal;

@ManagedBean
@SessionScoped
public class LoginManagedBean {
	
	// injected attributes
	
	@EJB
	private UserServiceLocal userService;
	
	// attributes
	
	private String username ;
	private String password ;
	
	
	private String new_name;
	private String new_email;
	private String new_username;
	private String new_password;
	
	private User user;
	
	private List<Tweet> tweets;  
    private List<User>  all_users;
    
    private Tweet selectedTweet;  
	// actions
	
	public String login()
	{
		try
		{
			// try to log in
			user = userService.login(username, password);
			if(user != null)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "Logged !", user.toString()));
				//récupérer les tweets à afficher
				tweets = userService.getListTweet(user);
				
				//tweets = new ArrayList<Tweet>();  
				//tweets.add(new Tweet("test tweet", null, user));
				return "loginSuccess";
			}
			else
			{
				 FacesMessage msg = new FacesMessage("Error", "Loggin or password unknown");  
		         FacesContext.getCurrentInstance().addMessage(null, msg);  
				 return "loginFailed";
			}
			
			// or create a new user
			//user = userService.create(username, password);
			
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "User created !", user.toString()));

		}
		catch(Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error !", e.getMessage()) );
			e.printStackTrace();
			return "loginFailed";
		}
	}
	
	public String register()
	{
		userService.create(new_name, new_email,new_username,new_password);
		return "loginSuccess";
	}
	
	
	public String home()
	{
		
		return "chaine";
	}
	
	public String connect()
	{
		all_users = userService.getAllUsers(user);	
		return "connect";
	}
	
	public String discover()
	{
		
		return "chaine";
	}
	
	public String me()
	{
		
		return "chaine";
	}
	
	public String composeTweet()
	{
		return "composeTweet";
	}
	
	public String disconnect()
	{
		
		return "disconnect";
	}
	
	
	// properties
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User getUser() {
		return user;
	}

	public String getNew_name() {
		return new_name;
	}
	public void setNew_name(String new_name) {
		this.new_name = new_name;
	}

	public String getNew_email() {
		return new_email;
	}

	public void setNew_email(String new_email) {
		this.new_email = new_email;
	}

	public String getNew_username() {
		return new_username;
	}

	public void setNew_username(String new_username) {
		this.new_username = new_username;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public Tweet getSelectedTweet() {
		return selectedTweet;
	}

	public void setSelectedTweet(Tweet selectedTweet) {
		this.selectedTweet = selectedTweet;
	}

	public List<User> getAll_users() {
		return all_users;
	}

	public void setAll_users(List<User> all_users) {
		this.all_users = all_users;
	}


}
