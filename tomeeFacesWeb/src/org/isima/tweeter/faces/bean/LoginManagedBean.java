package org.isima.tweeter.faces.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.isima.tweeter.model.Abonnement;
import org.isima.tweeter.model.Tweet;
import org.isima.tweeter.model.User;
import org.isima.tweeter.services.AbonnementServiceLocal;
import org.isima.tweeter.services.TweetServiceLocal;
import org.isima.tweeter.services.UserServiceLocal;

@ManagedBean
@SessionScoped
public class LoginManagedBean {
	
	// injected attributes == EJB
	
	@EJB
	private UserServiceLocal userService;
	
	@EJB
	private AbonnementServiceLocal abonnementService;
	
	@EJB
	private TweetServiceLocal tweetService;
	
	// attributes
	
	private String username ;
	private String password ;
	
	private String new_name;
	private String new_email;
	private String new_username;
	private String new_password;
	
	// user session courante
	private User user;

	// user abonnement-desabonnement 
	private User user2;
	
	private List<Tweet> follow_tweets;  
	private List<Tweet> tweets;  
    private List<Abonnement>  abt_abonnes;
    private List<Abonnement>  abt_non_abonnes;
    private List<User>  all_users;
    private List<User> users_abonnes;
    private List<User> users_non_abonnes;
    
    private Tweet selectedTweet;  
    private Tweet retweetTweet;
    private Tweet tweetToDelete;
    
	// actions

    public String connectUser()
	{
		abonnementService.create(user,user2);
		return connect();
		
	}
    
	public String disconnectUser()
	{
		abonnementService.delete(user,user2);
		return connect();
	}
		
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
				List<Tweet> temp = new ArrayList<Tweet>();
				abt_abonnes = userService.getAllAbonnes(user);
				follow_tweets = new ArrayList<Tweet>();
				if (abt_abonnes != null)
				{
					for (int i = 0 ; i < abt_abonnes.size() ; ++i)
					{
						//users_abonnes.add(abt_abonnes.get(i).getU2());
						temp = userService.getListTweet(abt_abonnes.get(i).getU2());
						if (temp != null)
						{
							for (int j = 0 ; j < temp.size() ; ++j)
							{
								follow_tweets.add(temp.get(j));
							}
						}
						
					}
				}
				return "loginSuccess";
			}
			else
			{
				 FacesMessage msg = new FacesMessage("Error", "Loggin or password unknown");  
		         FacesContext.getCurrentInstance().addMessage(null, msg);  
				 return "loginFailed";
			}
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
		user = null;
		user = userService.create(new_name, new_email,new_username,new_password);
		tweets = userService.getListTweet(user);
		return "loginSuccess";
	}
	
	public String home()
	{
		List<Tweet> temp = new ArrayList<Tweet>();
		abt_abonnes = userService.getAllAbonnes(user);
		users_abonnes = new ArrayList<User>();
		follow_tweets = new ArrayList<Tweet>();
		if (abt_abonnes != null)
		{
			for (int i = 0 ; i < abt_abonnes.size() ; ++i)
			{
				temp = userService.getListTweet(abt_abonnes.get(i).getU2());
				if (temp != null)
				{
					for (int j = 0 ; j < temp.size() ; ++j)
					{
						follow_tweets.add(temp.get(j));
					}
				}

			}
		}
		return "home";
	}
	
	public String connect()
	{
		all_users = userService.getAllUsers(user);
		abt_abonnes = userService.getAllAbonnes(user);
		users_abonnes = new ArrayList<User>();
		users_non_abonnes = new ArrayList<User>();
		if (abt_abonnes != null)
		{
			for (int i = 0 ; i < abt_abonnes.size() ; ++i)
			{
				users_abonnes.add(abt_abonnes.get(i).getU2());
			}
		}		
		boolean trouve = false;
		for (int i = 0 ; i < all_users.size() ; ++i)
		{
			trouve = false;
			for (int j = 0 ; j < users_abonnes.size() ; ++j)
			{
				if (users_abonnes.get(j).getId().compareTo(all_users.get(i).getId()) == 0)
				{
					trouve = true;
				}
						
			}
			if (!trouve)
			{
				users_non_abonnes.add(all_users.get(i));
			}		
		}
		return "connect";
	}
	
	
	public String deleteTweet()
	{
		try
		{
			tweetService.delete(tweetToDelete);
			return me();
		}
		catch(Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error !", e.getMessage()) );
			e.printStackTrace();
			return "tweetFailed";
		}
	}
	
	public String discover()
	{
		//not implemented yet
		return "discover";
	}
	
	public String me()
	{
		tweets = userService.getListTweet(user);
		return "myTweets";
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
	
	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
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
	
	public void setUser(User user) {
		this.user = user;
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

	public List<Abonnement> getAbt_abonnes() {
		return abt_abonnes;
	}

	public void setAbt_abonnes(List<Abonnement> abt_abonnes) {
		this.abt_abonnes = abt_abonnes;
	}

	public List<Abonnement> getAbt_non_abonnes() {
		return abt_non_abonnes;
	}

	public void setAbt_non_abonnes(List<Abonnement> abt_non_abonnes) {
		this.abt_non_abonnes = abt_non_abonnes;
	}

	public List<User> getUsers_abonnes() {
		return users_abonnes;
	}

	public void setUsers_abonnes(List<User> users_abonnes) {
		this.users_abonnes = users_abonnes;
	}

	public List<User> getUsers_non_abonnes() {
		return users_non_abonnes;
	}

	public void setUsers_non_abonnes(List<User> users_non_abonnes) {
		this.users_non_abonnes = users_non_abonnes;
	}

	public List<Tweet> getFollow_tweets() {
		return follow_tweets;
	}

	public void setFollow_tweets(List<Tweet> follow_tweets) {
		this.follow_tweets = follow_tweets;
	}

	public Tweet getRetweetTweet() {
		return retweetTweet;
	}

	public void setRetweetTweet(Tweet retweetTweet) {
		this.retweetTweet = retweetTweet;
	}

	public Tweet getTweetToDelete() {
		return tweetToDelete;
	}

	public void setTweetToDelete(Tweet tweetToDelete) {
		this.tweetToDelete = tweetToDelete;
	}


}
