package com.citi.portfoliomanager.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the client_portfolio database table.
 * 
 */
@Entity
@Table(name="client_portfolio")
@NamedQuery(name="ClientPortfolio.findAll", query="SELECT c FROM ClientPortfolio c")
public class ClientPortfolio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClientPortfolioPK id;

	private double buy_Price;

	private String portfolio_id;

	private int quantity;

	public ClientPortfolio() {
	}

	public ClientPortfolioPK getId() {
		return this.id;
	}

	public void setId(ClientPortfolioPK id) {
		this.id = id;
	}

	public double getBuy_Price() {
		return this.buy_Price;
	}

	public void setBuy_Price(double buy_Price) {
		this.buy_Price = buy_Price;
	}

	public String getPortfolio_id() {
		return this.portfolio_id;
	}

	public void setPortfolio_id(String portfolio_id) {
		this.portfolio_id = portfolio_id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}