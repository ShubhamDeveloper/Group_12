package com.citi.portfoliomanager.web;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.citi.portfoliomanager.ejb.PortfolioManagerBeanLocal;
import com.citi.portfoliomanager.jpa.Stock;
import com.citi.portfoliomanager.jpa.Transaction;
@Path("/categories")
public class CategoryResource {
	
	private PortfolioManagerBeanLocal bean;

	public CategoryResource() {
		try {
			InitialContext context = new InitialContext();
			bean = (PortfolioManagerBeanLocal) context.lookup(
					"java:app/PortfolioManagerEJB/PortfolioManagerBean!com.citi.portfoliomanager.ejb.PortfolioManagerBeanLocal");

		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	@GET
	@Produces("application/json")
	@Path("/{categoryName}/stocks")
	public List<Stock> getAllStocksInACategory(@PathParam("categoryName") String categoryName){
		//categoryName="IT";
		System.out.println(bean.getAllStocksInACategory(categoryName));
		return bean.getAllStocksInACategory(categoryName);
	}
	@GET
	@Path("/{categoryName}/exposure")
	@Produces("application/json")
	public ExposureLocal getExposureByCategoryName(@PathParam("categoryName")String categoryName){
		List<Transaction> transactions  = bean.getExposureByCategoryName(categoryName);
		ExposureLocal el = new ExposureLocal();
		el.setCategoryName(categoryName);
		double totalExposure = 0.0;
		for(Transaction transaction: transactions){
			totalExposure += transaction.getBuyprice()*transaction.getQuantity(); 
		}
		el.setExposure(totalExposure);
		return el;
	}
}

class ExposureLocal{
	private String categoryName;
	private double exposure;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public double getExposure() {
		return exposure;
	}
	public void setExposure(double exposure) {
		this.exposure = exposure;
	}
}
