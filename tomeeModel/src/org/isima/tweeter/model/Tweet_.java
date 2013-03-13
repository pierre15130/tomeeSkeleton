package org.isima.tweeter.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-12T09:44:43.233+0100")
@StaticMetamodel(Tweet.class)
public class Tweet_ {
	public static volatile SingularAttribute<Tweet, String> id_tweet;
	public static volatile SingularAttribute<Tweet, Date> date;
	public static volatile SingularAttribute<Tweet, String> contenu;
	public static volatile SingularAttribute<Tweet, byte[]> photo;
	public static volatile SingularAttribute<Tweet, User> user_t;
}
