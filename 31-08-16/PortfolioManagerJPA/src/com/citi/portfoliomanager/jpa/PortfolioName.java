package com.citi.portfoliomanager.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the portfolio_name database table.
 * 
 */
@Entity
@Table(name="portfolio_name")
@NamedQuery(name="PortfolioName.findAll", query="SELECT p FROM PortfolioName p")
public class PortfolioName implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String name;

	public PortfolioName() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}