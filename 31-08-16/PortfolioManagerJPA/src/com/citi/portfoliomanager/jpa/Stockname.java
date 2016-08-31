package com.citi.portfoliomanager.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stockname database table.
 * 
 */
@Table(name="stockname")
@Entity
@NamedQuery(name="Stockname.findAll", query="SELECT s FROM Stockname s")
public class Stockname implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String stockname;

	public Stockname() {
	}

	public String getStockname() {
		return this.stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}

}