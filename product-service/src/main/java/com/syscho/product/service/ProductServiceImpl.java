package com.syscho.product.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syscho.product.dao.ProductImgRepository;
import com.syscho.product.dao.ProductRepository;
import com.syscho.product.entity.ProductDO;
import com.syscho.product.entity.ProductImageDO;
import com.syscho.product.utils.CommonUtils;
import com.syscho.product.vo.ProductVO;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	private static final String PERCENTAGE = "%";

	private final ProductRepository productRepository;
	private final ProductImgRepository imageRepository;

	public ProductServiceImpl(ProductRepository productRepository, ProductImgRepository imageRepository) {
		this.productRepository = productRepository;
		this.imageRepository = imageRepository;
	}

	@Override
	public boolean addProduct(ProductVO prod) throws IOException {

		ProductDO entity = CommonUtils.voToEntityt(prod, ProductDO.class);
		entity.setProdId(UUID.randomUUID().toString());

		ProductDO prodDO = productRepository.save(entity);

		ProductImageDO imageDO = new ProductImageDO();
		imageDO.setProdId(entity.getProdId());

		if (!Objects.isNull(prod.getProdImg1())) {
			imageDO.setPicByte1(prod.getProdImg1().getBytes());
		}
		if (!Objects.isNull(prod.getProdImg2())) {
			imageDO.setPicByte2(prod.getProdImg2().getBytes());
		}
		imageRepository.save(imageDO);
		if (Objects.isNull(prodDO)) {
			return false;
		}

		return true;
	}

	@Override
	public ProductVO updateProduct(ProductVO prod) {

		ProductDO entity = CommonUtils.voToEntityt(prod, ProductDO.class);

		ProductDO prodDO = productRepository.save(entity);
		if (Objects.isNull(prodDO)) {
			return null;
		}

		return prod;
	}

	@Override
	public boolean delProduct(String prodId) {

		productRepository.deleteById(prodId);
		imageRepository.deleteById(prodId);
		
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public ProductVO getProductById(String prodId) {

		Optional<ProductDO> prodDO = productRepository.findById(prodId);
		if (!prodDO.isPresent()) {
			return null;
		}
		return CommonUtils.voToEntityt(prodDO.get(), ProductVO.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProductVO> getProductByName(String prodName) {

		String searchCriteria = wildChars(PERCENTAGE, prodName, PERCENTAGE);
		List<ProductDO> prodList = productRepository.findByProdNameLike(searchCriteria);

		return CommonUtils.copyList(prodList, ProductVO.class);

	}

	@Transactional(readOnly = true)
	@Override
	public List<ProductVO> getAllProducts() {
		List<ProductDO> prodList = productRepository.findAll();
		return CommonUtils.copyList(prodList, ProductVO.class);
	}

	private String wildChars(String prefix, String center, String suffix) {
		return prefix + center + suffix;
	}
}
