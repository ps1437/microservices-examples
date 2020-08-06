package com.syscho.product.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syscho.product.entity.ProductDO;
import com.syscho.product.entity.ProductImageDO;

@Repository
public interface ProductImgRepository extends CrudRepository<ProductImageDO, String> {

	

}
