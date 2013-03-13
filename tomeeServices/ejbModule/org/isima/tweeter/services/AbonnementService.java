package org.isima.tweeter.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.isima.tweeter.model.Abonnement;
import org.isima.tweeter.model.Abonnement_;
import org.isima.tweeter.model.User;

/**
 * Session Bean implementation class AbonnementService
 */
@Stateless
@LocalBean
public class AbonnementService implements AbonnementServiceLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
	private EntityManager em;
	
    public AbonnementService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Abonnement create(User u1, User u2) {
		// TODO Auto-generated method stub
		u1 = em.getReference(User.class, u1.getId());
		u2 = em.getReference(User.class, u2.getId());
		Abonnement ab = new Abonnement(u1, u2);
		em.persist(ab);
		
		return ab;
	}
	
	@Override
	public void delete(User u1, User u2) {
		// TODO Auto-generated method stub
		u1 = em.getReference(User.class, u1.getId());
		u2 = em.getReference(User.class, u2.getId());
		Abonnement ab = em.getReference(Abonnement.class, getAbonnement(u1, u2).getId_abonnement());
		em.remove(ab);
		
		
	}
    
	private Abonnement getAbonnement(User u1, User u2) 
	{
		//use the criteria builder to create the query expression
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Abonnement> criteriabCriteriaQuery = criteriaBuilder.createQuery(Abonnement.class);
		// select from the User table
		Root<Abonnement> from = criteriabCriteriaQuery.from(Abonnement.class);
		// where 
		criteriabCriteriaQuery.where( 
				criteriaBuilder.and(
						criteriaBuilder.equal(from.get(Abonnement_.u1), u1),
						criteriaBuilder.equal(from.get(Abonnement_.u2), u2) )
				);
		
		//execute the query
		TypedQuery<Abonnement> query = em.createQuery(criteriabCriteriaQuery);
		List<Abonnement> resultList = query.getResultList();
		if(resultList != null && resultList.size()>0)
		{
			return resultList.get(0);
		}
		return null;
	}
    

}
