package com.sheryian.major.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue
	 private Long id;
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id",referencedColumnName="category_id")
	private Category category;
	
	private double price;
	private double weight;
	private String description;
	private String imageName;
	
	
	
	
	
}
