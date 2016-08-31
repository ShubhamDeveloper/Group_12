package com.citi.portfoliomanager.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Table(name="transaction")
@Entity
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;

	private double buyprice;

	@Temporal(TemporalType.DATE)
	private Date purchase_date;

	private int quantity;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	@JsonBackReference
	private Category category;

	//bi-directional many-to-one association to Stock
	@ManyToOne
	@JoinColumn(name="stockname")
	@JsonBackReference
	private Stock stock;

	public Transaction() {
	}

	public int getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public double getBuyprice() {
		return this.buyprice;
	}

	public void setBuyprice(double buyprice) {
		this.buyprice = buyprice;
	}

	public Date getPurchase_date() {
		return this.purchase_date;
	}

	public void setPurchase_date(Date purchase_data) {
		this.purchase_date = purchase_data;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}