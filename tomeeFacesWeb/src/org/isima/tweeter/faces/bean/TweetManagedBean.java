package org.isima.tweeter.faces.bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.isima.tweeter.model.Tweet;
import org.isima.tweeter.model.User;
import org.isima.tweeter.services.TweetServiceLocal;

@ManagedBean
@SessionScoped
public class TweetManagedBean {
	
	// injected attributes
	
	@EJB
	private TweetServiceLocal tweetService;
	
	// attributes
	private String contenu;
	private byte[] photo;
	
    private Tweet tweet = new Tweet();  
    
    private User user;
	// actions
	
	public String tweeter()
	{
		try
		{
			// try to log in
			tweet = tweetService.create(contenu, photo, user);
			if(tweet != null)
			{
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "Logged !", user.toString()));
//				//récupérer les tweets à afficher
//				tweets = userService.getListTweet(user);
//				tweets = new ArrayList<Tweet>();  
//				tweets.add(new Tweet("test tweet", null, user));
				return "tweetSuccess";
			}
			else
			{
//				 FacesMessage msg = new FacesMessage("Error", "Loggin or password unknown");  
//		         FacesContext.getCurrentInstance().addMessage(null, msg);  
				 return "tweetFailed";
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
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Tweet getTweet() {
		return tweet;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}
	

}
