package com.syscho.product.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syscho.product.entity.ProductDO;

@Repository
public interface ProductRepository extends CrudRepository<ProductDO, String> {

	List<ProductDO> findByProdNameLike(String prodName);
	List<ProductDO> findAll();

}
