package com.citi.portfoliomanager.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the client_portfolio database table.
 * 
 */
@Embeddable
public class ClientPortfolioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String stock_name;

	@Temporal(TemporalType.DATE)
	private java.util.Date date;

	public ClientPortfolioPK() {
	}
	public String getStock_name() {
		return this.stock_name;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
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
		if (!(other instanceof ClientPortfolioPK)) {
			return false;
		}
		ClientPortfolioPK castOther = (ClientPortfolioPK)other;
		return 
			this.stock_name.equals(castOther.stock_name)
			&& this.date.equals(castOther.date);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.stock_name.hashCode();
		hash = hash * prime + this.date.hashCode();
		
		return hash;
	}
}