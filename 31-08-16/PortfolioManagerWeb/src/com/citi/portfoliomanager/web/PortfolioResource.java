package com.citi.portfoliomanager.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import com.citi.portfoliomanager.ejb.PortfolioManagerBeanLocal;
import com.citi.portfoliomanager.jpa.ClientPortfolio;
import com.citi.portfoliomanager.jpa.ClientPortfolioPK;
import com.citi.portfoliomanager.jpa.Transaction;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;




@Path("/portfolio")
public class PortfolioResource {

	private PortfolioManagerBeanLocal bean;

	public PortfolioResource() {
		try {
			InitialContext context = new InitialContext();
			bean = (PortfolioManagerBeanLocal) context.lookup(
					"java:app/PortfolioManagerEJB/PortfolioManagerBean!com.citi.portfoliomanager.ejb.PortfolioManagerBeanLocal");

		} catch (NamingException ex) {
			ex.printStackTrace();
		}

	}
	
	@GET
	@Path("/date")
	@Produces("text/plain")
	public String getDate() {
		return new Date().toString();
	}

	@GET
	@Produces("application/json")
	public List<ClientPortfolio> getEntirePortfolio() {
		return bean.getAllPortfolio();
	}

	@GET
	@Path("/stocks/{categoryName}")
	@Consumes("text/plain")
	@Produces("application/json")
	public List<ClientPortfolio> getAllStocksInPortfolioByCategoryName(@PathParam("categoryName") String categoryName){
		return bean.getAllStocksInPortfolioByCategoryName(categoryName);
	}
	
	
	
	@SuppressWarnings("deprecation")
	@POST
	@Consumes("application/json")
	// @Produces("application/json")
	public void addToPortfolio(NewTransaction newTransaction) throws ParseException {
	
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		
		
		ClientPortfolio clientPortfolio = new ClientPortfolio();
		ClientPortfolioPK clientPortfolioPK = new ClientPortfolioPK();
		clientPortfolioPK.setStock_name(newTransaction.getStockName());
		clientPortfolioPK.setDate(format.parse(newTransaction.getTransactionDate()));
		clientPortfolio.setId(clientPortfolioPK);
		clientPortfolio.setBuy_Price(newTransaction.getStockPrice());
		clientPortfolio.setPortfolio_id("122");
		clientPortfolio.setQuantity(newTransaction.getQuantity());
		bean.addNewTransaction(clientPortfolio);
	
		System.out.println(newTransaction.getStockName());
	}
	
	@GET
	@Produces("application/json")
	@Path("/investment")
	public List<Investment> getInvestment(){
		Map<String, Long> quantityMap = bean.getQuantityForAStock();
		Map<String,Double> exposureMap = bean.getInvestmentForAStock();
		List<Investment> investments = new ArrayList<Investment>();
		
		Set<String> nameSet = quantityMap.keySet();	
		for(String stockName : nameSet){
			System.out.println(stockName);
			Investment investment = new Investment();
			investment.setStockName(stockName);
			investment.setQuantity(quantityMap.get(stockName));
			investment.setExposure(exposureMap.get(stockName));
			investments.add(investment);
			
		}
	
		return investments;
	}
	
}

class Investment{
	private String stockName;
	private long quantity;
	private double exposure;
	public double getExposure() {
		return exposure;
	}
	public void setExposure(double exposure) {
		this.exposure = exposure;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}



//	public double getExposure() {
//		return exposure;
//	}
//	public void setExposure(double exposure) {
//		this.exposure = exposure;
//	}
}
class NewTransaction {
	private String stockName;
	private double stockPrice;
	private int quantity;
	private String category;
	private String transactionDate;
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

}
