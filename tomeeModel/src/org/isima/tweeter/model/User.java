package org.isima.tweeter.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	@OneToMany
	private List<Tweet> liste_tweets;
	
	@ManyToMany
	private List<User> abonnes;
	
	

	@ManyToMany
	private List<User> abonnements;
	
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

	@Override
	public String toString() {
		return ""+id+"/"+password;
	}
	
	public List<User> getAbonnes() {
		return abonnes;
	}

	public void setAbonnes(List<User> abonnes) {
		this.abonnes = abonnes;
	}

	public List<User> getAbonnements() {
		return abonnements;
	}

	public void setAbonnements(List<User> abonnements) {
		this.abonnements = abonnements;
	}

//	public byte[] getAvatar() {
//		return avatar;
//	}
//
//	public void setAvatar(byte[] avatar) {
//		this.avatar = avatar;
//	}



}
