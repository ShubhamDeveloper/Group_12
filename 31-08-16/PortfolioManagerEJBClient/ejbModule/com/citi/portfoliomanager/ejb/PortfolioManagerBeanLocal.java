package com.citi.portfoliomanager.ejb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.citi.portfoliomanager.jpa.Category;
import com.citi.portfoliomanager.jpa.CategoryName;
import com.citi.portfoliomanager.jpa.ClientPortfolio;
//import com.citi.portfoliomanager.jpa.Dummy;
import com.citi.portfoliomanager.jpa.MarketData;
import com.citi.portfoliomanager.jpa.Stock;
import com.citi.portfoliomanager.jpa.Stockname;
import com.citi.portfoliomanager.jpa.Transaction;

@Local
public interface PortfolioManagerBeanLocal {
	public List<MarketData> sayHello();
		
	//public void putString();
	//public void putString(MarketData dummy);

//	public double getAllPortfolio();
	 public List<ClientPortfolio> getAllPortfolio();
	 public double getExposureByIndustry(String categoryName);

	String putIntoPortfolio(String stockName, int quantity, double price, Date date, String portfolioID);
	public void addNewTransaction(ClientPortfolio clientPortfolio);
	public List<CategoryName> getAllCategories();
	public List<Stockname> getAllStocks();
	public List<ClientPortfolio> getAllStocksInPortfolioByCategoryName(String categoryName);
	public void putPortfolioName(String name);
	public List<Stock> getAllStocksInACategory(String categoryName);
	public MarketData getCurrentMarketData(String stockName);
	public List<Transaction> getExposureByCategoryName(String categoryName);
	//public List<Transaction> getInvestment();
	public Map<String, Long> getQuantityForAStock();
	public List<Transaction> getAllTransactions();
	public Stock getStock(String stockName);
	public void addNewTransactionToDB(Transaction transaction);
	public Category getCategoryInStock(String stockName);
	public void deleteTransactionWithId(int deleteTransactionId);
	public void modifyATransaction(int transactionId , double buyPrice, int quantity);
	public double getProfitInStock(String stockName);
	public Map<String, Double> getInvestmentForAStock();
}
