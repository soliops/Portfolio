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
public class OrderDTO {
	private String product_nm;
	private String product_dtc;
	private String product_price;
	private String product_point;
	private String product_total;
	private String product_code;
	private String product_ea; 
}
