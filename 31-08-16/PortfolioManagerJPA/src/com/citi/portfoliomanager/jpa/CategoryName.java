package com.citi.portfoliomanager.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name="category")
@NamedQuery(name="CategoryName.findAll", query="SELECT c FROM CategoryName c")
public class CategoryName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String categoryname;

	public CategoryName() {
	}

	public String getCategoryname() {
		return this.categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

}