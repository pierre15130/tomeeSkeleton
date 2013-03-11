package org.isima.tweeter.services;

import javax.ejb.Local;

import org.isima.tweeter.model.Tweet;
import org.isima.tweeter.model.User;

@Local
public interface TweetServiceLocal {
	Tweet create(String contenu, byte[] photo, User u);

}
