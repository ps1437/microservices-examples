package com.syscho.product.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity

@Table(name = "em_product")
public class ProductDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5973890327755508558L;
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prod_Id", nullable = false, unique = true)
	private String prodId;
	@Column(name = "prod_price", nullable = false, unique = true)
	private int prodPrice;
	@Column(name = "prod_name", nullable = false, unique = true)
	private String prodName;
	@Column(name = "prod_category")
	private String prodCategory;
	@Column(name = "prod_qnty", nullable = false)
	private int prodQnty;
	@Column(name = "prod_desc")
	private String prodDesc;

	

}
