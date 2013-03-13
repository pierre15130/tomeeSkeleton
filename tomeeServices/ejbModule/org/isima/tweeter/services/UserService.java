package org.isima.tweeter.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.isima.tweeter.model.Abonnement;
import org.isima.tweeter.model.Abonnement_;
import org.isima.tweeter.model.Tweet;
import org.isima.tweeter.model.Tweet_;
import org.isima.tweeter.model.User;
import org.isima.tweeter.model.User_;

@Stateless
public class UserService implements UserServiceLocal {
	
	// injected attributes
	
	@PersistenceContext
	private EntityManager em;
	
	
	// methods 
	
	@Override
	public User login(String username, String password) 
	{
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriabCriteriaQuery = criteriaBuilder.createQuery(User.class);
		// select from the User table
		Root<User> from = criteriabCriteriaQuery.from(User.class);
		// where 
		criteriabCriteriaQuery.where( 
				criteriaBuilder.and(
						criteriaBuilder.equal(from.get(User_.username), username),
						criteriaBuilder.equal(from.get(User_.password), password) )
				);
		
		//execute the query
		TypedQuery<User> query = em.createQuery(criteriabCriteriaQuery);
		List<User> resultList = query.getResultList();
		if(resultList != null && resultList.size()>0)
		{
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public User create(String name, String email, String username, String password)
	{
		User user = new User(name, email, username,password);
		em.persist(user);
		return user;
	}


	@Override
	public List<Tweet> getListTweet(User user) {
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Tweet> criteriabCriteriaQuery = criteriaBuilder.createQuery(Tweet.class);
		// select from the Tweet table
		Root<Tweet> from = criteriabCriteriaQuery.from(Tweet.class);
		// where 
		criteriabCriteriaQuery.where(criteriaBuilder.equal(from.get(Tweet_.user_t), user));
		
		//execute the query
		TypedQuery<Tweet> query = em.createQuery(criteriabCriteriaQuery);
		List<Tweet> resultList = query.getResultList();
		if(resultList != null && resultList.size()>0)
		{
			return resultList;
		}
		return null;

	}
	

	@Override
	public List<User> getAllUsers(User u) {
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriabCriteriaQuery = criteriaBuilder.createQuery(User.class);
		// select from the User table
		Root<User> from = criteriabCriteriaQuery.from(User.class);
		// where 
		criteriabCriteriaQuery.where(criteriaBuilder.notEqual(from.get(User_.id), u.getId()));
		
		//execute the query
		TypedQuery<User> query = em.createQuery(criteriabCriteriaQuery);
		List<User> resultList = query.getResultList();
		if(resultList != null && resultList.size()>0)
		{
			return resultList;
		}
		return null;
	}
	
	@Override
	public List<Abonnement> getAllAbonnes(User u) {
		// TODO Auto-generated method stub
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Abonnement> criteriabCriteriaQuery = criteriaBuilder.createQuery(Abonnement.class);
		// select from the User table
		Root<Abonnement> from = criteriabCriteriaQuery.from(Abonnement.class);
		// where 
		criteriabCriteriaQuery.where(criteriaBuilder.equal(from.get(Abonnement_.u1), u));
		
		//execute the query
		TypedQuery<Abonnement> query = em.createQuery(criteriabCriteriaQuery);
		List<Abonnement> resultList = query.getResultList();
		if(resultList != null && resultList.size()>0)
		{
			return resultList;
		}
		return null;
	}

}
