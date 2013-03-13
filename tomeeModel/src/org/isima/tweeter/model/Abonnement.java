package org.isima.tweeter.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Abonnement
 *
 */
@Entity
@Table(name="ABONNEMENT")
public class Abonnement implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Abonnement() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id_abonnement;
	
	@ManyToOne (cascade={CascadeType.MERGE})
	//@JoinColumn(name = "user1_id")	
	private User u1;
	
	@ManyToOne (cascade={CascadeType.MERGE})
	//@JoinColumn(name = "user2_id")
	private User u2;
	
	public Abonnement(User u1, User u2)
	{
		this.u1 = u1;
		this.u2 = u2;
	}

	public User getU1() {
		return u1;
	}

	public void setU1(User u1) {
		this.u1 = u1;
	}

	public User getU2() {
		return u2;
	}

	public void setU2(User u2) {
		this.u2 = u2;
	}

	public String getId_abonnement() {
		return id_abonnement;
	}

	public void setId_abonnement(String id_abonnement) {
		this.id_abonnement = id_abonnement;
	}

	
   
}
