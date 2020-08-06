package com.syscho.product.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVO {

	private String prodId;
	private int prodPrice;
	private String prodName;
	private int prodQnty;
	private String prodDesc;
	private String prodCategory;
    private MultipartFile prodImg1;
    private MultipartFile prodImg2;

    private byte[] picByte1;

    private byte[] picByte2;

}
