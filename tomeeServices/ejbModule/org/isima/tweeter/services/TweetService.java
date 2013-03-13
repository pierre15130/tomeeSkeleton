package org.isima.tweeter.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.isima.tweeter.model.Tweet;
import org.isima.tweeter.model.User;

/**
 * Session Bean implementation class TweetService
 */
@Stateless
@LocalBean
public class TweetService implements TweetServiceLocal {

    
	// injected attributes
	
	@PersistenceContext
	private EntityManager em;
	
	/**
     * Default constructor. 
     */
    public TweetService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Tweet create(String contenu, byte[] photo, User u) {
		
		u = em.getReference(User.class, u.getId());
		Tweet tweet = new Tweet(contenu, photo, u);
		em.persist(tweet);
		return tweet;
	}

	@Override
	public void delete(Tweet t) {
		// TODO Auto-generated method stub
		t = em.getReference(Tweet.class, t.getId_tweet());
		em.remove(t);
		
	}

}
