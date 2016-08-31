package com.citi.portfoliomanager.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the market_data database table.
 * 
 */
@Entity
@Table(name="market_data_one_day")
@NamedQuery(name="MarketData.findAll", query="SELECT m FROM MarketData m")
public class MarketData implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MarketDataPK id;

	private double closePrice;

	private double highPrice;

	private double lowPrice;

	private double openPrice;

	private int volume;

	public MarketData() {
	}

	public MarketDataPK getId() {
		return this.id;
	}

	public void setId(MarketDataPK id) {
		this.id = id;
	}

	public double getClosePrice() {
		return this.closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public double getHighPrice() {
		return this.highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	public double getLowPrice() {
		return this.lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getOpenPrice() {
		return this.openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public int getVolume() {
		return this.volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

}