package com.citi.portfoliomanager.web;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class PortfolioApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	
	
	public PortfolioApplication(){
	     singletons.add(new PortfolioResource());
	     singletons.add(new CategoryNameResource());
	     singletons.add(new StockResource());
	     singletons.add(new NameResource());
	     singletons.add(new CategoryResource());
	     singletons.add(new TransactionResource());
	}
	
	@Override
	public Set<Class<?>> getClasses() {
	     return empty;
	}
	
	@Override
	public Set<Object> getSingletons() {
	     return singletons;
	}

}
