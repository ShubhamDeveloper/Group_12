package com.citi.portfoliomanager.web;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.citi.portfoliomanager.ejb.PortfolioManagerBeanLocal;
import com.citi.portfoliomanager.jpa.Category;
import com.citi.portfoliomanager.jpa.MarketData;
import com.citi.portfoliomanager.jpa.MarketDataPK;
import com.citi.portfoliomanager.jpa.Stockname;

@Path("/stocks")
public class StockResource {
	private PortfolioManagerBeanLocal bean;

	public StockResource() {
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
	public List<Stockname> getAllStocks() {
		return bean.getAllStocks();

	}

	@GET
	@Produces("application/json")
	@Path("/{stockname}/category")
	@Consumes("application/json")
	public CategoryProduce getCategoryInStock(@PathParam("stockname") String stockName) {
		Category category = bean.getCategoryInStock(stockName);
		CategoryProduce categoryProduce = new CategoryProduce();
		categoryProduce.setCategoryName(category.getCategoryname());
		return categoryProduce;

	}	
	
	@GET
	@Produces("application/json")
	@Path("/{stockName}/current")
	public MarketDataLocal getCurrentMarketDataByStockName(@PathParam("stockName") String stockName) {

		if (bean == null)
			return null;

		MarketData md =  bean.getCurrentMarketData(stockName);
		
		
		MarketDataLocal mdl = new MarketDataLocal();
		mdl.setStockName(md.getId().getStockname());
		mdl.setCurrentDate(md.getId().getDate());
		mdl.setClosePrice(md.getClosePrice());
		mdl.setHighPrice(md.getHighPrice());
		mdl.setLowPrice(md.getLowPrice());
		mdl.setVolume(md.getVolume());
		
		return mdl;
	}
	
	@GET
	@Produces("application/json")
	@Path("/profit/{stockname}")
	@Consumes("application/json")
	public ProfitInStock getProfitInStock(@PathParam("stockname") String stockName) {
		double profit = bean.getProfitInStock(stockName);
		ProfitInStock profitInStock = new ProfitInStock();
		profitInStock.setProfit(profit);
		profitInStock.setStockName(stockName);
		return profitInStock;

	}

}
class ProfitInStock
{
	private String stockName;
	private double profit;
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	
}


class CategoryProduce{
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
class StockConsume{
	private String stockName;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
}

class MarketDataLocal{
	private String stockName;
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	private Date currentDate;
	private double closePrice;

	private double highPrice;

	private double lowPrice;

	private double openPrice;

	private int volume;
}