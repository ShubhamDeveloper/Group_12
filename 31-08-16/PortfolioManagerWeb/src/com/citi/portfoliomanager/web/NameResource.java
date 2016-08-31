package com.citi.portfoliomanager.web;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.citi.portfoliomanager.ejb.PortfolioManagerBeanLocal;

@Path("/names")
public class NameResource {

	PortfolioManagerBeanLocal bean;

	public NameResource() {
		try {
			InitialContext context = new InitialContext();
			bean = (PortfolioManagerBeanLocal) context.lookup(
					"java:app/PortfolioManagerEJB/PortfolioManagerBean!com.citi.portfoliomanager.ejb.PortfolioManagerBeanLocal");

		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	@POST
	@Consumes("application/json")
	//@Produces("text/plain")
	public void putName(PortfolioNameRecieve p) {
		 bean.putPortfolioName(p.getName());
	}
}

class PortfolioNameRecieve{
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
