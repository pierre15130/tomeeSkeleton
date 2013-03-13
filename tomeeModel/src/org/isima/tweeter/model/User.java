package org.isima.tweeter.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TWEET_USER")
public class User 
{
	
	// Attributes
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	private String username;
	private String name;
	private String email;
	private String password;
	
	//@Lob
	//private byte[] avatar;
	
	@OneToMany(mappedBy="user_t", cascade=CascadeType.ALL)
    private List<Tweet> liste_tweets;
	
	@OneToMany (cascade = {CascadeType.ALL} ,mappedBy="u1")
	private List<Abonnement> liste_abonnes;
	
	@OneToMany (cascade = {CascadeType.ALL} ,mappedBy="u2")
	private List<Abonnement> liste_abonnements;
	
	// Constructors
	
	public User() {
		// do nothing
	}
	
	public User(String name, String email, String username,String password) 
	{
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = username;
	}
	
	// Properties
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// methods
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public List<Tweet> getListe_tweets() {
		return liste_tweets;
	}

	public void setListe_tweets(List<Tweet> liste_tweets) {
		this.liste_tweets = liste_tweets;
	}
	
	public List<Abonnement> getListe_abonnes() {
		return liste_abonnes;
	}

	public void setListe_abonnes(List<Abonnement> liste_abonnes) {
		this.liste_abonnes = liste_abonnes;
	}

	public List<Abonnement> getListe_abonnements() {
		return liste_abonnements;
	}

	public void setListe_abonnements(List<Abonnement> liste_abonnements) {
		this.liste_abonnements = liste_abonnements;
	}

	@Override
	public String toString() {
		return ""+id+"/"+password;
	}


}
