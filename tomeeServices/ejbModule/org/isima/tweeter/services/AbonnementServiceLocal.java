package org.isima.tweeter.services;

import javax.ejb.Local;

import org.isima.tweeter.model.Abonnement;
import org.isima.tweeter.model.User;

@Local
public interface AbonnementServiceLocal {
	
	Abonnement create (User u1, User u2);
	
	void delete(User u1, User u2);

}
