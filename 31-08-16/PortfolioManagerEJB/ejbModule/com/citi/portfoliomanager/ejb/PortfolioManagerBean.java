package com.citi.portfoliomanager.ejb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.citi.portfoliomanager.jpa.Category;
import com.citi.portfoliomanager.jpa.CategoryName;
import com.citi.portfoliomanager.jpa.ClientPortfolio;
import com.citi.portfoliomanager.jpa.MarketData;
import com.citi.portfoliomanager.jpa.PortfolioName;
import com.citi.portfoliomanager.jpa.Stock;
import com.citi.portfoliomanager.jpa.Stockname;
import com.citi.portfoliomanager.jpa.Transaction;

//import solutions.onlineretailer.ejb.Product;

/**
 * Session Bean implementation class PortfolioManagerBean
 */
@Stateful
@Remote(PortfolioManagerBeanRemote.class)
@Local(PortfolioManagerBeanLocal.class)
public class PortfolioManagerBean implements PortfolioManagerBeanRemote, PortfolioManagerBeanLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext(name = "Por" + "tfolioManagerJPA-PU")
	private EntityManager entityManager;

	public PortfolioManagerBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MarketData> sayHello() {
		String sql = "SELECT d FROM MarketData AS d";
		System.out.println(sql);
		TypedQuery<MarketData> query = entityManager.createQuery(sql, MarketData.class);

		// Execute the query, and get a collection of products back.
		List<MarketData> MarketDataList = query.getResultList();

		return MarketDataList;

	}

	public List<ClientPortfolio> getAllPortfolio() {

		String sql = "Select c FROM ClientPortfolio as c";
		TypedQuery<ClientPortfolio> query = entityManager.createQuery(sql, ClientPortfolio.class);
		return query.getResultList();
	}
	// @Override
	// public void putString(MarketData MarketData){
	// entityManager.persist(MarketData);
	//
	// }

	@Override
	public double getExposureByIndustry(String categoryName) {
		String sql = "";
		TypedQuery<ClientPortfolio> query = entityManager.createQuery(sql, ClientPortfolio.class);
		List<ClientPortfolio> stocks = query.getResultList();
		double totalExposureByIndustry = 0.0;
		for (ClientPortfolio stock : stocks) {
			totalExposureByIndustry += stock.getQuantity() * stock.getBuy_Price();
		}
		return totalExposureByIndustry;
	}

	@Override
	public String putIntoPortfolio(String stockName, int quantity, double price, Date date, String portfolioID) {

		return null;
	}

	@Override
	public void addNewTransaction(ClientPortfolio clientPortfolio) {
		System.out.println(clientPortfolio.getBuy_Price());
		entityManager.persist(clientPortfolio);

	}

	@Override
	public List<CategoryName> getAllCategories() {
		String sql = "SELECT c FROM CategoryName as c";
		TypedQuery<CategoryName> query = entityManager.createQuery(sql, CategoryName.class);
		return query.getResultList();
	}

	@Override
	public List<Stockname> getAllStocks() {

		String sql = "SELECT s FROM Stockname as s";
		TypedQuery<Stockname> query = entityManager.createQuery(sql, Stockname.class);
		return query.getResultList();
	}

	@Override
	public List<ClientPortfolio> getAllStocksInPortfolioByCategoryName(String categoryName) {
		String sql = "";
		return null;
	}

	@Override
	public void putPortfolioName(String name) {
		PortfolioName portfolioname = new PortfolioName();
		portfolioname.setName(name);
		entityManager.persist(portfolioname);
		// return "Portfolio has been created!";
	}

	@Override
	public List<Stock> getAllStocksInACategory(String categoryName) {
		System.out.println("Hello World");
		String sql = "SELECT c FROM Category as c Where c.categoryName = :name";
		TypedQuery<Category> query = entityManager.createQuery(sql, Category.class);
		query.setParameter("name", categoryName);
		System.out.println(sql);
		Category c = query.getSingleResult();
		return c.getStocks();
	}

	@Override
	public MarketData getCurrentMarketData(String stockName) {
		///// ADDDDD DAAAATE!!!!!!!!!!!!!!!!!!!///

		String sql = "Select md from MarketData as md where md.id.stockname=:name";
		System.out.println(sql);
		TypedQuery<MarketData> query = entityManager.createQuery(sql, MarketData.class);

		Date date = new Date(20110103);

		query.setParameter("name", stockName);
		// query.setParameter("date", date);

		MarketData currentdata = query.getSingleResult();

		return currentdata;

	}

	@Override
	public List<Transaction> getExposureByCategoryName(String categoryName) {

		String sql = "SELECT c FROM Category as c Where c.categoryName=:name";
		TypedQuery<Category> query = entityManager.createQuery(sql, Category.class);
		query.setParameter("name", categoryName);
		//System.out.println(sql);
		Category c = query.getSingleResult();
		int cId = c.getCategoryId();
		// System.out.println(cId);
		String sqlTransaction = "Select T from Transaction as T where T.category.categoryId=:id";
		TypedQuery<Transaction> queryTransaction = entityManager.createQuery(sqlTransaction, Transaction.class);
		queryTransaction.setParameter("id", cId);
		return queryTransaction.getResultList();
	}

	@Override
	public Map<String, Long> getQuantityForAStock() {
		String sql = "SELECT T.stock.stockName, SUM(T.quantity) FROM Transaction as T GROUP BY T.stock.stockName";
		// String sql = "SELECT T FROM TRANSACTION AS T";
		//System.out.println(sql);
		TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class);
		List<Object[]> transactions = query.getResultList();
		Map<String, Long> resultMap = new HashMap<String, Long>(transactions.size());
		for (Object[] result : transactions)
			resultMap.put((String) result[0], (Long) result[1]);
		// HashMap<String,Integer> investmentSet = new HashMap<>();
		// for(Transaction transaction: transactions){
		// investmentSet.put(key, value)
		// }
		return resultMap;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		String sql = "Select T from Transaction as T";
		TypedQuery<Transaction> query = entityManager.createQuery(sql, Transaction.class);
		return query.getResultList();
	}

	@Override
	public Stock getStock(String stockName) {
		String sql = "SELECT s FROM Stock as s WHERE s.stockName =:name";
		TypedQuery<Stock> query = entityManager.createQuery(sql, Stock.class);
		query.setParameter("name", stockName);
		Stock stock = query.getSingleResult();
		//System.out.println(stock.getStockName());
		return stock;
	}

	@Override
	public void addNewTransactionToDB(Transaction transaction) {
		entityManager.persist(transaction);

	}

	@Override
	public Category getCategoryInStock(String stockName) {
//		String sql = "SELECT s FROM Stock as s WHERE s.stockName =:name";
//		TypedQuery<Stock> query = entityManager.createQuery(sql, Stock.class);
//		query.setParameter("name", stockName);
//		Stock stock = query.getSingleResult();
//		System.out.println(stock.getStockName());
//		return stock.getCategory();
		
		Stock stock = entityManager.find(Stock.class, stockName);
		return stock.getCategory();
	}

	@Override
	public void deleteTransactionWithId(int deleteTransactionId) {
		Transaction transaction = entityManager.find(Transaction.class, deleteTransactionId);
		if (transaction != null) {
			entityManager.remove(transaction);
		}
	}

	@Override
	public void modifyATransaction(int transactionId, double buyPrice, int quantity) {
		Transaction transaction = entityManager.find(Transaction.class, transactionId);
		if (transaction != null) {
			transaction.setBuyprice(buyPrice);
			transaction.setQuantity(quantity);
		}
		// entityManager.persist(transaction);

	}

	@Override
	public double getProfitInStock(String stockName) {
		String sql = "Select MD FROM MarketData as MD WHERE MD.id.stockname = :name AND md.id.date = :currDate";
		TypedQuery<MarketData> query = entityManager.createQuery(sql, MarketData.class);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			query.setParameter("currDate", format.parse("2011-01-03"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.setParameter("name", stockName);
		
		MarketData md = query.getSingleResult();
		double profit =-((getInvestmentForAStock().get(stockName))-(md.getClosePrice()*getQuantityForAStock().get(stockName))); 
		return profit;
	}
	
	public Map<String, Double> getInvestmentForAStock() {
		String sql = "SELECT T.stock.stockName, SUM(T.quantity*T.buyprice) FROM Transaction as T GROUP BY T.stock.stockName";
		
		//System.out.println(sql);
		TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class);
		List<Object[]> transactions = query.getResultList();
		Map<String, Double> resultMap = new HashMap<String, Double>(transactions.size());
		for (Object[] result : transactions)
			resultMap.put((String) result[0], (Double) result[1]);
		// HashMap<String,Integer> investmentSet = new HashMap<>();
		// for(Transaction transaction: transactions){
		// investmentSet.put(key, value)
		// }
		return resultMap;
	}
	

}
