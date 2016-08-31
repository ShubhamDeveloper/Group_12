package com.citi.portfoliomanager.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the market_data database table.
 * 
 */
@Embeddable
public class MarketDataPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String stockname;

	@Temporal(TemporalType.DATE)
	private java.util.Date date;

	public MarketDataPK() {
	}
	public String getStockname() {
		return this.stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	public java.util.Date getDate() {
		return this.date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MarketDataPK)) {
			return false;
		}
		MarketDataPK castOther = (MarketDataPK)other;
		return 
			this.stockname.equals(castOther.stockname)
			&& this.date.equals(castOther.date);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.stockname.hashCode();
		hash = hash * prime + this.date.hashCode();
		
		return hash;
	}
}