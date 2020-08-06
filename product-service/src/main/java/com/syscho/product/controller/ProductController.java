package com.syscho.product.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syscho.product.service.ProductService;
import com.syscho.product.vo.ProductVO;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	Environment environment;
	ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@GetMapping("/all")
	public List<ProductVO> findProducts() {

		return service.getAllProducts();
	}

	@GetMapping(path = "/name/{prodName}")
	public List<ProductVO> findProductByName(@PathVariable("prodName") String prodName) {

		return service.getProductByName(prodName);
	}

	@PostMapping(path = "/delete/{prodId}")
	public ResponseEntity<String> removeProduct(@PathVariable("prodId") String prodId) {

		boolean result = service.delProduct(prodId);

		if (result) {
			return new ResponseEntity<>("Prodcut Removed Successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Failed to Remove Product", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "/{prodId}")
	public ProductVO findProductById(@PathVariable("prodId") String prodId) {

		return service.getProductById(prodId);
	}

	@PostMapping(path = "/add")
	public ResponseEntity<String> addProduct(ProductVO productVO) throws IOException {
		System.out.println(productVO);

		boolean result = service.addProduct(productVO);

		if (result) {
			return new ResponseEntity<>("product Added Successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Failed to Add Product", HttpStatus.BAD_REQUEST);
		}

	}



	@PostMapping(path = "/update")
	public ResponseEntity<ProductVO> updateProduct(@RequestBody ProductVO productVO) {
		ProductVO updateProduct = service.updateProduct(productVO);
		return new ResponseEntity<>(updateProduct, HttpStatus.OK);

	}

}
