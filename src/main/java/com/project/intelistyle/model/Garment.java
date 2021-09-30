package com.project.intelistyle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "garment_item")
@Getter
@Setter
public class Garment extends Auditable{
	
	@Id
	@Column(name = "product_id", nullable = false)
	private Long product_id;
	
	@Column(name = "url", nullable = false)
	private String url;
	
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "product_description")
	private String product_description;
	
	@Column(name = "source")
	private String source;

	@Column(name = "product_title", nullable = false)
	private String product_title;

	@Column(name = "brand", nullable = false)
	private String brand;

	@Column(name = "currency_code", nullable = false)
	private String currency_code;

	@Column(name = "stock", nullable = false)
	private Integer stock;

}
