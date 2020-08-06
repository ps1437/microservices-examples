package com.syscho.product.service;

import java.io.IOException;
import java.util.List;

import com.syscho.product.vo.ProductVO;

public interface ProductService {

	boolean addProduct(ProductVO prod) throws IOException;

	ProductVO updateProduct(ProductVO prod);

	boolean delProduct(String prodId);

	ProductVO getProductById(String prodId);

	List<ProductVO> getProductByName(String prodName);

	List<ProductVO> getAllProducts();

}
