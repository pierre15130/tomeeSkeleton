package org.isima.tweeter.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="TWEET")
public class Tweet 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id_tweet;
	
	private Date date;
	private String contenu;
	
	@Lob
	private byte[] photo;
	
	@ManyToOne (cascade=CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user_t;
	
	@Transient
	private String nbMinute;
	
	public Tweet()
	{
		
	}
	
	public Tweet(String contenu, byte[] photo, User u)
	{
		this.contenu = contenu;
		this.user_t = u;
		this.photo = photo;
		this.date = new Date();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public User getUser() {
		return user_t;
	}

	public void setUser(User user) {
		this.user_t = user;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getId_tweet() {
		return id_tweet;
	}

	public void setId_tweet(String id_tweet) {
		this.id_tweet = id_tweet;
	}
	
	
	

}
