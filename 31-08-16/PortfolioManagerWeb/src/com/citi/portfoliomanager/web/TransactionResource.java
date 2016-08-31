package com.citi.portfoliomanager.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.citi.portfoliomanager.ejb.PortfolioManagerBeanLocal;
import com.citi.portfoliomanager.jpa.ClientPortfolio;
import com.citi.portfoliomanager.jpa.ClientPortfolioPK;
import com.citi.portfoliomanager.jpa.Stock;
import com.citi.portfoliomanager.jpa.Transaction;

@Path("/transactions")
public class TransactionResource {
	private PortfolioManagerBeanLocal bean;

	public TransactionResource() {

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
	public List<TransactionLocal> getAllTransactions() {
		List<Transaction> transactions = bean.getAllTransactions();
		List<TransactionLocal> transactionLocalList = new ArrayList<TransactionLocal>();
		for (Transaction transaction : transactions) {
			TransactionLocal transactionLocal = new TransactionLocal();
			transactionLocal.setTransactionId(transaction.getTransactionId());
			transactionLocal.setStockname(transaction.getStock().getStockName());
			transactionLocal.setBuyprice(transaction.getBuyprice());
			transactionLocal.setQuantity(transaction.getQuantity());
			transactionLocal.setPurchase_date(transaction.getPurchase_date().toString());
			transactionLocalList.add(transactionLocal);
		}
		return transactionLocalList;
	}

	@POST
	@Consumes("application/json")
	public void addToTransactions(TransactionReceive newTransaction) throws ParseException {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Transaction transaction = new Transaction();
		Stock stock = bean.getStock(newTransaction.getStockName());
		transaction.setStock(stock);
		transaction.setBuyprice(newTransaction.getBuyPrice());
		transaction.setCategory(stock.getCategory());
		transaction.setPurchase_date(format.parse(newTransaction.getTransactionDate()));
		transaction.setQuantity(newTransaction.getQuantity());

		bean.addNewTransactionToDB(transaction);

		// ClientPortfolio clientPortfolio = new ClientPortfolio();
		// ClientPortfolioPK clientPortfolioPK = new ClientPortfolioPK();
		// clientPortfolioPK.setStock_name(newTransaction.getStockName());
		// clientPortfolioPK.setDate(format.parse(newTransaction.getTransactionDate()));
		// clientPortfolio.setId(clientPortfolioPK);
		// clientPortfolio.setBuy_Price(newTransaction.getStockPrice());
		// clientPortfolio.setPortfolio_id("122");
		// clientPortfolio.setQuantity(newTransaction.getQuantity());
		// bean.addNewTransaction(clientPortfolio);

		// System.out.println(newTransaction.getStockName());
	}

	@DELETE
	@Consumes("application/json")
	public void addToTransactions(TransactionId deleteId) {
		bean.deleteTransactionWithId(deleteId.getTransactionId());
	}
	
	@PUT
	@Consumes("application/json")
	public void modifyATransaction(TransactionModfiy transactionModfiy){
		bean.modifyATransaction(transactionModfiy.getTransactionId(),transactionModfiy.getBuyPrice(),transactionModfiy.getQuantity());
	}

}
class TransactionId{
	private int transactionId;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
}
class TransactionModfiy{
	private int transactionId;
	private double buyPrice;
	private int quantity;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
class TransactionReceive {
	private String stockName;
	private double buyPrice;
	private int quantity;
	private String transactionDate;
	private String categoryName;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}

class TransactionLocal {
	private int transactionId;
	private String stockname;
	private double buyprice;
	private String purchase_date;
	private int quantity;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}

	public double getBuyprice() {
		return buyprice;
	}

	public void setBuyprice(double buyprice) {
		this.buyprice = buyprice;
	}

	public String getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
