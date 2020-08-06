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

@Table(name = "em_product_image")
public class ProductImageDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5578853800953201167L;

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prod_Id", nullable = false, unique = true)
	private String prodId;

	@Lob
	@Column(name = "prod_img1")
	private byte[] picByte1;
	@Lob
	@Column(name = "prod_img2")
	private byte[] picByte2;

}
