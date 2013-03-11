package org.isima.tweeter.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-09T17:26:23.249+0100")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> id;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile ListAttribute<User, Tweet> liste_tweets;
	public static volatile ListAttribute<User, User> abonnes;
	public static volatile ListAttribute<User, User> abonnements;
}
