package com.tj.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDTO {

	private int pidx;
	private String cbcate_code;
	private String cscate_code;
	private String product_code;
	private String product_name;
	private String product_addexplain;
	private String product_price;
	private String product_discount;
	private String product_disprice;
	private String product_stock;
	private String product_sale;
	private String product_saleout;
	private String product_img1;
	private String product_img2;
	private String product_img3;
	private String product_explain;
	private String product_date;
}
