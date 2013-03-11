package org.isima.tweeter.faces.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.isima.tweeter.services.UserServiceLocal;

@ManagedBean
@SessionScoped
public class HomeManagedBean {

	@EJB
	private UserServiceLocal userService;
}
